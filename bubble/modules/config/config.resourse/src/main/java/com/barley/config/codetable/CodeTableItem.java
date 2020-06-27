package com.barley.config.codetable;

import lombok.Getter;
import lombok.Setter;

public class CodeTableItem<T> {
	@Getter
	@Setter
	private T cid;
	
	@Getter
	@Setter
	private String description;
}
