package com.nghiavuquansu.configurate;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.common.Constants;

@Service
@EnableScheduling
public class ConJobService {

    @EventListener
    public void executeOnStartUp(ContextRefreshedEvent event){
        //Create Folder backup database
        File folderBackup = new File(Constants.PATH_FOLDER_EXTERNAL+ "/"+Constants.PATH_FOLDER_DATABASE_BACKUP);
        if(!folderBackup.exists()){
            System.out.println("Folder to backup database isn't exists. Create this one....");
            if(folderBackup.mkdirs()) System.out.println("Create successfull");
            else System.out.println("Create fail");
        }
    }

    //@Scheduled(cron = "0 0/1 * * * ?")
    @Scheduled(cron = "0 0 15 * * ?")
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
