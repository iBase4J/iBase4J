package org.ibase4j.core.base;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public abstract class BaseModel implements Serializable {

	public abstract Integer getId();

	public abstract void setId(Integer id);

	public abstract Integer getEnable();

	public abstract void setEnable(Integer enable);

	public abstract String getRemark();

	public abstract void setRemark(String remark);

	public abstract Date getCreateTime();

	public abstract void setCreateTime(Date createTime);

	public abstract Integer getCreateBy();

	public abstract void setCreateBy(Integer createBy);

	public abstract Date getUpdateTime();

	public abstract void setUpdateTime(Date updateTime);

	public abstract Integer getUpdateBy();

	public abstract void setUpdateBy(Integer updateBy);
}
