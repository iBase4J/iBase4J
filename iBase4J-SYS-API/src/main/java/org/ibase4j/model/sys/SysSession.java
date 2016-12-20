package org.ibase4j.model.sys;

import java.util.Date;
import org.ibase4j.core.base.BaseModel;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_session")
@SuppressWarnings("serial")
public class SysSession extends BaseModel {
    private String sessionId;
    
    @TableField("account_")
    private String account;

    @TableField("ip_")
    private String ip;

    private Date startTime;

    /**
     * @return the value of sys_session.session_id
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the value for sys_session.session_id
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    /**
     * @return the value of sys_session.account_
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account the value for sys_session.account_
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * @return the value of sys_session.ip_
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the value for sys_session.ip_
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * @return the value of sys_session.start_time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the value for sys_session.start_time
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sessionId=").append(sessionId);
        sb.append(", account=").append(account);
        sb.append(", ip=").append(ip);
        sb.append(", startTime=").append(startTime);
        sb.append("]");
        return sb.toString();
    }

    /**
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysSession other = (SysSession)that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSessionId() == null ? other.getSessionId() == null
                : this.getSessionId().equals(other.getSessionId()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getStartTime() == null ? other.getStartTime() == null
                : this.getStartTime().equals(other.getStartTime()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null
                : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null
                : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null
                : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null
                : this.getUpdateBy().equals(other.getUpdateBy()));
    }

    /**
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSessionId() == null) ? 0 : getSessionId().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        return result;
    }
}
