package com.pocketsunited.mangopayapiwrapper.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
@JsonAutoDetect(
        creatorVisibility = JsonAutoDetect.Visibility.DEFAULT,
        fieldVisibility = JsonAutoDetect.Visibility.NONE,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(
        ignoreUnknown = true)
@JsonInclude(
        value = JsonInclude.Include.NON_EMPTY)
public abstract class AbstractBase {

    @JsonProperty(
            value = "ID")
    Long id;

    @JsonProperty(
            value = "Tag")
    String tag;

    public Long getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    static abstract class Init<T extends Init<T, U>, U extends AbstractBase> {

        Logger logger = LoggerFactory.getLogger(getClass());

        U object;

        abstract T self();

        Init(U object) {
            if (null == object) {
                throw new IllegalArgumentException("'object may not be null!'");
            }
            this.object = object;
            postCreate();
        }

        public T withId(Long id) {
            object.id = id;
            return self();
        }

        public T withTag(String tag) {
            object.tag = tag;
            return self();
        }

        public boolean hasId() {
            return null != object.getId();
        }

        public boolean hasTag() {
            return null != object.getTag();
        }

        public final U build() {
            preBuild();
            if (validate()) {
                return object;
            }
            else {
                throw new IllegalArgumentException("Validation failed!");
            }
        }

        void postCreate() {}

        void preBuild() {}

        boolean validate() {
            return true;
        }
    }
}
