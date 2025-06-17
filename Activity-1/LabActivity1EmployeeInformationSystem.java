//Laboratory_Activity#1_Employee_Information_System
import java.util.Scanner; //#This imports module that we need to use.

public class EmployeeInformationSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object to read input from the user

        // Prompt the user for employee information
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine(); // Read the first name from the user

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine(); // Read the last name from the user

        System.out.print("Enter your age: ");
        int age = scanner.nextInt(); // Read the age as an integer
        scanner.nextLine(); // Consume the newline character left in the buffer after reading an integer

        System.out.print("Enter hours worked: ");
        double hoursWorked = scanner.nextDouble(); // Read the hours worked as a double

        System.out.print("Enter hourly wage: ");
        double hourlyWage = scanner.nextDouble(); // Read the hourly wage as a double

        // Compute employee's full name
        String fullName = firstName + " " + lastName; // Concatenate first and last names with a space

        // Compute employee's daily wage
        double dailyWage = hoursWorked * hourlyWage; // Calculate daily wage by multiplying hours worked and hourly wage

        // Display employee information

        System.out.println("\nEmployee Information:");
        System.out.println("-----------------------");
        System.out.println("Full Name    : " + fullName); // Display the full name
        System.out.println("Age          : " + age); // Display the age
        System.out.println("Daily Salary : PHP " + String.format("%.2f", dailySalary)); // Display the daily wage formatted to 2 decimal places

        scanner.close(); // Close the Scanner object to release resources
    }
}
