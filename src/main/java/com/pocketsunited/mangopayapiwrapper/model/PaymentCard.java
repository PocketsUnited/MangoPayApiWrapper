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

    static abstract class Init<T extends Init<T,U>, U extends PaymentCard> extends AbstractBase.Init<T,U> {

        Init(U object) {
            super(object);
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
    }
}
