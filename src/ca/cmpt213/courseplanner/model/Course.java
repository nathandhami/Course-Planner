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
	private int enrollmentCapacity;
	private int enrollmentTotal;
	private String instuctorName;
	private String courseType;

	public Course(String semesterId, String subject, String catalogId,
			String location, int enrollmentCapacity, int enrollmentTotal,
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
	
	
	
	

}
