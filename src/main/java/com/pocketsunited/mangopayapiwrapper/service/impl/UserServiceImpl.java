package com.pocketsunited.mangopayapiwrapper.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pocketsunited.mangopayapiwrapper.model.User;
import com.pocketsunited.mangopayapiwrapper.service.IUserService;

import java.util.Date;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public class UserServiceImpl extends BaseServiceImpl implements IUserService {

    private User.UserValidator createValidator = User.USER_CREATE_VALIDATOR;

    private User.UserValidator modifyValidator = User.USER_MODIFY_VALIDATOR;

    public void setCreateValidator(User.UserValidator createValidator) {
        if (null != createValidator) {
            this.createValidator = createValidator;
        }
        else {
            logger.warn("'createValidator' may not be null!");
        }
    }

    public void setModifyValidator(User.UserValidator modifyValidator) {
        if (null != modifyValidator) {
            this.modifyValidator = modifyValidator;
        }
        else {
            logger.warn("'modifyValidator' may not be null!");
        }
    }

    @Override
    public User create(User.UserBuilder userBuilder) {
        return executeCreateUser(userBuilder);
    }

    @Override
    public User createNaturalPerson(String firstName, String lastName, String email, Date birthday, String nationality, String ip, boolean canRegisterMeanOfPayment) {
        return createUser(User.PersonType.NATURAL_PERSON,firstName,lastName,email,birthday,nationality,ip,canRegisterMeanOfPayment);
    }

    @Override
    public User read(Long id) {
        return execute("/users/"+id,HttpMethod.GET,new TypeReference<User>() {});
    }

    @Override
    public User modify(User.UserBuilder userBuilder) {
        if (!userBuilder.hasUserValidator()) {
            userBuilder.withUserValidator(modifyValidator);
        }
        User user = userBuilder.build();
        return execute("/users/"+user.getId(),HttpMethod.PUT,user,new TypeReference<User>() {});
    }

    User createUser(User.PersonType personType, String firstName, String lastName, String email, Date birthday, String nationality, String ip, boolean canRegisterMeanOfPayment) {
        return executeCreateUser(User.builder().withPersonType(personType).withFirstName(firstName).withLastName(lastName).withEmail(email).withBirthday(birthday).withNationality(nationality).withIp(ip).withCanRegisterMeansOfPayment(canRegisterMeanOfPayment));
    }

    User executeCreateUser(User.UserBuilder userBuilder) {
        if (!userBuilder.hasUserValidator()) {
            userBuilder.withUserValidator(createValidator);
        }
        return execute("/users",HttpMethod.POST,userBuilder.build(),new TypeReference<User>() {});
    }
}
