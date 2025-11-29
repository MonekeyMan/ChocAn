package chocan;

public class Member extends Person {
    private String email;  //

    public Member(String firstName, String lastName, String number, String address, String city, String state, String zipCode, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.email = email;

        //public MemberCard GetCard();
        //public String GetEmail();
        //public static void RequestHealthService();
    }

    //NEED getters and setters
}
