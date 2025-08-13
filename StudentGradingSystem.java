import java.util.*;
import java.io.*;

public class StudentGradingSystem {
    private static List<Student> students = new ArrayList<>();
    private static final String FILE_NAME = "students.dat";

    public static void main(String[] args) {
        loadStudents();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Student Grading System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by Roll Number");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    searchStudent(sc);
                    break;
                case 4:
                    saveStudents();
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addStudent(Scanner sc) {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();
        System.out.print("Enter roll number: ");
        int rollNo = sc.nextInt();
        System.out.print("Enter marks (out of 100): ");
        double marks = sc.nextDouble();

        Student student = new Student(name, rollNo, marks);
        students.add(student);
        saveStudents();
        System.out.println("Student added successfully!");
    }

    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n--- All Students ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void searchStudent(Scanner sc) {
        System.out.print("Enter roll number to search: ");
        int rollNo = sc.nextInt();
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                System.out.println("Student Found: " + s);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (ArrayList<Student>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing student records found. Starting fresh...");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
