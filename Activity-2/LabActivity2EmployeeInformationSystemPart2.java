import java.util.Scanner; //This what we need to run the program
import java.math.RoundingMode; // modules that contains rounding off
import java.text.DecimalFormat; // for formatting decimal places

public class LabActivity2EmployeeInformationSystemPart2 { // Class Name and Activity Name
    public static void main(String[] args) { // Main Method
        Scanner scanner = new Scanner(System.in); // Creating a Scanner object to read user input
        System.out.print("Enter your first name: "); // Prompting user to input for first name and storing it in firstName
        String firstName = scanner.nextLine(); // to know the variable

        System.out.print("Enter your last name: "); // Prompting user input for last name and storing it in lastName
        String lastName = scanner.nextLine();

        System.out.print("Enter your age: "); // Prompting user input for her/his age and storing it in EmployeeAge
        int EmployeeAge = scanner.nextInt();

        System.out.print("Enter hours worked: "); // user input for hours worked and storing it in hoursWorked
        double hoursWorked = scanner.nextDouble();

        System.out.print("Enter hourly wage: "); // user input for hourly wage and storing it in hourlyWage
        double hourlyWage = scanner.nextDouble();

        String fullName = (lastName + "," + " "+ firstName).toUpperCase(); // Concatenating the last name and first name into uppercase.
        int ageOfRetirement = 65; // the retirement age
        int yearsToRetirement = Math.abs(ageOfRetirement - EmployeeAge); //We use Math.abs to ensure that the calculation of years left to retirement is positive.
        double dailySalary = hoursWorked * hourlyWage; // Calculating the daily salary by multiplying hours worked by hourly wage
        long finalDailySalary = Math.round(dailySalary); //this will help to round off the daily salary to the nearest whole number
        double weeklySalary = finalDailySalary * 5; // Calculating the weekly salary by multiplying the final daily salary by 5
        double monthlySalary = weeklySalary * 4; // Calculating the monthly salary by multiplying the weekly salary by 4
        double grossYearlySalary = monthlySalary * 12; // Calculating the gross yearly salary by multiplying the monthly salary by 12
        double netYearlySalary = grossYearlySalary - (0.32 * grossYearlySalary) - 1500; // Calculating the net yearly salary by deducting 32% tax and 1500 Php Government-Mandated benefits from the gross yearly salary.
        
        DecimalFormat df = new DecimalFormat("#.00"); // Creating a DecimalFormat instance to format the output into two decimal places
        df.setRoundingMode(RoundingMode.HALF_UP); // We use rounding mode to HALF_UP for the DecimalFormat instance

        System.out.println("\nEmployee Information"); // Printing our header        System.out.println("---------------------------------"); // Printing the divider line
        System.out.println("Full Name: " + fullName); // Printing the full name of the employee, stored in the variable fullName
        System.out.println("Age: " + EmployeeAge + " " + "years old"); // Printing the age of the employee adding the word “year's old”
        System.out.println("Years to Retirement: " + yearsToRetirement + " " + "years"); // Printing the number of years left to retire with adding "years" to last.
        System.out.println("Daily Salary: Php " + df.format(finalDailySalary)); // Printing the daily salary. The result must be formatted into two decimal places
        System.out.println("Weekly Salary: Php " + df.format(weeklySalary)); // Printing the weekly salary of the employee in Peso.
        System.out.println("Monthly Salary: Php " + df.format(monthlySalary)); // Printing the monthly salary of the employee in Peso.
        System.out.println("Gross Yearly Salary: Php " + df.format(grossYearlySalary)); // Printing the gross yearly salary of the employee in Peso.
        System.out.println("Net Yearly Salary: Php " + df.format(netYearlySalary)); // Printing the net yearly salary of the employee in Peso. 
        scanner.close(); // Closing the Scanner object
    }
}
