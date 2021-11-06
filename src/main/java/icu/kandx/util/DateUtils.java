package icu.kandx.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import icu.kandx.config.BirthDayConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 计算日期
 * @Author Shaodi.kou
 * @Date 2021/11/6 13:43
 */
public class DateUtils {
    private static DateTime current = new DateTime();

    /**
     * 在一起多少天
     * @return
     */
    public static long getLoveDay() {
        return DateUtil.betweenDay(DateUtil.parse("2020-12-12 00:00:00"), current, Boolean.TRUE);
    }

    /**
     * 距离周年多少天
     * @return
     */
    public static List<Integer> getLimitLoveDay() {
        List<Integer> list = new ArrayList<>();
        // 周年
        list.add(current.year() - 2020);
        if (current.toDateStr().equals(DateUtil.parse(current.year() + "-12-12"))) {
            list.add(0);
            return list;
        }
        long limitDay = 0;
        if (current.after(DateUtil.parse(current.year() + "-12-12"))) {
            limitDay = DateUtil.betweenDay(current, DateUtil.parse(current.year() + 1 + "-12-12"), Boolean.TRUE);

        }else {
            limitDay = DateUtil.betweenDay(current, DateUtil.parse(current.year() + "-12-12"), Boolean.TRUE);
        }
        list.add(Integer.parseInt(limitDay + ""));
        return list;
    }

    /**
     * 距离生日还有多少天
     * @return
     */
    public static long getLimitBirthday() {
        String birthDay = BirthDayConfig.birthDayMap.get(current.year() + "");
        if (current.toDateStr().equals(birthDay)) {
            return 0;
        }
        if (current.after(DateUtil.parse(birthDay))) {
            return DateUtil.betweenDay(current, DateUtil.parse(BirthDayConfig.birthDayMap.get(current.year() + 1 + "")), Boolean.TRUE);
        }else {
            return DateUtil.betweenDay(current, DateUtil.parse(birthDay), Boolean.TRUE);
        }
    }

    /**
     * 距离发工资还有多少天
     * @return
     */
    public static long getLimitWageDay() {
        // 当前月
        if (current.dayOfMonth() <= 15) {
            return 15 - current.dayOfMonth();
        }else {
            // 下个月
            return DateUtil.betweenDay(current, DateUtil.parse(current.year() + "-" + current.month() + "-" + 15), Boolean.TRUE);
        }
    }
}
