package ca.cmpt213.courseplanner.model;

/**
 * 
 * Represents a semester
 * First three digits are calendar year
 * Last digit is the calendar month
 * 1 = spring
 * 4 = summer
 * 7 = fall
 * 
 * Examples:
 * 1127 = fall 2012
 * 1131 = spring 2013
 * 1134 = summer 2013
 *
 * 1 represents the 21st century
 * 12 = year (e.g. 2012)
 * the final digit is the term: 1 for spring, 4 for summer and 7 for fall.
 */
public class Semester {
	
	private int calendarYear;
	private int calendarMonth;
	
	
	public Semester(int calendarYear, int calendarMonth){
		this.calendarYear = calendarYear;
		this.calendarMonth = calendarMonth;
	}

}
