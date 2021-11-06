package icu.kandx.service;

public interface SendService {
    /**
     * 发送天气提醒
     */
    void sendWeatherMsg();

    /**
     * 上交工资
     */
    void sendWageMsg();

    /**
     * 发送生日提醒
     */
    void sendBirthDayMsg();

    /**
     * 发送睡觉提醒
     */
    void sendSleepMsg();
}
