package com.videoke.thevoicefamily.utils;

import java.io.File;
import java.io.FilenameFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilitiesFiles  implements FilenameFilter {
	Logger logger = LoggerFactory.getLogger(UtilitiesFiles.class);

    
    String initials;
    
    // constructor to initialize object
    public UtilitiesFiles(String initials)
    {
        this.initials = initials;
    }
    
    // overriding the accept method of FilenameFilter
    // interface
    public boolean accept(File dir, String name)
    {
    	logger.info("name: "+name);
    	logger.info("dir: "+dir.getAbsolutePath());
        return name.toLowerCase().contains(initials);
    }
}
