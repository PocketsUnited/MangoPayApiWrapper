package com.pocketsunited.mangopayapiwrapper.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.codec.binary.Base64
import spock.lang.Specification

import java.security.KeyFactory
import java.security.spec.PKCS8EncodedKeySpec

/**
 *
 *
 * @author Michael Duergner <michael@pocketsunited.com>
 * @version
 */
class AbstractBaseServiceImplSpec extends Specification {

    static ObjectMapper objectMapper

    static BaseServiceImpl baseServiceImpl;

    def setupSpec() {
        objectMapper = new ObjectMapper()
        def privateKeyFile = new File(getClass().getClassLoader().getResource("example.pem").toURI())
        def bytes = new byte[privateKeyFile.length()]
        new FileInputStream(privateKeyFile).read(bytes)
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(bytes))
        def privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec)
        baseServiceImpl = new BaseServiceImpl()
        baseServiceImpl.setObjectMapper(objectMapper)
        baseServiceImpl.setPrivateKey(privateKey)
    }

    def "calculate HMAC-SHA1 signature for GET and DELETE"() {
        setup:
            def getResult
            def deleteResult
        when:
            getResult = baseServiceImpl.sign(BaseServiceImpl.HttpMethod.GET,"/v1/partner/xxx?ts=1234567890")
            deleteResult = baseServiceImpl.sign(BaseServiceImpl.HttpMethod.DELETE,"/v1/partner/xxx?ts=1234567890")
        then:
            getResult.equals("YykbT77qqTIGJk235TYDEyJmyNX7/w3E6tySZga6dNzlKV31oxmLy40ZrPlLVYLgIJWt6O9kzaDYOet2a/vtJh98+XZbxF5bREdDbNYTBKH7L47l9kf4p7MHbfSWa47xXB4RJTGxa0nf0h/EghdvfDqX21VIte6po3eQ1g4KbPXbz/zHbnzVfKfOagVymiT2s35hByfQxd5LV/fas+pNkHdvOlueOT/iE8WxlFknWt9et6ruPZ2fcBoeO8YIfSN4j+I6XbsSub5uKOa8HG6dKxQhrE+RQxsvRCBr/yiRf4opOnmqRHzISwXHIONxDEkhK6/AjsHXwfGIjkR3cJOIlw==")
            deleteResult.equals("jpmPP4IDwqisutXAuELrzPPujvWe2TDJMYff2oQtJkHQp2TGB6Vv2CJ27r+MKYHRE2XdB6IPOC9kOhS+C2qMTSbYnBep2KYDKizoa92LNjmhwI9DG+GDNASgMbETvTybQOKbzjJAJGu4YfdPpNvwjsbpqrf/okTpQPk/wPJuvK8xBNUr88H2ypa4wxDZY0nEkTmftcGJt90SihI0aX+QKfI/hJ9KK4cWal2L8Rp59UL2Ftf/4h+dD+RHhyl2raK5ctK+5w1hYdcyIXner/gUHbhw/q99Q1ohc8VPOMY1ZzfwMpbFhGf1qGl7h9DwDeWQKnymMBFd0fV2tGk56cr9Kw==")
    }

    def "calculate HMAC-SHA1 signature for POST and PUT"() {
        setup:
            def postResult
            def putResult
        when:
            def data = objectMapper.writeValueAsString([UserId: 1, FirstName: "John", LastName: "Doe"])
            postResult = baseServiceImpl.sign(BaseServiceImpl.HttpMethod.POST,"/v1/partner/xxx?ts=1234567890",data)
            putResult = baseServiceImpl.sign(BaseServiceImpl.HttpMethod.PUT,"/v1/partner/xxx?ts=1234567890",data)
        then:
            postResult.equals("BjVNzyM8kAZ1Zbau6Jo8ErJi+w7xUshT99jUhMyKeqEfiG6dshw2rFjmNVBPMzAQ5eatpAQgtxtb46i+ApM1EfV+/hHH2eq841u9O51JBxrJ0PqtJS727FudX8XXujavZVzCzt8YpuX+suw7hDG8fxfJMwHP9j4BDUbugcnASy+DdWcuCSY9TxjXv3MnbLBiAKh4p0T+w5ATjg3XNhje6hczHqsGJDgDGnwU7kiTAUKIYx4VwrWvoI1x6/2n8P8jnGcPwS0iUyVEuHJwHcsYFLSUzqyyr88p5a+d8WpaX9z5o40kT6ZYfZKXu0bRBD7ElkwquZV0RoEZ38J1i0Vipw==")
            putResult.equals("hBSazuuYODvPntdRcdqG+RA4QW9CKuXfaYU86e+cXxY6ainKKkdnm8ROOK8GzhlmJo5RyMl8q1nnLJLIVEh4/WHYuHtYvBv+HI7a/63xDYRxwUBV7xysHjvzVuYc5mJDgwaj1IneuWyug3Vcntv3YzNQ+qiydr9g8SaJvK3xZMUrwizhRUT95Ht34gTbb07rTYN9BwIAopcSh5fY6PU8h2Bd60zNJ41K7FY1sOLIV/1cKIMzyKGRDWU99ylF5ye082i55cguHB1lOzknWqxuvbBWpi+8tqJ8wdpzljFYBlQLDwu4K2tpUy1sg580VKNNMZOxPozEDHOllmli1L7m0A==")
    }
}