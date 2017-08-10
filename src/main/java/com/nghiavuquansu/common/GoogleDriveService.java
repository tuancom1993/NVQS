package com.nghiavuquansu.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver.Builder;
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
    
    public static Credential authorize() throws IOException {
        // Load client secrets.

        InputStream in = GoogleDriveService.class.getResourceAsStream("/client_secret_nvqs.json");
//        InputStream in = GoogleDriveService.class.getResourceAsStream("/My_Project_EC2_ThachThang.json");
        
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
        
        Builder builder = new LocalServerReceiver.Builder();
        builder.setHost("34.213.141.135");
        builder.setPort(-1);
        Credential credential = new AuthorizationCodeInstalledApp(flow, builder.build()).authorize("user");
        System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        
        /*InputStream in = GoogleDriveService.class.getResourceAsStream("/My_Project_EC2_ThachThang.json");
        GoogleCredential credential = GoogleCredential.fromStream(in).createScoped(SCOPES);*/
        return credential;
    }

    /**
     * Build and return an authorized Drive client service.
     * 
     * @return an authorized Drive client service
     * @throws IOException
     */
    public static Drive getDriveService() throws IOException {
        Credential credential = authorize();
        return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
    }

    public static void upfileToDrive(File file, String name, String folderParentId, String fileContentType) {
        try {
            com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
            fileMetadata.setName(name);

            if (folderParentId != null) {
                fileMetadata.setParents(Collections.singletonList(folderParentId));
            }

            FileContent mediaContent = new FileContent(
                    fileContentType == null ? DEFAULT_FILE_CONTENT_TYPE : fileContentType, file);

            com.google.api.services.drive.model.File fileUploaded = getDriveService().files()
                    .create(fileMetadata, mediaContent).setFields("id").execute();
            System.out.println("File ID: " + fileUploaded.getId()+ " | "+fileUploaded.getWebViewLink());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
