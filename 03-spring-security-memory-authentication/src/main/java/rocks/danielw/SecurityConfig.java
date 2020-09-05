package rocks.danielw;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin().and()
            .httpBasic();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
            .withUser("admin")
            .password("{noop}simsalabim") // no-op password encoder, only for demonstration purpose
            .roles("ADMIN")
            .and()
            .withUser("user")
            .password("{noop}password") // no-op password encoder, only for demonstration purpose
            .roles("USER");
  }

//  @Override
//  @Bean
//  protected UserDetailsService userDetailsService() {
//    UserDetails adminDetails = User.withDefaultPasswordEncoder() // not recommended for production, no password encryption
//            .username("admin")
//            .password("simsalabim")
//            .roles("ADMIN")
//            .build();
//
//    UserDetails userDetails = User.withDefaultPasswordEncoder() // not recommended for production, no password encryption
//            .username("user")
//            .password("password")
//            .roles("USER")
//            .build();
//
//    return new InMemoryUserDetailsManager(adminDetails, userDetails);
//  }
}
