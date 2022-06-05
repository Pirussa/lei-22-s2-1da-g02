package dto;

import pt.isep.lei.esoft.auth.domain.model.Email;

public class RegisterNewEmployeeDto {


    public String id;

    public String name;

    public String address;

    public String phoneNumber;

    public Email email;

    public String citizenCardNumber;

    public String password;

    public RegisterNewEmployeeDto() {
    }

    public RegisterNewEmployeeDto(String id, String name, String address, String phoneNumber, Email email, String citizenCardNumber, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.citizenCardNumber = citizenCardNumber;
        this.password = password;
    }
}

