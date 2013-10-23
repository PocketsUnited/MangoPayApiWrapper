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

    static abstract class Init<T extends Init<T,U>, U extends Beneficiary> extends AbstractDateBase.Init<T,U> {

        Init(U object) {
            super(object);
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
    }
}
