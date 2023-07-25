//package com.example.springboottools.config;
//
//import com.example.springboottools.util.ResponseStatus;
//import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import springfox.documentation.builders.*;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.schema.ScalarType;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * swagger config for open api.
// *
// * @author pdai
// */
//@Configuration
//@EnableOpenApi
//public class OpenApiConfig {
//
//     Java生成接口文档的最佳实现: SwaggerV3(OpenAPI）+ Knife4J。
//
//    /**
//     * open api extension by knife4j.
//     */
//    private final OpenApiExtensionResolver openApiExtensionResolver;
//
//    @Autowired
//    public OpenApiConfig(OpenApiExtensionResolver openApiExtensionResolver) {
//        this.openApiExtensionResolver = openApiExtensionResolver;
//    }
//
//    /**
//     * @return swagger config
//     */
//    @Bean
//    public Docket openApi() {
//        String groupName = "Test Group";
//        return new Docket(DocumentationType.OAS_30)
//                .groupName(groupName)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.any())
//                .build()
//                .globalRequestParameters(getGlobalRequestParameters())
//                .globalResponses(HttpMethod.GET, getGlobalResponse())
//                .extensions(openApiExtensionResolver.buildExtensions(groupName))
//                .extensions(openApiExtensionResolver.buildSettingExtensions());
//    }
//
//    /**
//     * @return global response code->description
//     */
//    private List<Response> getGlobalResponse() {
//        return ResponseStatus.HTTP_STATUS_ALL.stream().map(
//                        a -> new ResponseBuilder().code(a.getResponseCode()).description(a.getDescription()).build())
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * @return global request parameters
//     */
//    private List<RequestParameter> getGlobalRequestParameters() {
//        List<RequestParameter> parameters = new ArrayList<>();
//        parameters.add(new RequestParameterBuilder()
//                .name("AppKey")
//                .description("App Key")
//                .required(false)
//                .in(ParameterType.QUERY)
//                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//                .required(false)
//                .build());
//        return parameters;
//    }
//
//    /**
//     * @return api info
//     */
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("My API")
//                .description("test api")
//                .contact(new Contact("lw", "http://lw.tech", "780442619@qq.com"))
//                .termsOfServiceUrl("http://lw.tech/")
//                .version("1.0")
//                .build();
//    }
//}