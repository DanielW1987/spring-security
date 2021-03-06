# In memory authentication

The in memory user details manager:

- implements `UserDetailsService`
- used by Spring Boot Auto-configuration
- non-persistent implementation (users in memory map)
- mainly used for testing and demonstration purposes
- not recommended to use it in production

## Spring Security Authentication Process

![Spring Security Authentication Process](images/spring-security-authentication-process.png "Spring Security Authentication Process")

- Authentication Filter: A filter for a specific authentication type in den spring security filter chain (basic auth, remember me cookie, etc.)
- Authentication Manager: Standard API interface used by filter
- Authentication Provider: The implementation of authentication (in memory, database, etc.)
- User Details Service: Service to provide information about user
- Password Encoder: Service to encrypt and verify passwords
- Security Context: Holds details about authenticated entity
