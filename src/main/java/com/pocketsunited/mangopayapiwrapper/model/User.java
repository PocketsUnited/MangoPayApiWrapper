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
    Boolean hasRegisteredMeansOfPayment;

    @JsonProperty(
            value = "CanRegisterMeanOfPayment"
    )
    Boolean canRegisterMeanOfPayment;

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
    PersonType personType;

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

    static abstract class Init<T extends Init<T,U>, U extends User> extends AbstractDateBase.Init<T,U> {

        Init(U object) {
            super(object);
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
            object.personType = personType;
            return self();
        }

        public T withCanRegisterMeansOfPayment(Boolean canRegisterMeansOfPayment) {
            object.canRegisterMeanOfPayment = canRegisterMeansOfPayment;
            return self();
        }
    }
}