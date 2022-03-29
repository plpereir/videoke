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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.videoke.thevoicefamily.model.ModelMovie;
import com.videoke.thevoicefamily.model.URLYoutube;
import com.videoke.thevoicefamily.repository.RepositoryMovie;
import com.videoke.thevoicefamily.utils.GsonUtilities;
import com.videoke.thevoicefamily.utils.UtilitiesMovie;

@RestController
public class ControllerJSONMovie {
	Logger logger = LoggerFactory.getLogger(ControllerJSONMovie.class);
	
	
	@Autowired
	private RepositoryMovie repositoryMovie;

	@PostMapping(path = "movies/add", 
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> create(@RequestBody ModelMovie newMovie) {
		Map<String, String> map = new HashMap<String, String>();
		logger.info(newMovie.getMovieId().toString() + newMovie.getChannelId().toString() +  newMovie.getChannelTitle().toString() +  newMovie.getId() +  newMovie.getMovieTitle().toString().toUpperCase() +  "Single Movie");
		List<ModelMovie> modelMoviesList = new ArrayList<ModelMovie>();
		try
		{
			if ( repositoryMovie.findByMovieId(newMovie.getMovieId().toString()).size()>0)
			{	map.put("title", newMovie.getMovieTitle());
				map.put("movieID", newMovie.getMovieId().toString());
				map.put("Status", "esta música já foi registrada anteriormente");
			}
			else
			{
				repositoryMovie.saveCustom(newMovie.getMovieId().toString(), newMovie.getChannelId().toString(), newMovie.getChannelTitle().toString().toUpperCase(), newMovie.getId(), newMovie.getMovieTitle().toString().toUpperCase(), "Single Movie");
				modelMoviesList.addAll(repositoryMovie.findByMovieId(newMovie.getMovieId().toString()));
				if (repositoryMovie.findByMovieId(newMovie.getMovieId().toString()).size()>0)
				{
					map.put("Status", "música registrada com sucesso!");
					map.put("title", newMovie.getMovieTitle());
					map.put("movieID", newMovie.getMovieId().toString());
					logger.info("música registrada com sucesso!");
				}else
				{
					map.put("Status", "ocorreu um erro: "+newMovie.getMovieId().toString());
				}
			}
		    return new ResponseEntity<>(map, HttpStatus.CREATED);
			
		}catch(Exception ex)
		{
			logger.error("try add new movie: "+ex.toString());
			map.put("Status", "ocorreu um erro: "+ex.toString());
			return new ResponseEntity<>(map, HttpStatus.MULTI_STATUS);
		}
	}
	
	@GetMapping("")
    protected void doGet2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
  
       response.sendRedirect("index.html"); 
    }  
	
	@GetMapping("/")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
		response.sendRedirect("index.html"); 
    }  
	
	@GetMapping("movies/delete/{Id}")
	public Map<String, String> deleteMovies(@PathVariable String Id)
	{
		Map<String, String> map = new HashMap<String, String>();
		
		
		if (repositoryMovie.findByMovieId(Id).size()==0)
		{
			map.put("status", "video não existe ou inválido ;-(");
		}else
		{
			repositoryMovie.deleteMovie(Id);
			if (repositoryMovie.findByMovieId(Id).size()==0)
			{
				map.put("status", "video deletado com sucesso ;-)");
			}			
		}
		return map;
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
	
	@GetMapping("movies/findbyId/{Id}")
	public List<ModelMovie> findMoviesById(@PathVariable String Id)
	{
		List<ModelMovie> modelMoviesList = new ArrayList<ModelMovie>();
		modelMoviesList.addAll(repositoryMovie.findByMovieId(Id));
		
		return modelMoviesList;
	}
	@PostMapping(path = "/movies/new", 
	        	consumes = MediaType.APPLICATION_JSON_VALUE, 
	        	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> addVideoFromURL(@RequestBody URLYoutube urlYoutube) throws IOException, SQLException {		
		List<ModelMovie> modelMoviesList = new ArrayList<ModelMovie>();
		Map<String, String> map = new HashMap<String, String>();
		String urlVideo = UtilitiesMovie.getMovieIDFromURL(urlYoutube.getUrl());
		map.put("url Parameters", urlVideo);

		URL url = new URL(UtilitiesMovie.checkURL("https://youtube.googleapis.com/youtube/v3/videos?id="+urlVideo+"&key=AIzaSyDNVUxQbPK8PMM8tT0pVPizdNgLzNeV8qw&part=snippet,contentDetails,statistics&"));
		
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
			modelMoviesList.addAll(gsonUtilities.ParsingRootToModelMovieYoutubeMusic(gsonUtilities.ParsingGsonToRootYoutubeMusic(tmp)));
			
			return create(modelMoviesList.get(0));
		}else
		{
			logger.error("connections status: "+conn.getContentEncoding());
			map.put("Status", conn.getContentEncoding());
			return new ResponseEntity<>(map, HttpStatus.MULTI_STATUS);
		}
		
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
