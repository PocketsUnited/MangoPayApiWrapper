package com.pocketsunited.mangopayapiwrapper.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pocketsunited.mangopayapiwrapper.model.Beneficiary;
import com.pocketsunited.mangopayapiwrapper.model.User;
import com.pocketsunited.mangopayapiwrapper.service.IBeneficiaryService;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public class BeneficiaryServiceImpl extends BaseServiceImpl implements IBeneficiaryService {

    private Beneficiary.BeneficiaryValidator createValidator = Beneficiary.BENEFICIARY_CREATE_VALIDATOR;

    public void setCreateValidator(Beneficiary.BeneficiaryValidator createValidator) {
        if (null != createValidator) {
            this.createValidator = createValidator;
        }
        else {
            logger.warn("'createValidator' may not be null!");
        }
    }

    @Override
    public Beneficiary create(User user, String ownerName, String ownerAddress, String IBAN, String BIC) {
        return create(user.getId(), ownerName, ownerAddress, IBAN, BIC);
    }

    @Override
    public Beneficiary create(Long userId, String ownerName, String ownerAddress, String IBAN, String BIC) {
        return executeCreateBeneficiary(Beneficiary.builder().withUserId(userId).withBankAccountOwnerName(ownerName).withBankAccountOwnerAddress(ownerAddress).withBankAccountIBAN(IBAN).withBankAccountBIC(BIC));
    }

    @Override
    public Beneficiary create(Beneficiary.BeneficiaryBuilder beneficiaryBuilder) {
        return executeCreateBeneficiary(beneficiaryBuilder);
    }

    @Override
    public Beneficiary read(Long id) {
        return execute("/beneficiaries/"+id,HttpMethod.GET,new TypeReference<Beneficiary>() {});
    }

    protected Beneficiary executeCreateBeneficiary(Beneficiary.BeneficiaryBuilder beneficiaryBuilder) {
        if (!beneficiaryBuilder.hasBeneficiaryValidator()) {
            beneficiaryBuilder.withBeneficiaryValidator(createValidator);
        }
        return execute("/beneficiaries",HttpMethod.POST,beneficiaryBuilder.build(),new TypeReference<Beneficiary>() {});
    }
}
