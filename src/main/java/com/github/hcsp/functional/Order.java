package com.github.hcsp.functional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.Arrays;

public class Order implements Comparable<Order> {
    // 订单编号，全局唯一
    private Integer id;
    // 下单时间
    private Instant orderTime;
    // 是否开启，true为开启，false为关闭
    private boolean open;
    // 订单金额
    private BigDecimal amount;

    public Order(Integer id, Instant orderTime, boolean open, BigDecimal amount) {
        this.id = id;
        this.orderTime = orderTime;
        this.open = open;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public Instant getOrderTime() {
        return orderTime;
    }

    public boolean isOpen() {
        return open;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Order{"
                + "id="
                + id
                + ", orderTime="
                + orderTime
                + ", open="
                + open
                + ", amount="
                + amount
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return open == order.open &&
                Objects.equals(id, order.id) &&
                Objects.equals(orderTime, order.orderTime) &&
                Objects.equals(amount, order.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderTime, open, amount);
    }

    @Override
    public int compareTo(Order o) {
        if (this.open == o.open) {
            if (this.amount.compareTo(o.amount) > 0) {
                return -1;
            } else if (this.amount.compareTo(o.amount) == 0) {
                if (this.orderTime.getEpochSecond() - o.orderTime.getEpochSecond() != 0) {
                    return this.orderTime.getEpochSecond() - o.orderTime.getEpochSecond() > 0 ? 1 : -1;
                } else {
                    return this.id - o.id;
                }
            } else {
                return 1;
            }
        } else {
            return this.open ? -1 : 1;
        }

    }

    // 请尝试编写一个方法，输入一个订单列表，输出一个TreeSet，TreeSet中订单的排序规则是：
    // 1.首先按照是否关闭排序，未关闭的订单靠前；
    // 2.然后按照订单金额排序，订单金额大的靠前；
    // 3.然后按照下单时间排序，下单时间早的靠前
    public static TreeSet<Order> toTreeSet(List<Order> orders) {
//        orders.sort(Order::compareTo);
        orders.sort(Comparator.comparing(Order::isOpen).reversed()
                .thenComparing(Comparator.comparing(Order::getAmount).reversed())
                .thenComparing(Comparator.comparing(Order::getOrderTime).reversed()));
        return new TreeSet<>(orders);
    }

    public static void main(String[] args) {
        Instant now = Instant.now();
        /*
        System.out.println(
                toTreeSet(
                        Arrays.asList(
                                new Order(1, now, false, new BigDecimal("1")),
                                new Order(2, now.minusSeconds(1), true, new BigDecimal("2")),
                                new Order(3, now.minusSeconds(-1), true, new BigDecimal("3")),
                                new Order(4, now.minusSeconds(2), false, new BigDecimal("4")))));

        */
        System.out.println(
                toTreeSet(
                        Arrays.asList(
                                new Order(1, now, false, new BigDecimal("2")),
                                new Order(2, now, true, new BigDecimal("1")),
                                new Order(3, now.minusSeconds(1), true, new BigDecimal("1")),
                                new Order(4, now.minusSeconds(-1), true, new BigDecimal("3")),
                                new Order(5, now.minusSeconds(2), false, new BigDecimal("4")),
                                new Order(6, now.minusSeconds(2), false, new BigDecimal("4")))));

    }

    //[4, 3, 2, 5, 6, 1]

}
