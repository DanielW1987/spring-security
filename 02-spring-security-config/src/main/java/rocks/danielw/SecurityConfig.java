package rocks.danielw;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests(authorize -> {
              authorize
                      // allow all requests on root level
                      .antMatchers("/").permitAll()
                      // allow all on /api/v1/persons with any query parameter
                      .antMatchers("/api/v1/persons*").permitAll()
                      // allow all on /api/v1/persons, /api/v1/persons/123 but not /api/v1/persons/123/details
                      .antMatchers("/api/v1/persons/*").permitAll()
                      // allow GET requests for all resources behind /static
                      .antMatchers(HttpMethod.GET, "/static/**").permitAll();
            })
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin().and()
            .httpBasic();
  }
}
