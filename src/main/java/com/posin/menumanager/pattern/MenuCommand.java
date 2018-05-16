package com.posin.menumanager.pattern;

import android.util.Log;

import com.posin.menumanager.pattern.model.Dishes;
import com.posin.menumanager.utils.ArrayUtils;
import com.posin.menumanager.utils.LogUtils;
import com.posin.menumanager.utils.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by Greetty on 2018/5/8.
 * <p>
 * 控制副屏默认布局指令
 */
public class MenuCommand {

    private static final String TAG = "MenuCommand";


    /**
     * 生成菜单菜品指令
     *
     * @param menuMaps      菜品集合
     * @param discountPrice 优惠金额
     * @param sum           总金额
     * @param alreadyPay    已付金额
     * @param giveChange    找零
     * @param maxShow       最大显示数量
     * @param isChinese     是否为中文
     * @return 指令集合
     * @throws Exception 异常
     */
    public static String[][] addMenuCommandCode(LinkedHashMap<String, Dishes> menuMaps,
                                                double discountPrice, double sum, double alreadyPay,
                                                double giveChange, int maxShow, boolean isChinese)
            throws Exception {
        String[][] commandMenus = new String[][]{{}};
        if (menuMaps.isEmpty()) {
            throw new Exception("menu is null ,please Incoming menu ... ");
        }
//        Iterator<String> iterator = menuMaps.keySet().iterator();
        ListIterator<Map.Entry<String, Dishes>> iMenuMap = new ArrayList<>(menuMaps.entrySet()).
                listIterator(menuMaps.size());
        for (int i = 0; i < menuMaps.size(); i++) {

            String key = iMenuMap.previous().getKey();
            if (i >= maxShow) {
                break;
            }
            String dishName = menuMaps.get(key).getDishName();
            String[][] addMenuItemCommand = new String[][]{
                    {
                            "action", "add",
                            "parent", "layout_1_2",
                            "name", "layout_" + dishName,
                            "type", "linear_layout",
                            "orientation", "horizontal",
                            "width", "match_parent",
                            "height", "wrap_content",
                    },
                    {
                            "action", "add",
                            "parent", "layout_" + dishName,
                            "name", dishName,
                            "type", "text",
                            "gravity", "left",
                            "width", "0",
                            "weight", "1",
                            "height", "wrap_content",
                            "text", dishName,
                            "size", "16",
                            "color", "0xFFFFFFFF",
                    },
                    {
                            "action", "add",
                            "parent", "layout_" + dishName,
                            "name", "middle_" + dishName,
                            "type", "text",
                            "gravity", "center",
                            "width", "0",
                            "weight", "1",
                            "height", "wrap_content",
                            "text", StringUtils.append(menuMaps.get(key).getAmount(), "*",
                            menuMaps.get(key).getPrices(), isChinese ? "￥" : "$"),
                            "size", "16",
                            "color", "0xFFFFFFFF",
                    },
                    {
                            "action", "add",
                            "parent", "layout_" + dishName,
                            "name", "price_" + dishName,
                            "type", "text",
                            "gravity", "right",
                            "width", "0",
                            "weight", "1",
                            "height", "wrap_content",
                            "text", StringUtils.decimalFormat(menuMaps.get(key).getSubtotal(), 2),
                            "size", "16",
                            "color", "0xFFFFFFFF",
                    },
            };
            commandMenus = ArrayUtils.mergeTwoDimArrayVer(commandMenus, addMenuItemCommand);
        }

        String[][] payCommand = new String[][]{
                {
                        "action", "set",
                        "name", "text_pre",
                        "text", String.valueOf(discountPrice) + "元",
                },
                {
                        "action", "set",
                        "name", "text_cum",
                        "text", String.valueOf(sum) + "元",
                },
                {
                        "action", "set",
                        "name", "text_paid",
                        "text", String.valueOf(alreadyPay) + "元",
                },
                {
                        "action", "set",
                        "name", "text_odd",
                        "text", String.valueOf(giveChange) + "元",
                },

        };
        commandMenus = ArrayUtils.mergeTwoDimArrayVer(commandMenus, payCommand);
        return commandMenus;
    }


}
