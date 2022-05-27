package app.domain.model;

import dto.SNSUserDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnsUserTest {

    @Test
    void validateSNSUserWithNullEmail() {
        SnsUser user = new SnsUser("User Default2","", "29/10/2000", "Default # 4000-002 # Default", "915604430", "", 123332998, "35906158 3 ZZ5", "AAA22aa");
    assertFalse(user.validateSNSUser());
    }

    @Test void validateSNSUserWithInvalidEmail(){
        SnsUser user = new SnsUser("User Default2","", "29/10/2000", "Default # 4000-002 # Default", "915604430", "akhdakdh@gmail.pt", 123332998, "35906158 3 ZZ5", "AAA22aa");
        assertFalse(user.validateSNSUser());
    }

    @Test void validateSNSUserWithValidEmail(){
        SnsUser user = new SnsUser("User Default2","", "29/10/2000", "Default # 4000-002 # Default", "915604430", "akhdakdh@gmail.com", 123332998, "35906158 3 ZZ5", "AAA22aa");
        assertTrue(user.validateSNSUser());
    }


}