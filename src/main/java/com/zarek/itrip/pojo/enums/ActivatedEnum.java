package com.zarek.itrip.pojo.enums;

/**
 * <b>用户激活状态枚举信息</b>
 * @author Arthur
 * @version 1.0.0
 * @since 1.0.0
 */
public enum ActivatedEnum {
	ACTIVATED_ENABLE(1, "已激活"),
	ACTIVATED_DISABLE(0, "未激活");

	private Integer code;                       // 激活状态编码
	private String remark;                      // 类型说明

	private ActivatedEnum(Integer code, String remark) {
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
