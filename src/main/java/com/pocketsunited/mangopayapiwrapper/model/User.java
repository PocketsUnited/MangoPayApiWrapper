package com.pocketsunited.mangopayapiwrapper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pocketsunited.mangopayapiwrapper.util.JsonDateDeserializer;
import com.pocketsunited.mangopayapiwrapper.util.JsonDateSerializer;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public class User extends AbstractDateBase {

    public enum PersonType {
        NATURAL_PERSON, LEGAL_PERSONALITY
    }

    @JsonProperty(
            value = "Email")
    @Size(
            max = 255)
    String email;

    @JsonProperty(
            value = "FirstName")
    @Size(
            max = 100)
    String firstName;

    @JsonProperty(
            value = "LastName"
    )
    @Size(
            max = 100)
    String lastName;

    @JsonProperty(
            value = "Birthday"
    )
    @JsonSerialize(
            using = JsonDateSerializer.class)
    @JsonDeserialize(
            using = JsonDateDeserializer.class)
    Date birthday;

    @JsonProperty(
            value = "HasRegisteredMeansOfPayment"
    )
    Boolean hasRegisteredMeansOfPayment = Boolean.FALSE;

    @JsonProperty(
            value = "CanRegisterMeanOfPayment"
    )
    Boolean canRegisterMeanOfPayment = Boolean.FALSE;

    @JsonProperty(
            value = "IP"
    )
    @Size(
            max = 15,
            min = 7)
    String ip;

    @JsonProperty(
            value = "Nationality"
    )
    @Size(
            max = 2,
            min = 2)
    String nationality;

    @JsonProperty(
            value = "PersonType"
    )
    PersonType personType = PersonType.NATURAL_PERSON;

    @JsonProperty(
            value = "PersonalWalletAmount"
    )
    Long personalWalletAmount;

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Boolean getHasRegisteredMeansOfPayment() {
        return hasRegisteredMeansOfPayment;
    }

    public Boolean getCanRegisterMeanOfPayment() {
        return canRegisterMeanOfPayment;
    }

    public String getIp() {
        return ip;
    }

    public String getNationality() {
        return nationality;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public Long getPersonalWalletAmount() {
        return personalWalletAmount;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static final class UserBuilder extends Init<UserBuilder,User> {

        UserBuilder() {
            super(new User());
        }

        @Override
        UserBuilder self() {
            return this;
        }
    }

    public static final UserValidator USER_CREATE_VALIDATOR = new UserValidator() {

        @Override
        boolean validate(Init init) {
            return !init.hasId() && !init.hasCreationDate() && !init.hasUpdateDate() && init.hasEmail() && init.hasValidEmail() && init.hasFirstName() && init.hasLastName() && init.hasIp() && init.hasValidIp() && init.hasBirthday() && init.hasNationality();
        }
    };

    public static final UserValidator USER_MODIFY_VALIDATOR = new UserValidator() {

        @Override
        boolean validate(Init init) {
            return init.hasId() && !init.hasCreationDate() && !init.hasUpdateDate() && (!init.hasEmail() || init.hasValidEmail()) && (!init.hasIp() || init.hasValidIp());
        }
    };

    public static final UserValidator USER_DEFAULT_VALIDATOR = new UserValidator();

    public static class UserValidator {

        boolean validate(Init init) {
            return true;
        }
    }

    static abstract class Init<T extends Init<T,U>, U extends User> extends AbstractDateBase.Init<T,U> {

        private UserValidator userValidator;

        Init(U object) {
            super(object);
        }

        public T withUserValidator(UserValidator userValidator) {
            if (null != userValidator) {
                this.userValidator = userValidator;
            }
            else {
                logger.warn("'userValidator' may not be null! Discarding input, 'userValidator is still: {}", this.userValidator);
            }
            return self();
        }

        public T withEmail(String email) {
            object.email = email;
            return self();
        }

        public T withFirstName(String firstName) {
            object.firstName = firstName;
            return self();
        }

        public T withLastName(String lastName) {
            object.lastName = lastName;
            return self();
        }

        public T withBirthday(Date birthday) {
            object.birthday = birthday;
            return self();
        }

        public T withIp(String ip) {
            object.ip = ip;
            return self();
        }

        public T withNationality(String nationality) {
            object.nationality = nationality;
            return self();
        }

        public T withPersonType(PersonType personType) {
            if (null != personType) {
                object.personType = personType;
            }
            else {
                logger.warn("'personType' may not be null! Discarding input, 'personType' is still: {}",object.getPersonType());
            }
            return self();
        }

        public T withCanRegisterMeansOfPayment(Boolean canRegisterMeansOfPayment) {
            if (null != canRegisterMeansOfPayment) {
                object.canRegisterMeanOfPayment = canRegisterMeansOfPayment;
            }
            else {
                logger.warn("'canRegisterMeanOfPayment' may not be null! Discarding input, 'canRegisterMeanOfPayment' is still: {}",object.getCanRegisterMeanOfPayment());
            }
            return self();
        }

        public T asLegalPerson() {
            object.personType = PersonType.LEGAL_PERSONALITY;
            return self();
        }

        public T asNaturalPerson() {
            object.personType = PersonType.NATURAL_PERSON;
            return self();
        }

        public boolean hasUserValidator() {
            return null != userValidator;
        }

        public boolean hasEmail() {
            return null != object.getEmail() && !object.getEmail().isEmpty();
        }

        public boolean hasValidEmail() {
            // TODO Implement correct validation!
            return hasEmail();
        }

        public boolean hasFirstName() {
            return null != object.getFirstName() && !object.getFirstName().isEmpty();
        }

        public boolean hasLastName() {
            return null != object.getLastName() && !object.getLastName().isEmpty();
        }

        public boolean hasBirthday() {
            return null != object.getBirthday();
        }

        public boolean hasIp() {
            return null != object.getIp() && !object.getIp().isEmpty();
        }

        public boolean hasValidIp() {
            if (!hasIp()) {
                return false;
            }
            String[] parts = object.getIp().split("\\.");
            if (4 != parts.length) {
                return false;
            }
            for (String part : parts) {
                try {
                    int intPart = Integer.valueOf(part);
                    if (0 > intPart || 255 < intPart) {
                        return false;
                    }
                }
                catch (NumberFormatException e) {
                    return false;
                }
            }
            return true;
        }

        public boolean hasNationality() {
            return null != object.getNationality() && !object.getNationality().isEmpty();
        }

        public boolean hasPersonType() {
            return null != object.getPersonType();
        }

        @Override
        boolean validate() {
            return super.validate() && (null == userValidator || userValidator.validate(this));
        }
    }
}