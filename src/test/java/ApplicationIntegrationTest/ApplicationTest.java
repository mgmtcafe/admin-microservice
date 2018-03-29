package ApplicationIntegrationTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.ctscafe.admin.AdminApp;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApp.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class ApplicationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@SuppressWarnings("unchecked")
	@Test
	public void test() {

		Map<String, String> json = new HashMap<String, String>();
		json.put("address", "test");
		json.put("name", "Test Vendor");
		json.put("email", "t@mail.com");
		json.put("contact", "9230598306");
		json.put("location", "1");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Map<String, String>> requestEntity = new HttpEntity<Map<String, String>>(json, headers);

		String url = "/vendor/create";
		String response = "";
		try {
			response = this.restTemplate.postForObject(url, requestEntity, String.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map = (Map<String, Object>) gson.fromJson(response, map.getClass());
		assertThat(map.get("status")).isEqualTo("success");
	}
}
