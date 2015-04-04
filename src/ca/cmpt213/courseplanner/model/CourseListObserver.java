package ca.cmpt213.courseplanner.model;

/**
 * Interface for observers to implement to be able
 * to observe changes to CourseList objects
 */
public interface CourseListObserver {
	void stateChanged();
}
