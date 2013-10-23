package com.pocketsunited.mangopayapiwrapper.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public class Transfer extends AbstractDateBase {

    @JsonProperty(
            value = "PayerID")
    Long payerId;

    @JsonProperty(
            value = "BeneficiaryID")
    Long beneficiaryId;

    @JsonProperty(
            value = "Amount")
    Long amount;

    @JsonProperty(
            value = "ClientFeeAmount")
    Long clientFeeAmount = 0l;

    @JsonProperty(
            value = "PayerWalletID")
    Long payerWalletId = 0l;

    @JsonProperty(
            value = "BeneficiaryWalletID")
    Long beneficiaryWalletId = 0l;

    public Long getPayerId() {
        return payerId;
    }

    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    public Long getAmount() {
        return amount;
    }

    public Long getClientFeeAmount() {
        return clientFeeAmount;
    }

    public Long getPayerWalletId() {
        return payerWalletId;
    }

    public Long getBeneficiaryWalletId() {
        return beneficiaryWalletId;
    }

    public static TransferBuilder builder() {
        return new TransferBuilder();
    }

    public static class TransferBuilder extends Init<TransferBuilder,Transfer> {

        TransferBuilder() {
            super(new Transfer());
        }

        @Override
        TransferBuilder self() {
            return this;
        }
    }

    static abstract class Init<T extends Init<T,U>, U extends Transfer> extends AbstractDateBase.Init<T,U> {

        protected Init(U object) {
            super(object);
        }

        public T withPayerId(Long payerId) {
            object.payerId = payerId;
            return self();
        }

        public T withBeneficiaryId(Long beneficiaryId) {
            object.beneficiaryId = beneficiaryId;
            return self();
        }

        public T withPayerWalletId(Long payerWalletId) {
            object.payerWalletId = payerWalletId;
            return self();
        }

        public T withBeneficiaryWalletId(Long beneficiaryWalletId) {
            object.beneficiaryWalletId = beneficiaryWalletId;
            return self();
        }

        public T withAmount(Long amount) {
            if (0l <= amount) {
                object.amount = amount;
            }
            else {
                logger.warn("'amount' may not be less than 0! Discarding!");
            }
            return self();
        }

        public T withClientFeeAmount(Long clientFeeAmount) {
            if (0l <= clientFeeAmount) {
                object.clientFeeAmount = clientFeeAmount;
            }
            else {
                logger.warn("'clientFeeAmount' may not be less than 0! Discarding!");
            }
            return self();
        }

        @Override
        boolean validate() {
            if (!super.validate()) {
                return false;
            }
            if (null == object.amount) {
                logger.warn("'amount' must be set!");
                return false;
            }
            if (null == object.payerId && null == object.payerWalletId) {
                logger.warn("Either 'payerId' or 'payerWalletId' must to be set!");
                return false;
            }
            if (null == object.beneficiaryId && null == object.beneficiaryWalletId) {
                logger.warn("Either 'beneficiaryId' or 'beneficiaryWalletId' must be set!");
                return false;
            }
            return true;
        }
    }
}
