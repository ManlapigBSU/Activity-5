package Student;

import java.util.ArrayList;
import java.util.Scanner;

public class Utils implements Person {
    private static final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Student> studentList;

    public Utils(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public void displayMenu(String[] menuOptions) {
        System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println((i + 1) + ". " + menuOptions[i]);
        }
        System.out.print("Choose an option: ");
    }

    @Override
    public void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }

        System.out.println("\n===== STUDENT LIST =====");
        int index = 1;
        for (Student student : studentList) {
            System.out.println(index++ + ". Name: " + student.getName() + " | Grade: " + student.getGrade());
        }
        System.out.println("=======================");
    }

    @Override
    public void updateStudent() {
        displayAllStudents();
        if (studentList.isEmpty()) return;

        System.out.print("Enter the number of the student to update (0 to cancel): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) {
                System.out.println("Update canceled.");
                return;
            }

            if (choice < 1 || choice > studentList.size()) {
                System.out.println("Error: Invalid student number.");
                return;
            }

            Student student = studentList.get(choice - 1);
            System.out.println("Updating student: " + student.getName() + " (" + student.getGrade() + ")");

            System.out.print("Enter new name (press Enter to keep current): ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty()) {
                student.setName(newName);
            }

            System.out.print("Update grade level? (y/n): ");
            String updateGrade = scanner.nextLine().toLowerCase().trim();
            if (updateGrade.equals("y") || updateGrade.equals("yes")) {
                GradeLevel newGrade = chooseGradeLevel();
                if (newGrade != null) {
                    student.setGrade(newGrade);
                }
            }

            System.out.println("Student updated successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Please enter a number.");
        }
    }

    @Override
    public void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Error: Student name cannot be empty.");
            return;
        }

        GradeLevel grade = chooseGradeLevel();
        if (grade == null) return;

        studentList.add(new Student(name, grade));
        System.out.println("Student '" + name + "' added successfully!");
    }

    @Override
    public void deleteStudent() {
        displayAllStudents();
        if (studentList.isEmpty()) return;

        System.out.print("Enter the number of the student to delete (0 to cancel): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) {
                System.out.println("Deletion canceled.");
                return;
            }

            if (choice < 1 || choice > studentList.size()) {
                System.out.println("Error: Invalid student number.");
                return;
            }

            Student student = studentList.get(choice - 1);
            System.out.print("Are you sure you want to delete student '" + student.getName() + "'? (y/n): ");
            String confirm = scanner.nextLine().toLowerCase().trim();

            if (confirm.equals("y") || confirm.equals("yes")) {
                studentList.remove(choice - 1);
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Deletion canceled.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Please enter a number.");
        }
    }

    public GradeLevel chooseGradeLevel() {
        System.out.println("Choose grade level:");
        GradeLevel[] grades = GradeLevel.values();
        for (int i = 0; i < grades.length; i++) {
            System.out.println((i + 1) + ". " + formatGradeLevel(grades[i]));
        }

        System.out.print("Enter number (1-" + grades.length + "): ");
        try {
            int gradeChoice = Integer.parseInt(scanner.nextLine());
            if (gradeChoice < 1 || gradeChoice > grades.length) {
                System.out.println("Error: Invalid grade choice.");
                return null;
            }
            return grades[gradeChoice - 1];
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Please enter a number.");
            return null;
        }
    }

    private String formatGradeLevel(GradeLevel grade) {
        String gradeName = grade.toString();
        return gradeName.charAt(0) + gradeName.substring(1).toLowerCase();
    }
}