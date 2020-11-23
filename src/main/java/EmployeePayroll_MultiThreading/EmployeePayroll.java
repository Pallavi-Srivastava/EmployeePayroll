package EmployeePayroll_MultiThreading;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class EmployeePayroll {

	public static void main(String[] args) throws EmployeePayrollException {
		String jdbcURL = "jdbc:mysql://localhost:3306/payrollservice?useSSL=false";
		String username = "root";
		String password = "lovey";
		Connection connection;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
		} catch (ClassNotFoundException e) {
			throw new EmployeePayrollException(e.getMessage(), EmployeePayrollException.ExceptionType.NoSuchClass);
		}
		listDrivers();
		try {
			System.out.println("Connecting to database:" + jdbcURL);
			connection = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("Connection is successful:" + connection);
		} catch (SQLException e) {
			throw new EmployeePayrollException(e.getMessage(),
					EmployeePayrollException.ExceptionType.DatabaseException);
		}
	}

	private static void listDrivers() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = driverList.nextElement();
			System.out.println(" " + driverClass.getClass().getName());
		}
	}
}
