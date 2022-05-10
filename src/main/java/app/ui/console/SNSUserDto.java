package app.ui.console;

public class SNSUserDto {

    public String strName;
    public String strSNSUserNumber;
    public String strEmail;
    public String strPassword;

    public SNSUserDto(String strName, String strSNSUserNumber , String strEmail, String strPassword) {
        this.strName = strName;
        this.strSNSUserNumber = strSNSUserNumber;
        this.strEmail=strEmail;
        this.strPassword=strPassword;
    }

    public SNSUserDto() {

    }
}
