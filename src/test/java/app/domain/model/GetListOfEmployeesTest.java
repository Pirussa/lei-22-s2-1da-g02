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
     * Verifies if the list of nurses is filled.
     */

    public void fillListOfNurses() {
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


    @Test
    /**
     * Verifies if the list of nurses is empty.
     */

    public void fillListOfNursesFalse() {
        Employee nurse = new Nurse("NR-12345", "Nurse", "Rua / 1111-111 / Portugal", "912345678", "nurse@gmail.com", "11960343 8 ZW1", "AAA11aa");

        Company.getEmployees().add(nurse);

        company.fillListOfEmployeesWithAGivenRole();

        assertTrue(company.getNurseList().isEmpty());

    }


    @Test
    /**
     * Verifies if the list of receptionists is filled.
     */

    public void fillListOfReceptionists() {
        Employee receptionist = new Receptionist("RC-12345", "Receptionist", "Rua / 2222-222 / Portugal", "913456789", "receptionist@gmail.com", "14268862 2 ZX8", "BBB22bb");

        Company.getEmployees().add(receptionist);

        company.fillListOfEmployeesWithAGivenRole();

        assertFalse(company.getReceptionistList().isEmpty());

    }

    @Test
    /**
     * Verifies if the list of receptionists is empty.
     */

    public void fillListOfReceptionistsFalse() {
        Employee receptionist = new Receptionist("RC-12345", "Receptionist", "Rua / 2222-222 / Portugal", "913456789", "receptionist@gmail.com", "14268862 2 ZX8", "BBB22bb");

        Company.getEmployees().add(receptionist);

        company.fillListOfEmployeesWithAGivenRole();

        assertTrue(company.getReceptionistList().isEmpty());

    }

    @Test
    /**
     * Verifies if the list of centre coordinators is filled
     */

    public void fillListOfCentreCoordinators() {
        Employee centreCoordinator = new CenterCoordinator("CC-12345", "Centre Coordinator", "Rua / 3333-333 / Portugal", "914567894", "centrecoordinator@gmail.com", "35619927 4 ZX6", "CCC33cc");

        Company.getEmployees().add(centreCoordinator);

        company.fillListOfEmployeesWithAGivenRole();

        assertFalse(company.getCentreCoordinatorList().isEmpty());

    }

    @Test
    /**
     * Verifies if the list of centre coordinators is empty.
     */
    public void fillListOfCentreCoordinatorsFalse() {
        Employee centreCoordinator = new CenterCoordinator("CC-12345", "Centre Coordinator", "Rua / 3333-333 / Portugal", "914567894", "centrecoordinator@gmail.com", "35619927 4 ZX6", "CCC33cc");

        Company.getEmployees().add(centreCoordinator);

        company.fillListOfEmployeesWithAGivenRole();

        assertTrue(company.getCentreCoordinatorList().isEmpty());

    }

}