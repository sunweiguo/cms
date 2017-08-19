package com.njupt.swg.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_topic")
public class Topic {
	private int id;
	private String title;
	private String keyword;
	private int status;
	private int recommend;
	private String content;
	private String summary;
	private String author;
	private Date publishDate;
	private Date createDate;
	private Channel channel;
	private User user;
	//栏目图片id，如果该栏目是图片类型的栏目，就会显示这个id的图片
	private int channelPicId;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Column(name="publish_date")
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@ManyToOne
	@JoinColumn(name="cid")
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	@ManyToOne
	@JoinColumn(name="uid")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Column(name="channel_pic_id")
	public int getChannelPicId() {
		return channelPicId;
	}
	public void setChannelPicId(int channelPicId) {
		this.channelPicId = channelPicId;
	}
	@Override
	public String toString() {
		return "Topic [id=" + id + ", title=" + title + ", keyword=" + keyword + ", status=" + status + ", recommend="
				+ recommend + ", content=" + content + ", summary=" + summary + ", author=" + author + ", publishDate="
				+ publishDate + ", createDate=" + createDate + ", channel=" + channel + ", user=" + user
				+ ", channelPicId=" + channelPicId + "]";
	}
	
}
