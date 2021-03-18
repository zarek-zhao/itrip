package com.zarek.itrip.pojo.enums;

/**
 * <b>用户类型枚举信息</b>
 * @author Arthur
 * @version 1.0.0
 * @since 1.0.0
 */
public enum UserTypeEnum {
	USER_TYPE_SELF(0, "自注册用户"),
	USER_TYPE_WEICHAT(1, "微信登录"),
	USER_TYPE_QQ(2, "QQ登录"),
	USER_TYPE_WEIBO(3, "微博登录");

	private Integer code;                       // 类型编码
	private String remark;                      // 类型说明

	private UserTypeEnum(Integer code, String remark) {
		this.code = code;
		this.remark = remark;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
