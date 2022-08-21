package com.example.springbootweb.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: LengShui on 2022-08-21 12:20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
	private Integer code;
	private String message;
	private T data;
	public static <T> CommonResult<T> success(T data) {
		return new CommonResult<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
	}

	public static <T> CommonResult<T> success(String message, T data) {
		return new CommonResult<>(ResultEnum.SUCCESS.getCode(), message, data);
	}

	public static CommonResult<?> failed() {
		return new CommonResult<>(ResultEnum.COMMON_FAILED.getCode(), ResultEnum.COMMON_FAILED.getMessage(), null);
	}

	public static CommonResult<?> failed(String message) {
		return new CommonResult<>(ResultEnum.COMMON_FAILED.getCode(), message, null);
	}

	public static CommonResult<?> failed(IResult errorResult) {
		return new CommonResult<>(errorResult.getCode(), errorResult.getMessage(), null);
	}

	public static <T> CommonResult<T> instance(Integer code, String message, T data) {
		CommonResult<T> CommonResult = new CommonResult<>();
		CommonResult.setCode(code);
		CommonResult.setMessage(message);
		CommonResult.setData(data);
		return CommonResult;
	}
}
