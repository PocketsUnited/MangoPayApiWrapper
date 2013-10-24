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

    public static class TransferValidator {

        boolean validate(Init init) {
            return true;
        }
    }

    public static final TransferValidator TRANSFER_DEFAULT_VALIDATOR = new TransferValidator();

    public static final TransferValidator TRANSFER_CREATE_VALIDATOR = new TransferValidator() {

        @Override
        boolean validate(Init init) {
            return !init.hasId() && !init.hasCreationDate() && !init.hasUpdateDate() && init.hasAmount() && (init.hasPayerId() || init.hasBeneficiaryId());
        }
    };

    static abstract class Init<T extends Init<T,U>, U extends Transfer> extends AbstractDateBase.Init<T,U> {

        private TransferValidator transferValidator;

        protected Init(U object) {
            super(object);
        }

        public T withTransferValidator(TransferValidator transferValidator) {
            if (null != transferValidator) {
                this.transferValidator = transferValidator;
            }
            else {
                logger.warn("'transferValidator' may not be null! Discarding input, 'transferValidator' is still: {}", this.transferValidator);
            }
            return self();
        }

        public T withPayerId(Long payerId) {
            if (null == payerId || 0l < payerId) {
                object.payerId = payerId;
            }
            else {
                logger.warn("'payerId' may not be less than or equals to zero! Discarding input, 'payerId' is still: {}", object.getPayerId());
            }
            return self();
        }

        public T withBeneficiaryId(Long beneficiaryId) {
            if (null == beneficiaryId || 0l < beneficiaryId) {
                object.beneficiaryId = beneficiaryId;
            }
            else {
                logger.warn("'beneficiaryId' may not be less than or equals to zero! Discarding input, 'beneficiaryId' is still: {}", object.getBeneficiaryId());
            }
            return self();
        }

        public T withPayerWalletId(Long payerWalletId) {
            if (null != payerWalletId) {
                object.payerWalletId = payerWalletId;
            }
            else {
                logger.warn("'payerWalletId' may not be null! Discarding input, 'payerWalletId' is still: {}",object.getPayerWalletId());
            }
            return self();
        }

        public T withBeneficiaryWalletId(Long beneficiaryWalletId) {
            if (null != beneficiaryWalletId) {
                object.beneficiaryWalletId = beneficiaryWalletId;
            }
            else {
                logger.warn("'beneficiaryWalletId' may not be null! Discarding input, 'beneficiaryWalletId' is still: {}",object.getBeneficiaryWalletId());
            }
            return self();
        }

        public T withAmount(Long amount) {
            if (null != amount) {
                if (0l < amount) {
                    object.amount = amount;
                }
                else {
                    logger.warn("'amount' may not be less than or equal to zero! Discarding input, 'amount' is still: {}", object.getAmount());
                }
            }
            else {
                logger.warn("'amount' may not be null! Discarding input, 'amount' is still: {}", object.getAmount());
            }
            return self();
        }

        public T withClientFeeAmount(Long clientFeeAmount) {
            if (null != clientFeeAmount) {
                if (0l <= clientFeeAmount) {
                    object.clientFeeAmount = clientFeeAmount;
                }
                else {
                    logger.warn("'clientFeeAmount' may not be less than zero! Discarding input, 'clientFeeAmount is still: {}", object.getClientFeeAmount());
                }
            }
            else {
                logger.warn("'clientFeeAmount may not be null! Discarding input, 'clientFeeAmount is still: {}", object.getClientFeeAmount());
            }
            return self();
        }

        public boolean hasTransferValidator() {
            return null != transferValidator;
        }

        public boolean hasAmount() {
            return null != object.getAmount();
        }

        public boolean hasPayerId() {
            return null != object.getPayerId();
        }

        public boolean hasBeneficiaryId() {
            return null != object.getBeneficiaryId();
        }

        @Override
        boolean validate() {
            return super.validate() && (null == transferValidator || transferValidator.validate(this));
        }
    }
}
