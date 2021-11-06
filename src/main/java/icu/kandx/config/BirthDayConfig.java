package icu.kandx.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 农历日期计算太麻烦写死
 * @Author Shaodi.kou
 * @Date 2021/11/6 14:58
 */
public class BirthDayConfig {
    public static Map<String, String> birthDayMap = new HashMap<>();

    static {
        birthDayMap.put("2021", "2021-02-20");
        birthDayMap.put("2022", "2022-02-09");
        birthDayMap.put("2023", "2023-01-30");
        birthDayMap.put("2024", "2024-02-18");
        birthDayMap.put("2025", "2025-02-06");
        birthDayMap.put("2026", "2026-02-25");
    }
}
