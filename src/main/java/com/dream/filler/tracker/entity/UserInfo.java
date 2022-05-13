package com.dream.filler.tracker.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
@Entity(name = "UserInfo")
public class UserInfo implements Serializable{
	private static final long serialVersionUID = -7110204874589821914L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@ApiModelProperty(required = true)
	private String firstName;
	
	private String middleName;
	
	@ApiModelProperty(required = true)
	private String lastName;
	
	private String gender;
	
	private String dob;
	
	private String phoneNo;
	
	@ApiModelProperty(required = true)
	private String emailId;
	
	@ApiModelProperty(required = true)
	private String password;
	
	/*@ApiModelProperty(required = true)
	private Boolean isInvitedUser;*/
	
	//private String isInvitedBy;
	
	@ApiModelProperty(required = true)
	private Boolean tncAccepted;
	
	@ApiModelProperty(hidden = true)
	private Date tncTimestamp;
	
	@ApiModelProperty(hidden = true)
	private String appDeviceToken;
	
	@ApiModelProperty(hidden = true)
	private Date createdOn;
	
	@ApiModelProperty(hidden = true)
	private Date updatedOn;
	
	@ApiModelProperty(hidden = true)
	private String createdBy;
	
	@ApiModelProperty(hidden = true)
	private String updatedBy;
	
	@ApiModelProperty(hidden = true)
	private Integer pin;
	
	@ManyToOne
	private Role role;
	
	@ApiModelProperty(required = true)
	private String status;
	private String registrationType;
}
