package ApplicationIntegrationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ctscafe.admin.AdminApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApp.class)
public class MainClass {
	@Test
	public void applicationContextLoaded() {
	}

	@Test
	public void applicationContextTest() {
	    AdminApp.main(new String[] {});
	}
}
