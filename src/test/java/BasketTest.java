import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasketTest {

    Basket basket;
    Item item;

    @Before
    public void setup(){
        basket = new Basket();
        Item item1 = new Item("Timesplitters 2 (PS2)", 9.99);
        Item item2 = new Item("Elder Scrolls 6", 49.99);
        basket.add(item1, 1);
        basket.add(item2, 1);

        item = new Item("Monster Hunter World", 39.99);
    }

    @Test
    public void canCountBasketItems(){
        assertEquals(2, basket.countItems());
    }

    @Test
    public void canAddItem(){
        basket.add(item, 1);
        assertEquals(3, basket.countItems());
    }

    @Test
    public void canAddMultipleOfOneItem(){
        basket.add(item, 9);
        assertEquals(11, basket.countItems());
    }

    @Test
    public void cantAddNegativeQuantity(){
        basket.add(item, -9);
        assertEquals(2, basket.countItems());
    }

    @Test
    public void cantRemoveItemsNotInBasket(){
        basket.removeItem(item, 9);
        assertEquals(2, basket.countItems());
    }

    @Test
    public void canRemoveItemsInBasket(){
        basket.add(item, 1);
        basket.removeItem(item, 1);
        assertEquals(2, basket.countItems());
    }

    @Test
    public void canDecrementItemQty(){
        basket.add(item, 2);
        basket.removeItem(item, 1);
        assertEquals(3, basket.countItems());
    }

    @Test
    public void cantDecreaseByNegativeQty(){
        basket.add(item, 2);
        basket.removeItem(item, -91);
        assertEquals(4, basket.countItems());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantDecreaseByMoreThanQty(){
        basket.add(item, 2);
        basket.removeItem(item, 91);
        assertEquals(4, basket.countItems());
    }

    @Test
    public void canEmptyBasket(){
        basket.clear();
        assertEquals(0, basket.countItems());
    }

    @Test
    public void canCalculateTotalCost(){
        assertEquals(59.98, basket.getTotal(), 0.001);
    }

    @Test
    public void canCalculateTotalCostWithDuplicateItems(){
        basket.add(item, 2);
        assertEquals(139.96, basket.getTotal(), 0.001);
    }

    @Test
    public void canGetItemQuantityItemInBasket(){
        basket.add(item, 99);
        assertEquals(99, basket.getQty(item));
    }

    @Test
    public void canGetItemQuantityItemNotInBasket(){
        assertEquals(0, basket.getQty(item));
    }
}
