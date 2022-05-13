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
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "VehicleInfo")
public class VehicleInfo implements Serializable{
	
	private static final long serialVersionUID = 9174987945482421794L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long truckId;
	
	@ApiModelProperty(required = true)
	private String truckNumber;
	
	@ApiModelProperty(required = true)
	private Date entryDate;
	
	private String grNumber;
	
	private String tokenNumber;
	
	@ApiModelProperty(hidden = true)
	private Double rate;
	
	@ApiModelProperty(required = true)
	private Double grossWeight;
	
	@ApiModelProperty(hidden = true)
	private Double grossAmount;
	
	@ApiModelProperty(required = true)
	private Double advanceCash;
	
	@ApiModelProperty(required = true)
	private Double advanceDiesel;
	
	@ApiModelProperty(hidden = true)
	private Double balanceFrieght;
	
	@ApiModelProperty(hidden = true)
	private Double netWeight;
	
	@ApiModelProperty(hidden = true)
	private Double netAmount;
	
	@ApiModelProperty(hidden = true)
	private Date createdOn;
	
	@ApiModelProperty(hidden = true)
	private Date updatedOn;
	
	@ApiModelProperty(hidden = true)
	private String createdBy;
	
	@ApiModelProperty(hidden = true)
	private String updatedBy;
	
}
