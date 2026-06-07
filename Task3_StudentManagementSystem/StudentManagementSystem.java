import java.io.*;
import java.util.*;

class Student implements Serializable {

    private String name;
    private final int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNumber +
                " | Name: " + name +
                " | Grade: " + grade;
    }
}

public class StudentManagementSystem {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static final String FILE_NAME = "students.dat";

    public static void main(String[] args) {

        loadStudents();

        int choice;

        do {

            System.out.println("\n=================================");
            System.out.println("   STUDENT MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Edit Student");
            System.out.println("6. Save Data");
            System.out.println("7. Exit");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1 -> addStudent();

                case 2 -> removeStudent();

                case 3 -> searchStudent();

                case 4 -> displayStudents();

                case 5 -> editStudent();

                case 6 -> saveStudents();

                case 7 -> {
                    saveStudents();
                    System.out.println("Exiting System...");
                }

                default -> System.out.println("Invalid Choice!");
            }

        } while (choice != 7);
    }

    public static void addStudent() {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Grade: ");
        String grade = sc.nextLine().trim();

        if (grade.isEmpty()) {
            System.out.println("Grade cannot be empty!");
            return;
        }

        students.add(new Student(name, roll, grade));

        System.out.println("Student Added Successfully!");
    }

    public static void removeStudent() {

        System.out.print("Enter Roll Number to Remove: ");
        int roll = sc.nextInt();

        Student student = findStudent(roll);

        if (student != null) {
            students.remove(student);
            System.out.println("Student Removed Successfully!");
        } else {
            System.out.println("Student Not Found!");
        }
    }

    public static void searchStudent() {

        System.out.print("Enter Roll Number to Search: ");
        int roll = sc.nextInt();

        Student student = findStudent(roll);

        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student Not Found!");
        }
    }

    public static void displayStudents() {

        if (students.isEmpty()) {
            System.out.println("No Students Available!");
            return;
        }

        System.out.println("\n----- STUDENT RECORDS -----");

        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static void editStudent() {

        System.out.print("Enter Roll Number to Edit: ");
        int roll = sc.nextInt();
        sc.nextLine();

        Student student = findStudent(roll);

        if (student != null) {

            System.out.print("Enter New Name: ");
            String name = sc.nextLine();

            System.out.print("Enter New Grade: ");
            String grade = sc.nextLine();

            if (!name.trim().isEmpty()) {
                student.setName(name);
            }

            if (!grade.trim().isEmpty()) {
                student.setGrade(grade);
            }

            System.out.println("Student Updated Successfully!");

        } else {
            System.out.println("Student Not Found!");
        }
    }

    public static Student findStudent(int roll) {

        for (Student student : students) {

            if (student.getRollNumber() == roll) {
                return student;
            }
        }

        return null;
    }

    @SuppressWarnings({"unchecked", "UseSpecificCatch"})
    public static void loadStudents() {

        try {

            try (FileInputStream file = new FileInputStream(FILE_NAME)) {
                ObjectInputStream in =
                        new ObjectInputStream(file);
                
                students = (ArrayList<Student>) in.readObject();
                
                in.close();
            }

            System.out.println("Student Data Loaded.");

        } catch (Exception e) {

            System.out.println("No Previous Data Found.");
        }
    }

    @SuppressWarnings({"ConvertToTryWithResources", "UseSpecificCatch"})
    public static void saveStudents() {

        try {

            FileOutputStream file =
                    new FileOutputStream(FILE_NAME);

            ObjectOutputStream out =
                    new ObjectOutputStream(file);

            out.writeObject(students);

            out.close();
            file.close();

            System.out.println("Data Saved Successfully.");

        } catch (Exception e) {

            System.out.println("Error Saving Data.");
        }
    }
}
