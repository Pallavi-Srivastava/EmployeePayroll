package EmployeePayroll_MultiThreading;

import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import EmployeePayroll_MultiThreading.EmployeePayrollService.IOService;

public class EmployeePayrollTest {

	private static EmployeePayrollService employeePayrollService;

	@BeforeClass
	public static void createcensusAnalyser() {
		employeePayrollService = new EmployeePayrollService();
		System.out.println("Welcome to the Employee Payroll Program.. ");
	}

	@Test
	public void given6Employees_WhenAddedDataToDB_ShouldMatchEmployeesEnteriesWithSynchronisedBlock_GetThreadSafeExecution()
			throws EmployeePayrollException, SQLException {
		EmployeePayrollData[] arrayOfEmps = { new EmployeePayrollData(0, "Ani", "M", 1000000, LocalDate.now()),
				new EmployeePayrollData(0, "Bittu", "M", 2000000, LocalDate.now()),
				new EmployeePayrollData(0, "Alka", "F", 3000000, LocalDate.now()),
				new EmployeePayrollData(0, "Shiva", "M", 4000000, LocalDate.now()),
				new EmployeePayrollData(0, "Prachi", "F", 5000000, LocalDate.now()),
				new EmployeePayrollData(0, "Binu", "M", 6000000, LocalDate.now()), };

		employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
		Instant start = Instant.now();
		employeePayrollService.addEmployeePayrollDataMultipleThreads(Arrays.asList(arrayOfEmps));
		Instant end = Instant.now();
		System.out.println("Duration without thread: " + Duration.between(start, end));
		Instant Threadstart = Instant.now();
		employeePayrollService.addEmployeeToPayRollWIthThreads(Arrays.asList(arrayOfEmps));
		Instant Threadend = Instant.now();
		System.out.println("Duration without thread: " + Duration.between(Threadstart, Threadend));
		Assert.assertEquals(16, employeePayrollService.countEnteries(IOService.DB_IO));
	}
}
