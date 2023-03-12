package az.adnsu.tourmanagementproject.security;

import az.adnsu.tourmanagementproject.domain.enumeration.RoleName;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@AllArgsConstructor
@Import(SecurityProblemSupport.class)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final SecurityProblemSupport securityProblemSupport;
    private final TokenProvider tokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/authentication").permitAll()
                .antMatchers(HttpMethod.GET, "/users/{username}").hasRole(RoleName.MANAGER.name())
//                .antMatchers(HttpMethod.POST, "/tours").hasRole(RoleName.MANAGER.name())
                .antMatchers(HttpMethod.POST, "/tours").hasAnyRole(RoleName.MANAGER.name(),RoleName.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/tours/{id}").hasRole(RoleName.MANAGER.name())
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated();

        http
                .addFilterBefore(new JWTFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(securityProblemSupport)
                .accessDeniedHandler(securityProblemSupport);

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .formLogin().disable()
                .headers().frameOptions().disable();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
