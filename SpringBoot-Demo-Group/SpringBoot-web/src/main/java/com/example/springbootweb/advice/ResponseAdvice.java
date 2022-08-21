package com.example.springbootweb.advice;

import com.example.springbootweb.common.CommonResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: LengShui on 2022-08-21 12:26
 **/
@RestControllerAdvice(basePackages = "com.example.springbootweb.controller")
public class ResponseAdvice implements ResponseBodyAdvice<Object>{
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		if (body instanceof CommonResult) {
			return body;
		}
		return null;
	}
}
