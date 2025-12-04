package chocan;

import java.time.LocalDate;

/**
 * Simple test class for SummaryReport
 */
public class TestSummaryReport {
    public static void main(String[] args) {
        System.out.println("=== Testing SummaryReport ===\n");

        // Create DataCenter
        DataCenter dc = new DataCenter();

        // Add test providers
        dc.addProvider("John", "Smith", "555-1234", "123 Main St", "Portland", "OR", "97201", "123456789");
        dc.addProvider("Jane", "Doe", "555-5678", "456 Oak Ave", "Seattle", "WA", "98101", "987654321");
        System.out.println("Added 2 test providers.");

        // Add some test service records (within the last week)
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate threeDaysAgo = today.minusDays(3);

        // Add test records: providerNumber, memberNumber, serviceCode, fee, date
        dc.addServiceRecord(new ServiceRecord("123456789", "111111111", "000001", 12.99, today));
        dc.addServiceRecord(new ServiceRecord("123456789", "222222222", "000002", 8.99, yesterday));
        dc.addServiceRecord(new ServiceRecord("123456789", "333333333", "000003", 29.99, threeDaysAgo));
        dc.addServiceRecord(new ServiceRecord("987654321", "111111111", "000001", 12.99, today));
        dc.addServiceRecord(new ServiceRecord("987654321", "444444444", "000002", 8.99, yesterday));
        System.out.println("Added 5 test service records.\n");

        // Print the summary report
        SummaryReport.printSummaryReport(dc);

        System.out.println("\n=== Test Complete ===");
    }
}

