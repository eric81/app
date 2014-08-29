package com.eudemon.taurus.app.entity;

/**
 * Esession entity. @author MyEclipse Persistence Tools
 */

public class Esession extends com.eudemon.taurus.app.entity.BaseEntity implements java.io.Serializable {

	// Fields

	private String id;
	private String session;

	// Constructors

	/** default constructor */
	public Esession() {
	}

	/** minimal constructor */
	public Esession(String id) {
		this.id = id;
	}

	/** full constructor */
	public Esession(String id, String session) {
		this.id = id;
		this.session = session;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSession() {
		return this.session;
	}

	public void setSession(String session) {
		this.session = session;
	}

}