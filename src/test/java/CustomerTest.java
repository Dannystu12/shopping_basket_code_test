import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomerTest {
    Customer customer;

    @Before
    public void setup(){
        customer = new Customer("Daniel", true);
    }

    @Test
    public void hasName(){
        assertEquals("Daniel", customer.getName());
    }

    @Test
    public void hasLoyaltyCard(){
        assertTrue(customer.hasLoyaltyCard());
    }
}
