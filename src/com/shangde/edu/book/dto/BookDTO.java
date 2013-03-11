package com.shangde.edu.book.dto;

import java.io.Serializable;

import com.shangde.edu.book.domain.Book;
import com.shangde.edu.sys.domain.Subject;

public class BookDTO extends Book implements Serializable{
    private Subject subject;

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
    
}
