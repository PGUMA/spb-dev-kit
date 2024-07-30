package lab.pguma.spb_dev_kit.config

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        // NOTE:セキュリティ的にはNGな実装です
        // https://spring.pleiades.io/spring-security/reference/servlet/exploits/csrf.html#disable-csrf
        http.csrf { csrf ->
            csrf.disable() // Spring Securityを導入するとデフォルトで有効化されるため検証が手間になるので無効化
        }

        http
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers(EndpointRequest.toAnyEndpoint())
                    .authenticated()
                    .anyRequest().permitAll()

            }
            .httpBasic(withDefaults())

        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build()
        return InMemoryUserDetailsManager(user)
    }
}