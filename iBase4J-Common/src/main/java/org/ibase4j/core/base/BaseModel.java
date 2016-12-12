package org.ibase4j.core.base;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

@SuppressWarnings("serial")
public class BaseModel implements Serializable {
    @TableId(value = "id_", type = IdType.ID_WORKER)
    private Long id_;
    @TableField("enable_")
    private Boolean enable;
    @TableField("remark_")
    private String remark;
    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;

    /**
     * @return the id
     */
    public Long getId_() {
        return id_;
    }
    
     String getId() {
        return id_ == null ? "" : id_.toString();
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId_(Long id) {
        this.id_ = id;
    }
    
    public void setId(String id) {
        this.id_ = id == null ? null : Long.parseLong(id);
    }

    /**
     * @return the enable
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * @param enable
     *            the enable to set
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return the createBy
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy
     *            the createBy to set
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the updateBy
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * @param updateBy
     *            the updateBy to set
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
