import com.example.dao.MortgageDao;
import com.example.model.Mortgage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testing Database")
public class MortgageDaoTest {
    static MortgageDao mortgageDao;

    @Test
    @DisplayName("Running test Before all other tests")
    @BeforeAll
    static void init(){
        mortgageDao = new MortgageDao("testMortgage");
        mortgageDao.getMongoDatabase().drop();
    }

    @Test
    @AfterAll
    @DisplayName("Running test after all other tests")
    public static void cleanUp(){
        mortgageDao.getMongoDatabase().drop();
        assertEquals(0,mortgageDao.getCollectionCount());
    }

    @Test
    @DisplayName("Saving first new Mortgage Data and testing if data exists")
    public void saveMortgage(){
        assertEquals(0,mortgageDao.getCollectionCount());
        Mortgage mortgage = new Mortgage("Shiva",2000,5,2);
        mortgageDao.saveMortgageResult(mortgage);
        // testing if the collection count is increased by 1
        assertEquals(1, mortgageDao.getCollectionCount());
    }
}
