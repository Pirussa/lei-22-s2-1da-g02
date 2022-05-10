package app.domain.model;

import java.util.Objects;

public class SNSUser {

    private String strName;
    private String strSNSUserNumber;
    private String strEmail;
    private String strPassword;

    private final int MAXNUMBEROFCHARSSNSUSERNUMBER = 9;

    public SNSUser(String strName, String strSNSUserNumber ,String strEmail,String strPassword) {
        this.strName = strName;
        this.strSNSUserNumber = strSNSUserNumber;
        this.strEmail=strEmail;
        this.strPassword=strPassword;
    }

    public String getStrSNSUserNumber() {
        return strSNSUserNumber;
    }

    public boolean validateEmail(String strEmail) {
        if (!strEmail.contains("@") && !strEmail.contains("."))
            return false;

        String[] emailSplitter = strEmail.split("@");
        String[] validEmailDomain = {"gmail.com", "hotmail.com", "isep.ipp.pt", "sapo.pt", "outlook.com"};

        for (int position = 0; position < validEmailDomain.length; position++) {
            if (Objects.equals(emailSplitter[1], validEmailDomain[position]))
                return true;
        }
        return false;
    }

    public boolean validatePassword(String strPassword){
        if (strPassword.length()>=8){
            return true;
        } else return false;
    }

    public boolean validateSNSUserNumber(String strSNSUserNumber){
        if (strSNSUserNumber.matches("^[0-9]*$") && strSNSUserNumber.length()==MAXNUMBEROFCHARSSNSUSERNUMBER){
            return true;
        } else return false;
    }

    public boolean validateSNSUser(){
        return strName!=null && strEmail!=null && strPassword!=null && strSNSUserNumber!=null &&
                !strPassword.isEmpty() && !strEmail.isEmpty() && !strPassword.isEmpty() && !strSNSUserNumber.isEmpty() &&
                validateEmail(strEmail) && validatePassword(strPassword) && validateSNSUserNumber(strSNSUserNumber);
    }

    @Override
    public String toString() {
        return "Name of the SNS User: " + strName + '\n' +
                "SNS User Number of the SNS User: " + strSNSUserNumber + '\n' +
                "Email of the SNS User: " + strEmail+ '\n' +
                "Password of the SNS User: " + strPassword;
    }
}
