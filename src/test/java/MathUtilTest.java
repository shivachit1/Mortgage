import com.example.MathUtil;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testing Custom Build Math Power")
public class MathUtilTest {

    @Test
    @DisplayName("Power of Positive number with Positive number")
    public void testBothPositiveNumberPower(){
        assertEquals(4, MathUtil.power(2,2));
    }

    @Test
    @DisplayName("Power of Negative number with Positive number")
    public void testNegativeNumberPowerPositiveNumber(){
        assertEquals(4,MathUtil.power(-2,2));
    }

    @Test
    @DisplayName("Power of Positive number with Negative number")
    public void testPositiveNumberPowerNegativeNumber(){
        assertEquals(0.25,MathUtil.power(2,-2));
    }

    @Test
    @DisplayName("Power of Negative number with Negative number")
    public void testBothNegativeNumber(){
        assertEquals(0.25,MathUtil.power(-2,-2));
    }

    @Test
    @DisplayName("Power of positive number with Zero")
    public void testPositiveNumberPowerZero(){
        assertEquals(1,MathUtil.power(2,0));
    }

    @Test
    @DisplayName("Power of Negative number with Zero ")
    public void testNegativeNumberPowerZero(){
        assertEquals(1,MathUtil.power(-2,0));
    }

}
