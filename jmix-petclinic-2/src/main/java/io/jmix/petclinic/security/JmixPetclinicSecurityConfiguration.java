package io.jmix.petclinic.security;

import io.jmix.core.JmixSecurityFilterChainOrder;
import io.jmix.security.util.JmixHttpSecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * This configuration complements standard security configurations that come from Jmix modules (security-flowui, oidc,
 * authserver).
 * <p>
 * You can configure custom API endpoints security by defining {@link SecurityFilterChain} beans in this class.
 * In most cases, custom SecurityFilterChain must be applied first, so the proper
 * {@link org.springframework.core.annotation.Order} should be defined for the bean. The order value from the
 * {@link io.jmix.core.JmixSecurityFilterChainOrder#CUSTOM} is guaranteed to be smaller than any other filter chain
 * order from Jmix.
 * <p>
 * Example:
 *
 * <pre>
 * &#064;Bean
 * &#064;Order(JmixSecurityFilterChainOrder.CUSTOM)
 * SecurityFilterChain publicFilterChain(HttpSecurity http) throws Exception {
 *     http.securityMatcher("/public/**")
 *             .authorizeHttpRequests(authorize ->
 *                     authorize.anyRequest().permitAll()
 *             );
 *     return http.build();
 * }
 * </pre>
 *
 * @see io.jmix.securityflowui.security.FlowuiVaadinWebSecurity
 */
@Configuration
public class JmixPetclinicSecurityConfiguration {


    @Bean
    @Order(JmixSecurityFilterChainOrder.CUSTOM)
    SecurityFilterChain actuatorFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/actuator/**")
                .authorizeHttpRequests(authorize ->
                        authorize.anyRequest().permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable);
        JmixHttpSecurityUtils.configureAnonymous(http);
        return http.build();
    }

}