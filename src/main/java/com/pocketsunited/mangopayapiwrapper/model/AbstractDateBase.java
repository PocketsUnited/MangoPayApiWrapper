package com.pocketsunited.mangopayapiwrapper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pocketsunited.mangopayapiwrapper.util.JsonDateDeserializer;
import com.pocketsunited.mangopayapiwrapper.util.JsonDateSerializer;

import java.util.Date;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public abstract class AbstractDateBase extends AbstractBase {

    @JsonProperty(
            value = "CreationDate")
    @JsonSerialize(
            using = JsonDateSerializer.class)
    @JsonDeserialize(
            using = JsonDateDeserializer.class)
    Date creationDate;

    @JsonProperty(
            value = "UpdateDate")
    @JsonSerialize(
            using = JsonDateSerializer.class)
    @JsonDeserialize(
            using = JsonDateDeserializer.class)
    Date updateDate;

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    static abstract class Init<T extends Init<T,U>, U extends AbstractDateBase> extends AbstractBase.Init<T,U> {

        Init(U object) {
            super(object);
        }

        public T withCreationDate(Date creationDate) {
            object.creationDate = creationDate;
            return self();
        }

        public T withUpdateDate(Date updateDate) {
            object.updateDate = updateDate;
            return self();
        }

        public boolean hasCreationDate() {
            return null != object.getCreationDate();
        }

        public boolean hasUpdateDate() {
            return null != object.getUpdateDate();
        }
    }
}
