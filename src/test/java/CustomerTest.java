import com.example.model.Customer;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing Customer Model calculate method")
public class CustomerTest {

    @Test
    @DisplayName("Calculating Monthly Payment with all positive values")
    public void testCalculation(){
        Customer customer = new Customer("John",1000,10,1);
        customer.setMonthlyPayment(customer.calculate());
        assertEquals(87.92, customer.getMonthlyPayment());
    }

    @Test
    @DisplayName("Calculating Monthly Payment with Loan Amount as Zero")
    public void testZeroLoanAmountCalculation(){
        Customer customer = new Customer("John",0,10,1);
        assertThrows(IllegalArgumentException.class, customer::calculate,"Loan Amount should be greater than 0");
    }

    @Test
    @DisplayName("Calculating Monthly Payment with Negative Loan Amount")
    public void testNegativeLoanAmountCalculation(){
        Customer customer = new Customer("John",-1000,10,1);
        assertThrows(IllegalArgumentException.class, customer::calculate,"Loan Amount should be greater than 0");
    }

    @Test
    @DisplayName("Calculating Monthly Payment with Interest Rate as Zero")
    public void testZeroInterestRateCalculation(){
        Customer customer = new Customer("John",1000,0,1);
        assertThrows(IllegalArgumentException.class, customer::calculate,"Interest Rate should be greater than 0");
    }

    @Test
    @DisplayName("Calculating Monthly Payment with Negative Interest Rate")
    public void testNegativeInterestRateCalculation(){
        Customer customer = new Customer("John",1000,-1,1);
        assertThrows(IllegalArgumentException.class, customer::calculate,"Interest Rate should be greater than 0");
    }

    @Test
    @DisplayName("Calculating Monthly Payment with Years as Zero")
    public void testZeroYearsCalculation(){
        Customer customer = new Customer("John",1000,10,0);
        assertThrows(IllegalArgumentException.class, customer::calculate,"Years should be greater than 0");
    }

    @Test
    @DisplayName("Calculating Monthly Payment with Negative Years")
    public void testNegativeYearsCalculation(){
        Customer customer = new Customer("John",1000,10,-1);
        assertThrows(IllegalArgumentException.class, customer::calculate,"Years should be greater than 0");
    }

}
