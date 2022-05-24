package mapper;

import app.domain.model.SnsUser;
import dto.SNSUserDto;

public class SNSUserMapper {

    public SNSUserDto domainToSNSUserDto(SnsUser snsUser) {
        if (snsUser == null) return null;

        SNSUserDto snsUserDtodto = new SNSUserDto(snsUser.getStrName(), snsUser.getSnsUserNumber(), snsUser.getStrEmail(), snsUser.getStrBirthDate(), snsUser.getStrPhoneNumber(), snsUser.getStrSex(), snsUser.getStrAddress(), snsUser.getStrCitizenCardNumber(), snsUser.getStrPassword());
        snsUserDtodto.takenVaccines = snsUser.getTakenVaccines();
        return snsUserDtodto;
    }

    public SnsUser SNSUserDtoToDomain(SNSUserDto snsUserDto) {
        if (snsUserDto == null) return null;

        SnsUser snsUser = new SnsUser(snsUserDto.strName, snsUserDto.strSex, snsUserDto.strBirthDate, snsUserDto.strAddress, snsUserDto.strPhoneNumber, snsUserDto.strEmail, snsUserDto.snsUserNumber, snsUserDto.strCitizenCardNumber, snsUserDto.strPassword);
        snsUser.setTakenVaccines(snsUserDto.takenVaccines);
        return snsUser;
    }
}
