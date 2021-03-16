import com.example.MortgagePlan;
import com.example.dao.MortgageDao;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MortgagePlanTest {
    static MortgageDao mortgageDao;
    static ArrayList<Document> databaseData = new ArrayList<>();

    @Test
    @DisplayName("Running test Before all other tests")
    @BeforeAll
    static void init(){
        File resultFile = new File("src/result.txt");
        if (resultFile.exists()) {
            assertTrue(resultFile.delete());
        }

        mortgageDao = new MortgageDao("mortgage");
        MongoCollection<Document> collection = mortgageDao.getMongoDatabase().getCollection("mortgages");
        FindIterable<Document> mortgages =  collection.find();
        for (Document mortgage : mortgages) {
            databaseData.add(mortgage);
        }
        mortgageDao.getMongoDatabase().drop();
        assertEquals(0,mortgageDao.getCollectionCount());
    }

    @Test
    @DisplayName("Running test after all other tests")
    @AfterAll
    public static void cleanUp(){
        assertEquals(4,mortgageDao.getCollectionCount());
        MongoCollection<Document> collection = mortgageDao.getMongoDatabase().getCollection("mortgages");

        if(databaseData.size()!=0){
            collection.drop();
            collection.insertMany(databaseData);
            assertEquals(databaseData.size(),mortgageDao.getCollectionCount());
        }
    }

    @Test
    @DisplayName("Running Main function ")
    public void runMainFunction() throws IOException {
        File prospectsFile = new File("src/prospects.txt");
        assertTrue(prospectsFile.exists());
        MortgagePlan.main(new String[] {"arg1", "arg2", "arg3"});
    }

}
