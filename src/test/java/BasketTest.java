import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BasketTest {

    Basket basket;
    Item item;
    Customer customer1;
    Customer customer2;


    @Before
    public void setup(){
        customer1 = new Customer("Daniel", false);
        basket = new Basket(customer1);
        Item item1 = new Item("Timesplitters 2 (PS2)", 9.99, false);
        Item item2 = new Item("Elder Scrolls 6", 9.99, false);
        basket.add(item1, 1);
        basket.add(item2, 1);

        item = new Item("Monster Hunter World", 39.99, true);
        customer2 = new Customer("Matthew", true);
    }

    @Test
    public void canGetCustomer(){
        assertNotNull(basket.getCustomer());
    }

    @Test
    public void canSetCustomer(){
        basket.setCustomer(customer2);
        assertEquals(customer2, basket.getCustomer());
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
        assertEquals(19.98, basket.getGrossTotal(), 0.01);
    }

    @Test
    public void canCalculateTotalCostWithDuplicateItems(){
        basket.add(item, 2);
        assertEquals(99.96, basket.getGrossTotal(), 0.01);
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

    @Test
    public void canCalculateSimpleBogoff(){
        basket = new Basket(customer1);
        basket.add(new Item("Test", 5.99, true), 2);
        assertEquals(5.99, basket.getDiscount(basket.getGrossTotal()), 0.01);
    }

    @Test
    public void canCalculateBogoffWithResidualItem(){
        basket = new Basket(customer1);
        basket.add(new Item("Test", 5.99, true), 3);
        assertEquals(5.99 ,basket.getDiscount(basket.getGrossTotal()), 0.01);
    }

    @Test
    public void canCalculateDoubleBogoff(){
        basket = new Basket(customer1);
        basket.add(new Item("Test", 5.99, true), 4);
        assertEquals(11.98 ,basket.getDiscount(basket.getGrossTotal()), 0.01);
    }

    @Test
    public void canCalculateBogoffForNonBogoff() {
        basket = new Basket(customer1);
        basket.add(new Item("Test", 3.99, false), 4);
        assertEquals(0, basket.getDiscount(basket.getGrossTotal()), 0.01);
    }

        @Test
    public void canCalculateLoyaltyDiscountWithCard(){
        basket.setCustomer(customer2);
        assertEquals(0.4, basket.getDiscount(basket.getGrossTotal()), 0.01);
    }

    @Test
    public void canCalculateLoyaltyDiscountWithoutCard(){
        assertEquals(0, basket.getDiscount(basket.getGrossTotal()), 0.01);
    }

    @Test
    public void canCalculateValueDiscountUnder20(){
        assertEquals(0, basket.getDiscount(basket.getGrossTotal()), 0.01);
    }

    @Test
    public void canCalculateValueDiscountEquals20(){
        basket.add(new Item("Test", 0.02,false), 1);
        assertEquals(0, basket.getDiscount(basket.getGrossTotal()), 0.01);
    }

    @Test
    public void canCalculateValueDiscountOver20(){
        basket.add(item, 1);
        assertEquals(5.99, basket.getDiscount(basket.getGrossTotal()), 0.01);
    }

    @Test
    public void canCalculateNetValueWithAllDiscounts(){
        basket.setCustomer(customer2);
        basket.add(item, 2);
        assertEquals(52.89, basket.getNetTotal(), 0.01);
    }
}
