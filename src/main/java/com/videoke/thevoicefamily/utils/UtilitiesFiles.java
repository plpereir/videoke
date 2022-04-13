package com.videoke.thevoicefamily.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import org.apache.tomcat.util.codec.binary.Base64;
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
 
	public String encodeFileToBase64Binary(String fileName) throws IOException {

		File file = new File("/Users/pedro.pereira/Downloads/"+fileName);
		byte[] bytes = loadFile(file);
		byte[] encoded = Base64.encodeBase64(bytes);
		String encodedString = new String(encoded);

		return encodedString;
	}

	@SuppressWarnings("resource")
	private static byte[] loadFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);

	    long length = file.length();
	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }
	    byte[] bytes = new byte[(int)length];
	    
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }

	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }

	    is.close();
	    return bytes;
	}    

}
