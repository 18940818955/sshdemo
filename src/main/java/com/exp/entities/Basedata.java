package com.exp.entities;

import java.io.Serializable;

public class Basedata implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7833115547666057448L;
	private Integer id;
	private String name;
	private Integer parent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Basedata [id=" + id + ", name=" + name + ", parent=" + parent
				+ "]";
	}

	public Basedata(String name, Integer parent) {
		super();
		this.name = name;
		this.parent = parent;
	}

	public Basedata(Integer id) {
		super();
		this.id = id;
	}

	public Basedata() {
		super();
	}

}
