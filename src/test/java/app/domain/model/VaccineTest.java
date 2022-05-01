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
    private static List<VaccineAndAdminProcessDto> dtos;
    private Vaccine object;
    private Company c = App.getInstance().getCompany();


   // @BeforeAll
  //  public static void setup() {
  //      dtos = new LinkedList<>();
  //      VaccineAndAdminProcessDto dto1 = new VaccineAndAdminProcessDto();
  //      dto1.id = 1;
  //      dto1.name = "Teste";
  //      dto1.brand = "JUNIT";
  //      dto1.vt = new VaccineType("0", "sss", "l");
  //      //   dto1.
  //      //  dtos.add(dto1);
  //  }
//
//
  //  @Test
  //  public void validateVacine() {
  //      Vaccine v = new Vaccine("V", 1, "l", new AdministrationProcess());
//
  //      assertTrue(v.validateVaccine());
//
  //  }
}