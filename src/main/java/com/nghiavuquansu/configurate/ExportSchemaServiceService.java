package com.nghiavuquansu.configurate;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
public class ExportSchemaServiceService {

    
    public void exportSchemaOnStartUp(){
        System.out.println("Run on start up....!");

    }
    
    private String getOutPutPath(){
        String rootPath = System.getProperty("user.home") + "/resourses-sql";
        File directFolder = new File(rootPath);
        if(!directFolder.exists())
            directFolder.mkdirs();
        return rootPath;
    }
}
