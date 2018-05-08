package com.posin.menumanager.pattern.model;

/**
 * Created by Greetty on 2018/5/2.
 *
 * 菜品（菜单列表中的每一个菜）
 */
public class Dishes {

    //菜名
    private String DishName;
    //数量
    private int Amount;
    //单价
    private double prices;

    public Dishes(String dishName, int amount, double prices) {
        DishName = dishName;
        Amount = amount;
        this.prices = prices;
    }

    public String getDishName() {
        return DishName;
    }

    public void setDishName(String dishName) {
        DishName = dishName;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return "Dishes{" +
                "DishName='" + DishName + '\'' +
                ", Amount=" + Amount +
                ", prices=" + prices +
                '}';
    }
}
