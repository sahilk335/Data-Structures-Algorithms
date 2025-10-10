package Companies.Stripe;

import java.util.*;
import java.util.stream.Collectors;

import static Companies.Stripe.OrderShipping.Product.laptop;
import static Companies.Stripe.OrderShipping.Product.mouse;

/**
 * Part 1
 * you are given these 2 objects and you need to calculate for shipping cost for the given country in order object -
 *
 * order = {
 *     "country": "US",
 *     "items": [
 *         {"product": "mouse", "quantity": 5},
 *         {"product": "laptop", "quantity": 2}
 *     ]
 * }
 *
 * shipping = {
 *     "US": [
 *         {"product": "mouse", "cost": 500},
 *         {"product": "laptop", "cost": 1000}
 *     ],
 *     "CA": [
 *         {"product": "mouse", "cost": 700},
 *         {"product": "laptop", "cost": 1200}
 *     ]
 * }
 * in this case total shipping cost - 5 * 500 + 2 * 1000 -> 4500
 *
 * interviewer asked me to generate one more UT
 *
 * Part 2
 * If we are giving discounts for different units then how to cal shipping cost? & shipping object has been modified -
 *
 * shipping = {
 *     "CA": [
 *         {"product": "mouse", "cost": 500},
 *         {"product": "laptop", "cost": 1000}
 *     ],
 *     "US": [
 *         {"product": "mouse",
 *          "cost": [{
 *                     minQuantity:0,
 *                     maxQuantity:2,
 *                     cost: 200
 *                     },
 *                   {
 *                     minQuantity:3,
 *                     maxQuantity:null,
 *                     cost: 700
 *                     }
 *                     ]
 *             },
 *         {"product": "laptop",
 *             "cost": [{
 *                     minQuantity:0,
 *                     maxQuantity:null,
 *                     cost: 700
 *                     }
 *                     ]
 *                 }
 *     ]
 * }
 * here the shipping cost will be - 3 * 200 + 2 * 700 + 2 * 700 -> 3400
 *
 * Basically there are different slabs of choosing cost.
 *
 *
 * https://leetcode.com/discuss/post/6135840/stripe-phone-interview-experience-by-ano-3rgj/
 */

public class OrderShipping {
    enum Country {
        US,
        CA
    }

    enum Product {
        mouse,
        laptop
    }

    static class Order {
        Country country;
        Item[] items;

        Order(Country country, Item[] items) {
            this.country = country;
            this.items = items;
        }
    }

    static class Item {
        Product product;
        int quantity;

        Item(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
    }

    static class DiscountedCost {
        int minQuantity;
        int maxQuantity;
        int cost;

        DiscountedCost(Integer minQuantity, Integer maxQuantity, Integer cost) {
            this.minQuantity = minQuantity;
            this.maxQuantity = maxQuantity == null ? Integer.MAX_VALUE : maxQuantity;
            this.cost = cost;
        }
    }


    static class ShippingCost {
        Product product;
        Integer cost;

        DiscountedCost[] discountedCost;


        ShippingCost(Product product, int cost) {
            this.product = product;
            this.cost = cost;
        }

        ShippingCost(Product product, DiscountedCost[] discountedCost) {
            this.product = product;
            this.discountedCost = discountedCost;
        }

        int getDiscountedCostProduct(int unitIndex) throws Exception {
            Arrays.sort(discountedCost, Comparator.comparingInt(dc -> dc.minQuantity));
            for (DiscountedCost dc : discountedCost) {
                if (unitIndex >= dc.minQuantity && unitIndex <= dc.maxQuantity) {
                    return dc.cost;
                }
            }
            throw new Exception("Error in Getting Discounted Cost");
        }
    }

    public int calculateTotalCost(Map<Country, ShippingCost[]> shipping, Order order) throws Exception {
        int totalCost = 0;
        Country country = order.country;

        for (Item item : order.items) {
            Product product = item.product;
            Optional<ShippingCost> shippingCost = Arrays.stream(shipping.get(country)).filter(a -> a.product.equals(product)).findAny();
            if (shippingCost.get().cost != null) {
                totalCost += shippingCost.get().cost * item.quantity;
            } else {
                for (int quantity = 0; quantity < item.quantity; quantity++) {
                    totalCost += shippingCost.get().getDiscountedCostProduct(quantity);
                }
            }
        }
        return totalCost;
    }


    public static void main(String[] args) throws Exception {
        Map<Country, ShippingCost[]> shipping = new HashMap<>();
        shipping.put(Country.US, new ShippingCost[]{new ShippingCost(mouse, 500), new ShippingCost(laptop, 1000)});
        shipping.put(Country.CA, new ShippingCost[]{
                new ShippingCost(mouse, new DiscountedCost[]{
                        new DiscountedCost(0, 2, 200),
                        new DiscountedCost(3, null, 700)}
                ),
                new ShippingCost(laptop, new DiscountedCost[]{
                        new DiscountedCost(0, null, 700)
                })
        });
        Order order = new Order(Country.CA, new Item[]{new Item(mouse, 5), new Item(laptop, 2)});
        OrderShipping orderShipping = new OrderShipping();
        System.out.println(orderShipping.calculateTotalCost(shipping, order));
    }
}
