package com.pluralsight;

/**
 * Represents one employee and their pay information.
 */

public class Employee {

    // Employee details (private so they're only changed through getters/setters)
    private int employeeId;
    private String name;
    private double hoursWorked;
    private double payRate;

    /**
     * Creates an employee with all of their details.
     */
    public Employee(int employeeId, String name, double hoursWorked, double payRate) {
        this.employeeId = employeeId;
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.payRate = payRate;
    }

    // Getters and setters: read or update each field

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    /**
     * Calculates gross pay: pay rate × hours worked.
     */
    public  double getGrossPay() {
        return payRate * hoursWorked;
    }

    /**
     * Returns the employee's details as text (useful for printing/debugging).
     */

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", hoursWorked=" + hoursWorked +
                ", payRate=" + payRate +
                '}';
    }
}
