package app.controller;

import app.domain.model.Company;
import app.domain.model.SnsUser;
import dto.SNSUserDto;

import java.util.ArrayList;

public class LoadCSVController {
    private Company company = App.getInstance().getCompany();

    public SnsUser createSNSUser(SNSUserDto dto) {
        return company.createSNSUser(dto);
    }

    public String saveSNSUser(SNSUserDto dto){
        return company.saveSNSUser(dto);
    }

    public ArrayList<SnsUser> getSNSUserList(){
        return company.getSNSUserList();
    }
}
