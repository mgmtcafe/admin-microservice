package ApplicationIntegrationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.ctscafe.admin.AdminApp;

@RunWith(SpringRunner.class)
public class MainClass {

// Test class added ONLY to cover main() invocation not covered by application tests.


   @Test
   public void main() {
      AdminApp.main(new String[] {});
   }
}
