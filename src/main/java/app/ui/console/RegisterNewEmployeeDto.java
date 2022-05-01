package app.ui.console;

public class RegisterNewEmployeeDto {

    public RegisterNewEmployeeDto() {
    }

    public String role;

    public String id;

    public String name;

    public String address;

    public int phoneNumber;

    public String email;

    public int citizenCardNumber;

    public String password;

    public RegisterNewEmployeeDto(String role, String id, String name, String address, int phoneNumber, String email, int citizenCardNumber, String password) {
        this.role = role;
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.citizenCardNumber = citizenCardNumber;
        this.password = password;
    }
}

