package com.posin.menumanager.pattern.model;

/**
 * Created by Greetty on 2018/5/2.
 * <p>
 * 菜品（菜单列表中的每一个菜）
 */
public class Dishes {

    //菜名
    private String dishName;
    //数量
    private int amount;
    //单价
    private double prices;
    //是否显示在菜单中
    private boolean isShowing;

    public Dishes(String dishName, int amount, double prices, boolean isShowing) {
        this.dishName = dishName;
        this.amount = amount;
        this.prices = prices;
        this.isShowing = isShowing;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void setShowing(boolean showing) {
        this.isShowing = showing;
    }

    @Override
    public String toString() {
        return "Dishes{" +
                "DishName='" + dishName + '\'' +
                ", Amount=" + amount +
                ", prices=" + prices +
                ", isShowing=" + isShowing +
                '}';
    }
}
