package com.pocketsunited.mangopayapiwrapper.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pocketsunited.mangopayapiwrapper.model.Beneficiary;
import com.pocketsunited.mangopayapiwrapper.model.User;
import com.pocketsunited.mangopayapiwrapper.service.IBeneficiaryService;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public class BeneficiaryServiceImpl extends BaseServiceImpl implements IBeneficiaryService {

    @Override
    public Beneficiary create(User user, String ownerName, String ownerAddress, String IBAN, String BIC) {
        return create(user.getId(),ownerName,ownerAddress,IBAN,BIC);
    }

    @Override
    public Beneficiary create(Long userId, String ownerName, String ownerAddress, String IBAN, String BIC) {
        return execute("/beneficiaries",HttpMethod.POST,Beneficiary.builder().withUserId(userId).withBankAccountOwnerName(ownerName).withBankAccountOwnerAddress(ownerAddress).withBankAccountIBAN(IBAN).withBankAccountBIC(BIC).build(),new TypeReference<Beneficiary>() {});
    }

    @Override
    public boolean delete(Beneficiary beneficiary) {
        return delete(beneficiary.getId());
    }

    @Override
    public boolean delete(Long beneficiaryId) {
        return false;
    }
}
