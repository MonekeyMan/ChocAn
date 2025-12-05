package chocan;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public abstract class readAndWritable {
    
    public abstract void saveInfo() ;
    
    public abstract void writeInfo(String fileName) ;
    
    protected void writeMember(String path, Vector<Member> members) {

        try (FileWriter fw = new FileWriter(path, false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
        	
        	for(int i = 0; i < members.size(); i++) {

        		out.println(members.get(i).returnInfo());
        	}

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    protected void writeProvider(String path, Vector<Provider> providers) {

        try (FileWriter fw = new FileWriter(path, false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
        	
        	for(int i = 0; i < providers.size(); i++) {

        		out.println(providers.get(i).returnInfo());
        	}

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    protected void writeManager(String path, Vector<Manager> managers) {

        try (FileWriter fw = new FileWriter(path, false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
        	
        	for(int i = 0; i < managers.size(); i++) {

        		out.println(managers.get(i).returnInfo());
        	}

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    protected Vector<Member> readMembers(String fileName){
    	
    	Vector<Member> members = new Vector<>();
    	
    	try (InputStream input = readAndWritable.class.getResourceAsStream(fileName)) {
            // Check if the input stream was found
            if (input == null) {
                System.out.println("Error 1: The file was not found in the same package.");
                return null;
            }

            // Use Scanner to read the content line by line
            // Edited by Wheeler Knight on 12/4/2025 - Skip null entries from empty lines
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
            	String line = scanner.nextLine().trim();
            	if (!line.isEmpty()) {
            		Member m = readMemberLine(line);
            		if (m != null) members.add(m);
            	}
            }
            scanner.close();
            
            return members;

        } catch (IOException e) {
            // Handle potential IO exceptions
            e.printStackTrace();
        }
    	
    	return null;
    }
    
    protected Vector<Provider> readProviders(String fileName){
    	
    	Vector<Provider> provider = new Vector<>();
    	
    	try (InputStream input = readAndWritable.class.getResourceAsStream(fileName)) {
            // Check if the input stream was found
            if (input == null) {
                System.out.println("Error 1: The file was not found in the same package.");
                return null;
            }

            // Use Scanner to read the content line by line
            // Edited by Wheeler Knight on 12/4/2025 - Skip null entries from empty lines
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
            	String line = scanner.nextLine().trim();
            	if (!line.isEmpty()) {
            		Provider p = readProviderLine(line);
            		if (p != null) provider.add(p);
            	}
            }
            scanner.close();
            
            return provider;

        } catch (IOException e) {
            // Handle potential IO exceptions
            e.printStackTrace();
        }
    	
    	return null;
    }
    
    protected Vector<Manager> readManagers(String fileName){
    	
    	Vector<Manager> manager = new Vector<>();
    	
    	try (InputStream input = readAndWritable.class.getResourceAsStream(fileName)) {
            // Check if the input stream was found
            if (input == null) {
                System.out.println("Error 1: The file was not found in the same package.");
                return null;
            }

            // Use Scanner to read the content line by line
            // Edited by Wheeler Knight on 12/4/2025 - Skip null entries from empty lines
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
            	String line = scanner.nextLine().trim();
            	if (!line.isEmpty()) {
            		Manager m = readManagerLine(line);
            		if (m != null) manager.add(m);
            	}
            }
            scanner.close();
            
            return manager;

        } catch (IOException e) {
            // Handle potential IO exceptions
            e.printStackTrace();
        }
    	
    	return null;
    }
    
    protected Member readMemberLine(String line) {
    	
    	String[] parts = line.split("_");
    	
    	if(parts.length == 9){
    		Member returnMember = new Member(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8]);
    		return returnMember;
    	}
		
		return null;
    	
    }
    
    // Edited by Wheeler Knight on 12/4/2025 - Fixed array bounds check from 7 to 8 to match actual data format
    protected Provider readProviderLine(String line) {
    	
    	String[] parts = line.split("_");
    	
    	if(parts.length == 8){
    		Provider returnProvider = new Provider(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]);
    		return returnProvider;
    	}
		
		return null;
    	
    }
    
    // Edited by Wheeler Knight on 12/4/2025 - Fixed array bounds check from 7 to 8 to match actual data format
    protected Manager readManagerLine(String line) {
    	
    	String[] parts = line.split("_");
    	
    	if(parts.length == 8){
    		Manager returnManager = new Manager(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]);
    		return returnManager;
    	}
		
		return null;
    	
    }
    
    // Written by Wheeler Knight on 12/4/2025 - Added ServiceRecord persistence
    protected void writeServiceRecords(String path, Vector<ServiceRecord> records) {
        try (FileWriter fw = new FileWriter(path, false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
        	
        	for(int i = 0; i < records.size(); i++) {
        		ServiceRecord sr = records.get(i);
        		// Format: providerNumber_memberNumber_serviceCode_serviceFee_serviceDate
        		String dateStr = sr.getServiceDate() != null ? sr.getServiceDate().toString() : "";
        		out.println(sr.getProviderNumber() + "_" + sr.getMemberNumber() + "_" + 
        		           sr.getServiceCode() + "_" + sr.getServiceFee() + "_" + dateStr);
        	}

        } catch (IOException e) {
            System.err.println("Error writing service records to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Written by Wheeler Knight on 12/4/2025 - Added ServiceRecord reading
    protected Vector<ServiceRecord> readServiceRecords(String fileName){
    	Vector<ServiceRecord> records = new Vector<>();
    	
    	try (InputStream input = readAndWritable.class.getResourceAsStream(fileName)) {
            if (input == null) {
                return records; // Return empty vector if file doesn't exist
            }

            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
            	String line = scanner.nextLine().trim();
            	if (!line.isEmpty()) {
            		ServiceRecord sr = readServiceRecordLine(line);
            		if (sr != null) records.add(sr);
            	}
            }
            scanner.close();
            
            return records;

        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	return records;
    }
    
    // Written by Wheeler Knight on 12/4/2025 - Parse service record line
    protected ServiceRecord readServiceRecordLine(String line) {
    	String[] parts = line.split("_");
    	
    	if(parts.length >= 5){
    		try {
    			String providerNumber = parts[0];
    			String memberNumber = parts[1];
    			String serviceCode = parts[2];
    			double serviceFee = Double.parseDouble(parts[3]);
    			java.time.LocalDate serviceDate = null;
    			if (!parts[4].isEmpty()) {
    				serviceDate = java.time.LocalDate.parse(parts[4]);
    			}
    			return new ServiceRecord(providerNumber, memberNumber, serviceCode, serviceFee, serviceDate);
    		} catch (Exception e) {
    			System.err.println("Error parsing service record: " + line);
    		}
    	}
		
		return null;
    }
    
    // Written by Wheeler Knight on 12/4/2025 - Added ServiceRequest persistence
    protected void writeServiceRequests(String path, Vector<ServiceRequest> requests) {
        try (FileWriter fw = new FileWriter(path, false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
        	
        	for(int i = 0; i < requests.size(); i++) {
        		ServiceRequest sr = requests.get(i);
        		// Format: memberNumber_providerName_serviceType
        		out.println(sr.member.getCard().getMemberNumber() + "_" + sr.providerName + "_" + sr.serviceType);
        	}

        } catch (IOException e) {
            System.err.println("Error writing service requests to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
