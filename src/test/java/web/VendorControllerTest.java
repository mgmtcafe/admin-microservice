package web;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
		Vendor v = new Vendor(1, "test vendor", "test@test.com", "1234567891", "test address", 1);
		when(vendorRepositoryMock.save(any(Vendor.class))).thenReturn(v);
		
		when(vendorRepositoryMock.findVendorByEmail(any())).thenReturn(null);
		when(vendorRepositoryMock.findVendorByContact(any())).thenReturn(null);
	};

	@Test
	public void checkLocationWhenIsCorrectShouldReturnLocationDetails() throws Exception {

		this.mockMvc.perform(get("/vendor/location")).andExpect(MockMvcResultMatchers.content().string(stringResponse));
	}

	@Test
	public void createVendorWhenFieldsAreCorrectShouldReturnSuccessObject() throws Exception {
		Map<String, String> json = new HashMap<String, String>();
		json.put("address", "test");
		json.put("name", "Test Vendor");
		json.put("email", "test@mail.com");
		json.put("contact", "9230598305");
		json.put("location", "1");
		Gson gson = new Gson();
		String expected  = "{\"status\":\"success\",\"message\":{\"vendorId\":1,\"vendorName\":\"test vendor\",\"vendorEmail\":\"test@test.com\",\"vendorContact\":\"1234567891\",\"vendorAddress\":\"test address\",\"vendorLocationId\":1}}";
		mockMvc.perform(post("/vendor/create").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(json))).andExpect(MockMvcResultMatchers.content().string(expected));

	}

}
