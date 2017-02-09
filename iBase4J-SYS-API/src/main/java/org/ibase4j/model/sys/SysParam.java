package org.ibase4j.model.sys;

import org.ibase4j.core.base.BaseModel;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_param")
@SuppressWarnings("serial")
public class SysParam extends BaseModel {
    private String paramKey;
    private String paramValue;

    /**
     * @return the value of sys_param.param_key
     */
    public String getParamKey() {
        return paramKey;
    }

    /**
     * @param paramKey the value for sys_param.param_key
     */
    public void setParamKey(String paramKey) {
        this.paramKey = paramKey == null ? null : paramKey.trim();
    }

    /**
     * @return the value of sys_param.param_value
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * @param paramValue the value for sys_param.param_value
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    /**
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paramKey=").append(paramKey);
        sb.append(", paramValue=").append(paramValue);
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
        SysParam other = (SysParam) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getParamKey() == null ? other.getParamKey() == null : this.getParamKey().equals(other.getParamKey()))
            && (this.getParamValue() == null ? other.getParamValue() == null : this.getParamValue().equals(other.getParamValue()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getParamKey() == null) ? 0 : getParamKey().hashCode());
        result = prime * result + ((getParamValue() == null) ? 0 : getParamValue().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}