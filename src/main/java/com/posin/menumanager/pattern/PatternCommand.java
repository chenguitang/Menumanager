package com.posin.menumanager.pattern;

import com.posin.menumanager.utils.StringUtils;

/**
 * Created by Greetty on 2018/5/8.
 * <p>
 * 控制副屏默认布局指令
 */
public class PatternCommand {


    /**
     * 添加一个菜品到菜单栏
     * 格式化后的指令
     *
     * @param name      菜品名称
     * @param amount    菜品数量
     * @param prices    菜品单价
     * @param sum       所有菜品总计
     * @param isChinese 是否为中文
     * @return 指令集
     */
    public static String[][] addItemCommand(String name, int amount, double prices,
                                            double sum, boolean isChinese) {

        if (isChinese) {
            return PatternCommand.getAddViewCode(StringUtils.formatMaxLength(name, 5), amount +
                            " * " + prices + " ￥", String.valueOf(amount * prices) + " 元",
                    String.valueOf(sum));

        } else {
            return PatternCommand.getAddViewCode(StringUtils.formatMaxLength(name, 5), amount +
                            " * " + prices + " $", String.valueOf(amount * prices) + " $",
                    String.valueOf(sum));
        }
    }

    /**
     * 修改菜单栏菜品信息
     * 格式化后的指令
     *
     * @param name      菜品名称
     * @param amount    菜品数量
     * @param prices    菜品单价
     * @param sum       所有菜品总计
     * @param isChinese 是否为中文
     * @return 指令集
     */
    public static String[][] setItemCommand(String name, int amount, double prices,
                                            double sum, boolean isChinese) {
        if (isChinese) {
            return PatternCommand.getSetViewCode(StringUtils.formatMaxLength(name, 5), amount +
                            " * " + prices + " ￥", String.valueOf(amount * prices) + " 元",
                    String.valueOf(sum));
        } else {
            return PatternCommand.getSetViewCode(StringUtils.formatMaxLength(name, 5), amount +
                    " * " + prices + " $", String.valueOf(amount * prices) + " $", String.valueOf(sum));
        }
    }


    /**
     * 修改文本显示
     *
     * @param name 布局名称
     * @param text 文本值
     * @return 指令集
     */
    public static String[][] getSetPromptView(String name, String text) {
        String[][] vcs = new String[][]{
                {
                        "action", "set",
                        "name", name,
                        "text", text,
                },
        };

        return vcs;
    }

    /**
     * 添加一条菜品
     *
     * @param name          菜品名称
     * @param middleContent 数量 * 单价
     * @param subPrice      小计金额
     * @param sum           累计金额
     * @return 指令集
     */
    public static String[][] getAddViewCode(String name, String middleContent, String subPrice, String sum) {

        String[][] vcs = new String[][]{
                {
                        "action", "add",
                        "parent", "layout_1_2",
                        "name", "layout_" + name,
                        "type", "linear_layout",
                        "orientation", "horizontal",
                        "width", "match_parent",
                        "height", "wrap_content",
                },
                {
                        "action", "add",
                        "parent", "layout_" + name,
                        "name", name,
                        "type", "text",
                        "gravity", "left",
                        "width", "0",
                        "weight", "1",
                        "height", "wrap_content",
                        "text", name,
                        "size", "16",
                        "color", "0xFFFFFFFF",
                },
                {
                        "action", "add",
                        "parent", "layout_" + name,
                        "name", "middle_" + name,
                        "type", "text",
                        "gravity", "center",
                        "width", "0",
                        "weight", "1",
                        "height", "wrap_content",
                        "text", middleContent,
                        "size", "16",
                        "color", "0xFFFFFFFF",
                },
                {
                        "action", "add",
                        "parent", "layout_" + name,
                        "name", "price_" + name,
                        "type", "text",
                        "gravity", "right",
                        "width", "0",
                        "weight", "1",
                        "height", "wrap_content",
                        "text", subPrice,
                        "size", "16",
                        "color", "0xFFFFFFFF",
                },
                {
                        "action", "set",
                        "name", "text_cum",
                        "text", sum + "元",
                },
        };
        return vcs;
    }

    /**
     * 获取更改视图代码
     *
     * @param name          菜品名称
     * @param middleContent 数量 * 单价
     * @param subPrice      小计金额
     * @param sum           累计金额
     * @return 指令集
     */
    public static String[][] getSetViewCode(String name, String middleContent, String subPrice, String sum) {

        String[][] vcs = new String[][]{
                {
                        "action", "set",
                        "name", "middle_" + name,
                        "text", middleContent,
                },
                {
                        "action", "set",
                        "name", "price_" + name,
                        "text", subPrice,
                },
                {
                        "action", "set",
                        "name", "text_cum",
                        "text", sum + "元",
                },
        };

        return vcs;
    }

    /**
     * 获取移除一条视图的代码
     *
     * @param name 菜品名称
     * @param sum  累计金额
     * @return 指令集
     */
    public static String[][] getRemoveViewCode(String name, String sum) {
        String[][] vcs = new String[][]{
                {
                        "action", "remove",
                        "name", "layout_" + name,
                },
                {
                        "action", "set",
                        "name", "text_cum",
                        "text", sum + "元",
                },
        };
        return vcs;
    }

    /**
     * 获取修改支付视图代码
     *
     * @param alreadyPay 已收款
     * @param giveChange 找零
     * @return 指令集
     */
    public static String[][] getResultViewCode(String alreadyPay, String giveChange) {

        String[][] vcs = new String[][]{
                {
                        "action", "set",
                        "name", "text_paid",
                        "text", alreadyPay,
                },
                {
                        "action", "set",
                        "name", "text_odd",
                        "text", giveChange,
                },
        };

        return vcs;
    }
}
