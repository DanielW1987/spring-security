package rocks.danielw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class SimpleRestControllerIT {

  @Autowired
  private WebApplicationContext context;

  MockMvc mockMvc;

  @BeforeEach
  void setup() {
    mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
  }

  @Test
  @WithMockUser(username = "user") // can be any user
  void testFindPersons() throws Exception {
    mockMvc.perform(get("/api/v1/persons"))
            .andExpect(status().isOk());
  }

  @Test
  void testFindPersonsWithBasicAuth() throws Exception {
    mockMvc.perform(get("/api/v1/persons").with(httpBasic("admin", "simsalabim"))) // must be the configured user and password
            .andExpect(status().isOk());
  }
}
