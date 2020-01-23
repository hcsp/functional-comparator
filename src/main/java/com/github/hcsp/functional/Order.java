package com.github.hcsp.functional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class Order {
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

    // 请尝试编写一个方法，输入一个订单列表，输出一个TreeSet，TreeSet中订单的排序规则是：
    // 1.首先按照是否关闭排序，未关闭的订单靠前；
    // 2.然后按照订单金额排序，订单金额大的靠前；
    // 3.然后按照下单时间排序，下单时间早的靠前
    public static TreeSet<Order> toTreeSet(List<Order> orders) {
        return null;
    }

    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println(
                toTreeSet(
                        Arrays.asList(
                                new Order(1, now, false, new BigDecimal("1")),
                                new Order(2, now.minusSeconds(1), true, new BigDecimal("2")),
                                new Order(3, now.minusSeconds(-1), true, new BigDecimal("3")),
                                new Order(4, now.minusSeconds(2), false, new BigDecimal("4")))));
    }
}
