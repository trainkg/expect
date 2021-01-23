package org.barley.entity;

public class INView {

	private String id;

	private String name;

	private Integer type;

	private int cc;

	public INView(String id, String name, Integer type, int cc) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.cc = cc;
	}

	public INView(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public INView() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public int getCc() {
		return cc;
	}

	public void setCc(int cc) {
		this.cc = cc;
	}

}
