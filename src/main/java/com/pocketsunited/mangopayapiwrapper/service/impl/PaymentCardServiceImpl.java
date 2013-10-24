package com.pocketsunited.mangopayapiwrapper.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pocketsunited.mangopayapiwrapper.model.PaymentCard;
import com.pocketsunited.mangopayapiwrapper.model.User;
import com.pocketsunited.mangopayapiwrapper.service.IPaymentCardService;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public class PaymentCardServiceImpl extends BaseServiceImpl implements IPaymentCardService {

    public static final class PaymentCardListTypeReference extends TypeReference<List<PaymentCard>> {}

    private PaymentCard.PaymentCardValidator createValidator = PaymentCard.PAYMENT_CARD_CREATE_VALIDATOR;

    private String templateUrl;

    private String returnUrl;

    public void setCreateValidator(PaymentCard.PaymentCardValidator createValidator) {
        if (null != createValidator) {
            this.createValidator = createValidator;
        }
        else {
            logger.warn("'createValidator' may not be null!");
        }
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    @PostConstruct
    void onPostConstruct() {
        super.onPostConstruct();
        if (null == returnUrl) {
            throw new RuntimeException("'returnUrl' must be set!");
        }
    }

    @Override
    public PaymentCard read(Long id) {
        return execute("/cards/" + id, HttpMethod.GET, new TypeReference<PaymentCard>() {
        });
    }

    @Override
    public PaymentCard create(User owner) {
        return create(owner.getId());
    }

    @Override
    public PaymentCard create(Long ownerId) {
        return executeCreate(PaymentCard.builder().withOwnerId(ownerId));
    }

    @Override
    public PaymentCard create(User owner, String returnUrl) {
        return create(owner.getId(),returnUrl);
    }

    @Override
    public PaymentCard create(Long ownerId, String returnUrl) {
        return executeCreate(PaymentCard.builder().withOwnerId(ownerId).withReturnUrl(returnUrl));
    }

    @Override
    public PaymentCard create(User owner, String returnUrl, String templateUrl) {
        return create(owner.getId(),returnUrl,templateUrl);
    }

    @Override
    public PaymentCard create(Long ownerId, String returnUrl, String templateUrl) {
        return executeCreate(PaymentCard.builder().withReturnUrl(returnUrl).withOwnerId(ownerId).withTemplateUrl(templateUrl));
    }

    @Override
    public PaymentCard create(User owner, String returnUrl, String templateUrl, String culture) {
        return create(owner.getId(),returnUrl,templateUrl,culture);
    }

    @Override
    public PaymentCard create(Long ownerId, String returnUrl, String templateUrl, String culture) {
        return executeCreate(PaymentCard.builder().withReturnUrl(returnUrl).withOwnerId(ownerId).withTemplateUrl(templateUrl).withCulture(culture));
    }

    @Override
    public PaymentCard create(PaymentCard.PaymentCardBuilder paymentCardBuilder) {
        return executeCreate(paymentCardBuilder);
    }

    @Override
    public List<PaymentCard> cardsByUser(User owner) {
        return cardsByUser(owner.getId());
    }

    @Override
    public List<PaymentCard> cardsByUser(Long ownerId) {
        return executeForList("/users/" + ownerId + "/cards", HttpMethod.GET, new PaymentCardListTypeReference());
    }

    PaymentCard executeCreate(PaymentCard.PaymentCardBuilder paymentCardBuilder) {
        if (!paymentCardBuilder.hasPaymentCardValidator()) {
            paymentCardBuilder.withPaymentCardValidator(createValidator);
        }
        if (!paymentCardBuilder.hasValidReturnUrl()) {
            paymentCardBuilder.withReturnUrl(returnUrl);
        }
        if (!paymentCardBuilder.hasValidTemplateUrl() && null != templateUrl) {
            paymentCardBuilder.withTemplateUrl(templateUrl);
        }

        return execute("/cards",HttpMethod.POST,paymentCardBuilder.build(),new TypeReference<PaymentCard>() {});
    }
}
