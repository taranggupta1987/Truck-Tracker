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
@Entity(name = "LoadInfo")
public class LoadInfo implements Serializable{
	
	private static final long serialVersionUID = 7804313785748382849L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long LoadId;
	
	@ApiModelProperty(required = true)
	private String loadDate;
	
	@ApiModelProperty(required = true)
	private String truckId;
	
	@ApiModelProperty(required = false)
	private String grNumber;
	
	@ApiModelProperty(required = false)
	private String token;
	
	@ApiModelProperty(required = true)
	private Boolean rate;
	
	@ApiModelProperty(required = true)
	private Boolean weight;
	
	@ApiModelProperty(required = true)
	private Boolean advanceCash;
	
	@ApiModelProperty(required = true)
	private Boolean advanceDiesel;
	
	@ApiModelProperty(hidden = true)
	private Date createdOn;
	
	@ApiModelProperty(hidden = true)
	private Date updatedOn;
	
	@ApiModelProperty(hidden = true)
	private String createdBy;
	
	@ApiModelProperty(hidden = true)
	private String updatedBy;
	
}
