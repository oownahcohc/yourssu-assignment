package yourssu.assignment.config.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.builders.ResponseBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.ApiKey
import springfox.documentation.service.Contact
import springfox.documentation.service.SecurityScheme
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.DocExpansion
import springfox.documentation.swagger.web.UiConfiguration
import springfox.documentation.swagger.web.UiConfigurationBuilder
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.stream.Collectors
import java.util.stream.Stream

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration::class)
class SwaggerConfig : WebMvcConfigurationSupport() {

    // springfox-swagger-ui 경로 등록
    public override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/swagger-ui/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
    }

    @Bean
    fun uiConfig(): UiConfiguration? {
        return UiConfigurationBuilder.builder()
            .docExpansion(DocExpansion.LIST)
            .build()
    }

    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .securitySchemes(authorization())
            .ignoredParameterTypes()
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java))
            .paths(PathSelectors.ant("/**"))
            .build()
            .useDefaultResponseMessages(false)
            .globalResponses(HttpMethod.GET, createGlobalResponseMessages())
            .globalResponses(HttpMethod.POST, createGlobalResponseMessages())
            .globalResponses(HttpMethod.PUT, createGlobalResponseMessages())
            .globalResponses(HttpMethod.DELETE, createGlobalResponseMessages())
    }

    private fun createGlobalResponseMessages(): List<springfox.documentation.service.Response?>? {
        return Stream.of(
            HttpStatus.BAD_REQUEST,
            HttpStatus.UNAUTHORIZED,
            HttpStatus.CONFLICT,
            HttpStatus.FORBIDDEN,
            HttpStatus.NOT_FOUND,
            HttpStatus.INTERNAL_SERVER_ERROR,
            HttpStatus.BAD_GATEWAY,
            HttpStatus.SERVICE_UNAVAILABLE
        )
            .map { httpStatus: HttpStatus ->
                createResponseMessage(httpStatus)
            }
            .collect(Collectors.toList())
    }

    private fun createResponseMessage(httpStatus: HttpStatus): springfox.documentation.service.Response? {
        return ResponseBuilder()
            .code(httpStatus.value().toString())
            .description(httpStatus.reasonPhrase)
            .build()
    }

    private fun apiInfo(): ApiInfo? {
        return ApiInfoBuilder()
            .title(API_NAME)
            .version(API_VERSION)
            .description(API_DESCRIPTION)
            .contact(Contact(CREATOR, CONTACT_URL, EMAIL))
            .build()
    }

    private fun authorization(): List<SecurityScheme>? {
        return listOf<SecurityScheme>(ApiKey("Authorization", "Authorization", "header"))
    }

    companion object {
        private const val API_NAME = "억지사지"
        private const val API_VERSION = "0.0.1"
        private const val API_DESCRIPTION = "억지사지 API 명세서"
        private const val CREATOR = "Cho Chan Woo"
        private const val EMAIL = "chocw0402@gmail.com"
        private const val CONTACT_URL = "https://www.instagram.com/breakfast_wu/"
    }
}