package org.ibase4j.core.support.weixin.model;

/**
 * @author ShenHuaJie
 * @since 2017年2月3日 下午5:09:23
 */
public class AccessToken {
    private String access_token;
    private Long expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }
}
