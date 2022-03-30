package com.videoke.thevoicefamily.model;


import javax.persistence.*;
import javax.persistence.Id;
@Entity
@Table(name="Singer")
public class Singer {
	
	@Id
	@Column(name = "requestNumber")
	private Long requestNumber;
	@Column(name = "songID")
	private String songID;
	@Column(name = "name")
	private String name;
	@Column(name = "songTitle")
	private String songTitle;
	
	public Singer()
	{
		
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public String getSongID() {
		return songID;
	}

	public void setSongID(String songID) {
		this.songID = songID;
	}

	public Long getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(Long requestNumber) {
		this.requestNumber = requestNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
