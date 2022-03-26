package com.videoke.thevoicefamily.controller;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.videoke.thevoicefamily.model.ModelMovie;
import com.videoke.thevoicefamily.repository.RepositoryMovie;
import com.videoke.thevoicefamily.utils.GsonUtilities;
import com.videoke.thevoicefamily.utils.UtilitiesMovie;

@RestController
public class ControllerJSONMovie {
	Logger logger = LoggerFactory.getLogger(ControllerJSONMovie.class);
	
	
	@Autowired
	private RepositoryMovie repositoryMovie;

	@GetMapping("")
    protected void doGet2(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
        response.sendRedirect("index.html");  
    }  
	
	@GetMapping("/")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
        response.sendRedirect("index.html");  
    }  
	
	@GetMapping("movies/findall")
	public List<ModelMovie> findMovies()
	{
		List<ModelMovie> modelMoviesList = new ArrayList<ModelMovie>();
		modelMoviesList.addAll(repositoryMovie.findAll());
		return modelMoviesList;
	}
	
	@GetMapping("movies/findbytitle/{title}")
	public List<ModelMovie> findMoviesByTitle(@PathVariable String title)
	{
		List<ModelMovie> modelMoviesList = new ArrayList<ModelMovie>();
		modelMoviesList.addAll(repositoryMovie.findByMovieTitle(title.replace(" ", "%").toUpperCase()));
		return modelMoviesList;
	}
	
	@GetMapping("movies/findbytitlestr/{title}")
	public String findMoviesByTitleStr(@PathVariable String title)
	{
		List<ModelMovie> modelMoviesList = new ArrayList<ModelMovie>();
		modelMoviesList.addAll(repositoryMovie.findByMovieTitle(title.replace(" ", "%").toUpperCase()));
		
		String tmpStr = null;
		for(ModelMovie mm:modelMoviesList)
		{
			tmpStr += mm.getMovieId()+" - "+mm.getMovieTitle()+"<hr>";
		}
		
		return tmpStr;
	}
	
	@GetMapping("/movies/{channelID}/{Key}/{nextPageToken}")
	public Map<String, String> getMoviesList(@PathVariable String channelID, @PathVariable String Key,  @PathVariable String nextPageToken) throws IOException, SQLException {
		List<ModelMovie> modelMoviesList = new ArrayList<ModelMovie>();
		URL url = new URL(UtilitiesMovie.checkURL("https://www.googleapis.com/youtube/v3/search?channelId="+channelID+"&key="+Key+"&maxResults=50&type=video&order=date&part=id,snippet&pageToken="+nextPageToken));
		logger.info(url.toString());
		Map<String, String> map = new HashMap<String, String>();
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		String tmp = "";
		if (conn.getResponseCode() == 200) {
			Scanner scan = new Scanner(url.openStream());

			while (scan.hasNext()) {
				tmp = tmp + scan.nextLine();
			}
			GsonUtilities gsonUtilities = new GsonUtilities();
			modelMoviesList.addAll(gsonUtilities.ParsingRootToModelMovie(gsonUtilities.ParsingGsonToRoot(tmp)));
		}else
		{
			map.put("status", "error, change key");
			map.put("connection message", conn.getResponseMessage());
			map.put("url connection", conn.getURL().toString());	
			map.put("channelID", channelID);
			map.put("Key", Key);
			map.put("nextPageToken", nextPageToken);
			
			logger.error("connection error: "+conn.getResponseMessage());
			return map;
		}
		UtilitiesMovie utilitiesMovie = new UtilitiesMovie();
		if(modelMoviesList.size()>0)
		{
			String nextPageTokenReturn = utilitiesMovie.writeFile(modelMoviesList);
			logger.info("nextPageToken: " + nextPageTokenReturn);			
		}else
		{
			logger.info("nextPageToken: not return items");	
		}



		if (modelMoviesList.size()>0)
		for (ModelMovie modelMovie : modelMoviesList) {
			
			try
			{
				if (modelMovie.getNextPageToken()!=null)
				{
					repositoryMovie.saveCustom(modelMovie.getMovieId().toString(), modelMovie.getChannelId().toString(), modelMovie.getChannelTitle().toString(), modelMovie.getId(), modelMovie.getMovieTitle().toString().toUpperCase(), modelMovie.getNextPageToken().toString());
					
				}else
				{
					repositoryMovie.saveCustom(modelMovie.getMovieId().toString(), modelMovie.getChannelId().toString(), modelMovie.getChannelTitle().toString(), modelMovie.getId(), modelMovie.getMovieTitle().toString().toUpperCase(), "LastPage");					
				}
				
			}catch(Exception ex)
			{
				
				logger.error("error saving: "+ex.toString());
				logger.error("getMovieId "+modelMovie.getMovieId());
				logger.error("getChannelId "+modelMovie.getChannelId());
				logger.error("getChannelTitle "+modelMovie.getChannelTitle());
				logger.error("getId "+modelMovie.getId());
				logger.error("getMovieTitle "+modelMovie.getMovieTitle());
				logger.error("getNextPageToken "+modelMovie.getNextPageToken());
				map.put("database error", ex.getMessage());
			}
			map.put("status", "success");
			map.put("channelID", channelID);
			map.put("Key", Key);
			map.put("nextPageToken", modelMovie.getNextPageToken());

		}
		else
		{
			map.put("status", "error");
			map.put("return", "not found elements");
			map.put("connection message", conn.getResponseMessage());
			map.put("url connection", conn.getURL().toString());

		}
		return map;
	}
}
