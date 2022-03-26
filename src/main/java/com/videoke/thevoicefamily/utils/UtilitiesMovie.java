package com.videoke.thevoicefamily.utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.videoke.thevoicefamily.model.ModelMovie;

public class UtilitiesMovie {
	Logger logger = LoggerFactory.getLogger(UtilitiesMovie.class);

	private String generateLine(ModelMovie modelMovie) {
		return (modelMovie.getChannelTitle() + ";" + modelMovie.getChannelId() + ";" + modelMovie.getMovieId() + ";"
				+ modelMovie.getMovieTitle() + ";" + modelMovie.getNextPageToken()
				+ ";youtube-dl.exe https://www.youtube.com/watch?v=" + modelMovie.getMovieId() + ";");
	}

	private String dateFormatFile() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	public String writeFile(List<ModelMovie> modelMovieList) throws IOException {

		Writer output;
		output = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("movie-list-karaoke" + dateFormatFile() + ".csv", true), "UTF-8"));

		for (ModelMovie modelMovie : modelMovieList) {
			try {
				output.append("\n");
				output.append(generateLine(modelMovie));
				logger.info("save line file: " + generateLine(modelMovie));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("error save line file: " + e.toString());
			}
		}
		output.close();

		if(modelMovieList.size()>0)
		{
			logger.info("nextPageToken: " + modelMovieList.get(0).getNextPageToken());		
			return modelMovieList.get(0).getNextPageToken();
		}else
		{
			logger.info("nextPageToken: not return items");	
			return null;
		}

		
	}
	
	public static  String checkURL(String tmp)
	{
		String temp = tmp;
		if (temp.contains("&pageToken=null"))	
		{
			return temp.replace("&pageToken=null", "");
		}else
		{
			return temp;
		}
	}

}
