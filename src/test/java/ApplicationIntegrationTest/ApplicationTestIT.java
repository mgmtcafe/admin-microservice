//package ApplicationIntegrationTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.ctscafe.admin.AdminApp;
//import com.google.gson.Gson;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = AdminApp.class, webEnvironment = WebEnvironment.RANDOM_PORT)
//@AutoConfigureTestDatabase
//public class ApplicationTestIT {
//	@Autowired
//	private TestRestTemplate restTemplate;
//
//	@SuppressWarnings("unchecked")
//	@Test
//	public void test() {
//
//		Map<String, String> json = new HashMap<String, String>();
//		json.put("address", "test");
//		json.put("name", "Test Vendor");
//		json.put("email", "krishanu.das2009@gmail.com");
//		json.put("contact", "9230598306");
//		json.put("location", "1");
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//
//		String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDIzODcyNzIsInVzZXJfbmFtZSI6ImFkbWluQGFkbWluLmNvbSIsImF1dGhvcml0aWVzIjpbIlZFTkRPUiIsIkFETUlOIl0sImp0aSI6IjY4NGIzYzVlLTg3YjctNDY1Yi1iYzg1LTJmMjY4NDk4MWU1ZCIsImNsaWVudF9pZCI6ImNsaWVudCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSIsIm9wZW5pZCJdfQ.iha45IpRMX3yZ6Oeza9CORCwPRQwgX0BTVjtgayVkgnXwXXCYnXHwKkLUq0LL88XIKwlGLxc45d6WUJrw2rZMMJpfUmKO4SS7HOXqiiy1P2x7kCaFiYqrpwGGJBa5Ncec7J2vJJVlg4Wo-73bydsM1gr_CDvimDIoNuB7raEAz6YojStPgG7PTQerg-ikgaXwbsgTNmtBQ5lw3rdiSsyr5uSYxlH62bXLqkEYTY61Nm-ZEnD18WR9K0qlK9rZSpoy2akm0h7x7bNm-NCFwIwZCI8CqESSpJt3kAP8_SY7EZRTpH5gD_jE4027pzQlDeIcEiHL18CY6zuMc02x7Sgug";
//		headers.set("Authorization", "Bearer " + token);
//
//		HttpEntity<Map<String, String>> requestEntity = new HttpEntity<Map<String, String>>(json, headers);
//
//		String url = "/vendor/create";
//		String response = "";
//		try {
//			response = this.restTemplate.postForObject(url, requestEntity, String.class);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		Gson gson = new Gson();
//		Map<String, Object> map = new HashMap<String, Object>();
//		map = (Map<String, Object>) gson.fromJson(response, map.getClass());
//		assertThat(map.get("status")).isEqualTo("success");
//	}
//}
