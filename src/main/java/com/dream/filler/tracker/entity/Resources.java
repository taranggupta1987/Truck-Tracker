package com.dream.filler.tracker.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "Resources")
public class Resources implements Serializable {
	private static final long serialVersionUID = 5025788184511828167L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long resourceId;
	
	private String resource;
	
	private Date createdOn;
	
	private Date updatedOn;
	
	private String createdBy;
	
	private String updatedBy;

}
