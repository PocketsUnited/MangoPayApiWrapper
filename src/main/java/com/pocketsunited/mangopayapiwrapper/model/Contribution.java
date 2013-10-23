package com.pocketsunited.mangopayapiwrapper.model;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public class Contribution extends AbstractDateBase {

    Long userId;

    Long walletId;

    Long amount;

    Long clientFeeAmount = 0l;

    Long leetchiFeeAmount;

    Boolean succeeded = Boolean.FALSE;

    Boolean completed = Boolean.FALSE;

    Long paymentCardId;
}
