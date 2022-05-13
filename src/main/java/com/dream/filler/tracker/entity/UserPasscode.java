package com.dream.filler.tracker.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
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
@Entity(name = "UserPasscode")
public class UserPasscode implements Serializable{
	private static final long serialVersionUID = 234753083715775714L;
	
	@Id
	private String userId;
	private String passcode;
	private Date expiresOn;
	
	
	

}
