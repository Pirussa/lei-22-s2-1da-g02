package app.ui.console;

public class SNSUserDto {

    public String strName;
    public String strSNSUserNumber;
    public String strEmail;
    public String strBirthDate;
    public String strPhoneNumber;
    public String strSex;
    public String strAddress;
    public String strCitizenCardNumber;
    public String strPassword;

    public SNSUserDto(String strName, String strSNSUserNumber , String strEmail, String strBirthDate,String strPhoneNumber,
                      String strSex, String strAddress, String strCitizenCardNumber, String strPassword) {
        this.strName = strName;
        this.strSNSUserNumber = strSNSUserNumber;
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
