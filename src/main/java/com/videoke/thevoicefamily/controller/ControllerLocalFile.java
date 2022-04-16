package com.videoke.thevoicefamily.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.videoke.thevoicefamily.configs.Configs;
import com.videoke.thevoicefamily.repository.RepositoryMovie;
import com.videoke.thevoicefamily.utils.UtilitiesFiles;

@RestController
public class ControllerLocalFile {
	@Autowired
	private RepositoryMovie repositoryMovie;
	
	@Autowired
	private Configs configs;

	private String getPath() {
		String temp = configs.getPath();
		return temp;
	}

	Logger logger = LoggerFactory.getLogger(ControllerLocalFile.class);
	// Create an object of the File class
	// Replace the file path with path of the directory
	// File directory = new File("");

	// Create an object of Class MyFilenameFilter
	// Constructor with name of file which is being
	// searched
	@GetMapping("/files/{fileName}")
	public Map<String, String> getFiles(@PathVariable String fileName) {
		Map<String, String> map = new HashMap<String, String>();

		File directory = new File(getPath());

		UtilitiesFiles filter = new UtilitiesFiles(fileName.toLowerCase());

		// store all names with same name
		// with/without extension
		String[] flist = directory.list(filter);
		{
			// Empty array
			if (flist == null) {
				map.put("filename0", "Empty directory or directory does not exists.");
				logger.info("Empty directory or directory does not exists.");
			} else {

				// Print all files with same name in directory
				// as provided in object of MyFilenameFilter
				// class
				for (int i = 0; i < flist.length; i++) {
					map.put("filename" + i, flist[i]);

					logger.info("filename" + i + flist[i]);
				}
			}
		}

		if (map.size() > 0) {
			return map;
		} else {
			map.put("filename0", "Empty directory or directory does not exists.");
			logger.info("Empty directory or directory does not exists.");
			return map;
		}
	}

	@GetMapping("/files/online")
	public Map<String, String> getOnLine() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("online", configs.getOnLine());
		map.put("path", getPath());
		return map;
	}
	
	@GetMapping("/files/getfile/{fileName}")
	public String getfile(@PathVariable String fileName) throws IOException 
	{
		String tmp = repositoryMovie.getFileNameById(fileName);
		UtilitiesFiles uf = new UtilitiesFiles(tmp);
		return uf.encodeFileToBase64Binary(tmp);
	}
	
}
