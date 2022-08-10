package org.demo.com.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: LengShui on 2022-08-10 15:24
 **/
@Configuration
@EnableSwagger2
@EnableKnife4j
public class Knife4jConfig {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.demo.com.web.test"))
				.paths(PathSelectors.any())
				.build();

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.description("微微一笑接口测试文档")
				.contact(new Contact("哈哈哈", "https://github.com.orikey0", "lhhsdustenactus@163.com"))
				.version("v1.1.0")
				.title("API测试文档")
				.build();
	}
}
