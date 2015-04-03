package ca.cmpt213.courseplanner.model;

/**
 * 
 * Represents a semester First three digits are calendar year Last digit is the
 * calendar month 1 = spring 4 = summer 7 = fall
 * 
 * Examples: 1127 = fall 2012 1131 = spring 2013 1134 = summer 2013
 *
 * 1 represents the 21st century 12 = year (e.g. 2012) the final digit is the
 * term: 1 for spring, 4 for summer and 7 for fall.
 */
public class Semester {

	private String calendarYear;
	private String calendarMonth;
	private String semesterId;
	private int lastIndexOfSemesterId;

	public Semester(String semesterId) {

		this.semesterId = semesterId;
		lastIndexOfSemesterId = semesterId.length() - 1;
		setCalendarMonth();
		setCalendarYear();
	}

	private int retrieveCurrentCentury(int calendarYearNumber) {
		int calendarCenturyNumber = Integer
				.parseInt(semesterId.substring(0, 1));
		int currentCentury = 2000;

		// only initiate if we are in 22nd century
		for (int i = 1; i < calendarCenturyNumber; i++) {
			currentCentury += 100;
		}

		return currentCentury;
	}

	private void setCalendarYear() {
		int calendarYearNumber = Integer.parseInt(semesterId.substring(1,
				lastIndexOfSemesterId));

		calendarYearNumber += retrieveCurrentCentury(calendarYearNumber);

		calendarYear = String.valueOf(calendarYearNumber);

	}

	private void setCalendarMonth() {
		int calendarMonthNumber = Integer.parseInt(semesterId
				.substring(lastIndexOfSemesterId));
		if (1 == calendarMonthNumber) {
			calendarMonth = "Spring";
		}

		else if (4 == calendarMonthNumber) {
			calendarMonth = "Summer";
		}

		else if (7 == calendarMonthNumber) {
			calendarMonth = "Fall";
		}

	}

	public String getSemesterId() {
		return semesterId;
	}

	public String getSemesterMonth() {
		return calendarMonth;
	}

	public String getSemesterYear() {
		return calendarYear;
	}
}
