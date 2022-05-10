package app.controller;

import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.ui.console.SNSUserDto;

import java.util.ArrayList;

public class LoadCSVController {
    private Company company = App.getInstance().getCompany();

    public SNSUser createSNSUser(SNSUserDto dto) {
        return company.createSNSUser(dto);
    }

    public void saveSNSUser(SNSUserDto dto){
        company.saveSNSUser(dto);
    }

    public ArrayList<SNSUser> getSNSUserList(){
        return company.getSNSUserList();
    }
}
