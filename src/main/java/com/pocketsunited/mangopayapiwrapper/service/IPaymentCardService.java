package com.pocketsunited.mangopayapiwrapper.service;

import com.pocketsunited.mangopayapiwrapper.model.PaymentCard;
import com.pocketsunited.mangopayapiwrapper.model.User;

import java.util.List;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public interface IPaymentCardService {

    PaymentCard read(Long id);

    PaymentCard createPaymentCard(User owner);
    PaymentCard createPaymentCard(Long ownerId);

    PaymentCard createPaymentCard(User owner, String returnUrl);
    PaymentCard createPaymentCard(Long ownerId, String returnUrl);

    PaymentCard create(Long ownerId, String returnUrl, String templateUrl);

    PaymentCard create(Long ownerId, String returnUrl, String templateUrl, String culture);
//
//    PaymentCard createPaymentCard(User owner, String cardNumber);
//    PaymentCard createPaymentCard(Long ownerId, String cardNumber);

    List<PaymentCard> cardsByUser(User owner);
    List<PaymentCard> cardsByUser(Long ownerId);
}