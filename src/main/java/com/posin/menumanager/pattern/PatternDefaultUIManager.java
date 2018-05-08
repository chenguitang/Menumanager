package com.posin.menumanager.pattern;

import com.posin.menumanager.pattern.model.Dishes;
import com.posin.menumanager.socket.ConnManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Greetty on 2018/5/2.
 * <p>
 * 默认UI布局
 */
public class PatternDefaultUIManager {

    private static PatternDefaultUIManager mPatternDefaultUIManager = null;
    private static ArrayList<Dishes> menuLists = null;
    //    private static Vector<Dishes> mMenuVector=null;
    private static int SHOW_MAX_DISHES = 5;   //默认菜单栏只显示5个菜品
    private static double sum = 0; //总计


    public static synchronized PatternDefaultUIManager getInstance() {
        if (mPatternDefaultUIManager == null) {
            mPatternDefaultUIManager = new PatternDefaultUIManager();
        }
        if (menuLists == null) {
            menuLists = new ArrayList<>();
        }
//        if (mMenuVector==null){
//            mMenuVector=new Vector<>();
//        }
        return mPatternDefaultUIManager;
    }

    private PatternDefaultUIManager() {
    }


    /**
     * 是否已初始化布局UI
     */
    private static boolean HAVE_INIT = false;

    /**
     * 初始化默认布局
     *
     * @param showMaxDish 显示的最大菜品数
     * @throws Exception 异常
     */
    public void init(int showMaxDish) throws Exception {
        SHOW_MAX_DISHES = showMaxDish;
        ConnManager.getConnManager().sendViewCode(
                PatternConfig.getLayoutCommand(PatternConfig.LAYOUT_DEFAULT));
    }

    /**
     * 清除所有菜品，及统计等，恢复到初始化状态
     *
     * @throws Exception 异常
     */
    public void ClearDishes() throws Exception {
        ConnManager.getConnManager().sendViewCode(
                PatternConfig.getLayoutCommand(PatternConfig.LAYOUT_DEFAULT));
    }

    /**
     * 菜单栏增加一个菜品
     *
     * @param name   菜名
     * @param number 数量
     * @param prices 单价
     * @throws Exception 异常
     */
    public void addMenu(String name, int number, double prices) throws Exception {
        Dishes nowDishes = null;
        boolean haveExist = false;  //菜单栏是否已存在该菜品
        sum = number * prices;
        if (menuLists.size() > 0) {
            for (Dishes dishes : menuLists) {
                if (dishes.getDishName().equals(name)) { //菜单栏已有该菜品，修改菜品数量
                    dishes.setAmount(dishes.getAmount() + number);
                    nowDishes = dishes;
                    haveExist = true;
                    break;
                }
            }
        }


        //判断菜单栏菜品数量是否已超出最大限制
        if (menuLists.size() > SHOW_MAX_DISHES) {

        }


        //已添加到菜单栏的菜品
        if (haveExist) {
            ConnManager.getConnManager().sendViewCode(setCommand(nowDishes));
        } else {  //未添加到菜单栏的菜品
            nowDishes = new Dishes(name, number, prices);
            ConnManager.getConnManager().sendViewCode(addCommand(nowDishes));
        }
        menuLists.add(nowDishes);
    }

    private String[][] addCommand(Dishes dishes) {

        return PatternCommand.getAddViewCode(dishes.getDishName(), dishes.getAmount() + " * " +
                dishes.getPrices() + " ￥", String.valueOf(dishes.getAmount() * dishes.getPrices()) +
                " 元", String.valueOf(sum));
    }

    private String[][] setCommand(Dishes dishes) {

        return PatternCommand.getSetViewCode(dishes.getDishName(), dishes.getAmount() + " * " +
                dishes.getPrices() + " ￥", String.valueOf(dishes.getAmount() * dishes.getPrices()) +
                " 元", String.valueOf(sum));
    }

    /**
     * 菜单栏减少一个菜品
     *
     * @param name   菜名
     * @param number 数量
     * @param prices 单价
     * @throws Exception 异常
     */
    public void subsideMenu(String name, int number, double prices) throws Exception {
        if (menuLists.size() > 0) {
            for (Dishes dishes : menuLists) {
                if (dishes.getDishName().equals(name)) {

                }
            }
        } else {
            menuLists.add(new Dishes(name, number, prices));
        }
    }


}
