package org.ibase4j.model.sys;

import org.ibase4j.core.base.BaseModel;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_dic_index")
@SuppressWarnings("serial")
public class SysDicIndex extends BaseModel {
    private String catalogId;
    @TableField("key_")
    private String keyValue;
    @TableField("name_")
    private String keyName;

    /**
     * @return the value of sys_dic_index.catalog_id
     */
    public String getCatalogId() {
        return catalogId;
    }

    /**
     * @param catalogId the value for sys_dic_index.catalog_id
     */
    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId == null ? null : catalogId.trim();
    }

    /**
     * @return the value of sys_dic_index.key_
     */
    public String getKeyValue() {
        return keyValue;
    }

    /**
     * @param key the value for sys_dic_index.key_
     */
    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue == null ? null : keyValue.trim();
    }

    /**
     * @return the value of sys_dic_index.name_
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * @param name the value for sys_dic_index.name_
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName == null ? null : keyName.trim();
    }

    /**
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", catalogId=").append(catalogId);
        sb.append(", key=").append(keyValue);
        sb.append(", name=").append(keyName);
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
        SysDicIndex other = (SysDicIndex)that;
        return (this.getId_() == null ? other.getId_() == null : this.getId_().equals(other.getId_()))
            && (this.getCatalogId() == null ? other.getCatalogId() == null
                : this.getCatalogId().equals(other.getCatalogId()))
            && (this.getKeyValue() == null ? other.getKeyValue() == null : this.getKeyValue().equals(other.getKeyValue()))
            && (this.getKeyName() == null ? other.getKeyName() == null : this.getKeyName().equals(other.getKeyName()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null
                : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null
                : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null
                : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null
                : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId_() == null) ? 0 : getId_().hashCode());
        result = prime * result + ((getCatalogId() == null) ? 0 : getCatalogId().hashCode());
        result = prime * result + ((getKeyValue() == null) ? 0 : getKeyValue().hashCode());
        result = prime * result + ((getKeyName() == null) ? 0 : getKeyName().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}
