package com.example.springbootweb.common;

import lombok.Data;

/**
 * @author: LengShui on 2022-08-21 12:07
 **/

public enum ResultEnum implements IResult{
	/**
	 * 通用结果校验
	 */
	SUCCESS(20001,"接口调用成功"),
	ERROR(30001, "接口调用失败"),
	COMMON_FAILED(40001, "通用异常失败");

	Integer code;
	String message;
	@Override
	public Integer getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	 ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
