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

    @Override
    public Transfer transferMoneyFromPersonalAccountToPersonalAccount(User payer, User payee, Long amount) throws SignatureException {
        return transferMoneyFromPersonalAccountToPersonalAccount(payer.getId(), payee.getId(), amount);
    }

    @Override
    public Transfer transferMoneyFromPersonalAccountToPersonalAccount(Long payerId, Long payeeId, Long amount) throws SignatureException {
        return executeTransfer(Transfer.builder().withPayerId(payerId).withBeneficiaryId(payeeId).withAmount(amount).build());
    }

    @Override
    public Transfer transferMoneyFromPersonalAccountToWallet(User payer, Wallet payeeWallet, Long amount) throws SignatureException {
        return transferMoneyFromPersonalAccountToWallet(payer.getId(), payeeWallet.getId(), amount);
    }

    @Override
    public Transfer transferMoneyFromPersonalAccountToWallet(Long payerId, Long payeeWalletId, Long amount) throws SignatureException {
        return executeTransfer(Transfer.builder().withPayerId(payerId).withBeneficiaryWalletId(payeeWalletId).withAmount(amount).build());
    }

    @Override
    public Transfer transferMoneyFromWalletToPersonalAccount(Wallet payerWallet, User payee, Long amount) throws SignatureException {
        return transferMoneyFromWalletToPersonalAccount(payerWallet.getId(), payee.getId(), amount);
    }

    @Override
    public Transfer transferMoneyFromWalletToPersonalAccount(Long payerWalletId, Long payeeId, Long amount) throws SignatureException {
        return executeTransfer(Transfer.builder().withPayerWalletId(payerWalletId).withBeneficiaryId(payeeId).withAmount(amount).build());
    }

    Transfer executeTransfer(Transfer transfer) {
        return execute("/transfer",HttpMethod.POST,transfer,new TypeReference<Transfer>() {});
    }
}
