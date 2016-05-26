package de.mlo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Arc {
	
	@Id
	@GeneratedValue
	@Getter
	@Setter
	private int id;
	
	@Column
	private String name;

}
