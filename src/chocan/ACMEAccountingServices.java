package chocan;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class ACMEAccountingServices{
	
	private Vector<Member> members = new Vector<>();
    private Vector<Member> suspendedMembers = new Vector<>(); //ACME's list of suspended members, should talk to VerifyMember
    
    public ACMEAccountingServices() {
    	
    	//Reads from "members.txt" and "supendedmembers.txt"
    	try (InputStream input = ACMEAccountingServices.class.getResourceAsStream("members.txt")) {
            // Check if the input stream was found
            if (input == null) {
                System.out.println("Error: The file was not found in the same package.");
                return;
            }

            // Use Scanner to read the content line by line
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
            	members.add(readMember(scanner.nextLine()));
            }
            scanner.close();

        } catch (IOException e) {
            // Handle potential IO exceptions
            e.printStackTrace();
        }
 
        
    	try (InputStream input = ACMEAccountingServices.class.getResourceAsStream("suspendedmembers.txt")) {
            // Check if the input stream was found
            if (input == null) {
                System.out.println("Error: The file was not found in the same package.");
                return;
            }

            // Use Scanner to read the content line by line (same as above)
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
            	suspendedMembers.add(readMember(scanner.nextLine()));
            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    	
    }
    

    public void addMember(String firstName, String lastName, String phoneNumber, String address, String city, String state, String zipCode, String email, String number) {
    	members.add(new Member(firstName, lastName, phoneNumber, address, city, state, zipCode, email, number));
    }
    
    public void addMember(Member newMember) {
    	members.add(newMember);
    }
    
    public Member[] getMembers() {
    	return members.toArray(new Member[members.size()]);
    }
    
    public Member[] getSuspendedMembers() {
    	return suspendedMembers.toArray(new Member[suspendedMembers.size()]);
    }
    
    public void suspendMember() { 
    	for(int i = 0; i < members.size(); i++) {
    		
    	}
    }
    
    public void unsuspendMember() {
    	
    }
    
    private Member readMember(String line) {
    	
    	String[] parts = line.split("_");
    	
    	if(parts.length == 8){
    		Member returnMember = new Member(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8]);
    		return returnMember;
    	}
		
		return null;
    	
    }
    
    public void saveInfo() {
    	
    	 writeInfo("members.txt");
    	 writeInfo("suspendedmembers.txt");
    }
    
    private void writeInfo(String fileName) {
    	
    	Vector<Member> currMembers = null;
    	
    	if(fileName == "members.txt") {
    		currMembers = members;
    	}
    	if(fileName == "suspendedmembers.txt") {
    		currMembers = suspendedMembers;
    	}
    	
    	String relativePath = "src/chocan/" + fileName;

        try (FileWriter fw = new FileWriter(relativePath, false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
        	
        	for(int i = 0; i < currMembers.size(); i++) {
        		out.println(currMembers.get(i).returnInfo());
        	}

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
