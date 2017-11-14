package org.ibase4j.service;

import java.io.File;
import java.util.Map;

import org.ibase4j.bean.Member;
import org.ibase4j.model.TMember;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import top.ibase4j.core.base.BaseService;
import top.ibase4j.core.util.DataUtil;
import top.ibase4j.core.util.InstanceUtil;
import top.ibase4j.core.util.QrcodeUtil;
import top.ibase4j.core.util.UploadUtil;

/**
 * <p>
 * 会员 服务实现类
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-10-12
 */
@Service
@CacheConfig(cacheNames = "member")
public class MemberService extends BaseService<TMember> {

    private final TMember MEMBER = new TMember();

    @Transactional
    public TMember update(TMember record) {
        if (record.getId() == null) {
            record.setUserName("会员" + record.getPhone().substring(7, 10));
            record.setNickName("会员" + record.getPhone().substring(7, 10));
        }
        TMember result = super.update(record);

        if (result != null) {
            if (result.getId() != null) {
                if (DataUtil.isEmpty(result.getQrCode())) {
                    File file = new File("/usr/temp");
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    Map<String, Object> data = InstanceUtil.newHashMap();
                    data.put("id", result.getId());
                    data.put("type", "0");
                    String qrcodeFilePath = QrcodeUtil.createQrcode(JSON.toJSONString(data), "/usr/temp");
                    String path = UploadUtil.remove2DFS("member", result.getId().toString(), qrcodeFilePath)
                        .getRemotePath();
                    if (DataUtil.isNotEmpty(path)) {
                        result.setQrCode(path);
                        result = super.update(result);
                    }
                }
            }
        }
        return result;
    }

    public Member getBaseInfo(Long id) {
        TMember member = super.queryById(id);
        Member result = null;
        if (member != null) {
            result = InstanceUtil.to(member, Member.class);
        }
        return result;
    }

    public TMember getInfo(Long id) {
        TMember member = super.queryById(id);
        if (member != null) {
            member.setPassword(null);
            member.setUuid(null);
        }
        return member;
    }

    public Object updataphone(Map<String, Object> map) {
        TMember member1 = new TMember();
        member1.setIdCard((String)map.get("idCard"));
        member1.setPhone((String)map.get("orderPhone"));
        member1.setRealName((String)map.get("realname"));
        TMember member2 = super.selectOne(member1);
        if (member2.getId() == null) {
            return "请输入正确的信息 ";
        } else {
            member2.setPhone((String)map.get("newPhone"));
            return super.update(member2);
        }
    }

    public Object authentication(Map<String, Object> map) {// 实名认证
        MEMBER.setId(Long.parseLong((String)map.get("memberId")));
        TMember selectOne = super.selectOne(MEMBER);
        selectOne.setRealName((String)map.get("realName"));
        selectOne.setIdCard((String)map.get("idCard"));
        return super.update(selectOne);
    }
}
