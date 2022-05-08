package app.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MassVaccinationCenterTest {

    MassVaccinationCenter mvc = new MassVaccinationCenter("HC-1213","kahsdd21","933398881","hasjd-2131@gmail.com",
            "933398881","www.akhjd.com","12","23","12","13","ajkshd",
            "7777-111","kahjsda","CC-1234","asdad");

    @Test
    void validateMassVaccinationCenters(){
        assertTrue(mvc.validateMassVaccinationCenters());
        assertNotNull(mvc);
    }

    @Test
    void validateVaccinationCenterHours() {
        assertTrue(mvc.validateVaccinationCenterHours("2","4"));
        assertTrue(mvc.validateVaccinationCenterHours("0","24"));
        assertFalse(mvc.validateVaccinationCenterHours("4","4"));
        assertFalse(mvc.validateVaccinationCenterHours("4","2"));
        assertFalse(mvc.validateVaccinationCenterHours("25","2"));
        assertFalse(mvc.validateVaccinationCenterHours("24","2"));
        assertFalse(mvc.validateVaccinationCenterHours("0","0"));
        assertFalse(mvc.validateVaccinationCenterHours("1","25"));
    }

    @Test
    void validateWebsite() {
        String[] strTopLevelDomain = {".pt",".com"};
        assertTrue(mvc.validateWebsite("www.kajshdj.com",strTopLevelDomain,"www."));
        assertFalse(mvc.validateWebsite("wwwkajshdj.com",strTopLevelDomain,"www."));
    }

    @Test
    void validateEmail() {
        assertTrue(mvc.validateEmail("skajdhkaj@gmail.com"));
    }

    @Test
    void validatePhoneNumberAndFax() {
        assertTrue(mvc.validatePhoneNumberAndFax("933398881"));
    }

    @Test
    void validateZipCode() {
        assertTrue(mvc.validateZipCode("1113-112"));
    }

    @Test
    void validateSlotDuration() {
        assertTrue(mvc.validateSlotDuration("231"));
    }

    @Test
    void validateVaccinesPerSlot() {
        assertTrue(mvc.validateVaccinesPerSlot("21"));
    }
}