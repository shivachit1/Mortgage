import com.example.App;
import com.example.services.CustomerService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    static CustomerService customerService;
    static ArrayList<Document> databaseData = new ArrayList<>();

    @Test
    @DisplayName("Running test Before all other tests")
    @BeforeAll
    static void init(){
        File prospectsFile = new File("src/prospects.txt");
        assertTrue(prospectsFile.exists());

        File resultFile = new File("src/result.txt");
        if (resultFile.exists()) {
            assertTrue(resultFile.delete());
        }

        customerService = new CustomerService("mortgage");
        MongoCollection<Document> collection = customerService.getMongoDatabase().getCollection("customers");
        FindIterable<Document> customers =  collection.find();
        for (Document customer : customers) {
            databaseData.add(customer);
        }
        customerService.getMongoDatabase().drop();
        assertEquals(0, customerService.getCollectionCount());
    }

    @Test
    @DisplayName("Running test after all other tests")
    @AfterAll
    public static void cleanUp(){
        assertEquals(4, customerService.getCollectionCount());
        MongoCollection<Document> collection = customerService.getMongoDatabase().getCollection("customers");

        if(databaseData.size()!=0){
            collection.drop();
            collection.insertMany(databaseData);
            assertEquals(databaseData.size(), customerService.getCollectionCount());
        }
    }

    @Test
    @DisplayName("Running Main function ")
    public void runMainFunction() throws IOException {
        App.main(new String[] {"arg1", "arg2", "arg3"});
    }

}
