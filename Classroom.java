/**
 * Classroom
 * The Best Version
 * @author Daniel Kim
 * Date of completion
 * Class that creates a classroom object
 */
class Classroom extends Course {
	Student[] attendance = new Student[10];
	int index = 0;
	
	Classroom(String courseName, int max) {
		super(courseName, max);
	}
	boolean addAttendance(Student s) {
		while(attendance[index] != null) {
			index++;
		}
		attendance[index] = s;
		return true;
	}
}