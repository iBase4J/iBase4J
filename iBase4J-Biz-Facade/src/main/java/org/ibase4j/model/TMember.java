package org.ibase4j.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModelProperty;
import top.ibase4j.core.base.BaseModel;

/**
 * <p>
 * 会员
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-10-12
 */
@TableName("t_member")
@SuppressWarnings("serial")
public class TMember extends BaseModel {

    /**
     * 姓名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 密码
     */
    @TableField("password_")
    private String password;
    /**
     * 电话
     */
    @TableField("phone_")
    private String phone;

    @ApiModelProperty(value = "APP会话token")
    @TableField("token_")
    private String token;

    @ApiModelProperty(value = "手机端唯一标识")
    @TableField("uuid_")
    private String uuid;
    
    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;
    /**
     * 邮箱
     */
    @TableField("email_")
    private String email;
    /**
     * 性别(0:未知;1:男;2:女)
     */
    @TableField("sex_")
    private Integer sex;
    /**
     * 头像
     */
    @TableField("avatar_")
    private String avatar;
    /**
     * 二维码
     */
    @TableField("qr_code")
    private String qrCode;
    /**
     * 个性签名
     */
    @TableField("personal_sign")
    private String personalSign;
    /**
     * 身份证号码
     */
    @TableField("id_card")
    private String idCard;
    /**
     * 出生日期
     */
    @TableField("birth_day")
    private String birthDay;
    /**
     * 微信
     */
    @TableField("wei_xin")
    private String weiXin;
    /**
     * 微博
     */
    @TableField("wei_bo")
    private String weiBo;
    /**
     * QQ
     */
    @TableField("qq_")
    private String qq;
    /**
     * 是否在线
     */
    @TableField("is_online")
    private Integer isOnline;
    /**
     * 所在国家
     */
    @TableField("location_country")
    private String locationCountry;
    /**
     * 所在省市
     */
    @TableField("location_province")
    private String locationProvince;
    /**
     * 所在市
     */
    @TableField("location_city")
    private String locationCity;
    /**
     * 所在区
     */
    @TableField("location_area")
    private String locationArea;
    /**
     * 信用积分
     */
    @TableField("credit_score")
    private Integer creditScore;
    /**
     * 实名状态
     */
    @TableField("is_real_name")
    private Integer isRealName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getPersonalSign() {
        return personalSign;
    }

    public void setPersonalSign(String personalSign) {
        this.personalSign = personalSign;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    public String getWeiBo() {
        return weiBo;
    }

    public void setWeiBo(String weiBo) {
        this.weiBo = weiBo;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }

    public String getLocationProvince() {
        return locationProvince;
    }

    public void setLocationProvince(String locationProvince) {
        this.locationProvince = locationProvince;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationArea() {
        return locationArea;
    }

    public void setLocationArea(String locationArea) {
        this.locationArea = locationArea;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public Integer getIsRealName() {
        return isRealName;
    }

    public void setIsRealName(Integer isRealName) {
        this.isRealName = isRealName;
    }

}
