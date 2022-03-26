package com.videoke.thevoicefamily.utils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.videoke.thevoicefamily.controller.ControllerJSONMovie;
import com.videoke.thevoicefamily.model.Item;
import com.videoke.thevoicefamily.model.ModelMovie;
import com.videoke.thevoicefamily.model.Root;

public class GsonUtilities {
	Logger logger = LoggerFactory.getLogger(ControllerJSONMovie.class);


	public Root ParsingGsonToRoot(String json) {
		Gson gson = new Gson();

		Root root = gson.fromJson(json, Root.class);

		return root;
	}

	public List<ModelMovie> ParsingRootToModelMovie(Root root) throws SQLException {
		List<ModelMovie> modelMovieList = new ArrayList<ModelMovie>();

		for (Item item : root.items) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Long id = timestamp.getTime();
			
			ModelMovie modelMovie = new ModelMovie();

			modelMovie.setNextPageToken(root.getNextPageToken());
			modelMovie.setId(id);
			modelMovie.setChannelId(item.getSnippet().getChannelId());
			modelMovie.setChannelTitle(item.getSnippet().getChannelTitle());
			modelMovie.setMovieId(item.getId().getVideoId());
			modelMovie.setMovieTitle(item.getSnippet().getTitle());

			modelMovieList.add(modelMovie);
		}
		return modelMovieList;
	}
}
