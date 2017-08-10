package com.nghiavuquansu.configurate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.common.Constants;
import com.nghiavuquansu.common.GoogleDriveService;
import com.nghiavuquansu.common.Utils;

@Service
@EnableScheduling
public class ConJobService {

    @EventListener
    public void executeOnStartUp(ContextRefreshedEvent event) {
        // Create Folder backup database
        File folderBackup = new File(Constants.PATH_FOLDER_EXTERNAL + "/" + Constants.PATH_FOLDER_DATABASE_BACKUP);
        if (!folderBackup.exists()) {
            System.out.println("Folder to backup database isn't exists. Create this one....");
            if (folderBackup.mkdirs())
                System.out.println("Create successfull");
            else
                System.out.println("Create fail");
        }
        executeCronJobBackupDB();
    }

    //@Scheduled(cron = "0 0/1 * * * ?")
    @Scheduled(cron = "0 10 16 * * ?")
    public void executeCronJobBackupDB() {
        StringBuffer output = new StringBuffer();
        Process p;
        try {
            String backupFileShell = new ClassPathResource("backup_database.sh").getURL().getFile().toString();
            System.out.println("------****" + backupFileShell);
            String command = "sh " + backupFileShell;
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            System.out.println("Command print out");
            System.out.println(output.toString());
            uploadBackupFileToGoogleDrive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void uploadBackupFileToGoogleDrive() {
        try {
            File folderContainBackupBD = new File(
                    Constants.PATH_FOLDER_EXTERNAL + "/" + Constants.PATH_FOLDER_DATABASE_BACKUP);
            File[] backupDBFiles = folderContainBackupBD.listFiles(getFilterByFileName());
            List<File> files = new ArrayList<File>(Arrays.asList(backupDBFiles));
            Collections.sort(files, new Comparator<File>() {
                public int compare(File f1, File f2) {
                    return Long.compare(f1.lastModified(), f2.lastModified());
                }
            });
            /*
             * for (int i = 0; i < files.size(); i++) { System.out.println(
             * "File in root after filterFileName: " + (i + 1) + ": " +
             * files.get(i).getName()); }
             */

            Iterator<File> it = files.iterator();
            while (it.hasNext()) {
                if (files.size() > Constants.NUMBER_DATABASE_BACKUP_FILE) {
                    File fileRemove = it.next();
                    if (fileRemove.delete()) {
                        it.remove();
                    }
                } else
                    break;
            }
            /*
             * for (int i = 0; i < files.size(); i++) { System.out.println(
             * "File in root after filter to remove: " + (i + 1) + ": " +
             * files.get(i).getName()); }
             */

            File backupFile = files.get(files.size() - 1);

            GoogleDriveService.upfileToDrive(backupFile, backupFile.getName(), /*Constants.FOLDER_ID_PARENT*/null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private FilenameFilter getFilterByFileName() {
        return new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                try {
                    if (!"sql".equals(Utils.getExtension(name)))
                        return false;

                    String dbName = name.substring(0, name.indexOf(((int) (char) '_')));
                    return "nghiavuquansu".equals(dbName);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        };
    }
}
