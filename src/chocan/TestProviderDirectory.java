package chocan;

/**
 * Simple test class for ProviderDirectory
 * Run this to verify ProviderDirectory works correctly
 */
public class TestProviderDirectory {
    public static void main(String[] args) {
        System.out.println("=== Testing ProviderDirectory ===\n");
        
        // Create DataCenter (provides the service data)
        DataCenter dc = new DataCenter();
        
        // Create ProviderDirectory
        ProviderDirectory directory = new ProviderDirectory(dc);
        
        // Test 1: Display to console
        System.out.println("Test 1: Display directory to console");
        System.out.println("------------------------------------");
        directory.displayProviderDirectory();
        
        // Test 2: Save to file
        System.out.println("\nTest 2: Save directory to file");
        System.out.println("-------------------------------");
        String testFilePath = "test_provider_directory.txt";
        directory.saveProviderDirectoryToFile(testFilePath);
        System.out.println("Directory saved to: " + testFilePath);
        
        System.out.println("\n=== Tests Complete ===");
    }
}

