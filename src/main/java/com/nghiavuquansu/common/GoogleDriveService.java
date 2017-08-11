package com.nghiavuquansu.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.Permission;

public class GoogleDriveService {
    /** Application name. */
    private static final String APPLICATION_NAME = "Drive API Java NVQS";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),
            ".credentials/drive-java");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /**
     * Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials at
     * ~/.credentials/drive-java-quickstart
     */
    private static final java.util.Collection<String> SCOPES = DriveScopes.all();

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    private static final String DEFAULT_FILE_CONTENT_TYPE = "application/sql";

    /**
     * Creates an authorized Credential object.
     * 
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorizeInstalledApplications() throws IOException {
        // Load client secrets.
        InputStream in = GoogleDriveService.class.getResourceAsStream("/client_secret_installed_app.json");

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
                clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();

        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());

        return credential;
    }

    public static Credential authorizeServiceAccount() throws IOException {
        // For Service Account
        InputStream in = GoogleDriveService.class.getResourceAsStream("/My_Project_ThachThang_Service_Account.json");

        GoogleCredential credential = GoogleCredential.fromStream(in).createScoped(SCOPES);
        return credential;
    }

    /**
     * Build and return an authorized Drive client service.
     * 
     * @return an authorized Drive client service
     * @throws IOException
     */
    public static Drive getDriveService(int service) throws IOException {
        Credential credential = null;

        if (service == Enums.GoogleAPIAccount.INSTALLED_APPLICATIONS.getValue()) {
            credential = authorizeInstalledApplications();
        } else if (service == Enums.GoogleAPIAccount.SERVICE_ACCOUNTS.getValue()) {
            credential = authorizeServiceAccount();
        }

        return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
    }

    public static void upfileToDrive(File file, String name, String folderParentId, String fileContentType, int service) {
        try {
            com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
            fileMetadata.setName(name);

            if (folderParentId != null) {
                fileMetadata.setParents(Collections.singletonList(folderParentId));
            }

            FileContent mediaContent = new FileContent(
                    fileContentType == null ? DEFAULT_FILE_CONTENT_TYPE : fileContentType, file);

            com.google.api.services.drive.model.File fileUploaded = getDriveService(service).files()
                    .create(fileMetadata, mediaContent).setFields("id, name, webContentLink, webViewLink, permissions")
                    .execute();
            System.out.println("File uploaded - ID: " + fileUploaded.getId() + " | " + fileUploaded.getWebContentLink() + " | "
                    + fileUploaded.getWebViewLink() + " | Permission: " + fileUploaded.getPermissions());

            Permission newPermission = new Permission();
            newPermission.setType("user");
            newPermission.setRole("reader");
            newPermission.setEmailAddress("phuong.thachthang.backup@gmail.com");

            getDriveService(service).permissions().create(fileUploaded.getId(), newPermission).execute();

            List<com.google.api.services.drive.model.File> fileRecives =  getAllFilesInDrive(service);
            System.out.println("List file affter backup - Size: "+fileRecives.size());
            for (com.google.api.services.drive.model.File fileR : fileRecives) {
                System.out.println("ID: " + fileR.getId() + " | Name: " + fileR.getName() + " | LinkDown: "
                        + fileR.getWebContentLink() + " | LinkView: " + fileR.getWebViewLink() 
                        + " | Upload Date: " + fileR.getCreatedTime() + " | Permissions: " + fileR.getPermissions());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static List<com.google.api.services.drive.model.File> getAllFilesInDrive(int service) throws IOException{
        return getDriveService(service).files().list()
                .setFields("files(id, name, webContentLink, webViewLink, createdTime ,permissions)").execute().getFiles();
    }

    public static void deleteAllFile(int service) {
        try {
            System.out.println("Start delete all file");

            List<com.google.api.services.drive.model.File> fileRecives = getAllFilesInDrive(service);
            for (com.google.api.services.drive.model.File fileR : fileRecives) {
                System.out.println("Delete - ID: " + fileR.getId() + " | Name: " + fileR.getName() + " | Link: "
                        + fileR.getWebContentLink() + " | Permission: " + fileR.getPermissions());

                getDriveService(service).files().delete(fileR.getId()).execute();
            }

            fileRecives = getAllFilesInDrive(service);
            System.out.println("List file affter delete - Size: "+fileRecives.size());
            for (com.google.api.services.drive.model.File fileR : fileRecives) {
                System.out.println("ID: " + fileR.getId() + " | Name: " + fileR.getName() + " | Link: "
                        + fileR.getWebContentLink() + " | Permission: " + fileR.getPermissions());

                getDriveService(service).files().delete(fileR.getId()).execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Permission getPermissionInFirst(List<Permission> permissions) {
        return permissions.get(0);
    }
}
