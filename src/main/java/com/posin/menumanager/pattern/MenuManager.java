package com.posin.menumanager.pattern;

import com.posin.menumanager.pattern.model.Dishes;
import com.posin.menumanager.socket.ConnManager;
import com.posin.menumanager.socket.listener.Callback;
import com.posin.menumanager.socket.listener.SendCallback;
import com.posin.menumanager.utils.ArrayUtils;

import java.util.LinkedHashMap;

/**
 * Created by Greetty on 2018/5/2.
 * <p>
 * 默认UI布局
 */
public class MenuManager {

    private static final String TAG = "MenuManager";
    private static MenuManager mPatternDefaultUIManager = null;
    private static LinkedHashMap<String, Dishes> menuMap;
    private static int SHOW_MAX_DISHES = 10;   //默认菜单栏只显示5个菜品
    private static boolean IS_CHINESE = true;  //是否为中文


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
     * 初始化默认布局
     *
     * @param showMaxDish 显示的最大菜品数
     * @param isChinese   是否为中文
     * @throws Exception 异常
     */
    public void init(int showMaxDish, boolean isChinese, final Callback connectCallback) throws Exception {
        SHOW_MAX_DISHES = showMaxDish;
        IS_CHINESE = isChinese;
        menuMap.clear();
        ConnManager.getConnManager().connectServer(connectCallback);
//        ConnManager.getConnManager().sendViewCode(
//                MenuConfig.getLayoutCommand(MenuConfig.LAYOUT_DEFAULT), sendCallback);

    }


    /**
     * 清除所有菜品，及统计等，恢复到初始化状态
     *
     * @throws Exception 异常
     */
    public void clearDishes(SendCallback sendCallback) throws Exception {
        menuMap.clear();
        ConnManager.getConnManager().sendViewCode(
                MenuConfig.getLayoutCommand(MenuConfig.LAYOUT_DEFAULT), sendCallback);
    }


    /**
     * @param menuMaps      LinkedHashMap<String, Dishes> 菜品集合
     * @param discountPrice 优惠金额
     * @param sum           总计金额
     * @param alreadyPay    已付金额
     * @param giveChange    找零
     * @param sendCallback  回调方法
     * @throws Exception 异常
     */
    public void setMenu(LinkedHashMap<String, Dishes> menuMaps,
                        double discountPrice, double sum, double alreadyPay,
                        double giveChange, SendCallback sendCallback) throws Exception {
        String[][] defaultUiCommand = MenuConfig.getLayoutCommand(MenuConfig.LAYOUT_DEFAULT);

        String[][] menuCommand = MenuCommand.addMenuCommandCode(menuMaps, discountPrice, sum, alreadyPay,
                giveChange, SHOW_MAX_DISHES, IS_CHINESE);

        String[][] addMenuCommand = ArrayUtils.mergeTwoDimArrayVer(defaultUiCommand, menuCommand);

        ConnManager.getConnManager().sendViewCode(addMenuCommand, sendCallback);

    }


}
