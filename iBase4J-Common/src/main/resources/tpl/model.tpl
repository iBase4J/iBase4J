package org.ibase4j.model.{MODULE};

import org.ibase4j.core.base.BaseModel;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * {POJONAME}实体类
 * @author ShenHuaJie
 * @version {CURR_TIME}
 */
@TableName("{POJOTABLE}")
@SuppressWarnings("serial")
public class {POJONAME} extends BaseModel {
	{{GET_SET}}
}