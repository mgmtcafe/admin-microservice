package web;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.ctscafe.admin.controller.VendorController;
import com.ctscafe.admin.model.Vendor;
import com.ctscafe.admin.repository.VendorRepository;
import com.google.gson.Gson;

public class VendorControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private VendorController vendorController;

	private VendorRepository vendorRepositoryMock;
	private RestTemplate restTemplate;
	String stringResponse;

	@Before
	public void setUp() {
		restTemplate = mock(RestTemplate.class);
		vendorRepositoryMock = mock(VendorRepository.class);
		
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
		
		stringResponse = "{" + "\"message\":" + "{" + "\"id\":1,"
				+ "\"address\":\"Cognizant GTP Campus, SEZ Plot No. IT 27, PIN- 743502, Bantala, Kolkata, West Bengal 743502\","
				+ "\"latitude\":\"22.5555\"," + "\"longitude\":\"80.6666\"," + "\"code\":\"GTP\"" + "},"
				+ "\"status\":\"OK\"" + "}";

		Mockito.when(
				restTemplate.getForObject("http://locationmicro.cfapps.io/location/getLocationById/1", String.class))
				.thenReturn(stringResponse);
//		Mockito.when(
//				restTemplate.postForObject(any(), any(),  any()))
//				.thenReturn("success");
		
		Vendor v = new Vendor(1, "test vendor", "test@test.com", "1234567891", "test address", 1);
		
		when(vendorRepositoryMock.save(any(Vendor.class))).thenReturn(v);

		when(vendorRepositoryMock.findVendorByEmail(any())).thenReturn(null);
		
		when(vendorRepositoryMock.findVendorByContact(any())).thenReturn(null);
	};

	@Test
	@WithMockUser(roles = {"ADMIN"})
	public void checkLocationWhenIsCorrectShouldReturnLocationDetails() throws Exception {
		this.mockMvc.perform(get("/vendor/location")).andExpect(MockMvcResultMatchers.content().string(stringResponse));
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	public void createVendorWhenFieldsAreCorrectShouldReturnSuccessObject() throws Exception {
		Map<String, String> json = new HashMap<String, String>();
		json.put("address", "test");
		json.put("name", "Test Vendor");
		json.put("email", "test@mail.com");
		json.put("contact", "9230598305");
		json.put("location", "1");
		Gson gson = new Gson();
		String expected = "{\"status\":\"success\",\"message\":{\"vendorId\":1,\"vendorName\":\"test vendor\",\"vendorEmail\":\"test@test.com\",\"vendorContact\":\"1234567891\",\"vendorAddress\":\"test address\",\"vendorLocationId\":1}}";
		String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDIzODcyNzIsInVzZXJfbmFtZSI6ImFkbWluQGFkbWluLmNvbSIsImF1dGhvcml0aWVzIjpbIlZFTkRPUiIsIkFETUlOIl0sImp0aSI6IjY4NGIzYzVlLTg3YjctNDY1Yi1iYzg1LTJmMjY4NDk4MWU1ZCIsImNsaWVudF9pZCI6ImNsaWVudCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSIsIm9wZW5pZCJdfQ.iha45IpRMX3yZ6Oeza9CORCwPRQwgX0BTVjtgayVkgnXwXXCYnXHwKkLUq0LL88XIKwlGLxc45d6WUJrw2rZMMJpfUmKO4SS7HOXqiiy1P2x7kCaFiYqrpwGGJBa5Ncec7J2vJJVlg4Wo-73bydsM1gr_CDvimDIoNuB7raEAz6YojStPgG7PTQerg-ikgaXwbsgTNmtBQ5lw3rdiSsyr5uSYxlH62bXLqkEYTY61Nm-ZEnD18WR9K0qlK9rZSpoy2akm0h7x7bNm-NCFwIwZCI8CqESSpJt3kAP8_SY7EZRTpH5gD_jE4027pzQlDeIcEiHL18CY6zuMc02x7Sgug";
		mockMvc.perform(post("/vendor/create").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(json)).header("Authorization", token))
				.andExpect(MockMvcResultMatchers.content().string(expected)).andDo(print());

	}
}
