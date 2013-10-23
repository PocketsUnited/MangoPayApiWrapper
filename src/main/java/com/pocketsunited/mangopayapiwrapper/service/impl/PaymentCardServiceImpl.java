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

    public static final class PaymentCardListTypeReference extends TypeReference<List<PaymentCard>> {};

    private String templateUrl;

    private String returnUrl;

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
        return execute("/cards/"+id,HttpMethod.GET,new TypeReference<PaymentCard>() {});
    }

    @Override
    public PaymentCard createPaymentCard(User owner) {
        return createPaymentCard(owner.getId());
    }

    @Override
    public PaymentCard createPaymentCard(Long ownerId) {
        return executeCreate(builder().withOwnerId(ownerId).build());
    }

    @Override
    public PaymentCard createPaymentCard(User owner, String returnUrl) {
        return createPaymentCard(owner.getId(),returnUrl);
    }

    @Override
    public PaymentCard createPaymentCard(Long ownerId, String returnUrl) {
        return executeCreate(builder().withOwnerId(ownerId).withReturnUrl(returnUrl).build());
    }

    @Override
    public PaymentCard create(Long ownerId, String returnUrl, String templateUrl) {
        return executeCreate(builder().withReturnUrl(returnUrl).withOwnerId(ownerId).withTemplateUrl(templateUrl).build());
    }

    @Override
    public PaymentCard create(Long ownerId, String returnUrl, String templateUrl, String culture) {
        return executeCreate(builder().withReturnUrl(returnUrl).withOwnerId(ownerId).withTemplateUrl(templateUrl).withCulture(culture).build());
    }

    //
//    @Override
//    public PaymentCard createPaymentCard(User owner, String cardNumber) {
//        return createPaymentCard(owner.getId(), cardNumber);
//    }
//
//    @Override
//    public PaymentCard createPaymentCard(Long ownerId, String cardNumber) {
//        return executeCreate(builder().withOwnerId(ownerId).withCardNumber(cardNumber).build());
//    }

    @Override
    public List<PaymentCard> cardsByUser(User owner) {
        return cardsByUser(owner.getId());
    }

    @Override
    public List<PaymentCard> cardsByUser(Long ownerId) {
        return executeForList("/users/"+ownerId+"/cards",HttpMethod.GET,new PaymentCardListTypeReference());
    }

    PaymentCard executeCreate(PaymentCard paymentCard) {
        return execute("/cards",HttpMethod.POST,paymentCard,new TypeReference<PaymentCard>() {});
    }

    PaymentCard.PaymentCardBuilder builder() {
        PaymentCard.PaymentCardBuilder paymentCardBuilder = PaymentCard.builder().withReturnUrl(returnUrl);
        if (null != templateUrl) {
            paymentCardBuilder.withTemplateUrl(templateUrl);
        }
        return paymentCardBuilder;
    }
}
