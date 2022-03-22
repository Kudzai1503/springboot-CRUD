package com.example.demo;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:swagger.properties")
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI(OpenApiProperties properties) {

        return new OpenAPI()
                .info(getInfo(properties));
    }

    @Bean
    public Info getInfo(OpenApiProperties properties) {
        return new Info()
                .title(properties.getProjectTitle())
                .description(properties.getProjectDescription())
                .version(properties.getProjectVersion())
                .license(getLicense());
    }

    @Bean
    public OpenApiProperties openApiProperties(@Value("${swagger.application.description}") String appDesciption,
                                               @Value("${swagger.application.version}") String appVersion,
                                               @Value("${swagger.application.title}") String appTitle) {
        OpenApiProperties properties = new OpenApiProperties();
        properties.setProjectDescription(appDesciption);
        properties.setProjectTitle(appTitle);
        properties.setProjectVersion(appVersion);
        return properties;
    }

    private License getLicense() {
        return new License()
                .name("Unlicense")
                .url("https://unlicense.org/");
    }
}

class OpenApiProperties {
    private String projectTitle;
    private String projectDescription;
    private String projectVersion;

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectVersion() {
        return projectVersion;
    }

    public void setProjectVersion(String projectVersion) {
        this.projectVersion = projectVersion;
    }
}

