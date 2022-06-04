package app.controller;

import app.domain.model.Company;
import app.domain.model.SnsUser;
import app.domain.model.Vaccine;
import app.domain.shared.Constants;
import app.domain.shared.GenericClass;
import app.ui.console.DataFromLegacySystemUI;

import java.io.NotSerializableException;
import java.util.ArrayList;
import java.util.List;

public class DataFromLegacySystemController {
    private Company company = App.getInstance().getCompany();

    public ArrayList<SnsUser> getSNSUserList(){
        return company.getSnsUserList();
    }

    public List<Vaccine> getVaccines() {
        return company.getVaccines();
    }

}
