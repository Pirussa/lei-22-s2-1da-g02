package app.domain.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class HealthcareCenterTest {

    ArrayList<String> strVaccineTypeTest = new ArrayList<>();

    HealthcareCenter hc = new HealthcareCenter("HC-2132","asdad","933398881","hasjd-2131@gmail.com",
            "933398881","www.akhjd.pt","5","9","123","133","ajkshd",
            "7337-111","kahjsda","CC-1234","asdad","asd", strVaccineTypeTest);

    @Test
    void validateHealthcareCenters(){
        strVaccineTypeTest.add("kjahsd");
        strVaccineTypeTest.add("skajdha");
        assertTrue(hc.validateHealthcareCenters());
        assertNotNull(hc);
    }

    @Test
    void validateVaccinationCenterHours() {
        assertTrue(hc.validateVaccinationCenterHours("2","4"));
        assertTrue(hc.validateVaccinationCenterHours("0","24"));
        assertFalse(hc.validateVaccinationCenterHours("4","4"));
        assertFalse(hc.validateVaccinationCenterHours("4","2"));
        assertFalse(hc.validateVaccinationCenterHours("25","2"));
        assertFalse(hc.validateVaccinationCenterHours("24","2"));
        assertFalse(hc.validateVaccinationCenterHours("0","0"));
        assertFalse(hc.validateVaccinationCenterHours("1","25"));
    }

    @Test
    void validateWebsite() {
        String[] strTopLevelDomain = {".pt",".com"};
        assertTrue(hc.validateWebsite("www.kajshdj.com",strTopLevelDomain,"www."));
        assertFalse(hc.validateWebsite("wwwkajshdj.com",strTopLevelDomain,"www."));
    }

    @Test
    void validateEmail() {
        assertTrue(hc.validateEmail("skajdhkaj@gmail.com"));
    }

    @Test
    void validatePhoneNumberAndFax() {
        assertTrue(hc.validatePhoneNumberAndFax("933398881"));
    }

    @Test
    void validateZipCode() {
        assertTrue(hc.validateZipCode("1113-112"));
    }

    @Test
    void validateSlotDuration() {
        assertTrue(hc.validateSlotDuration("231"));
    }

    @Test
    void validateVaccinesPerSlot() {
        assertTrue(hc.validateVaccinesPerSlot("21"));
    }
}