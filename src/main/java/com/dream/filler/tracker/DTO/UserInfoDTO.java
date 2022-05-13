package com.dream.filler.tracker.DTO;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO implements Serializable {

	private static final long serialVersionUID = 7115799163322551688L;
	
	private String firstName;
	private String lastName;
	private Integer pin;
	private String email;
}
