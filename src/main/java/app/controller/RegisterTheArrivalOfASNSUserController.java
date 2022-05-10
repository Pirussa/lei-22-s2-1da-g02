package app.controller;

import app.domain.model.Company;

public class RegisterTheArrivalOfASNSUserController {

    private Company company = App.getInstance().getCompany();

    public RegisterTheArrivalOfASNSUserController() {}

    public String checkAppointment(int SNSNumber) {
        return company.checkAppointment(SNSNumber);
    }

    public void registerArrival(int SNSNumber) {
        company.registerArrival(SNSNumber);
    }
}
