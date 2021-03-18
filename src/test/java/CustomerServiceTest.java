import com.example.services.CustomerService;
import com.example.model.Customer;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testing Database")
public class CustomerServiceTest {
    static CustomerService customerService;

    @Test
    @DisplayName("Running test Before all other tests")
    @BeforeAll
    static void init(){
        customerService = new CustomerService("testMortgage");
        customerService.getMongoDatabase().drop();
    }

    @Test
    @AfterAll
    @DisplayName("Running test after all other tests")
    public static void cleanUp(){
        customerService.getMongoDatabase().drop();
        assertEquals(0, customerService.getCollectionCount());
    }

    @Test
    @DisplayName("Saving first new Customer Data and testing if data exists")
    public void saveCustomer(){
        assertEquals(0, customerService.getCollectionCount());
        Customer customer = new Customer("Shiva",2000,5,2);
        customerService.saveCustomer(customer);
        // testing if the collection count is increased by 1
        assertEquals(1, customerService.getCollectionCount());
    }
}
