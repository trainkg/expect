package com.barley.config.codetable;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class CodeTableItem implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private String cid;
	
	@Getter
	@Setter
	private String description;
}
