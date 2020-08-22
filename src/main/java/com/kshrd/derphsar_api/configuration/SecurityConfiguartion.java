package com.kshrd.derphsar_api.configuration;


import com.kshrd.derphsar_api.configuration.jwtconfiguration.JwtAuthenticationEntryPoint;
import com.kshrd.derphsar_api.configuration.jwtconfiguration.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguartion extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userServiceImp;
        @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()

//                .requestMatchers()
//                    .antMatchers(HttpMethod.GET,"/api/v1/**")
//                    .and()

                .authorizeRequests()
                    .antMatchers("/api/v1/login").permitAll()
                    .antMatchers(HttpMethod.POST,"/api/v1/register").permitAll()
                    .antMatchers(HttpMethod.GET,"/api/v1/categories").permitAll()
                    .antMatchers(HttpMethod.GET,"/api/v1/products").permitAll()
                    .antMatchers(HttpMethod.GET,"/api/v1/products/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/api/v1/new-products").permitAll()
                    .antMatchers(HttpMethod.GET,"/api/v1/all-products").permitAll()
                    .antMatchers(HttpMethod.GET,"/api/v1/related-products").permitAll()
                    .antMatchers(HttpMethod.GET,"/api/v1/popular-products").permitAll()
                    .antMatchers(HttpMethod.GET,"/api/v1/new-products-in-shop/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/api/v1/shops").permitAll()
                    .antMatchers(HttpMethod.GET,"/api/v1/shops/**").permitAll()
                   // .antMatchers(HttpMethod.GET ,"api/v1/**").hasAnyRole("ADMIN","SHOPKEEPER", "BUYER")
                   .antMatchers(HttpMethod.DELETE,"api/v1/admin/users/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

        @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImp).passwordEncoder(encoder);
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
