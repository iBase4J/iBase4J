/**
 * 
 */
package org.ibase4j.core.support.generator;

import java.util.List;

import org.mybatis.generator.api.PluginAdapter;

/**
 * 
 * @author ShenHuaJie
 * @version 2016年6月18日 下午11:38:25
 */
public class ExpandPlugin extends PluginAdapter {

	public boolean validate(List<String> warnings) {
		return true;
	}

}
