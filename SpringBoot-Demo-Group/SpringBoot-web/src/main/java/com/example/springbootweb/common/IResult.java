package com.example.springbootweb.common;

/**
 * @author: LengShui on 2022-08-21 12:06
 **/
public interface IResult {
	/**
	 * 获取code信息
	 * @return
	 */
	Integer getCode();

	/**
	 * 获取描述信息
	 * @return
	 */
	String getMessage();
}
