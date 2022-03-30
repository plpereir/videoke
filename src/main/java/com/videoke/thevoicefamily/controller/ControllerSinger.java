package com.videoke.thevoicefamily.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.videoke.thevoicefamily.model.Singer;
import com.videoke.thevoicefamily.repository.RepositorySinger;

@RestController
public class ControllerSinger {
	Logger logger = LoggerFactory.getLogger(ControllerSinger.class);

	@Autowired
	private RepositorySinger repositorySinger;

	@PostMapping(path = "/songs/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> addNewSong(@RequestBody Singer singer) {
		Map<String, String> map = new HashMap<String, String>();

		if (repositorySinger.findBySongID(singer.getSongID().toString()).size() > 0) {
			map.put("status", singer.getName().toString().toUpperCase() + " esta música já está na fila ;-(");
		} else {
			try {
				repositorySinger.saveCustom(singer.getRequestNumber(), singer.getName().toString().toUpperCase(),
						singer.getSongTitle().toString(), singer.getSongID().toString());
			} catch (Exception ex) {
				logger.error("error try save singer " + ex.toString());
			}
			if (repositorySinger.findBySongID(singer.getSongID().toString()).size() > 0) {
				map.put("status", singer.getName().toString().toUpperCase() + " a música foi registrada na fila ;-)!");
			} else {
				map.put("status", singer.getName().toString().toUpperCase()
						+ " não foi possível registrar a música na fila ;-(!");
			}
		}

		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}
	
	@GetMapping("songs/deleteByRequestNumber/{RequestNumber}")
	public Map<String, String>deleteByRequestNumber(@PathVariable Long RequestNumber)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			repositorySinger.deleteSong(RequestNumber);
			map.put("status","musica removida da lista com sucesso");
		}catch(Exception ex)
		{
			map.put("status", "erro ao remover da lista");
		}
		return map;
	}
	
	@GetMapping("songs/findbySongID/{songID}")
	public List<Singer> findSongsByID(@PathVariable String songID)
	{
		List<Singer> modelSingerList = new ArrayList<Singer>();
		modelSingerList.addAll(repositorySinger.findBySongID(songID));
		return modelSingerList;
	}
	
	@GetMapping("songs/findall")
	public List<Singer> findSongs()
	{
		List<Singer> modelSingerList = new ArrayList<Singer>();
		modelSingerList.addAll(repositorySinger.findAll());
		return modelSingerList;
	}
	
}
