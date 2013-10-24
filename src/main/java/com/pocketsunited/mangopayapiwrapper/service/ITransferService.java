package com.pocketsunited.mangopayapiwrapper.service;

import com.pocketsunited.mangopayapiwrapper.model.Transfer;
import com.pocketsunited.mangopayapiwrapper.model.User;
import com.pocketsunited.mangopayapiwrapper.model.Wallet;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public interface ITransferService {

    Transfer create(User payer, User payee, Long amount) throws SignatureException;
    Transfer create(Long payerID, Long payeeID, Long amount) throws SignatureException;

    Transfer create(Transfer.TransferBuilder transferBuilder);
}
