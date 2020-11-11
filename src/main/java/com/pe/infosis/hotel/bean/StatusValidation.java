
package com.pe.infosis.hotel.bean;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "status")
public class StatusValidation
{
    @JsonProperty
    private String code;
    @JsonProperty
    private List<ErrorValidation> errors;
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<ErrorValidation> getErrors() {
		if(errors == null) {
			errors = new ArrayList<ErrorValidation>();
		}
		return errors;
	}
	public void setErrors(List<ErrorValidation> errors) {
		this.errors = errors;
	}
	
    
    
}
