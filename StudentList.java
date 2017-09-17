/**
 * StudentList
 * The Best Version
 * @author Daniel Kim
 * Date of Completion
 * Class that creates a database for student objects
 */
public class StudentList {
	Student[] listOfStudents;
	int index = 0;
	
	StudentList() {
		listOfStudents = new Student[10];
	}
	StudentList(int size) {
		listOfStudents = new Student[size];
	}
	
	boolean addStudent(Student s) {
		while(listOfStudents[index] != null) {
			index++;
		}
		listOfStudents[index] = s;
		return true;
	}
}
