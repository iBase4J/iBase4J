package org.ibase4j.model.sys;

import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import top.ibase4j.core.base.BaseModel;

@TableName("sys_menu")
@SuppressWarnings("serial")
public class SysMenu extends BaseModel {
	public SysMenu() {
	}

	public SysMenu(String request, String menuName) {
		this.request = request;
		this.menuName = menuName;
	}

	private String menuName;
	private Boolean menuType;
	private Long parentId;

	@TableField("iconcls_")
	private String iconcls;

	@TableField("request_")
	private String request;

	@TableField("expand_")
	private Boolean expand;

	private Integer sortNo;
	private String isShow;

	@TableField("permission_")
	private String permission;

	@TableField(exist = false)
	private String parentName;
	@TableField(exist = false)
	private Integer leaf = 1;
	@TableField(exist = false)
	private String typeName;
	@TableField(exist = false)
	private String permissionText;
	@TableField(exist = false)
	private List<SysMenu> menuBeans;
	
	/**
	 * @return the value of sys_menu.menu_name
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * @param menuName
	 *            the value for sys_menu.menu_name
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName == null ? null : menuName.trim();
	}

	/**
	 * @return the value of sys_menu.menu_type
	 */
	public Boolean getMenuType() {
		return menuType;
	}

	/**
	 * @param menuType
	 *            the value for sys_menu.menu_type
	 */
	public void setMenuType(Boolean menuType) {
		this.menuType = menuType;
	}

	/**
	 * @return the value of sys_menu.parent_id
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the value for sys_menu.parent_id
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the value of sys_menu.iconcls_
	 */
	public String getIconcls() {
		return iconcls;
	}

	/**
	 * @param iconcls
	 *            the value for sys_menu.iconcls_
	 */
	public void setIconcls(String iconcls) {
		this.iconcls = iconcls == null ? null : iconcls.trim();
	}

	/**
	 * @return the value of sys_menu.request_
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * @param request
	 *            the value for sys_menu.request_
	 */
	public void setRequest(String request) {
		this.request = request == null ? null : request.trim();
	}

	/**
	 * @return the value of sys_menu.expand_
	 */
	public Boolean getExpand() {
		return expand;
	}

	/**
	 * @param expand
	 *            the value for sys_menu.expand_
	 */
	public void setExpand(Boolean expand) {
		this.expand = expand;
	}

	/**
	 * @return the value of sys_menu.sort_no
	 */
	public Integer getSortNo() {
		return sortNo;
	}

	/**
	 * @param sortNo
	 *            the value for sys_menu.sort_no
	 */
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	/**
	 * @return the value of sys_menu.is_show
	 */
	public String getIsShow() {
		return isShow;
	}

	/**
	 * @param isShow
	 *            the value for sys_menu.is_show
	 */
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	/**
	 * @return the value of sys_menu.permission_
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * @param permission
	 *            the value for sys_menu.permission_
	 */
	public void setPermission(String permission) {
		this.permission = permission == null ? null : permission.trim();
	}

	public Integer getLeaf() {
		return leaf;
	}

	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getPermissionText() {
		return permissionText;
	}

	public void setPermissionText(String permissionText) {
		this.permissionText = permissionText;
	}

	public List<SysMenu> getMenuBeans() {
		return menuBeans;
	}

	public void setMenuBeans(List<SysMenu> menuBeans) {
		this.menuBeans = menuBeans;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", menuName=").append(menuName);
		sb.append(", menuType=").append(menuType);
		sb.append(", parentId=").append(parentId);
		sb.append(", iconcls=").append(iconcls);
		sb.append(", request=").append(request);
		sb.append(", expand=").append(expand);
		sb.append(", sortNo=").append(sortNo);
		sb.append(", isShow=").append(isShow);
		sb.append(", permission=").append(permission);
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
		SysMenu other = (SysMenu) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getMenuName() == null ? other.getMenuName() == null
						: this.getMenuName().equals(other.getMenuName()))
				&& (this.getMenuType() == null ? other.getMenuType() == null
						: this.getMenuType().equals(other.getMenuType()))
				&& (this.getParentId() == null ? other.getParentId() == null
						: this.getParentId().equals(other.getParentId()))
				&& (this.getIconcls() == null ? other.getIconcls() == null
						: this.getIconcls().equals(other.getIconcls()))
				&& (this.getRequest() == null ? other.getRequest() == null
						: this.getRequest().equals(other.getRequest()))
				&& (this.getExpand() == null ? other.getExpand() == null : this.getExpand().equals(other.getExpand()))
				&& (this.getSortNo() == null ? other.getSortNo() == null : this.getSortNo().equals(other.getSortNo()))
				&& (this.getIsShow() == null ? other.getIsShow() == null : this.getIsShow().equals(other.getIsShow()))
				&& (this.getPermission() == null ? other.getPermission() == null
						: this.getPermission().equals(other.getPermission()))
				&& (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
				&& (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
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
		result = prime * result + ((getMenuName() == null) ? 0 : getMenuName().hashCode());
		result = prime * result + ((getMenuType() == null) ? 0 : getMenuType().hashCode());
		result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
		result = prime * result + ((getIconcls() == null) ? 0 : getIconcls().hashCode());
		result = prime * result + ((getRequest() == null) ? 0 : getRequest().hashCode());
		result = prime * result + ((getExpand() == null) ? 0 : getExpand().hashCode());
		result = prime * result + ((getSortNo() == null) ? 0 : getSortNo().hashCode());
		result = prime * result + ((getIsShow() == null) ? 0 : getIsShow().hashCode());
		result = prime * result + ((getPermission() == null) ? 0 : getPermission().hashCode());
		result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
		result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
		result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
		result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
		return result;
	}
}