package ca.cmpt213.courseplanner.model;

public class Course {
	
	private int semesterId;
	private String subject;
	private int catalogId;
	private String location;
	private int enrollmentNumber;
	private String instuctorName;
	private String courseType;
	
	public Course(int semesterId, String subject, int catalogId,
			String location, int enrollmentNumber, String instuctorName,
			String courseType) {
		super();
		this.semesterId = semesterId;
		this.subject = subject;
		this.catalogId = catalogId;
		this.location = location;
		this.enrollmentNumber = enrollmentNumber;
		this.instuctorName = instuctorName;
		this.courseType = courseType;
	}
	
	
	
	

}
