package com.hybernetproject;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Student implements Serializable {

	@Id
	private int sid;
	private String sname;
	private String sdept;
	private String sloc;

}
