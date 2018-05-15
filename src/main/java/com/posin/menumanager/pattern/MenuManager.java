package com.posin.menumanager.pattern;

import android.util.Log;

import com.posin.menumanager.pattern.model.Dishes;
import com.posin.menumanager.socket.ConnManager;
import com.posin.menumanager.socket.listener.ConnectCallback;
import com.posin.menumanager.socket.listener.SendCallback;
import com.posin.menumanager.utils.DoubleUtils;
import com.posin.menumanager.utils.LogUtils;
import com.posin.menumanager.utils.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Greetty on 2018/5/2.
 * <p>
 * 默认UI布局
 */
public class MenuManager {

    private static final String TAG = "MenuManager";
    private static MenuManager mPatternDefaultUIManager = null;
    private static ArrayList<Dishes> menuLists = null;
    private static LinkedHashMap<String, Dishes> menuMap;
    //    private static Vector<Dishes> mMenuVector=null;
    private static int SHOW_MAX_DISHES = 5;   //默认菜单栏只显示5个菜品
    private static boolean IS_CHINESE = true;  //是否为中文
//    private static double sum = 0; //总计


    public static synchronized MenuManager getInstance() {
        if (mPatternDefaultUIManager == null) {
            mPatternDefaultUIManager = new MenuManager();
        }

        if (menuMap == null) {
            menuMap = new LinkedHashMap<>();
        }
        return mPatternDefaultUIManager;
    }

    private MenuManager() {
    }

    /**
     * 连接广告系统
     *
     * @param callback ConnectCallback
     */
    public void connect(ConnectCallback callback) {
        ConnManager.getConnManager().connectServer(callback);
    }

    /**
     * 初始化默认布局
     *
     * @param showMaxDish 显示的最大菜品数
     * @param isChinese   是否为中文
     * @throws Exception 异常
     */
    public void initDefaultUI(int showMaxDish, boolean isChinese, final SendCallback sendCallback) throws Exception {
        SHOW_MAX_DISHES = showMaxDish;
        IS_CHINESE = isChinese;
//        sum = 0;
        menuMap.clear();
        ConnManager.getConnManager().sendViewCode(
                MenuConfig.getLayoutCommand(MenuConfig.LAYOUT_DEFAULT), sendCallback);

    }


    /**
     * 清除所有菜品，及统计等，恢复到初始化状态
     *
     * @throws Exception 异常
     */
    public static void clearDishes(SendCallback sendCallback) throws Exception {
//        sum = 0;
        menuMap.clear();
        ConnManager.getConnManager().sendViewCode(
                MenuConfig.getLayoutCommand(MenuConfig.LAYOUT_DEFAULT), sendCallback);
    }

    /**
     * 菜单栏增加一个菜品
     *
     * @param name   菜名
     * @param number 数量
     * @param prices 单价
     * @throws Exception 异常
     */
    public void addMenu(String name, int number, double prices, double sum, SendCallback sendCallback) throws Exception {
        Dishes nowDishes = null;
//        sum = DoubleUtils.add(sum, DoubleUtils.multiply(number, prices));
//        Log.e(TAG, "add sum : " + sum);
        if (!menuMap.isEmpty()) {  //map不为空

            if (menuMap.size() > SHOW_MAX_DISHES) {  //菜品数量已大于限制显示数量
                if (menuMap.containsKey(name)) {  //菜单中已存在该菜品，修改菜品信息
                    Dishes dishes = menuMap.get(name);
                    if (dishes.isShowing()) {  //该菜品正在显示，直接修改信息
                        dishes.setAmount((dishes.getAmount() + number));
                        dishes.setShowing(true);
                        nowDishes = dishes;

                        LogUtils.Error(TAG, "name: " + nowDishes.getDishName() + "  prices: " +
                                nowDishes.getPrices() + "  amount: " + nowDishes.getAmount());
                        Log.e(TAG, "prices*amount: " + nowDishes.getPrices() * nowDishes.getAmount());
                        ConnManager.getConnManager().sendViewCode(MenuCommand.setItemCommand(
                                nowDishes.getDishName(), nowDishes.getAmount(), nowDishes.getPrices(),
                                sum, IS_CHINESE), sendCallback);

                    } else { //该菜品没有显示，删除显示的第一个菜品，修改信息并添加到显示栏
                        removeFirstShowingItem(menuMap, sum, sendCallback);
                        dishes.setAmount((dishes.getAmount() + number));
                        dishes.setShowing(true);
                        nowDishes = dishes;
                        ConnManager.getConnManager().sendViewCode(MenuCommand.addItemCommand(
                                nowDishes.getDishName(), nowDishes.getAmount(), nowDishes.getPrices(),
                                sum, IS_CHINESE), sendCallback);
                    }
                } else { //菜单中没有该商品，删除显示的第一个菜品，添加到菜单中

                    removeFirstShowingItem(menuMap, sum, sendCallback);
                    nowDishes = new Dishes(name, number, prices, true);
                    //菜单列表显示新菜品
                    ConnManager.getConnManager().sendViewCode(MenuCommand.addItemCommand(
                            nowDishes.getDishName(), nowDishes.getAmount(), nowDishes.getPrices(),
                            sum, IS_CHINESE), sendCallback);
                }


            } else {  //菜品数量小于限制显示数量
                if (menuMap.containsKey(name)) {  //菜单中已存在该菜品，修改菜品信息
                    Dishes dishes = menuMap.get(name);
                    dishes.setAmount((dishes.getAmount() + number));
                    dishes.setShowing(true);
                    nowDishes = dishes;

                    ConnManager.getConnManager().sendViewCode(MenuCommand.setItemCommand(
                            nowDishes.getDishName(), nowDishes.getAmount(), nowDishes.getPrices(),
                            sum, IS_CHINESE), sendCallback);

                } else { //该菜品不存在菜单中,直接添加菜品
                    //添加新菜品到菜单
                    nowDishes = new Dishes(name, number, prices, true);
                    //菜单列表显示新菜品
                    ConnManager.getConnManager().sendViewCode(MenuCommand.addItemCommand(
                            nowDishes.getDishName(), nowDishes.getAmount(), nowDishes.getPrices(),
                            sum, IS_CHINESE), sendCallback);
                }
            }

        } else {
            nowDishes = new Dishes(name, number, prices, true);
            ConnManager.getConnManager().sendViewCode(MenuCommand.addItemCommand(
                    nowDishes.getDishName(), nowDishes.getAmount(), nowDishes.getPrices(),
                    sum, IS_CHINESE), sendCallback);
        }
        menuMap.put(nowDishes.getDishName(), nowDishes);
    }

