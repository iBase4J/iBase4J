package org.ibase4j.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class Member implements Serializable {
	private Long id;
	private String nickName;
	private String avatar;
    private String sex;
	private String qrCode;
	@ApiModelProperty(value = "国家")
	private String locationCountry = "ZHCN";
	@ApiModelProperty(value = "省份")
	private String locationProvince;
	@ApiModelProperty(value = "城市")
	private String locationCity;
	@ApiModelProperty(value = "地区")
	private String locationArea;
	@ApiModelProperty(value = "出生日期")
	private Date birthDay;
	@ApiModelProperty(value = "个性签名")
	private String personalSign;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
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

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getPersonalSign() {
		return personalSign;
	}

	public void setPersonalSign(String personalSign) {
		this.personalSign = personalSign;
	}
}
