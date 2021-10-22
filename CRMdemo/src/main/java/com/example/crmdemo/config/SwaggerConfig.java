package com.example.crmdemo.config;


import com.example.crmdemo.common.config.BaseSwaggerConfig;
import com.example.crmdemo.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 *
 * @Profile 注解 标识加载在dev和test文件使用
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig{

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.example.crmdemo.modules")
                .title("crmdemo")
                .description("crmdemo项目相关接口文档")
                .contactName("crmdemo")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }

}
