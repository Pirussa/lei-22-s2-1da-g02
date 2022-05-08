package app.domain.model;

import app.controller.App;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


/**
 * US011 - Get a list of Employees with a given role.
 *
 * @author João Leitão <1211063@isep.ipp.pt>
 */

class GetListOfEmployeesTest {


    Company company = App.getInstance().getCompany();


    @Test

    /**
     * Verifies if the lists of employees with a given role are well filled.
     */

    public void fillListOfEmployeesWithAGivenRole() {
        Employee nurse = new Nurse("NR-12345", "Nurse", "Rua / 1111-111 / Portugal", "912345678", "nurse@gmail.com", "11960343 8 ZW1", "AAA11aa");
        Employee receptionist = new Receptionist("RC-12345", "Receptionist", "Rua / 2222-222 / Portugal", "913456789", "receptionist@gmail.com", "14268862 2 ZX8", "BBB22bb");
        Employee centreCoordinator = new CenterCoordinator("CC-12345", "Centre Coordinator", "Rua / 3333-333 / Portugal", "914567894", "centrecoordinator@gmail.com", "35619927 4 ZX6", "CCC33cc");

        Company.getEmployees().add(nurse);
        Company.getEmployees().add(receptionist);
        Company.getEmployees().add(centreCoordinator);

        company.fillListOfEmployeesWithAGivenRole();
        assertFalse(company.getNurseList().isEmpty());
        assertFalse(company.getReceptionistList().isEmpty());
        assertFalse(company.getCentreCoordinatorList().isEmpty());
    }
}