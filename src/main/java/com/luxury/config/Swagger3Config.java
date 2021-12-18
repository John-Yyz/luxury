package com.luxury.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@ComponentScan("com.luxury.controller")
public class Swagger3Config implements WebMvcConfigurer{

    @Bean
    public Docket createRestApi() {
        //添加head参数start
        //ParameterBuilder tokenPar = new ParameterBuilder();
        //List<Parameter> pars = new ArrayList<Parameter>();
        //tokenPar.name("x-access-token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        //tokenPar.name("uid").description("用户ID").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        //tokenPar.name("platype").description("操作来源").modelRef(new ModelRef("int")).parameterType("header").required(false).build();
        //tokenPar.name("reqIp").description("操作IP").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        //tokenPar.name("jwtToken").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        //tokenPar.name("headParam").description("验签参数").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        //pars.add(tokenPar.build());
        //添加head参数end

        /*return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())// 对所有api进行监控
                .apis(RequestHandlerSelectors.basePackage("com.luxury.controller"))
                .paths(PathSelectors.regex("/error").negate())
                .build()
                .globalRequestParameters(getGlobalRequestParameters())
                .globalResponses(HttpMethod.PUT, getGlobalResponseMessage());*/
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()// 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.any())// 对指定目录下文件进行监控
                .paths(PathSelectors.regex("/error.*").negate())// 错误路径不监控
                .paths(PathSelectors.any())// 对根下所有路径进行监控
                .build()
                .globalRequestParameters(getGlobalRequestParameters())
                .globalResponses(HttpMethod.GET, getGlobalResponseMessage())
                .globalResponses(HttpMethod.POST, getGlobalResponseMessage())
                .globalResponses(HttpMethod.DELETE, getGlobalResponseMessage())
                .globalResponses(HttpMethod.PUT, getGlobalResponseMessage());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("更多请咨询服务开发者Ray。")
                .termsOfServiceUrl("http://localhost:8090/luxury/swagger-ui/index.html")//接口访问地址
                .version("1.0")
                .build();
    }

    /**
     * 生成全局通用参数
     *
     * @return
     */

    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("uid")
                .description("用户ID")
                .required(false)
                .in(ParameterType.HEADER)
                .build());
        parameters.add(new RequestParameterBuilder()
                    .name("platype")
                .description("操作来源")
                .required(false)
                .in(ParameterType.HEADER)
                .build());
        parameters.add(new RequestParameterBuilder()
                .name("reqIp")
                .description("请求IP")
                .required(false)
                .in(ParameterType.HEADER)
                .build());
        parameters.add(new RequestParameterBuilder()
                .name("jwtToken")
                .description("token")
                .required(false)
                .in(ParameterType.HEADER)
                .build());
        return parameters;
    }

    /**
     * 生成通用响应信息
     *
     * @return
     */
    private List<Response> getGlobalResponseMessage() {
        List<Response> responseList = new ArrayList<>();
        responseList.add(new ResponseBuilder().code("404").description("找不到资源").build());
        return responseList;
    }


}
