package com.pocketsunited.mangopayapiwrapper.service;

import com.pocketsunited.mangopayapiwrapper.model.Withdrawal;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public interface IWithdrawalService {

    Withdrawal create(Withdrawal.WithdrawalBuilder withdrawalBuilder);

    Withdrawal read(Long id);
}
