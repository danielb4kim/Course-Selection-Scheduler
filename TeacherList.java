/**
 * TeacherList
 * The Best Version
 * @author Daniel Kim
 * Date of Completion
 * Class that creates a database for teacher objects
 */
public class TeacherList {
	Teacher[] listOfTeachers;
	int index = 0;
	
	TeacherList() {
		listOfTeachers = new Teacher[10];
	}
	TeacherList(int size) {
		listOfTeachers = new Teacher[size];
	}
	
	boolean addTeacher(Teacher t) {
		while(listOfTeachers[index] != null) {
			index++;
		}
		listOfTeachers[index] = t;
		return true;
	}
}
