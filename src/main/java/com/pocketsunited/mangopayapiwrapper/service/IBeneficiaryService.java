package com.pocketsunited.mangopayapiwrapper.service;

import com.pocketsunited.mangopayapiwrapper.model.Beneficiary;
import com.pocketsunited.mangopayapiwrapper.model.User;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public interface IBeneficiaryService {

    Beneficiary create(User user, String ownerName, String ownerAddress, String IBAN, String BIC);

    Beneficiary create(Long userId, String ownerName, String ownerAddress, String IBAN, String BIC);

    Beneficiary create(Beneficiary.BeneficiaryBuilder beneficiaryBuilder);

    Beneficiary read(Long id);
}
