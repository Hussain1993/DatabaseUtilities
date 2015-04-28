package com.Hussain.pink.triangle.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Hussain on 28/04/2015.
 */
public class PropsGetter {
    private static final Logger LOG = LoggerFactory.getLogger(PropsGetter.class);

    private String configFilePathKey = "properties.file.path";

    private static PropsGetter instance;
    private static final Object instanceLock = new Object();

    private String propertiesFileName;
    private Properties properties;

    private PropsGetter(){super();}


    public static PropsGetter getInstance(){
        if(instance == null)
        {
            synchronized (instanceLock)
            {
                if(instance == null)
                {
                    instance = new PropsGetter();
                }
                instanceLock.notifyAll();
            }
        }
        return instance;
    }

    public void init(){
        File propertiesFile = new File(System.getProperty(this.configFilePathKey));
        try(InputStream is = new FileInputStream(propertiesFile)){
            properties = new Properties();
            properties.load(is);
            propertiesFileName = propertiesFile.getName();
        }
        catch (IOException fofException){
            LOG.error("There was an error with the properties file {}",propertiesFile.getPath(),fofException);
        }
    }

    public void init(String configFilePathKey){
        if(configFilePathKey != null && !configFilePathKey.isEmpty())
        {
            this.configFilePathKey = configFilePathKey;
        }
        init();
    }

    public String getProperty(String key){
        if(properties.containsKey(key))
        {
            return properties.getProperty(key);
        }
        else
        {
            LOG.error("The properties file {} does not contain the key {}",propertiesFileName,key);
            return properties.getProperty(key);//This will return null
        }
    }
}
