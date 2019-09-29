package com.github.hcsp.functional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderTest {
    @Test
    public void test() {
        Instant now = Instant.now();
        TreeSet<Order> orders =
                Order.toTreeSet(
                        Arrays.asList(
                                new Order(1, now, false, new BigDecimal("2")),
                                new Order(2, now, true, new BigDecimal("1")),
                                new Order(3, now.minusSeconds(1), true, new BigDecimal("1")),
                                new Order(4, now.minusSeconds(-1), true, new BigDecimal("3")),
                                new Order(5, now.minusSeconds(2), false, new BigDecimal("4")),
                                new Order(6, now.minusSeconds(2), false, new BigDecimal("4"))));

        Assertions.assertEquals(
                Arrays.asList(4, 3, 2, 5, 6, 1),
                orders.stream().map(Order::getId).collect(Collectors.toList()));
    }
}
