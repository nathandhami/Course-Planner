package ca.cmpt213.courseplanner.model;

/**
 * Represents a university course
 * 
 */
public class Course {

	private String semesterId;
	private String subject;
	private String catalogId;
	private String location;
	private String enrollmentCapacity;
	private String enrollmentTotal;
	private String instuctorName;
	private String courseType;


	public Course(String semesterId, String subject, String catalogId,
			String location, String enrollmentCapacity, String enrollmentTotal,
			String instuctorName, String courseType) {
		super();
		this.semesterId = semesterId;
		this.subject = subject;
		this.catalogId = catalogId;
		this.location = location;
		this.enrollmentCapacity = enrollmentCapacity;
		this.enrollmentTotal = enrollmentTotal;
		this.instuctorName = instuctorName;
		this.courseType = courseType;
	}
	
	public String getFullCourseName(){
		return subject + " " + catalogId;
	}

	public boolean isCourseEnrollmentEmpty() throws Exception {
		if (Integer.parseInt(enrollmentTotal) == 0) {
			return true;
		}

		else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Course [semesterId=" + semesterId + ", subject=" + subject
				+ ", catalogId=" + catalogId + ", location=" + location
				+ ", enrollmentCapacity=" + enrollmentCapacity
				+ ", enrollmentTotal=" + enrollmentTotal + ", instuctorName="
				+ instuctorName + ", courseType=" + courseType + "]";
	}
	
	public String getSemesterId() {
		return semesterId;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getCatalogId() {
		return catalogId;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getEnrollmentCapacity() {
		return enrollmentCapacity;
	}
	
	public String getEnrollmentTotal() {
		return enrollmentTotal;
	}
	
	public String getInstuctorName() {
		return instuctorName;
	}
	
	public String getCourseType() {
		return courseType;
	}
	
}
