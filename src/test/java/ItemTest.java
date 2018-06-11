import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {
    Item item1;

    @Before
    public void setup(){
        item1 = new Item("Timesplitters 2 (PS2)", 9.99);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantCreateItemWithNegativePrice(){
        new Item("Test Item", -1);
    }

    @Test
    public void hasName(){
        assertEquals("Timesplitters 2 (PS2)", item1.getName());
    }

    @Test
    public void hasPrice(){
        assertEquals(9.99, item1.getPrice(), 0.001);
    }
}
