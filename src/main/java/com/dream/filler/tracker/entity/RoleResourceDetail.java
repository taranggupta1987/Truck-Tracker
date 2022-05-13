package com.dream.filler.tracker.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "RoleResourceDetail")
public class RoleResourceDetail implements Serializable {
	private static final long serialVersionUID = -6984776111592798803L;
	
	@EmbeddedId
	private RoleResourceId roleResourceId;
	
	private Boolean read = false;
	
	private Boolean write = false;
	
	private Boolean delete = false;
	
	private Date createdOn;
	
	private Date updatedOn;
	
	private String createdBy;
	
	private String updatedBy;
}
