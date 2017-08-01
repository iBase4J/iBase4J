/**
 * 
 */
package org.ibase4j.model;

import java.io.Serializable;

/**
 * 
 * @author ShenHuaJie
 * @version 2017年3月18日 下午1:40:45
 */
@SuppressWarnings("serial")
public class Login implements Serializable {

    private String account;
    private String password;

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
