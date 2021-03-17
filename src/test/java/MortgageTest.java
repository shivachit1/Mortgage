import com.example.model.Mortgage;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing Mortgage Model calculate method")
public class MortgageTest {

    @Test
    @DisplayName("Calculating Monthly Payment with all positive values")
    public void testCalculation(){
        Mortgage mortgage = new Mortgage("John",1000,10,1);
        mortgage.setMonthlyPayment(mortgage.calculate());
        assertEquals(87.92,mortgage.getMonthlyPayment());
    }

    @Test
    @DisplayName("Calculating Monthly Payment with Loan Amount as Zero")
    public void testZeroLoanAmountCalculation(){
        Mortgage mortgage = new Mortgage("John",0,10,1);
        assertThrows(IllegalArgumentException.class, mortgage::calculate,"Loan Amount should be greater than 0");
    }

    @Test
    @DisplayName("Calculating Monthly Payment with Negative Loan Amount")
    public void testNegativeLoanAmountCalculation(){
        Mortgage mortgage = new Mortgage("John",-1000,10,1);
        assertThrows(IllegalArgumentException.class, mortgage::calculate,"Loan Amount should be greater than 0");
    }

    @Test
    @DisplayName("Calculating Monthly Payment with Interest Rate as Zero")
    public void testZeroInterestRateCalculation(){
        Mortgage mortgage = new Mortgage("John",1000,0,1);
        assertThrows(IllegalArgumentException.class, mortgage::calculate,"Interest Rate should be greater than 0");
    }

    @Test
    @DisplayName("Calculating Monthly Payment with Negative Interest Rate")
    public void testNegativeInterestRateCalculation(){
        Mortgage mortgage = new Mortgage("John",1000,-1,1);
        assertThrows(IllegalArgumentException.class, mortgage::calculate,"Interest Rate should be greater than 0");
    }

    @Test
    @DisplayName("Calculating Monthly Payment with Years as Zero")
    public void testZeroYearsCalculation(){
        Mortgage mortgage = new Mortgage("John",1000,10,0);
        assertThrows(IllegalArgumentException.class, mortgage::calculate,"Years should be greater than 0");
    }

    @Test
    @DisplayName("Calculating Monthly Payment with Negative Years")
    public void testNegativeYearsCalculation(){
        Mortgage mortgage = new Mortgage("John",1000,10,-1);
        assertThrows(IllegalArgumentException.class, mortgage::calculate,"Years should be greater than 0");
    }

}
