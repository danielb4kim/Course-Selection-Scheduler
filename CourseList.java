/**
 * CourseList
 * The Best Version
 * @author Daniel Kim
 * Date of Completion
 * Class that creates a database for course objects
 */
public class CourseList {
	Course[] listOfCourses;
	int index = 0;
	
	CourseList() {
		listOfCourses = new Course[10];
	}
	CourseList(int size) {
		listOfCourses = new Course[size];
	}
	
	boolean addCourse(Course c) {
		while(listOfCourses[index] != null) {
			index++;
		}
		listOfCourses[index] = c;
		return true;
	}
}