/**
 * Course
 * The Best Version
 * @author Daniel Kim
 * Date of Completion
 * Class that creates a course object
 */
abstract class Course {
	
	private String courseName;
	private int max;
	
	Course(String courseName, int max) {
		this.courseName = courseName;
		this.max = max;
	}
	
	public String getCourseName() {
		return this.courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getMax() {
		return this.max;
	}
	public void setMax(int max) {
		this.max = max;
	}
}
