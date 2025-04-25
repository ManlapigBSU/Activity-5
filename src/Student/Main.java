package Student;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static final String[] menuOptions = {
            "Add Student",
            "Display All Students",
            "Update Student",
            "Delete Student",
            "Exit"
    };

    public static void main(String[] args) {
        Utils utils = new Utils(students);

        while (true) {
            utils.displayMenu(menuOptions);

            String input = scanner.nextLine();
            int option;

            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a number.");
                continue;
            }

            switch (option) {
                case 1 -> utils.addStudent();
                case 2 -> utils.displayAllStudents();
                case 3 -> utils.updateStudent();
                case 4 -> utils.deleteStudent();
                case 5 -> {
                    System.out.println("Exiting Student Management System...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Error: Invalid choice. Please select from 1-5.");
            }
        }
    }
}