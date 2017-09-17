/**
 * ScheduleMaker
 * The Best Version
 * @author Daniel Kim
 * Date of Completion
 * Program that creates timetables for students
 */

//importing file IO and scanner
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class ScheduleMaker {
	
	/**
	 * personSort
	 * Method that sorts contents in text file
	 * @param personInputData - String array holding data from text files
	 * @param pIDLoop - Integer holding length of personInputData array
	 * @param people - String array to hold either student or teacher names
	 * @param ID - Integer for determining which file the data is from
	 * @return
	 */
	public static String[] personSort(String personInputData[], int pIDLoop, String people[], int ID) {
		int firstInt = 0;
		int secondInt = 0;
		String numbers = "0123456789";
		
		for (int firstLoop = 0; firstLoop < pIDLoop; firstLoop++) {
			for (int secondLoop = 0; secondLoop < numbers.length(); secondLoop++) {
				if (personInputData[firstLoop].charAt(3) != numbers.charAt(secondLoop)) {
					firstInt++;
				}		
			}// end of secondLoop
			
			//Student specific name identification
			if (firstInt == 10 && ID == 0) {
				people[secondInt] = personInputData[firstLoop] + " " + personInputData[firstLoop+1];
				firstLoop+=2;
				secondInt++;
			}
			//Teacher specific name identification
			else if (firstInt == 10 && ID == 1) {
				people[secondInt] = personInputData[firstLoop];
				secondInt++;
			}
			firstInt = 0;
		}
		return people;
	}// end of personSort
	
	/**
	 * nonNameSort
	 * Method that sorts all student data that is not their name
	 * @param studentInputData - String array holding data from student.txt
	 * @param stuIDLoop - Integer holding length of studentInputData
	 * @param studentCourses - 2D string array to hold courses each student is taking
	 */
	public static String[][] studentCoursesSort(String studentInputData[], int stuIDLoop, String studentCourses[][]) {
		int firstInt = 0;
		int fFirstInt = 0;
		int fSecondInt = 0;
		String numbers = "1234";
		
		for (int firstLoop = 3; firstLoop < stuIDLoop; firstLoop++) {
			for (int secondLoop = 0; secondLoop < numbers.length(); secondLoop++) {
				if (studentInputData[firstLoop].charAt(3) == numbers.charAt(secondLoop) && (firstLoop-2)%9 != 0) {
					studentCourses[fFirstInt][fSecondInt] = studentInputData[firstLoop];
					fSecondInt++;
				}
			}// end of secondLoop
			
			if ((firstLoop-8)%9 == 0) {
				fFirstInt++;
			}
			if (fSecondInt == 6) {
				fSecondInt = 0;
			}
			
		}// end of firstLoop
		return studentCourses;
	}// end of nonNameSort
	
	/**
	 * teacherCourseSort
	 * @param teacherInputData
	 * @param sIDLoop
	 * @param teacherCourses
	 */
	public static void teacherCourseSort(String teacherInputData[], int sIDLoop, String teacherCourses[][]) {
		int firstInt = 0;
		int secondInt = 0;
		int countInt = 0;
		String numbers = "1234";
		for (int firstLoop = 2; firstLoop < sIDLoop; firstLoop++) {
			for (int secondLoop = 0; secondLoop < numbers.length(); secondLoop++) {
				if(teacherInputData[firstLoop].charAt(3) == numbers.charAt(secondLoop)) {
					teacherCourses[firstInt][secondInt] = teacherInputData[firstLoop];
					secondInt++;
				}
				else {
					countInt++;
				}
			}
			if (countInt == 4) {
				firstInt++;
			}
		}
	}// end of teacherCourseSort
	
	/**
	 * courseAssign
	 * Method that assigns students and teachers to open courses
	 * @param studentCourses
	 * @param studentSchedule
	 * @param courseCodes
	 * @param classCapacity
	 * @param studentRoster
	 * @return
	 */
	public static String[][] courseAssign(String studentCourses[][], String classAttendance[][], String courseCodes[], int classCapacity[], String studentRoster[]) {
		for (int i = 0; i < courseCodes.length; i++) {
			int classSize = 0;
			int a = 0;
			for (int j = 0; j < studentCourses.length; j++) {
				for (int k = 0; k < studentCourses[0].length; k++) {
					if (studentCourses[j][k] != null && courseCodes[i] != null) {
						if (courseCodes[i].equals(studentCourses[j][k]) && classSize <= classCapacity[i]) {
							classAttendance[i][a] = studentRoster[j];
							a++;
							k = studentCourses[0].length;
							classSize++;
						}
					}
				}
			}
		}
		return classAttendance;
	}// end of courseAssign
	
	//Start of main method
	public static void main(String[] args) throws Exception{
		String principal;
		
		String studentRoster[] = new String[10];
		String studentNumbers[] = new String[10];
		String studentCourses[][] = new String[10][6];
		String classAttendance[][] = new String[10][10];
		String teacherCourses[][] = new String[10][10];
		String studentInputData[] = new String[100];
		String staffInputData[] = new String[100];
		String teacherRoster[] = new String[10];
		String courseCodes[] = new String[10];
		int classCapacity[] = new int[10];
		
		int stuIDLoop = 0;
		int ccLoop = 0;
		int maxLoop = 0;
		int sIDLoop = 0;
		int ID;
		
		Student student = null;
		
		File students = new File("students.txt");
		File staff = new File("staff.txt");
		File courses = new File("courses.txt");
		
		Scanner studentInput = new Scanner(students);	
		Scanner staffInput = new Scanner(staff);
		Scanner coursesInput = new Scanner(courses);
		
		PrintWriter scheduleOutput = new PrintWriter("schedule.txt");
		
		//Reading from student.txt
		while (studentInput.hasNext()) {
			studentInputData[stuIDLoop] = studentInput.next();
			stuIDLoop++;
		}
		studentInput.close();
		
		//Reading from courses.txt
		while (coursesInput.hasNext()) {
			courseCodes[ccLoop] = coursesInput.next();
			ccLoop++;
			classCapacity[maxLoop] = coursesInput.nextInt();
			maxLoop++;
		}
		coursesInput.close();
		
		//Reading from staff.txt
		while (staffInput.hasNext()) {
			staffInputData[sIDLoop] = staffInput.next();
			sIDLoop++;
		}
		staffInput.close();
		
		//organize input based on either course code or teacher name
		ID = 0;
		studentRoster = personSort(studentInputData, stuIDLoop, studentRoster, ID);
		studentCoursesSort(studentInputData, stuIDLoop, studentCourses);
		ID = 1;
		teacherRoster = personSort(staffInputData, sIDLoop, teacherRoster, ID);
		
		principal = teacherRoster[0];
		
		CourseList courseList = new CourseList();
		for (int cOC = 0; cOC < courseCodes.length; cOC++) {
			if (courseCodes[cOC] != null) {
			Course course = new Classroom(courseCodes[cOC], classCapacity[cOC]);
			course.getCourseName();
			course.setCourseName(courseCodes[cOC]);
			course.getMax();
			course.setMax(classCapacity[cOC]);
			courseList.addCourse(course);
			}
		}
		
		StudentList studentList = new StudentList();
		for (int sOC = 0; sOC < studentRoster.length; sOC++) {
				student = new Student();
				student.getName();
				student.setName(studentRoster[sOC]);
				student.getStudentNumber();
				student.setStudentNumber(studentNumbers[sOC]);
				student.getCourse1();
				student.setCourse1(studentCourses[sOC][0]);
				student.getCourse2();
				student.setCourse2(studentCourses[sOC][1]);
				student.getCourse3();
				student.setCourse3(studentCourses[sOC][2]);
				student.getCourse4();
				student.setCourse4(studentCourses[sOC][3]);
				student.getAltCourse1();
				student.setAltCourse1(studentCourses[sOC][4]);
				student.getAltCourse2();
				student.setAltCourse2(studentCourses[sOC][5]);
				studentList.addStudent(student);
		}
		
		TeacherList teacherList = new TeacherList();
		for (int tOC = 0; tOC < teacherRoster.length; tOC++) {
			Teacher teacher = new Teacher();
			teacher.getTeacherName();
			teacher.setTeacherName(teacherRoster[tOC]);
			teacherList.addTeacher(teacher);
		}
		
//		for (int i = 0; i < courseCodes.length; i++) {
//			int classSize = 0;
//			Classroom classroom = new Classroom(courseCodes[i], classCapacity[i]);
//			for (int j = 0; j < studentCourses.length-5; j++) {
//				for (int k = 0; k < studentCourses.length-5; k++) {
//					if (studentCourses[j][k] != null) {
//						if (studentCourses[j][k].equals(classroom.getCourseName()) && classSize <= classroom.getMax()) {
//							classroom.addAttendance(student);
//							classSize++;
//						}
//					}
//				}
//			}
//		}
		
		classAttendance = courseAssign(studentCourses, classAttendance, courseCodes, classCapacity, studentRoster);
		for (int i = 0; i < courseCodes.length; i++) {
			if (courseCodes[i] != null) {
				System.out.println(courseCodes[i]);
				for (int j = 0; j < classAttendance[0].length; j++) {
					if (classAttendance[i][j] != null) {
						System.out.println(classAttendance[i][j]);
					}
				}
			}
		}
		
		System.out.println("Outputting...");
		scheduleOutput.println("PRINCIPAL");
		scheduleOutput.println(principal);
		scheduleOutput.println("\n");
		scheduleOutput.println("------");
		scheduleOutput.println("COURSE");
		scheduleOutput.println();
		scheduleOutput.close();
	}// end of main method
}// end of class