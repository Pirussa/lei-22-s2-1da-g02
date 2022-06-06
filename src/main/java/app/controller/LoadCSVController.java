package app.controller;

import app.domain.model.Company;
import app.domain.model.SnsUser;
import app.domain.shared.Constants;
import app.domain.shared.GenericClass;
import dto.SnsUserDto;

import java.io.NotSerializableException;
import java.util.ArrayList;

/**
 * US014
 *
 * @author 1210816@isep.ipp.pt
 */
public class LoadCSVController {
    private final Company company = App.getInstance().getCompany();
    GenericClass<SnsUser> generics = new GenericClass<>();

    /**
     * Create a sns user
     *
     * @param dto the dto
     * @return the sns user
     */
    public SnsUser createSNSUser(SnsUserDto dto) {
        return company.createSNSUser(dto);
    }

    /**
     * Saves a sns user.
     *
     * @param dto the dto
     * @return a string that confirms if the User was saved or not
     */
    public boolean saveSNSUser(SnsUserDto dto){
        return company.saveSNSUser(dto);
    }

    /**
     * Get sns user list
     *
     * @return the array list of SNS Users
     */
    public ArrayList<SnsUser> getSNSUserList(){
        return company.getSnsUserList();
    }


}
