package com.sayma.notesapp01.mynotesapp.entity;

import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "notes")
public class Note {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull(message = "Title cannot be null")
	@Size(min = 1,message = "Title cannot be null")
	@Size(max = 20,message = "Title should not be more than 20 characters")
	@Column(name = "title")
	private String title;

	@NotNull(message = "Description cannot be null")
	@Size(min = 1,message = "Description cannot be null")
	@Size(max = 100,message = "Description of Note should not be more than 100 characters")
	@Column(name = "description")
	private String desc;
	
	@Column(name = "creation_date")
	private LocalDateTime creationDate;
	
	@Column(name = "updation_date")	
	private LocalDateTime updationDate;

	@Column(name = "userid")	
	private String userid;	

	
	public Note() {

	}

	public Note(int id,
			@NotNull(message = "Title cannot be null") @Size(min = 1, message = "Title cannot be null") @Size(max = 20, message = "Title should not be more than 20 characters") String title,
			@NotNull(message = "Description cannot be null") @Size(min = 1, message = "Description cannot be null") @Size(max = 100, message = "Description of Note should not be more than 100 characters") String desc,
			LocalDateTime creationDate, LocalDateTime updationDate, String userid) {
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.creationDate = creationDate;
		this.updationDate = updationDate;
		this.userid = userid;
	
	}	
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}	

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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getUpdationDate() {
		return updationDate;
	}

	public void setUpdationDate(LocalDateTime updationDate) {
		this.updationDate = updationDate;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", desc=" + desc + ", creationDate=" + creationDate
				+ ", updationDate=" + updationDate + "]";
	}
	
	
}
