package com.pocketsunited.mangopayapiwrapper.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public class Beneficiary extends AbstractDateBase {

    @JsonProperty(
            value = "UserID")
    Long userId = 0l;

    @JsonProperty(
            value = "BankAccountOwnerName")
    String bankAccountOwnerName;

    @JsonProperty(
            value = "BankAccountOwnerAddress")
    String bankAccountOwnerAddress;

    @JsonProperty(
            value = "BankAccountIBAN")
    String bankAccountIBAN;

    @JsonProperty(
            value = "BankAccountBIC")
    String bankAccountBIC;

    public Long getUserId() {
        return userId;
    }

    public String getBankAccountOwnerName() {
        return bankAccountOwnerName;
    }

    public String getBankAccountOwnerAddress() {
        return bankAccountOwnerAddress;
    }

    public String getBankAccountIBAN() {
        return bankAccountIBAN;
    }

    public String getBankAccountBIC() {
        return bankAccountBIC;
    }

    public static BeneficiaryBuilder builder() {
        return new BeneficiaryBuilder();
    }

    public static final class BeneficiaryBuilder extends Init<BeneficiaryBuilder,Beneficiary> {

        BeneficiaryBuilder() {
            super(new Beneficiary());
        }

        @Override
        BeneficiaryBuilder self() {
            return this;
        }
    }

    public static class BeneficiaryValidator {

        boolean validate(Init init) {
            return true;
        }
    }

    public static final BeneficiaryValidator BENEFICIARY_DEFAULT_VALIDATOR = new BeneficiaryValidator();

    public static final BeneficiaryValidator BENEFICIARY_CREATE_VALIDATOR = new BeneficiaryValidator() {

        @Override
        boolean validate(Init init) {
            return !init.hasId() && !init.hasCreationDate() && !init.hasUpdateDate() && init.hasBankAccountOwnerName() && init.hasValidBankAccountOwnerAddress() && init.hasValidBankAccountBIC() && init.hasValidBankAccountIBAN();
        }
    };

    static abstract class Init<T extends Init<T,U>, U extends Beneficiary> extends AbstractDateBase.Init<T,U> {

        private BeneficiaryValidator beneficiaryValidator;

        Init(U object) {
            super(object);
        }

        public T withBeneficiaryValidator(BeneficiaryValidator beneficiaryValidator) {
            if (null != beneficiaryValidator) {
                this.beneficiaryValidator = beneficiaryValidator;
            }
            else {
                logger.warn("'beneficiaryValidator' may not be null! Discarding input, 'beneficiaryValidator' is still: {}", this.beneficiaryValidator);
            }
            return self();
        }

        public T withUserId(Long userId) {
            object.userId = userId;
            return self();
        }

        public T withBankAccountOwnerName(String bankAccountOwnerName) {
            object.bankAccountOwnerName = bankAccountOwnerName;
            return self();
        }

        public T withBankAccountOwnerAddress(String bankAccountOwnerAddress) {
            object.bankAccountOwnerAddress = bankAccountOwnerAddress;
            return self();
        }

        public T withBankAccountBIC(String bankAccountBIC) {
            object.bankAccountBIC = bankAccountBIC;
            return self();
        }

        public T withBankAccountIBAN(String bankAccountIBAN) {
            object.bankAccountIBAN = bankAccountIBAN;
            return self();
        }

        public boolean hasBeneficiaryValidator() {
            return null != beneficiaryValidator;
        }

        public boolean hasUserId() {
            return null != object.getUserId();
        }

        public boolean hasValidUserId() {
            return hasUserId() && 0l < object.getUserId();
        }

        public boolean hasBankAccountOwnerName() {
            return null != object.getBankAccountOwnerName() && !object.getBankAccountOwnerName().isEmpty();
        }

        public boolean hasBankAccountOwnerAddress() {
            return null != object.getBankAccountOwnerAddress() && !object.getBankAccountOwnerAddress().isEmpty();
        }

        public boolean hasValidBankAccountOwnerAddress() {
            //TODO Maybe implement some more sophisticated address checking
            return hasBankAccountOwnerAddress();
        }

        public boolean hasBankAccountBIC() {
            return null != object.getBankAccountBIC() && !object.getBankAccountBIC().isEmpty();
        }

        public boolean hasValidBankAccountBIC() {
            // TODO Implement validation check for BIC
            return hasBankAccountBIC();
        }

        public boolean hasBankAccountIBAN() {
            return null != object.getBankAccountIBAN() && !object.getBankAccountIBAN().isEmpty();
        }

        public boolean hasValidBankAccountIBAN() {
            // TODO Implement validation check for IBAN
            return hasBankAccountIBAN();
        }

        @Override
        boolean validate() {
            return super.validate() && (null == beneficiaryValidator || beneficiaryValidator.validate(this));
        }
    }
}
