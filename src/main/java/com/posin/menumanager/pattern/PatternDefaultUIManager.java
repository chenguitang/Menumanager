package com.posin.menumanager.pattern;

import android.util.Log;

import com.posin.menumanager.pattern.model.Dishes;
import com.posin.menumanager.socket.ConnManager;
import com.posin.menumanager.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

/**
 * Created by Greetty on 2018/5/2.
 * <p>
 * 默认UI布局
 */
public class PatternDefaultUIManager {

    private static final String TAG = "PatternDefaultUIManager";
    private static PatternDefaultUIManager mPatternDefaultUIManager = null;
    private static ArrayList<Dishes> menuLists = null;
    private static Map<String, Dishes> menuMap;
    //    private static Vector<Dishes> mMenuVector=null;
    private static int SHOW_MAX_DISHES = 5;   //默认菜单栏只显示5个菜品
    private static boolean IS_CHINESE = true;  //是否为中文
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
        if (menuMap == null) {
            menuMap = new HashMap<>();
        }
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
     * @param isChinese   是否为中文
     * @throws Exception 异常
     */
    public void init(int showMaxDish, boolean isChinese) throws Exception {
        SHOW_MAX_DISHES = showMaxDish;
        IS_CHINESE = isChinese;
        sum = 0;
        menuMap.clear();
        ConnManager.getConnManager().sendViewCode(
                PatternConfig.getLayoutCommand(PatternConfig.LAYOUT_DEFAULT));
    }

    /**
     * 清除所有菜品，及统计等，恢复到初始化状态
     *
     * @throws Exception 异常
     */
    public void clearDishes() throws Exception {
        sum = 0;
        menuMap.clear();
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
        if (!menuMap.isEmpty()) {  //map不为空
            if (menuMap.containsKey(name)) {  //菜单栏已存在该菜品，修改菜品数量
                Dishes dishes = menuMap.get(name);
                dishes.setAmount((dishes.getAmount() + number));
                dishes.setShowing(true);
                nowDishes = dishes;
                haveExist = true;
                //判断菜单栏菜品数量是否已超出最大限制

            } else if (menuMap.size() > SHOW_MAX_DISHES) {
                //移除第一条菜品
            }


        }

        if (haveExist) {  //已添加到菜单栏的菜品
            ConnManager.getConnManager().sendViewCode(PatternCommand.setItemCommand(
                    nowDishes.getDishName(), nowDishes.getAmount(), nowDishes.getPrices(),
                    sum, IS_CHINESE));
        } else {  //未添加到菜单栏的菜品
            nowDishes = new Dishes(name, number, prices, true);
            ConnManager.getConnManager().sendViewCode(PatternCommand.addItemCommand(
                    nowDishes.getDishName(), nowDishes.getAmount(), nowDishes.getPrices(),
                    sum, IS_CHINESE));
        }
        menuMap.put(nowDishes.getDishName(), nowDishes);
        menuLists.add(nowDishes);
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
            menuLists.add(new Dishes(name, number, prices, true));
        }
    }


}
