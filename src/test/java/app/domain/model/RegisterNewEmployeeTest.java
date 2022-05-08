package app.domain.model;

import app.controller.App;
import app.ui.console.utils.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegisterNewEmployeeTest {

    private static List<Employee> employees;

    private Company comp = App.getInstance().getCompany();

    @Test
    public void registerNewEmployee() {
        Employee emp = new Employee("NR-12345", "Employee", "Rua / 2222-222 / Portugal", "912345678", "employee@isep.ipp.pt", "14268862 2 ZX8", "OWc3GK1");
        assertTrue(emp.validateEmployee());
    }

    @Test
    public void registerNullNewEmployee() {
        assertFalse(new Employee("", "", "", "", "", "", "").validateEmployee());
    }

    @Test
    public void registerNullNameNewEmployee() {
        assertFalse(new Employee("NR-12345", "", "Rua / 1111-111 / Portugal", "912345678", "employee@isep.ipp.pt", "35619927 4 ZX6", "AAA22aa").validateEmployee());
    }

    @Test
    void registerNullAddressNewEmployee() {
        assertFalse(new Employee("NR-12345", "Employee", "", "912345678", "employee@isep.ipp.pt", "35619927 4 ZX6", "BBB33bb").validateEmployee());

    }

    @Test
    void registerNullPhoneNumberNewEmployee() {
        assertFalse(new Employee("NR-12345", "Employee", "Rua / 1111-111 / Portugal", "", "employee@isep.ipp.pt", "35619927 4 ZX6", "BBB33bb").validateEmployee());
    }

    @Test
    void registerNullEmailNewEmployee() {
        assertFalse(new Employee("NR-12345", "Employee", "Rua / 1111-111 / Portugal", "912345678", "", "35619927 4 ZX6", "BBB33bb").validateEmployee());
    }

    @Test
    void registerNullCitizenCardNumberNewEmployee() {
        assertFalse(new Employee("NR-12345", "Employee", "Rua / 1111-111 / Portugal", "912345678", "employee@isep.ipp.pt", "", "BBB33bb").validateEmployee());
    }

    @Test
    void registerInvalidAddressNewEmployee() {
        assertFalse(new Employee("NR-12345", "Employee", "Rua / 11111111 / Portugal", "912345678", "employee@isep.ipp.pt", "35619927 4 ZX6", "BBB33bb").validateEmployee());
    }

    @Test
    void registerInvalidPhoneNumberNewEmployee() {
        assertFalse(new Employee("NR-12345", "Employee", "Rua / 1111-111 / Portugal", "812345678", "employee@isep.ipp.pt", "35619927 4 ZX6", "BBB33bb").validateEmployee());
    }

    @Test
    void registerInvalidEmailNewEmployee() {
        assertFalse(new Employee("NR-12345", "Employee", "Rua / 1111-111 / Portugal", "912345678", "employee@unknown", "35619927 4 ZX6", "BBB33bb").validateEmployee());
    }

    @Test
    void registerInvalidCitizenCardNumberNewEmployee() {
        assertFalse(new Employee("NR-12345", "Employee", "Rua / 1111-111 / Portugal", "912345678", "employee@isep.ipp.pt", "80526907 4 ZZ2", "BBB33bb").validateEmployee());
    }
}