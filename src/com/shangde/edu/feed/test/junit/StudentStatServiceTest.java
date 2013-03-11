/**
 * 
 */
package com.shangde.edu.feed.test.junit;

import com.shangde.edu.feed.domain.StudentStat;
import com.shangde.edu.feed.service.IStudentStat;

/**
 * @author fd
 * 
 */
public class StudentStatServiceTest extends BaseTest {

	public void testStudentStat() {

		IStudentStat studentStatService = (IStudentStat) this.springContext
				.getBean("studentStatService");

		StudentStat studentStat = new StudentStat();

		studentStat.setFromId(12);
		studentStat.setArriveWxxIds("1,2,3,4,5");

		studentStatService.addStudentStat(studentStat);

	}

}
