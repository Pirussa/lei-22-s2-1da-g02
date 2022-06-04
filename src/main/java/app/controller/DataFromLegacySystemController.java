package app.controller;

import app.domain.model.Company;
import app.domain.model.SnsUser;

import java.util.ArrayList;

public class DataFromLegacySystemController {
    private Company company = App.getInstance().getCompany();

    public ArrayList<SnsUser> getSNSUserList(){
        return company.getSnsUserList();
    }
}
