package com.videoke.thevoicefamily.model.music;

import java.util.ArrayList;
import java.util.Date;

public class Snippet {
    public Date publishedAt;
    public String channelId;
    public String title;
    public String description;
    public Thumbnails thumbnails;
    public String channelTitle;
    public ArrayList<String> tags;
    public String categoryId;
    public String liveBroadcastContent;
    public Localized localized;
    public String defaultAudioLanguage;
	public Date getPublishedAt() {
		return publishedAt;
	}
	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Thumbnails getThumbnails() {
		return thumbnails;
	}
	public void setThumbnails(Thumbnails thumbnails) {
		this.thumbnails = thumbnails;
	}
	public String getChannelTitle() {
		return channelTitle;
	}
	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getLiveBroadcastContent() {
		return liveBroadcastContent;
	}
	public void setLiveBroadcastContent(String liveBroadcastContent) {
		this.liveBroadcastContent = liveBroadcastContent;
	}
	public Localized getLocalized() {
		return localized;
	}
	public void setLocalized(Localized localized) {
		this.localized = localized;
	}
	public String getDefaultAudioLanguage() {
		return defaultAudioLanguage;
	}
	public void setDefaultAudioLanguage(String defaultAudioLanguage) {
		this.defaultAudioLanguage = defaultAudioLanguage;
	}
    
    
}
