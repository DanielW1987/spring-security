package rocks.danielw;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.util.DigestUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordEncodingTest {

  private static final String PASSWORD = "password";

  @Test
  void hashingExample() { // dont' use it in production
    String md5EncodedPassword = DigestUtils.md5DigestAsHex(PASSWORD.getBytes());
    System.out.println(md5EncodedPassword);
  }

  @Test
  void hashingSaltExample() { // dont' use it in production
    String salt = "ThisIsMySaltValue";
    String saltedPassword = PASSWORD + salt;
    String md5EncodedPassword = DigestUtils.md5DigestAsHex(saltedPassword.getBytes());
    System.out.println(md5EncodedPassword);
  }

  @Test
  void testNoOp() { // dont' use it in production
    PasswordEncoder noOpPasswordEncoder = NoOpPasswordEncoder.getInstance();
    System.out.println(noOpPasswordEncoder.encode(PASSWORD));
  }

  @Test
  void testLdapPasswordEncoder() { // dont' use it in production
    PasswordEncoder ldapPasswordEncoder = new LdapShaPasswordEncoder();
    System.out.println(ldapPasswordEncoder.encode(PASSWORD));
    System.out.println(ldapPasswordEncoder.encode(PASSWORD)); // creates a different value

    String encodedPassword = ldapPasswordEncoder.encode(PASSWORD);
    assertTrue(ldapPasswordEncoder.matches(PASSWORD, encodedPassword));
  }

  @Test
  void testSha256() { // dont' use it in production
    PasswordEncoder sha256PasswordEncoder = new StandardPasswordEncoder();
    System.out.println(sha256PasswordEncoder.encode(PASSWORD));
    System.out.println(sha256PasswordEncoder.encode(PASSWORD)); // creates a different value

    String encodedPassword = sha256PasswordEncoder.encode(PASSWORD);
    sha256PasswordEncoder.matches(PASSWORD, encodedPassword);
  }

  @Test
  void testBCrypt() { // Springs default password encoder, recommended for production
    PasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder(16); // Default strength is 10, 16 is highest
    System.out.println(bcryptPasswordEncoder.encode(PASSWORD));

    String encodedPassword = bcryptPasswordEncoder.encode(PASSWORD);
    assertTrue(bcryptPasswordEncoder.matches(PASSWORD, encodedPassword));
  }
}
