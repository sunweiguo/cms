package com.njupt.swg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="t_keyword")
public class Keyword implements Comparable<Keyword>{
	private int id;
	private String name;
	private int times;
	private String nameFullPy;
	private String nameShortPy;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	@Column(name="name_full_py")
	public String getNameFullPy() {
		return nameFullPy;
	}
	public void setNameFullPy(String nameFullPy) {
		this.nameFullPy = nameFullPy;
	}
	@Column(name="name_short_py")
	public String getNameShortPy() {
		return nameShortPy;
	}
	public void setNameShortPy(String nameShortPy) {
		this.nameShortPy = nameShortPy;
	}
	public Keyword() {
		super();
	}
	public Keyword(String name, int times) {
		super();
		this.name = name;
		this.times = times;
	}
	@Override
	public int compareTo(Keyword o) {
		
		return this.times<o.times?-1:(this.times==o.times?0:1);
	}
}
