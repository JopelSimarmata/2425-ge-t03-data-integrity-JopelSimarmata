package academic.driver;

import java.util.ArrayList;
import java.util.Scanner;
import academic.model.Course;
import academic.model.Student;
import academic.model.Enrollment;

/**
 * @autor 12S23036 Jopel Simarmata
 * @autor 12S23024 Eska Silaen
 */
public class Driver2 {

    public static void main(String[] _args) {

        Scanner scn = new Scanner(System.in);
        Boolean cek_s = false;
        Boolean cek_c = false;

        ArrayList<Course> daftarMataKuliah = new ArrayList<>();
        ArrayList<Student> daftarMahasiswa = new ArrayList<>();
        ArrayList<Enrollment> daftarEnrollments = new ArrayList<>();

        ArrayList<String> invalid = new ArrayList<>();

        while (true) {
            String command = scn.nextLine();

            if (command.equalsIgnoreCase("---")) {
                break;
            }
            String[] temp = new String[5];
            // split command
            String[] splitCommand = command.split("#");

            temp[0] = splitCommand[0];
            temp[1] = splitCommand[1];
            temp[2] = splitCommand[2];
            temp[3] = splitCommand[3];
            temp[4] = splitCommand[4];

            if (temp[0].equals("course-add")) {
                Course course = new Course(temp[1], temp[2], temp[3], temp[4]);
                daftarMataKuliah.add(course);

            } else if (temp[0].equals("student-add")) {
                Student student = new Student(temp[1], temp[2], temp[3], temp[4]);
                daftarMahasiswa.add(student);

            } else if (temp[0].equals("enrollment-add")) {
                cek_s = false;
                cek_c = false;

                for (int i = 0; i < daftarMahasiswa.size(); i++) {
                    if (daftarMahasiswa.get(i).getNim().equals(temp[2])) {
                        cek_s = true;
                        break;
                    }
                }

                for (int i = 0; i < daftarMataKuliah.size(); i++) {
                    if (daftarMataKuliah.get(i).getCodeCourse().equals(temp[1])) {
                        cek_c = true;
                        break;
                    }
                }

                if (!cek_s) {
                    invalid.add("invalid student|" + temp[2]);
                }

                if (!cek_c) {
                    invalid.add("invalid course|" + temp[1]);
                }

                if (cek_s && cek_c) {
                    Enrollment enrollment = new Enrollment(temp[1], temp[2], temp[3], temp[4]);
                    daftarEnrollments.add(enrollment);
                }
            }
        }

        // print invalid
        for (int i = 0; i < invalid.size(); i++) {
            for (int j = i + 1; j < invalid.size(); j++) {
                if (invalid.get(i).equals(invalid.get(j))) {
                    invalid.remove(j);
                }
            }
            System.out.println(invalid.get(i));
        }

        for (int i = daftarMataKuliah.size() - 1; i >= 0; i--) {
            daftarMataKuliah.get(i).DisplayCourse();
        }

        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            daftarMahasiswa.get(i).DisplayStudent();
        }

        for (Enrollment enrollment : daftarEnrollments) {
            enrollment.DisplayEnrollment();
        }
    }
}