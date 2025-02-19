package academic.driver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import academic.model.Course;
import academic.model.Student;
import academic.model.Enrollment;

/**
 * @author 12S23036 Jopel Simarmata
 * @author 12S23024 Eska Silaen
 */
public class Driver2 {

    public static void main(String[] _args) {

        Scanner scn = new Scanner(System.in);
        ArrayList<Course> daftarMataKuliah = new ArrayList<>();
        ArrayList<Student> daftarMahasiswa = new ArrayList<>();
        ArrayList<Enrollment> daftarEnrollments = new ArrayList<>();

        while (true) {
            String command = scn.nextLine();
            if (command.equalsIgnoreCase("---")) {
                break;
            }
            
            String[] splitCommand = command.split("#");
            String commandType = splitCommand[0];
            
                switch (commandType) {
                    case "course-add":
                        daftarMataKuliah.add(new Course(splitCommand[1], splitCommand[2], splitCommand[3], splitCommand[4]));
                        break;
                    
                    case "student-add":
                        daftarMahasiswa.add(new Student(splitCommand[1], splitCommand[2], splitCommand[3], splitCommand[4]));
                        break;
                    
                    case "enrollment-add":
                        String courseCode = splitCommand[1];
                        String studentNim = splitCommand[2];
                        boolean validCourse = daftarMataKuliah.stream().anyMatch(c -> c.getCodeCourse().equals(courseCode));
                        boolean validStudent = daftarMahasiswa.stream().anyMatch(s -> s.getNim().equals(studentNim));
                        
                        if (!validCourse) {
                            System.out.println("invalid course|" + courseCode);
                            break;
                        }
                        
                        if (!validStudent) {
                            System.out.println("invalid student|" + studentNim);
                            break;
                        }
                        
                        daftarEnrollments.add(new Enrollment(courseCode, studentNim, splitCommand[3], splitCommand[4]));
                        break;
                }
        }
        
        // Cetak data
        Collections.reverse(daftarMataKuliah);
        daftarMataKuliah.forEach(Course::DisplayCourse);
        daftarMahasiswa.forEach(Student::DisplayStudent);
        daftarEnrollments.forEach(Enrollment::DisplayEnrollment);
    }
}