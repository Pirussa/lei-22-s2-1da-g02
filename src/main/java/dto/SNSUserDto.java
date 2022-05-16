package dto;

public class SNSUserDto {

    public String strName;
    public int snsUserNumber;
    public String strEmail;
    public String strBirthDate;
    public String strPhoneNumber;
    public String strSex;
    public String strAddress;
    public String strCitizenCardNumber;
    public String strPassword;

    public SNSUserDto(String strName, int snsUserNumber , String strEmail, String strBirthDate,String strPhoneNumber,
                      String strSex, String strAddress, String strCitizenCardNumber, String strPassword) {
        this.strName = strName;
        this.snsUserNumber = snsUserNumber;
        this.strEmail=strEmail;
        this.strBirthDate = strBirthDate;
        this.strPhoneNumber=strPhoneNumber;
        this.strSex=strSex;
        this.strAddress=strAddress;
        this.strCitizenCardNumber=strCitizenCardNumber;
        this.strPassword=strPassword;
    }

    public SNSUserDto() {

    }
}
