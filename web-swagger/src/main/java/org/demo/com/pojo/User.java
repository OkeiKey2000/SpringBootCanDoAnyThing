package org.demo.com.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*
*
* @author: LengShui on 2022-08-10 15:25
*
**/
@Data
@ApiModel(value = "用户实体")
public class User {

	@ApiModelProperty(value = "id")
	private Integer id;

	@ApiModelProperty(value = "用户名")
	private  String username;

	@ApiModelProperty(value = "性别，0男，1女")
	private Integer sex;

}
