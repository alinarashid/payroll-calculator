package com.pluralsight;
import java.io.BufferedReader;
import java.io.FileReader;

public class EmployeeApp {
    public static void main(String[] args) {

        try{
            BufferedReader reader = new BufferedReader(new FileReader("employees.csv"));

            reader.readLine();
            String line;

            while ((line = reader.readLine()) !=null){
                String[] fields = line.split("\\|");

                int employeeId = Integer.parseInt(fields[0]);
                String name = fields[1];
                double hoursWorked = Double.parseDouble(fields[2]);
                double payRate = Double.parseDouble(fields[3]);

                Employee employee = new Employee(employeeId, name, hoursWorked, payRate);

                System.out.printf("%d - %s - $%.2f%n", employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
            }
            reader.close();

        } catch (Exception e){
            System.out.println("Could not read employee file");
        }
    }
}
