package com.pocketsunited.mangopayapiwrapper.service;

import com.pocketsunited.mangopayapiwrapper.model.Transfer;
import com.pocketsunited.mangopayapiwrapper.model.User;
import com.pocketsunited.mangopayapiwrapper.model.Wallet;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public interface ITransferService {

    Transfer transferMoneyFromPersonalAccountToPersonalAccount(User payer, User payee, Long amount) throws SignatureException;
    Transfer transferMoneyFromPersonalAccountToPersonalAccount(Long payerID, Long payeeID, Long amount) throws SignatureException;

    Transfer transferMoneyFromPersonalAccountToWallet(User payer, Wallet payeeWallet, Long amount) throws SignatureException;
    Transfer transferMoneyFromPersonalAccountToWallet(Long payerId, Long payeeWalletId, Long amount) throws SignatureException;

    Transfer transferMoneyFromWalletToPersonalAccount(Wallet payer, User payee, Long amount) throws SignatureException;
    Transfer transferMoneyFromWalletToPersonalAccount(Long payerWalletId, Long payeeId, Long amount) throws SignatureException;
}
