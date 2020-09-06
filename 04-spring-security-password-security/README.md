# Password Security

The Spring Security Team recommends using an adaptive one way encoding function such as:

- BCrypt (Default)
- Pbkdf2
- SCrypt

## Default Implementation

The default implementation is a bean like the following:

```java
@Bean
public PasswordEncoder passwordEncoder() {
  return PasswordEncoderFactories.createDelegatingPasswordEncoder();
}
```

The called factory method is implemented as follows:

```java
public static PasswordEncoder createDelegatingPasswordEncoder() {
  String encodingId = "bcrypt";
  Map<String, PasswordEncoder> encoders = new HashMap<>();
  encoders.put(encodingId, new BCryptPasswordEncoder());
  encoders.put("ldap", new org.springframework.security.crypto.password.LdapShaPasswordEncoder());
  encoders.put("MD4", new org.springframework.security.crypto.password.Md4PasswordEncoder());
  encoders.put("MD5", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("MD5"));
  encoders.put("noop", org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
  encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
  encoders.put("scrypt", new SCryptPasswordEncoder());
  encoders.put("SHA-1", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-1"));
  encoders.put("SHA-256", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-256"));
  encoders.put("sha256", new org.springframework.security.crypto.password.StandardPasswordEncoder());
  encoders.put("argon2", new Argon2PasswordEncoder());

  return new DelegatingPasswordEncoder(encodingId, encoders);
}
```

So every password is encrypted with BCrypt by default. Furthermore it is possible to use any other encryption algorithm by simply
putting a `{ldap}' (or other encoding ID) in front of the password.

## BCrypt

BCrypt will internally generate a random salt. This is important to understand because it means that each call will have a different
result. To make this random salt generation work, BCrypt will store the salt inside the hash value itself. For instance, in the following
hash value:

```
$2a$10$ZLhnHxdpHETcxmtEStgpI./Ri1mksgJ9iDP36FmfMdYyVg9g0b2dq
```

There are three fields separated by $:

- The `2a` represents the BCrypt algorithm version.
- The `10` represents the strength of the algorithm.
- The `ZLhnHxdpHETcxmtEStgpI.` part is actually the randomly generated salt. Basically, the first 22 characters are salt. The remaining
part of the last field is the actual hashed version of the plain text.

Also, be aware that the BCrypt algorithm generates a String of length 60, so we need to make sure that the password will be stored in a
column that can accommodate it. A common mistake is to create a column of a different length and then get an Invalid Username or Password
error at authentication time.

Taken from: <https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt>