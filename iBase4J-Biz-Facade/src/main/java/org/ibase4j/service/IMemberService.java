package org.ibase4j.service;

import java.util.Map;

import org.ibase4j.bean.Member;
import org.ibase4j.model.TMember;

import top.ibase4j.core.base.IBaseService;

/**
 * @author ShenHuaJie
 * @since 2018年4月24日 上午10:45:11
 */
public interface IMemberService extends IBaseService<TMember> {
    public Member getBaseInfo(Long id);

    public TMember getInfo(Long id);

    public Object updataphone(Map<String, Object> map);

    public Object authentication(Map<String, Object> map);// 实名认证
}
