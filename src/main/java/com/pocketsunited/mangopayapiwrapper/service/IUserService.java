package com.pocketsunited.mangopayapiwrapper.service;

import com.pocketsunited.mangopayapiwrapper.model.User;

import java.util.Date;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public interface IUserService {

    User createNaturalPerson(String firstName, String lastName, String email, Date birthday, String nationality, String ip, boolean canRegisterMeanOfPayment);

    User read(Long id);
}
