/**
 * 
 */
package org.ibase4j.core.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
 * 
 * @author ShenHuaJie
 * 
 * @version 2016年6月3日 下午2:30:14
 * 
 */
public interface BaseMapper<T extends BaseModel> extends com.baomidou.mybatisplus.mapper.BaseMapper<T> {

	List<Long> selectIdPage(@Param("cm") Map<String, Object> params);

	List<Long> selectIdPage(RowBounds rowBounds, @Param("cm") Map<String, Object> params);

}
