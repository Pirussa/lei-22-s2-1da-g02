package app.domain.model;

import app.controller.App;
import app.controller.SpecifyVaccineAndAdminProcessController;
import app.ui.console.SpecifyVaccineAndAdminProcessUI;
import app.ui.console.VaccineAndAdminProcessDto;
import app.ui.console.utils.Utils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VaccineTest {
    private static List<Vaccine> vaccines;

    private Company c = App.getInstance().getCompany();

    @Test
    /**
     * Verifies if a Vaccine with all the right attributes is created
     */
    public void createVaccine() {
        Vaccine v = Utils.createVaccine("Test", 12, "Brand1", 20.0, 12, 15, 16);
        assertTrue(v.validateVaccine());

    }

    @Test
    /**
     * Verifies if a Vaccine with all the attributes null is created
     */
    public void createNullVaccine() {
        assertFalse(Utils.createVaccine("", 0, "", 20.0, 12, 15, 12).validateVaccine());
    }

    @Test
    /**
     * Verifies if a Vaccine with empty name is created
     */
    public void createVaccineWithNullName() {
        assertFalse(Utils.createVaccine("", 1234, "Brand", 20.0, 12, 15, 12).validateVaccine());
    }

    @Test
    /**
     * Verifies if a Vaccine with invalid id is created
     */
    public void createVaccineWithNullId() {
        assertFalse(Utils.createVaccine("Test", 0, "Brand", 20.0, 12, 15, 12).validateVaccine());
    }

    @Test
    /**
     * Verifies if a Vaccine with empty brand is created
     */
    public void createVaccineWithNullBrand() {
        assertFalse(Utils.createVaccine("Test", 123, "", 20.0, 12, 15, 12).validateVaccine());
    }

}