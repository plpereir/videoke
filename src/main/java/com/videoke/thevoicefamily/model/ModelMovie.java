package com.videoke.thevoicefamily.model;


import javax.persistence.*;
import javax.persistence.Id;
@Entity
@Table(name = "movies")
public class ModelMovie {
	
	@Column(name = "id")
	private long id;
	@Column(name = "nextPageToken")
	private String nextPageToken;
	@Id
	@Column(name = "movieId")
	private String movieId;
	@Column(name = "movieTitle")
	private String movieTitle;
	@Column(name = "channelId")
	private String channelId;
	@Column(name = "channelTitle")
	private String channelTitle;

	public ModelMovie() {
	}
	
	public ModelMovie(String nextPageToken, String movieId,String movieTitle, String channelId, String channelTitle) {
		this.nextPageToken = nextPageToken;
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.channelId = channelId;
		this.channelTitle = channelTitle;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long Id) {
		id = Id;
	}

	public String getNextPageToken() {
		return nextPageToken;
	}

	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelTitle() {
		return channelTitle;
	}

	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}

	@Override
	public String toString() {
		return "ModelMovie [Id=" + id + ", nextPageToken=" + nextPageToken + ", movieId=" + movieId + ", movieTitle="
				+ movieTitle + ", channelId=" + channelId + ", channelTitle=" + channelTitle + "]";
	}

}
