package rocks.danielw;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class SimpleRestController {

  @GetMapping(path = "/api/v1/persons", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PersonsResponse> getPersons() {
    var persons = List.of(
      Person.builder().firstName("Max").lastName("Mustermann").birthDay(LocalDate.of(1980, 10, 1)).build()
    );

    return ResponseEntity.ok(new PersonsResponse(persons));
  }
}
