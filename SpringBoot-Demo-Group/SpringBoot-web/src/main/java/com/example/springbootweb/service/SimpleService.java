package com.example.springbootweb.service;

import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author: LengShui on 2022-08-21 12:00
 **/
@Service
public class SimpleService {
	public Boolean getRes(Integer data) {
		if (Objects.equals(data, 1)) {
			return false;
		}
		if (Objects.equals(data, 2)) {
			throw
		}
		return true;
	}
}
