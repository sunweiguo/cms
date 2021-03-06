package com.njupt.swg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_attachment")
public class Attachment {
	private int id;
	private String newName;
	private String oldName;
	private String type;
	private String suffix;
	private long size;
	private int isIndexPic;
	private int isImg;
	private int isAttach;
	private Topic topic;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="new_name")
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	@Column(name="old_name")
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	@Column(name="is_index_pic")
	public int getIsIndexPic() {
		return isIndexPic;
	}
	public void setIsIndexPic(int isIndexPic) {
		this.isIndexPic = isIndexPic;
	}
	@Column(name="is_img")
	public int getIsImg() {
		return isImg;
	}
	public void setIsImg(int isImg) {
		this.isImg = isImg;
	}
	@ManyToOne
	@JoinColumn(name="tid")
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	@Column(name="is_attch")
	public int getIsAttach() {
		return isAttach;
	}
	public void setIsAttach(int isAttach) {
		this.isAttach = isAttach;
	}
}
