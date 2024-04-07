package mx.com.gm.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user")
//                .password("{noop}userPass")
//                .roles("USER")
//                .build());
//        manager.createUser(User.withUsername("admin")
//                .password("{noop}adminPass")
//                .roles("USER", "ADMIN")
//                .build());
//        return manager;
//    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//
//    @Autowired
//    public void configure(AuthenticationManagerBuilder build) throws Exception {
//        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        CookieClearingLogoutHandler cookies = new CookieClearingLogoutHandler("our-custom-cookie");
        http
                .logout((logout) -> logout.addLogoutHandler(cookies))
                .formLogin(form->form
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/",true))
                .authorizeHttpRequests((authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry.requestMatchers("/editar/**", "/agregar/**","/eliminar").hasAnyRole("ADMIN")
                                .requestMatchers("/").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/webjars/**").permitAll()
                                .anyRequest().authenticated()))
//        http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//                        authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
//                                .requestMatchers("/editar/**", "/agregar/**","/eliminar").hasAnyRole("ADMIN")
//                                .requestMatchers("/").hasAnyRole("USER", "ADMIN")
//                                .requestMatchers("/login").permitAll()
//                                .anyRequest().authenticated())
//                .formLogin(form->form
//                        .loginPage("/login").permitAll())
//                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling().accessDeniedPage("/403");
//                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth){
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                    .password("{noop}123")
//                    .roles("ADMIN","USER")
//                ;
//    }
}
