package com.shangde.edu.sys.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoleDto extends Role implements Serializable{
	private List<Subject> refSubject = new ArrayList<Subject>();

	public List<Subject> getRefSubject() {
		return refSubject;
	}

	public void setRefSubject(List<Subject> refSubject) {
		this.refSubject = refSubject;
	}
}
