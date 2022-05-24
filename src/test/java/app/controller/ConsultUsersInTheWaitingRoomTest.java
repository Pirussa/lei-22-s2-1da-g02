package app.controller;

import app.domain.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * US005 - Consult the users in the waiting room of a Vaccination Center.
 *
 * @author João Leitão <1211063@isep.ipp.pt>
 */

class ConsultUsersInTheWaitingRoomTest {

    private Company company = App.getInstance().getCompany();

    /**
     * Verifies if the list of users in the waiting room of a Vaccination Center is filled.
     */
    @Test
    void listOfUsersInTheWaitingRoom() {
        SnsUser snsuser1 = new SnsUser("User Default","Male","01/01/1998","Default # 4000-000 # Default","915604428","u@gmail.com",100000000,"14698413 7 ZY7","AAA00aa");
        SnsUser snsuser2 = new SnsUser("User Default1","Male","01/01/2003","Default # 4000-001 # Default","915604429","u1@gmail.com",200000000,"16068893 0 ZX7","AAA11aa");

        VaccineType vt1 = new VaccineType("COVID", "A vaccine to prevent serious infections of the Covid-19 Virus", VaccineType.vaccineTechnologies[5]);

        Arrival arrival1 = new Arrival(100000000,vt1);
        Arrival arrival2 = new Arrival(200000000,vt1);

        VaccinationCenter vc = new VaccinationCenter("1234", "CVC Matosinhos", "915607321", "cvcmatosinhos@gmail.com", "915607321","www.cvcmatosinhos.com", "1", "9", "30", "16", "Rua do Amial", "4460-098", "Matosinhos", "CC-95634");

        vc.registerArrival(arrival1,vc);
        vc.registerArrival(arrival2,vc);

        company.getSNSUserList().add(snsuser1);
        company.getSNSUserList().add(snsuser2);


        ConsultUsersInTheWaitingRoomController ctrl = new ConsultUsersInTheWaitingRoomController();


        ctrl.listOfUsersInTheWaitingRoom(vc);
        assertFalse(ctrl.listOfUsersInTheWaitingRoom(vc).isEmpty());


    }

    /**
     * Verifies if the list of users in the waiting room of a Vaccination Center is empty.
     */

    @Test
    void listOfUsersInTheWaitingRoomFalse() {
        SnsUser snsuser1 = new SnsUser("User Default","Male","01/01/1998","Default # 4000-000 # Default","915604428","u@gmail.com",100000000,"14698413 7 ZY7","AAA00aa");
        SnsUser snsuser2 = new SnsUser("User Default1","Male","01/01/2003","Default # 4000-001 # Default","915604429","u1@gmail.com",200000000,"16068893 0 ZX7","AAA11aa");

        VaccineType vt1 = new VaccineType("COVID", "A vaccine to prevent serious infections of the Covid-19 Virus", VaccineType.vaccineTechnologies[5]);

        Arrival arrival1 = new Arrival(100000000,vt1);
        Arrival arrival2 = new Arrival(200000000,vt1);

        VaccinationCenter vc = new VaccinationCenter("1234", "CVC Matosinhos", "915607321", "cvcmatosinhos@gmail.com", "915607321","www.cvcmatosinhos.com", "1", "9", "30", "16", "Rua do Amial", "4460-098", "Matosinhos", "CC-95634");

        vc.registerArrival(arrival1,vc);
        vc.registerArrival(arrival2,vc);

        company.getSNSUserList().add(snsuser1);
        company.getSNSUserList().add(snsuser2);


        ConsultUsersInTheWaitingRoomController ctrl = new ConsultUsersInTheWaitingRoomController();


        ctrl.listOfUsersInTheWaitingRoom(vc);
        assertTrue(ctrl.listOfUsersInTheWaitingRoom(vc).isEmpty());
    }
}