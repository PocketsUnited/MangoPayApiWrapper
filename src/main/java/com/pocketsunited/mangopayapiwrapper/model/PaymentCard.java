package com.pocketsunited.mangopayapiwrapper.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public class PaymentCard extends AbstractBase {

    @JsonProperty(
            value = "OwnerID")
    Long ownerId;

    @JsonProperty(
            value = "CardNumber")
    String cardNumber;

    @JsonProperty(
            value = "RedirectURL")
    String redirectUrl;

    @JsonProperty(
            value = "TemplateURL")
    String templateUrl;

    @JsonProperty(
            value = "ReturnURL")
    String returnUrl;

    @JsonProperty(
            value = "Culture")
    String culture;

    public Long getOwnerId() {
        return ownerId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String getTemplateUrl() {
        return templateUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public String getCulture() {
        return culture;
    }

    public static PaymentCardBuilder builder() {
        return new PaymentCardBuilder();
    }

    public static final class PaymentCardBuilder extends Init<PaymentCardBuilder,PaymentCard> {

        PaymentCardBuilder() {
            super(new PaymentCard());
        }

        @Override
        PaymentCardBuilder self() {
            return this;
        }
    }

    public static class PaymentCardValidator {

        boolean validate(Init init) {
            return true;
        }
    }

    public static final PaymentCardValidator PAYMENT_CARD_DEFAULT_VALIDATOR = new PaymentCardValidator();

    public static final PaymentCardValidator PAYMENT_CARD_CREATE_VALIDATOR = new PaymentCardValidator() {

        @Override
        boolean validate(Init init) {
            return !init.hasId() && init.hasValidOwnerId() && init.hasValidReturnUrl() && (!init.hasTemplateUrl() || init.hasValidTemplateUrl());
        }
    };

    static abstract class Init<T extends Init<T,U>, U extends PaymentCard> extends AbstractBase.Init<T,U> {

        private PaymentCardValidator paymentCardValidator;

        Init(U object) {
            super(object);
        }

        public T withPaymentCardValidator(PaymentCardValidator paymentCardValidator) {
            if (null != paymentCardValidator) {
                this.paymentCardValidator = paymentCardValidator;
            }
            else {
                logger.warn("'paymentCardValidator' may not be null! Discarding input, 'paymentCardValidator' is still: {}", this.paymentCardValidator);
            }
            return self();
        }

        public T withOwnerId(Long ownerId) {
            object.ownerId = ownerId;
            return self();
        }

        public T withCardNumber(String cardNumber) {
            object.cardNumber = cardNumber;
            return self();
        }

        public T withRedirectUrl(String redirectUrl) {
            object.redirectUrl = redirectUrl;
            return self();
        }

        public T withTemplateUrl(String templateUrl) {
            object.templateUrl = templateUrl;
            return self();
        }

        public T withReturnUrl(String returnUrl) {
            object.returnUrl = returnUrl;
            return self();
        }

        public T withCulture(String culture) {
            object.culture = culture;
            return self();
        }

        public boolean hasPaymentCardValidator() {
            return null != paymentCardValidator;
        }

        public boolean hasOwnerId() {
            return null != object.getOwnerId();
        }

        public boolean hasValidOwnerId() {
            return hasOwnerId() && 0l < object.getOwnerId();
        }

        public boolean hasReturnUrl() {
            return null != object.getReturnUrl() && !object.getReturnUrl().isEmpty();
        }

        public boolean hasValidReturnUrl() {
            // TODO Implement URL sanity checks
            return hasReturnUrl();
        }

        public boolean hasTemplateUrl() {
            return null != object.getTemplateUrl() && !object.getTemplateUrl().isEmpty();
        }

        public boolean hasValidTemplateUrl() {
            // TODO Implement URL sanity checks
            return hasTemplateUrl();
        }

        @Override
        boolean validate() {
            return super.validate() && (null == paymentCardValidator || paymentCardValidator.validate(this));
        }
    }
}
