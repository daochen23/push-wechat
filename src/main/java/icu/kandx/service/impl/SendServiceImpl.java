package icu.kandx.service.impl;

import cn.hutool.core.date.DateTime;
import icu.kandx.api.APIUtil;
import icu.kandx.entity.MessageDTO;
import icu.kandx.entity.MessageModel;
import icu.kandx.entity.weather.WeatherEntity;
import icu.kandx.service.SendService;
import icu.kandx.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author Shaodi.kou
 * @Date 2021/11/6 12:37
 */
@Service
@Slf4j
public class SendServiceImpl implements SendService {
    @Autowired
    private APIUtil apiUtil;

    @Value("${weather.url}")
    private String weatherUrl;

    @Value("${server.sendUrl}")
    private String sendUrl;

    @Value("${server.testOpenId}")
    private String testOpenId;

    @Value("${server.openId}")
    private String openId;

    @Override
    public void sendWeatherMsg() {
        String weatherMedal = MessageModel.WEATHER_MODEL;
        WeatherEntity weatherInfo = apiUtil.getWeatherInfo(weatherUrl);
        if (weatherInfo == null) {
            log.error("[SendService]: getWeatherInfo failed");
            return;
        }
        WeatherEntity.DataDTO dataDTO = weatherInfo.getData().get(0);
        String dateTime = dataDTO.getDate() + ", " + dataDTO.getWeek();
        String weatherMsg = String.format(weatherMedal, dateTime, DateUtils.getLoveDay(), DateUtils.getLimitBirthday(),
                DateUtils.getLimitLoveDay().get(0), DateUtils.getLimitLoveDay().get(1), DateUtils.getLimitWageDay(), weatherInfo.getCity(), dataDTO.getWea(), dataDTO.getWeaDay(),
                dataDTO.getWeaNight(), dataDTO.getTem1(), dataDTO.getTem2(), dataDTO.getIndex().get(0).getLevel(),
                dataDTO.getIndex().get(3).getLevel(), dataDTO.getAirLevel());
        log.info("weatherInfo: {}", weatherMsg);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTitle("晓可爱的天气提醒");
        messageDTO.setDesp(weatherMsg);
        messageDTO.setOpenid(openId);
        apiUtil.sendMessage(sendUrl, messageDTO);
    }

    /**
     * 上交工资
     */
    @Override
    public void sendWageMsg() {
        String wageMedal = MessageModel.WAGE_MODEL;
        String wageMsg = String.format(wageMedal, new DateTime().toDateStr());
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTitle("工资单");
        messageDTO.setOpenid(openId);
        messageDTO.setDesp(wageMsg);
        apiUtil.sendMessage(sendUrl, messageDTO);
    }

    /**
     * 发送生日提醒
     */
    @Override
    public void sendBirthDayMsg() {
        DateTime current = new DateTime();
        int num = current.year() - 1999;
        String birthdayModel = MessageModel.BIRTHDAY_MODEL;
        String birthDayMsg = String.format(birthdayModel, num);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTitle("生日提醒");
        messageDTO.setOpenid(openId);
        messageDTO.setDesp(birthDayMsg);
        apiUtil.sendMessage(sendUrl, messageDTO);
    }

    /**
     * 发送睡觉提醒
     */
    @Override
    public void sendSleepMsg() {
        String hintSleepModel = MessageModel.HINT_SLEEP_MODEL;
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTitle("睡觉觉提醒");
        messageDTO.setOpenid(openId);
        messageDTO.setDesp(hintSleepModel);
        apiUtil.sendMessage(sendUrl, messageDTO);
    }
}
