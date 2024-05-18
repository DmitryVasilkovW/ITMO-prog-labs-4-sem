package org.lab4.app.config;

import lombok.NoArgsConstructor;
import org.lab4.app.services.UserService;
import org.lab4.app.tokenManager.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@NoArgsConstructor
public class SecurityConfigurator
{
    private TokenFilter tokenFilter;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService)
    {
        this.userService = userService;
    }

    @Autowired
    public void setTokenFilter(TokenFilter tokenFilter)
    {
        this.tokenFilter = tokenFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    @Primary
    public AuthenticationManagerBuilder configureAuthenticationManagerBuilder(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
    {
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer ->
                        httpSecurityCorsConfigurer.configurationSource(request ->
                                new CorsConfiguration().applyPermitDefaultValues())
                )
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/owner").hasAnyRole("Owner", "Admin")
                        .requestMatchers("/owner/owners").hasRole("Admin")
                        .requestMatchers("/owner/{id}").hasRole("Admin")
                        .requestMatchers("/owner/name/{name}").hasRole("Admin")
                        .requestMatchers("/owner/birthDate/{birthDate}").hasRole("Admin")
                        .requestMatchers(HttpMethod.POST,"/owner").hasRole("Admin")
                        .requestMatchers(HttpMethod.PUT,"/owner/{id}").hasRole("Admin")
                        .requestMatchers(HttpMethod.DELETE,"/owner/{id}").hasRole("Admin")
                        .requestMatchers("/cats/{id}").hasRole("Admin")
                        .requestMatchers("/cats/name/{name}").hasRole("Admin")
                        .requestMatchers("/cats/breed/{breed}").hasRole("Admin")
                        .requestMatchers("/cats/birthDate/{birthDate}").hasRole("Admin")
                        .requestMatchers("/cats/color/{color}").hasRole("Admin")
                        .requestMatchers("/cats").hasRole("Admin")
                        .requestMatchers(HttpMethod.POST, "/cats").hasRole("Admin")
                        .requestMatchers(HttpMethod.PUT, "/cats/{id}").hasRole("Admin")
                        .requestMatchers(HttpMethod.DELETE, "/cats/{id}").hasRole("Admin")
                        .requestMatchers("/cats/id/{id}/ownerId/{ownerId}").hasAnyRole("Owner", "Admin")
                        .requestMatchers("/cats/name/{name}/id/{id}").hasAnyRole("Owner", "Admin")
                        .requestMatchers("/cats/breed/{breed}/id/{id}").hasAnyRole("Owner", "Admin")
                        .requestMatchers("/cats/birthDate/{birthDate}/id/{id}").hasAnyRole("Owner", "Admin")
                        .requestMatchers("/cats/color/{color}/id/{id}").hasAnyRole("Owner", "Admin")
                        .requestMatchers("/cats/id/{id}").hasAnyRole("Owner", "Admin")
                        .requestMatchers("/owner/**").fullyAuthenticated()
                        .requestMatchers("/cats/**").fullyAuthenticated()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}