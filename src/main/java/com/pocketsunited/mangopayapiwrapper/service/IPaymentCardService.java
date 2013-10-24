package com.pocketsunited.mangopayapiwrapper.service;

import com.pocketsunited.mangopayapiwrapper.model.PaymentCard;
import com.pocketsunited.mangopayapiwrapper.model.User;

import java.util.List;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public interface IPaymentCardService {

    PaymentCard read(Long id);

    PaymentCard create(User owner);
    PaymentCard create(Long ownerId);

    PaymentCard create(User owner, String returnUrl);
    PaymentCard create(Long ownerId, String returnUrl);

    PaymentCard create(User owner, String returnUrl, String templateUrl);
    PaymentCard create(Long ownerId, String returnUrl, String templateUrl);

    PaymentCard create(User owner, String returnUrl, String templateUrl, String culture);
    PaymentCard create(Long ownerId, String returnUrl, String templateUrl, String culture);

    PaymentCard create(PaymentCard.PaymentCardBuilder paymentCardBuilder);

    List<PaymentCard> cardsByUser(User owner);
    List<PaymentCard> cardsByUser(Long ownerId);
}