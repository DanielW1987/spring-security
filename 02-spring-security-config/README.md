# Spring Security Configuration

You can configure Spring Security with a configuration class, that looks as follows:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

}
```

The `WebSecurityConfigurerAdapter` has default configuration for Spring Security, for example the following method:

```java
protected void configure(HttpSecurity http) throws Exception {
  logger.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");

  http
    .authorizeRequests()
      .anyRequest().authenticated()
      .and()
    .formLogin().and()
    .httpBasic();
}
```

## Difference between antMatcher and mvcMatcher

From the official documentation

> `antMatcher(String antPattern)` - Allows configuring the `HttpSecurity` to only be invoked when matching the provided ant pattern.
> `mvcMatcher(String mvcPattern)` - Allows configuring the `HttpSecurity` to only be invoked when matching the provided Spring MVC pattern.

Generally `mvcMatcher` is more secure than an `antMatcher`. As an example:

- `antMatchers("/secured")` matches only the exact `/secured` URL
- `mvcMatchers("/secured")` matches `/secured` as well as `/secured/`, `/secured.html`, `/secured.xyz`

and therefore is more general and can also handle some possible configuration mistakes.
`mvcMatcher` uses the same rules that Spring MVC uses for matching (when using `@RequestMapping` annotation).
