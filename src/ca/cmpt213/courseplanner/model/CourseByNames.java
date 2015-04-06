package ca.cmpt213.courseplanner.model;

import java.util.ArrayList;

public class CourseByNames {

        private ArrayList<Course> individualCourses = new ArrayList<Course>();
        private String name;
        
        public CourseByNames(String name) {
                
                this.name = name;
        }
        
        public void addCourses(ArrayList<Course> c){
                
                for(int i=0; i<c.size(); i++){
                        
                        if(c.get(i).getFullCourseName().equals(name)){
                                individualCourses.add(c.get(i));
                        }
                }
                
        }
        
        public ArrayList<Course> getIndividualCourses(){
                return individualCourses;
        }
        
        public String getCourseName(){
                return name;
        }

}