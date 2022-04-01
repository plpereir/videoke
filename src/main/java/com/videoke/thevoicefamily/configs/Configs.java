package com.videoke.thevoicefamily.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:songs.properties")
public class Configs {
	
	Logger logger = LoggerFactory.getLogger(Configs.class);

	@Value( "${songs.online}" )
	private String onLine;
	
	@Value( "${songs.path}" )
	private String path;	
	
	public void readProperties()
	{
		logger.info("get songs from Youtube: "+onLine);
		logger.info("get path songs from local: "+path);
	}	
	
	public String getPath()
	{
		return path;
	}
	
	public String getOnLine()
	{
		return onLine;
	}
}
