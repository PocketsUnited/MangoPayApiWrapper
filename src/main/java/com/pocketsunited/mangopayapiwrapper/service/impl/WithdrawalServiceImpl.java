package com.pocketsunited.mangopayapiwrapper.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pocketsunited.mangopayapiwrapper.model.Withdrawal;
import com.pocketsunited.mangopayapiwrapper.service.IWithdrawalService;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public class WithdrawalServiceImpl extends BaseServiceImpl implements IWithdrawalService {

    private Withdrawal.WithdrawalValidator createValidator = Withdrawal.WITHDRAWAL_CREATE_VALIDATOR;

    public void setCreateValidator(Withdrawal.WithdrawalValidator createValidator) {
        if (null != createValidator) {
            this.createValidator = createValidator;
        }
        else {
            logger.warn("'createValidator' may not be null!");
        }
    }

    @Override
    public Withdrawal create(Withdrawal.WithdrawalBuilder withdrawalBuilder) {
        return executeCreate(withdrawalBuilder);
    }

    @Override
    public Withdrawal read(Long id) {
        return execute("/withdrawals/"+id,HttpMethod.GET,new TypeReference<Withdrawal>() {});
    }

    protected Withdrawal executeCreate(Withdrawal.WithdrawalBuilder withdrawalBuilder) {
        if (!withdrawalBuilder.hasWithdrawalValidator()) {
            withdrawalBuilder.withWithdrawalValidator(createValidator);
        }
        return execute("/withdrawals",HttpMethod.POST,withdrawalBuilder.build(),new TypeReference<Withdrawal>() {});
    }
}
