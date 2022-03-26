package com.videoke.thevoicefamily.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Thumbnails {
    @JsonProperty("default") 
    public Default mydefault;
    public Medium medium;
    public High high;
    
	public Default getMydefault() {
		return mydefault;
	}
	public void setMydefault(Default mydefault) {
		this.mydefault = mydefault;
	}
	public Medium getMedium() {
		return medium;
	}
	public void setMedium(Medium medium) {
		this.medium = medium;
	}
	public High getHigh() {
		return high;
	}
	public void setHigh(High high) {
		this.high = high;
	}
    
    
}
