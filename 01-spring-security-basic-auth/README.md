# Spring Security Basic Auth

As soon as the dependency 'spring-boot-starter-security' is on the classpath, Basic Auth is automatically activated for all endpoints.

When the application is started, a password (random UUID) is generated on the console. The default username is `user`.

All requests are redirected to `/login` by default where Spring Security provides a simple login form. In addition, a logout mechanism
is available by default behind `/logout`.

In file (request-examples/basic-auth-request.http)[request-examples/basic-auth-request.http] you can see an example request
with basic authentication. If you execute it via IntelliJ it is not necessary to encode username and password with Base64. Postman
has also support to do basic authentication.

## Customizing default values

You can customize the default username and password in file `application.properties`:

```
spring.security.user.name=admin
spring.security.user.password=simsalabim
```
