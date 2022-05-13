package com.dream.filler.tracker.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
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
@Entity(name = "Role")
public class Role implements Serializable {
	private static final long serialVersionUID = -3477531420430835154L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roleId;
	
	private String role;
	
	@ApiModelProperty(hidden = true)
	private Date createdOn;
	
	@ApiModelProperty(hidden = true)
	private Date updatedOn;
	
	@ApiModelProperty(hidden = true)
	private String createdBy;
	
	@ApiModelProperty(hidden = true)
	private String updatedBy;

}
