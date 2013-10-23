package com.pocketsunited.mangopayapiwrapper.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pocketsunited.mangopayapiwrapper.model.AbstractBase;
import com.pocketsunited.mangopayapiwrapper.service.SignatureException;
import com.pocketsunited.mangopayapiwrapper.util.PrivateKeyFactory;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.List;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public class BaseServiceImpl {

    protected enum HttpMethod {
        GET,POST,PUT,DELETE
    }

    private static final String HEADER_SIGNATURE = "X-Leetchi-Signature";

    private static final String PRODUCTION_HOST = "https://api.leetchi.com";
    private static final String SANDBOX_HOST = "https://api-preprod.leetchi.com";
    private static final String URL_BASE = "/v1/partner/";

    Logger logger = LoggerFactory.getLogger(getClass());
    Logger stackTraceLogger = LoggerFactory.getLogger("trace."+getClass().getName());

    ObjectMapper objectMapper;

    HttpClient httpClient;

    private PrivateKey privateKey;

    private String partnerId;

    private boolean sandbox = Boolean.FALSE;

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public void setSandbox(boolean sandbox) {
        this.sandbox = sandbox;
    }

    public void setPrivateKeyFileFileName(String privateKeyFileFileName) {
        PrivateKeyFactory privateKeyFactory = new PrivateKeyFactory();
        privateKeyFactory.setPrivateKeyURI(privateKeyFileFileName);
        this.privateKey = privateKeyFactory.getPrivateKey();
    }

    @PostConstruct
    public final void postConstruct() {
        if (null == objectMapper) {
            objectMapper = new ObjectMapper();
        }
        if (null == httpClient) {
            httpClient = new DefaultHttpClient(new PoolingClientConnectionManager());
        }
        if (null == privateKey) {
            throw new RuntimeException("'privateKey' needs to be set!");
        }
        if (null == partnerId) {
            throw new RuntimeException("'partnerId' must be set!");
        }
    }

    void onPostConstruct() {}

//    <T extends AbstractBase> T execute(String urlFragment, HttpMethod method, Class<T> resultClass) {
//        return doExecute(urlFragment, method, null, new TypeReference<T>() {});
//    }
//
//    <T extends AbstractBase> T execute(String urlFragment, HttpMethod method, AbstractBase payload, Class<T> resultClass) {
//        return doExecute(urlFragment, method, payload, new TypeReference<T>() {});
//    }

    <T extends AbstractBase> T execute(String urlFragment, HttpMethod method, TypeReference<T> typeReference) {
        return doExecute(urlFragment, method, null, typeReference);
    }

    <T extends List<? extends AbstractBase>> T executeForList(String urlFragment, HttpMethod method, TypeReference<T> typeReference) {
        return doExecute(urlFragment, method, null, typeReference);
    }

    <T extends AbstractBase> T execute(String urlFragment, HttpMethod method, AbstractBase payload, TypeReference<T> typeReference) {

        return doExecute(urlFragment, method, payload, typeReference);
    }

    private <T> T doExecute(String urlFragment, HttpMethod method, AbstractBase payload, TypeReference<T> typeReference) {
        HttpRequestBase request = null;
        String body = null;
        switch (method) {
            case GET:
                request = new HttpGet();
                break;
            case DELETE:
                request = new HttpDelete();
                break;
            case POST:
                try {
                    request = new HttpPost();
                    body = objectMapper.writeValueAsString(payload);
                    ((HttpPost)request).setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
                    break;
                }
                catch (JsonProcessingException e) {
                    logger.error(e.getMessage());
                    stackTraceLogger.error(e.getMessage(),e);
                    throw new RuntimeException(e.getMessage());
                }
            case PUT:
                try {
                    request = new HttpPut();
                    body = objectMapper.writeValueAsString(payload);
                    ((HttpPut)request).setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
                    break;
                }
                catch (JsonProcessingException e) {
                    logger.error(e.getMessage());
                    stackTraceLogger.error(e.getMessage(),e);
                    throw new RuntimeException(e.getMessage());
                }
            default:
                throw new IllegalArgumentException("Invalid method submitted!");
        }
        Exception exception;
        try {
            Long timestamp = (Long)(System.currentTimeMillis() / 1000);
            String urlToSign = URL_BASE+partnerId+urlFragment+"?ts="+timestamp.toString();
            String signature = sign(method,urlToSign,body);
            request.setURI(new URI(getHost()+urlToSign));
            request.addHeader(HEADER_SIGNATURE,signature);
            HttpResponse response = httpClient.execute(request);
            if (200 == response.getStatusLine().getStatusCode()) {
                if (null != typeReference) {
                    return objectMapper.readValue(response.getEntity().getContent(),typeReference);
                }
                else {
                    return null;
                }
            }
            else {
                String responseBody = EntityUtils.toString(response.getEntity());
                logger.warn(response.getStatusLine().getStatusCode()+": "+responseBody);
                throw new RuntimeException(responseBody);
            }
        }
        catch (SignatureException e) {
            exception = e;
        }
        catch (JsonProcessingException e) {
            exception = e;
        }
        catch (ClientProtocolException e) {
            exception = e;
        }
        catch (IOException e) {
            exception = e;
        }
        catch (URISyntaxException e) {
            exception = e;
        }
        if (!request.isAborted()) {
            request.abort();
        }
        logger.error(exception.getMessage());
        stackTraceLogger.error(exception.getMessage(),exception);
        throw new RuntimeException("");
    }

    private String sign(HttpMethod method, String url) throws SignatureException {
        return sign(method,url,(String)null);
    }

    private String sign(HttpMethod method, String url, String body) throws SignatureException {
        Exception exception;
        try {
            StringBuilder toSign = new StringBuilder(method.name());
            toSign.append("|");
            toSign.append(url);
            toSign.append("|");
            if (null != body) {
                toSign.append(body);
                toSign.append("|");
            }
            System.out.println(toSign.toString());
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(privateKey);
            signature.update(toSign.toString().getBytes("UTF-8"));
            String signatureString = new String(Base64.encodeBase64(signature.sign()),"UTF-8").trim();
            System.out.println(signatureString);
            return signatureString;
        }
        catch (NoSuchAlgorithmException e) {
            exception = e;
        }
        catch (InvalidKeyException e) {
            exception = e;
        }
        catch (UnsupportedEncodingException e) {
            exception = e;
        }
        catch (java.security.SignatureException e) {
            exception = e;
        }

        StringBuilder text = new StringBuilder(exception.getClass().getSimpleName());
        text.append(" when trying to calculate signature for method ");
        text.append(method);
        text.append(", url ");
        text.append(url);
        if (null != body) {
            text.append(", body ");
            text.append(body);
        }
        String exceptionMessage = text.toString();
        logger.error(exceptionMessage);
        stackTraceLogger.error(exceptionMessage,exception);
        throw new SignatureException(exceptionMessage, exception);
    }

    private String getHost() {
        String result = sandbox ? SANDBOX_HOST : PRODUCTION_HOST;
        logger.info("getHost(): "+result);
        System.out.println("host: "+result);
        return result;
    }
}
