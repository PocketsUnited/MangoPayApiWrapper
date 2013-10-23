package com.pocketsunited.mangopayapiwrapper.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pocketsunited.mangopayapiwrapper.model.User;
import com.pocketsunited.mangopayapiwrapper.service.IUserService;

import java.util.Date;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public class UserServiceImpl extends BaseServiceImpl implements IUserService {

    @Override
    public User createNaturalPerson(String firstName, String lastName, String email, Date birthday, String nationality, String ip, boolean canRegisterMeanOfPayment) {
        return createUser(User.PersonType.NATURAL_PERSON,firstName,lastName,email,birthday,nationality,ip,canRegisterMeanOfPayment);
    }

    @Override
    public User read(Long id) {
        return execute("/users/"+id,HttpMethod.GET,new TypeReference<User>() {});
    }

    User createUser(User.PersonType personType, String firstName, String lastName, String email, Date birthday, String nationality, String ip, boolean canRegisterMeanOfPayment) {
        return executeCreateUser(User.builder().withPersonType(personType).withFirstName(firstName).withLastName(lastName).withEmail(email).withBirthday(birthday).withNationality(nationality).withIp(ip).withCanRegisterMeansOfPayment(canRegisterMeanOfPayment).build());
    }

    User executeCreateUser(User user) {
        return execute("/users",HttpMethod.POST,user,new TypeReference<User>() {});
    }
}
