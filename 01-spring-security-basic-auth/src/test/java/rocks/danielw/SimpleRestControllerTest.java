package rocks.danielw;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.springframework.boot.test.context.SpringBootTest.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SimpleRestControllerTest implements WithAssertions {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void testFindPersonsWithTestRestTemplate() {
    var expectedPerson = Person.builder().firstName("Max").lastName("Mustermann").birthDay(LocalDate.of(1980, 10, 1)).build();

    // you can also add an interceptor for authentication, see https://stackoverflow.com/a/44013141
    ResponseEntity<PersonsResponse> response = restTemplate.withBasicAuth("admin", "simsalabim")
            .getForEntity("http://localhost:" + port + "/api/v1/persons", PersonsResponse.class);

    assertThat(response.getStatusCodeValue()).isEqualTo(200);
    assertThat(response.getBody().getPersons().get(0)).isEqualTo(expectedPerson);
  }
}
