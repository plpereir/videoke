package com.videoke.thevoicefamily.model;


import javax.persistence.*;
import javax.persistence.Id;
@Entity
@Table(name="Singer")
public class Singer {
	
	@Id
	@Column(name = "requestNumber")
	private Long requestNumber;
	@Column(name = "name")
	private String name;
	@Column(name = "song")
	private String song;
	
	public Singer()
	{
		
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

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}
	
	

}
