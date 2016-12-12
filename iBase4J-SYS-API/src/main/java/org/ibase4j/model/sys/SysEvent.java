package org.ibase4j.model.sys;

import org.ibase4j.core.base.BaseModel;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_event")
@SuppressWarnings("serial")
public class SysEvent extends BaseModel {
    @TableField("title_")
    private String title;

    private String requestUri;

    @TableField("parameters_")
    private String parameters;

    @TableField("method_")
    private String method;

    private String clientHost;
    private String userAgent;

    @TableField("status_")
    private Integer status;

    /**
     * @return the value of sys_event.title_
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the value for sys_event.title_
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * @return the value of sys_event.request_uri
     */
    public String getRequestUri() {
        return requestUri;
    }

    /**
     * @param requestUri the value for sys_event.request_uri
     */
    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri == null ? null : requestUri.trim();
    }

    /**
     * @return the value of sys_event.parameters_
     */
    public String getParameters() {
        return parameters;
    }

    /**
     * @param parameters the value for sys_event.parameters_
     */
    public void setParameters(String parameters) {
        this.parameters = parameters == null ? null : parameters.trim();
    }

    /**
     * @return the value of sys_event.method_
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method the value for sys_event.method_
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * @return the value of sys_event.client_host
     */
    public String getClientHost() {
        return clientHost;
    }

    /**
     * @param clientHost the value for sys_event.client_host
     */
    public void setClientHost(String clientHost) {
        this.clientHost = clientHost == null ? null : clientHost.trim();
    }

    /**
     * @return the value of sys_event.user_agent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * @param userAgent the value for sys_event.user_agent
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    /**
     * @return the value of sys_event.status_
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the value for sys_event.status_
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", title=").append(title);
        sb.append(", requestUri=").append(requestUri);
        sb.append(", parameters=").append(parameters);
        sb.append(", method=").append(method);
        sb.append(", clientHost=").append(clientHost);
        sb.append(", userAgent=").append(userAgent);
        sb.append(", status=").append(status);
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
        SysEvent other = (SysEvent)that;
        return (this.getId_() == null ? other.getId_() == null : this.getId_().equals(other.getId_()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getRequestUri() == null ? other.getRequestUri() == null
                : this.getRequestUri().equals(other.getRequestUri()))
            && (this.getParameters() == null ? other.getParameters() == null
                : this.getParameters().equals(other.getParameters()))
            && (this.getMethod() == null ? other.getMethod() == null : this.getMethod().equals(other.getMethod()))
            && (this.getClientHost() == null ? other.getClientHost() == null
                : this.getClientHost().equals(other.getClientHost()))
            && (this.getUserAgent() == null ? other.getUserAgent() == null
                : this.getUserAgent().equals(other.getUserAgent()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null
                : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null
                : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null
                : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null
                : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    /**
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId_() == null) ? 0 : getId_().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getRequestUri() == null) ? 0 : getRequestUri().hashCode());
        result = prime * result + ((getParameters() == null) ? 0 : getParameters().hashCode());
        result = prime * result + ((getMethod() == null) ? 0 : getMethod().hashCode());
        result = prime * result + ((getClientHost() == null) ? 0 : getClientHost().hashCode());
        result = prime * result + ((getUserAgent() == null) ? 0 : getUserAgent().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}
