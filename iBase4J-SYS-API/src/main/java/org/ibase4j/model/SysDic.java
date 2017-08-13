package org.ibase4j.model;

import org.ibase4j.core.base.BaseModel;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_dic")
@SuppressWarnings("serial")
public class SysDic extends BaseModel {
	@TableField("type_")
	private String type;
	@TableField("code_")
	private String code;
	private String codeText;
	private Integer sortNo;
	@TableField("editable_")
	private Boolean editable;
    @TableField("parent_type")
    private String parentType;
    @TableField("parent_code")
    private String parentCode;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the value of sys_dic.code_
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the value for sys_dic.code_
	 */
	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	/**
	 * @return the value of sys_dic.code_text
	 */
	public String getCodeText() {
		return codeText;
	}

	/**
	 * @param codeText
	 *            the value for sys_dic.code_text
	 */
	public void setCodeText(String codeText) {
		this.codeText = codeText == null ? null : codeText.trim();
	}

	/**
	 * @return the value of sys_dic.sort_no
	 */
	public Integer getSortNo() {
		return sortNo;
	}

	/**
	 * @param sortNo
	 *            the value for sys_dic.sort_no
	 */
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	/**
	 * @return the value of sys_dic.editable_
	 */
	public Boolean getEditable() {
		return editable;
	}

	/**
	 * @param editable
	 *            the value for sys_dic.editable_
	 */
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    /**
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", type=").append(type);
		sb.append(", code=").append(code);
		sb.append(", codeText=").append(codeText);
		sb.append(", sortNo=").append(sortNo);
		sb.append(", editable=").append(editable);
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
		SysDic other = (SysDic) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
				&& (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
				&& (this.getCodeText() == null ? other.getCodeText() == null
						: this.getCodeText().equals(other.getCodeText()))
				&& (this.getSortNo() == null ? other.getSortNo() == null : this.getSortNo().equals(other.getSortNo()))
				&& (this.getEditable() == null ? other.getEditable() == null
						: this.getEditable().equals(other.getEditable()))
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
		result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
		result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
		result = prime * result + ((getCodeText() == null) ? 0 : getCodeText().hashCode());
		result = prime * result + ((getSortNo() == null) ? 0 : getSortNo().hashCode());
		result = prime * result + ((getEditable() == null) ? 0 : getEditable().hashCode());
		result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
		result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
		result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
		result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
		return result;
	}
}
