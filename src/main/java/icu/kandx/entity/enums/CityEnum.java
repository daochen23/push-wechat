package icu.kandx.entity.enums;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Shaodi.kou
 * @Date 2021/12/26 15:49
 */
public enum CityEnum {
    YU_HUA_TAI(320114, "南京市 雨花台区"),
    NAN_LE(410923, "濮阳市 南乐县");
    private int code;

    private String msg;

    CityEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getCityName(int code) {
        CityEnum[] enums = CityEnum.values();
        for (CityEnum cityEnum : enums) {
            if (cityEnum.getCode() == code) {
                return cityEnum.getMsg();
            }
        }
        throw new IllegalArgumentException("bad request: " + code);
    }
}
