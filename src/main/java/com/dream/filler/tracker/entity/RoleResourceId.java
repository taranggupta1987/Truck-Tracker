package com.dream.filler.tracker.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

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
@Embeddable
public class RoleResourceId implements Serializable {
	private static final long serialVersionUID = 1485345193313882172L;
	
	@ManyToOne
	@JsonProperty
	private Role role;
	
	@ManyToOne
	@JsonProperty
	private Resources resource;


}
