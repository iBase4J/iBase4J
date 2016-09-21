package org.ibase4j.model.sys;

import org.ibase4j.core.base.BaseModel;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_catalog")
@SuppressWarnings("serial")
public class SysCatalog extends BaseModel {
    private String cascadeId;
    private String rootKey;
    private String rootName;
    @TableField("name_")
    private String name;
    private String hotkey;
    private String parentId;
    private String isLeaf;
    private String isAutoExpand;
    private String iconName;
    private Integer sortNo;

    /**
     * @return the value of sys_catalog.cascade_id
     */
    public String getCascadeId() {
        return cascadeId;
    }

    /**
     * @param cascadeId the value for sys_catalog.cascade_id
     */
    public void setCascadeId(String cascadeId) {
        this.cascadeId = cascadeId == null ? null : cascadeId.trim();
    }

    /**
     * @return the value of sys_catalog.root_key
     */
    public String getRootKey() {
        return rootKey;
    }

    /**
     * @param rootKey the value for sys_catalog.root_key
     */
    public void setRootKey(String rootKey) {
        this.rootKey = rootKey == null ? null : rootKey.trim();
    }

    /**
     *
     * @return the value of sys_catalog.root_name
     */
    public String getRootName() {
        return rootName;
    }

    /**
     * @param rootName the value for sys_catalog.root_name
     */
    public void setRootName(String rootName) {
        this.rootName = rootName == null ? null : rootName.trim();
    }

    /**
     * @return the value of sys_catalog.name_
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the value for sys_catalog.name_
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return the value of sys_catalog.hotkey_
     */
    public String getHotkey() {
        return hotkey;
    }

    /**
     * @param hotkey the value for sys_catalog.hotkey_
     */
    public void setHotkey(String hotkey) {
        this.hotkey = hotkey == null ? null : hotkey.trim();
    }

    /**
     * @return the value of sys_catalog.parent_id
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId the value for sys_catalog.parent_id
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * @return the value of sys_catalog.is_leaf_
     */
    public String getIsLeaf() {
        return isLeaf;
    }

    /**
     * @param isLeaf the value for sys_catalog.is_leaf_
     */
    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf == null ? null : isLeaf.trim();
    }

    /**
     * @return the value of sys_catalog.is_auto_expand
     */
    public String getIsAutoExpand() {
        return isAutoExpand;
    }

    /**
     * @param isAutoExpand the value for sys_catalog.is_auto_expand
     */
    public void setIsAutoExpand(String isAutoExpand) {
        this.isAutoExpand = isAutoExpand == null ? null : isAutoExpand.trim();
    }

    /**
     * @return the value of sys_catalog.icon_name
     */
    public String getIconName() {
        return iconName;
    }

    /**
     * @param iconName the value for sys_catalog.icon_name
     */
    public void setIconName(String iconName) {
        this.iconName = iconName == null ? null : iconName.trim();
    }

    /**
     * @return the value of sys_catalog.sort_no
     */
    public Integer getSortNo() {
        return sortNo;
    }

    /**
     * @param sortNo the value for sys_catalog.sort_no
     */
    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    /**
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cascadeId=").append(cascadeId);
        sb.append(", rootKey=").append(rootKey);
        sb.append(", rootName=").append(rootName);
        sb.append(", name=").append(name);
        sb.append(", hotkey=").append(hotkey);
        sb.append(", parentId=").append(parentId);
        sb.append(", isLeaf=").append(isLeaf);
        sb.append(", isAutoExpand=").append(isAutoExpand);
        sb.append(", iconName=").append(iconName);
        sb.append(", sortNo=").append(sortNo);
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
        SysCatalog other = (SysCatalog)that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCascadeId() == null ? other.getCascadeId() == null
                : this.getCascadeId().equals(other.getCascadeId()))
            && (this.getRootKey() == null ? other.getRootKey() == null : this.getRootKey().equals(other.getRootKey()))
            && (this.getRootName() == null ? other.getRootName() == null
                : this.getRootName().equals(other.getRootName()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getHotkey() == null ? other.getHotkey() == null : this.getHotkey().equals(other.getHotkey()))
            && (this.getParentId() == null ? other.getParentId() == null
                : this.getParentId().equals(other.getParentId()))
            && (this.getIsLeaf() == null ? other.getIsLeaf() == null : this.getIsLeaf().equals(other.getIsLeaf()))
            && (this.getIsAutoExpand() == null ? other.getIsAutoExpand() == null
                : this.getIsAutoExpand().equals(other.getIsAutoExpand()))
            && (this.getIconName() == null ? other.getIconName() == null
                : this.getIconName().equals(other.getIconName()))
            && (this.getSortNo() == null ? other.getSortNo() == null : this.getSortNo().equals(other.getSortNo()))
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
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCascadeId() == null) ? 0 : getCascadeId().hashCode());
        result = prime * result + ((getRootKey() == null) ? 0 : getRootKey().hashCode());
        result = prime * result + ((getRootName() == null) ? 0 : getRootName().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getHotkey() == null) ? 0 : getHotkey().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getIsLeaf() == null) ? 0 : getIsLeaf().hashCode());
        result = prime * result + ((getIsAutoExpand() == null) ? 0 : getIsAutoExpand().hashCode());
        result = prime * result + ((getIconName() == null) ? 0 : getIconName().hashCode());
        result = prime * result + ((getSortNo() == null) ? 0 : getSortNo().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}
