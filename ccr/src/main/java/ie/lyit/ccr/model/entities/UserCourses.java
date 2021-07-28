package ie.lyit.ccr.model.entities;

import java.io.Serializable;

/**
 *
 * @author aks
 */

public class UserCourses implements Serializable {

	private String courseName;
	private String skillName;
	private String courseDescription;

	public UserCourses(String courseName, String skillName, String courseDescription) {
		//super();
		setCourseName(courseName);
		setSkillName(skillName);
		setCourseDescription(courseDescription);
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
}
