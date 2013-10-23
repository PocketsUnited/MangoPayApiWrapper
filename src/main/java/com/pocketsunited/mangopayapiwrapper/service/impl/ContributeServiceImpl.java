package com.pocketsunited.mangopayapiwrapper.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pocketsunited.mangopayapiwrapper.model.Contribution;
import com.pocketsunited.mangopayapiwrapper.model.PaymentCard;
import com.pocketsunited.mangopayapiwrapper.model.User;
import com.pocketsunited.mangopayapiwrapper.service.IContributeService;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public class ContributeServiceImpl extends BaseServiceImpl implements IContributeService {

    @Override
    public Contribution immediateContribution(User user, PaymentCard paymentCard, Long amount) {
        return immediateContribution(user.getId(), paymentCard.getId(), amount);
    }

    @Override
    public Contribution immediateContribution(Long userId, Long paymentCardId, Long amount) {
        return execute("/immediate-contributions",HttpMethod.POST,null,new TypeReference<Contribution>() {});
    }
}
