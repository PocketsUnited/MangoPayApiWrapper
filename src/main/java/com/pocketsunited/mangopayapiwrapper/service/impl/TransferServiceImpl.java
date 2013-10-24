package com.pocketsunited.mangopayapiwrapper.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pocketsunited.mangopayapiwrapper.model.Transfer;
import com.pocketsunited.mangopayapiwrapper.model.User;
import com.pocketsunited.mangopayapiwrapper.model.Wallet;
import com.pocketsunited.mangopayapiwrapper.service.ITransferService;
import com.pocketsunited.mangopayapiwrapper.service.SignatureException;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public class TransferServiceImpl extends BaseServiceImpl implements ITransferService {

    private Transfer.TransferValidator createValidator = Transfer.TRANSFER_CREATE_VALIDATOR;

    public void setCreateValidator(Transfer.TransferValidator createValidator) {
        if (null != createValidator) {
            this.createValidator = createValidator;
        }
        else {
            logger.warn("'createValidator' may not be null!");
        }
    }

    @Override
    public Transfer create(User payer, User payee, Long amount) throws SignatureException {
        return create(payer.getId(), payee.getId(), amount);
    }

    @Override
    public Transfer create(Long payerId, Long payeeId, Long amount) throws SignatureException {
        return executeTransfer(Transfer.builder().withPayerId(payerId).withBeneficiaryId(payeeId).withAmount(amount));
    }

    @Override
    public Transfer create(Transfer.TransferBuilder transferBuilder) {
        return executeTransfer(transferBuilder);
    }

    Transfer executeTransfer(Transfer.TransferBuilder transferBuilder) {
        if (!transferBuilder.hasTransferValidator()) {
            transferBuilder.withTransferValidator(createValidator);
        }
        return execute("/transfer",HttpMethod.POST,transferBuilder.build(),new TypeReference<Transfer>() {});
    }
}
