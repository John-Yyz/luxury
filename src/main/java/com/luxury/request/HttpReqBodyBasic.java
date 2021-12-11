package com.luxury.request;

import javax.validation.Valid;
import java.io.Serializable;



public class HttpReqBodyBasic<T extends Serializable> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Valid
	protected T params;

	public T getParams() {
		return params;
	}

	public void setParams(T params) {
		this.params = params;
	}
}
