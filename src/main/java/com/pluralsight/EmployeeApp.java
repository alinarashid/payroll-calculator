package com.pluralsight;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Payroll Calculator
 * Reads employee data from employees.csv, creates an Employee object
 * for each line, and prints each employee's ID, name and gross pay.
 */

public class EmployeeApp {
    public static void main(String[] args) {

        // try: reading the file can fail (e.g. file not found)
        try{
            // Open employees.csv for reading
            BufferedReader reader = new BufferedReader(new FileReader("employees.csv"));

            // Skip the header line (id|name|hours-worked|pay-rate)
            reader.readLine();
            String line;

            // Read each employee line until the end of the file
            while ((line = reader.readLine()) !=null){

                // Split the line on | into separate fields
                String[] fields = line.split("\\|");

                // Convert each field from text into the right data type
                int employeeId = Integer.parseInt(fields[0]);
                String name = fields[1];
                double hoursWorked = Double.parseDouble(fields[2]);
                double payRate = Double.parseDouble(fields[3]);

                // Create an Employee object from this line's data
                Employee employee = new Employee(employeeId, name, hoursWorked, payRate);

                // Print Id, name and gross pay (money format with 2 decimals)
                System.out.printf("%d - %s - $%.2f%n", employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
            }
            // Close the file
            reader.close();

        } catch (Exception e){
            // Runs if the file can't be read or a line has bad data
            System.out.println("Could not read employee file");
        }
    }
}
