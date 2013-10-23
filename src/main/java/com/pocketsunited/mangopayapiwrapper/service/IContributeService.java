package com.pocketsunited.mangopayapiwrapper.service;

import com.pocketsunited.mangopayapiwrapper.model.Contribution;
import com.pocketsunited.mangopayapiwrapper.model.PaymentCard;
import com.pocketsunited.mangopayapiwrapper.model.User;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public interface IContributeService {

    Contribution immediateContribution(User user, PaymentCard paymentCard, Long amount);
    Contribution immediateContribution(Long userId, Long paymentCardId, Long amount);
}