    /**
     * 删除菜单显示栏的第一个菜品
     *
     * @param menuMap 菜单集合
     * @param sum     总计
     */
    private void removeFirstShowingItem(LinkedHashMap<String, Dishes> menuMap, double sum,
                                        SendCallback sendCallback) {
        for (String key : menuMap.keySet()) {
            Dishes removeDishes = menuMap.get(key);
            Log.e(TAG, "=====>>>> name: " + removeDishes.getDishName());
            if (removeDishes.isShowing()) {
                LogUtils.Error(TAG, "移除的菜品名字为： " + removeDishes.getDishName());
                ConnManager.getConnManager().sendViewCode(
                        MenuCommand.getRemoveViewCode(removeDishes.getDishName(),
                                StringUtils.decimalFormat(sum, 2)), sendCallback);
                removeDishes.setShowing(false);
                menuMap.put(removeDishes.getDishName(), removeDishes);
                break;
            }
        }
    }


    /**
     * 菜单栏减少一个菜品
     *
     * @param name   菜名
     * @param number 数量
     * @param prices 单价
     * @throws Exception 异常
     */
    public void subsideMenu(String name, int number, double prices, double sum, SendCallback sendCallback) throws Exception {

        if (!menuMap.isEmpty()) {  //列表不为空
            if (menuMap.containsKey(name)) { //菜单中存在该菜品

                Log.e(TAG, "=======  sum = " + sum);

                sum = DoubleUtils.subtract(sum, DoubleUtils.multiply(number, prices));
                LogUtils.Error(TAG, "sum: " + sum + "  number: " + number + "  prices: " + prices);

                Dishes dishes = menuMap.get(name);
                if (dishes.getAmount() <= number) {
                    menuMap.remove(name); //从列表中删除
                    ConnManager.getConnManager().sendViewCode(
                            MenuCommand.getRemoveViewCode(name,
                                    StringUtils.decimalFormat(sum, 2)), sendCallback);

                    //如果菜单列表中存在未显示的菜品，显示出来
                    for (String s : menuMap.keySet()) {
                        Dishes addShowDishes = menuMap.get(s);
                        if (!addShowDishes.isShowing()) {
                            addShowDishes.setShowing(true);
                            menuMap.put(addShowDishes.getDishName(), addShowDishes);
                            ConnManager.getConnManager().sendViewCode(MenuCommand.addItemCommand(
                                    addShowDishes.getDishName(), addShowDishes.getAmount(),
                                    addShowDishes.getPrices(), sum, IS_CHINESE), sendCallback);
                            break;
                        }
                    }

                } else {
                    if (dishes.isShowing()) {  //菜品正在显示
                        dishes.setAmount(dishes.getAmount() - number);
                        dishes.setPrices(prices);
                        menuMap.put(name, dishes);
                        ConnManager.getConnManager().sendViewCode(MenuCommand.setItemCommand(
                                name, dishes.getAmount(), dishes.getPrices(), sum, IS_CHINESE), sendCallback);
                    } else {
                        dishes.setAmount(dishes.getAmount() - number);
                        dishes.setPrices(prices);
                        dishes.setShowing(true);
                        menuMap.put(name, dishes);
                        ConnManager.getConnManager().sendViewCode(MenuCommand.addItemCommand(
                                name, dishes.getAmount(), dishes.getPrices(), sum, IS_CHINESE), sendCallback);
                    }
                }
            } else {
                throw new Exception("The menu does not exist in the menu ...");
            }
        } else {
            throw new Exception("menu does is empty ...");
        }
    }

    /**
     * 获取修改支付视图代码
     *
     * @param alreadyPay 已收款
     * @return 指令集
     */
    public static void pay(double alreadyPay, double sum, SendCallback sendCallback) {
        ConnManager.getConnManager().sendViewCode(MenuCommand.getResultViewCode(
                String.valueOf(alreadyPay + (IS_CHINESE ? "元" : "$")), String.valueOf(
                        DoubleUtils.subtract(alreadyPay, sum)) +
                        (IS_CHINESE ? "元" : "$")), sendCallback);
    }


//    /**
//     * 获取菜单总额
//     *
//     * @return double
//     */
//    public static double getSum() {
//        return sum;
//    }
}
