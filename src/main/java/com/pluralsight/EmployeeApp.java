package com.pluralsight;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Payroll Calculator
 * Asks the user for an employee file to read and a payroll file to create.
 * Reads each employee, calculates their gross pay, and writes the results
 * to the new payroll file.
 * - .csv file  -> id|name|gross pay
 * - .json file -> a JSON array of { "id", "name", "grossPay" } objects (bonus)
 */
public class EmployeeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for the file to read and the file to create
        System.out.print("Enter the file to read: ");
        String inputFile = scanner.nextLine();

        System.out.print("Enter the name of the payroll file to create: ");
        String outputFile = scanner.nextLine();

        // Check the output format once and remember it
        boolean isJson = outputFile.endsWith(".json");

        // Try: reading or writing a file can fail (e.g. file not found)
        try {
            // Open the input file for reading
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            // Create the new payroll file for writing
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            // Before the loop: JSON starts with [, CSV starts with a header line
            if (isJson) {
                writer.write("[\n");
            } else {
                writer.write("id|name|gross pay\n");
            }

            // Skip the input file's header line (id|name|hours-worked|pay-rate)
            reader.readLine();
            String line;

            // Tracks the first JSON item (no comma before it)
            boolean first = true;

            // Read each employee line until the end of the file
            while ((line = reader.readLine()) != null) {

                // Split the line on | into separate fields
                String[] fields = line.split("\\|");

                // Convert each field from text into the right data type
                int employeeId = Integer.parseInt(fields[0]);
                String name = fields[1];
                double hoursWorked = Double.parseDouble(fields[2]);
                double payRate = Double.parseDouble(fields[3]);

                // Create an Employee object from this line's data
                Employee employee = new Employee(employeeId, name, hoursWorked, payRate);

                if (isJson) {
                    // Comma before every item except the first
                    if (!first) {
                        writer.write(",\n");
                    }
                    // Build the JSON object and write it
                    String item = String.format("{ \"id\": %d, \"name\" : \"%s\", \"grossPay\" : %.2f }",
                            employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
                    writer.write(item);
                    first = false;
                } else {
                    // Build the CSV line and write it
                    String row = String.format("%d|%s|%.2f\n",
                            employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
                    writer.write(row);
                }
            }

            // After the loop: close the JSON array
            if (isJson) {
                writer.write("\n]");
            }

            // Close both files (closing the writer saves the data)
            reader.close();
            writer.close();

            System.out.println("Payroll file has been created!");

        } catch (Exception e) {
            // Runs if a file can't be read/written or a line has bad data
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}