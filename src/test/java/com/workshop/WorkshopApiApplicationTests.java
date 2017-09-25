package com.workshop;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WorkshopApiApplicationTests {

	private static String USERNAME = "user";
	private static String PASSWORD = "password";
	private static String CLIENT_ID = "workshop";
	private static String CLIENT_SECRET = "secret";
	private static String GRANT_TYPE = "password";

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private HttpEntity getAuthorizationRequest(String username, String password, String clientId, String clientSecret, String grantType) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", grantType);
		map.add("client_id", clientId);
		map.add("client_secret", clientSecret);
		map.add("username", username);
		map.add("password", password);

		return new HttpEntity(map, headers);
	}
	private String authorize(String username, String password, String clientId, String clientSecret, String grantType) {
		HttpEntity entity = getAuthorizationRequest(username, password, clientId, clientSecret, grantType);
		ResponseEntity<JsonNode> response = restTemplate.exchange("http://localhost:" + port + "/oauth/token", HttpMethod.POST, entity, JsonNode.class);
		return response.getBody().get("access_token").toString();
	}

	@Test
	public void unauthorizedHello() throws Exception {
		ResponseEntity<String> hello = restTemplate.exchange("http://localhost:" + port + "/closed/hello", HttpMethod.GET, null, String.class);
		assertThat(hello.getStatusCode().equals(HttpStatus.UNAUTHORIZED));
	}

    @Test
    public void authorizedClosedHello() throws Exception {
        String accessToken = authorize(USERNAME, PASSWORD, CLIENT_ID, CLIENT_SECRET, GRANT_TYPE);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        ResponseEntity<String> hello = restTemplate.exchange("http://localhost:" + port + "/closed/hello", HttpMethod.GET, new HttpEntity(null, headers), String.class);
        assertThat(hello.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    public void openHello() throws Exception {
        ResponseEntity<String> hello = restTemplate.exchange("http://localhost:" + port + "/hello", HttpMethod.GET, null, String.class);
        assertThat(hello.getStatusCode().equals(HttpStatus.OK));
    }

}
