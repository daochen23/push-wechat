package icu.kandx.service.impl;

import cn.hutool.core.date.DateTime;
import icu.kandx.api.APIUtil;
import icu.kandx.entity.MessageDTO;
import icu.kandx.entity.MessageMedal;
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
        String weatherMedal = MessageMedal.WEATHER_MEDAL;
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
        messageDTO.setOpenid(testOpenId);
        apiUtil.sendMessage(sendUrl, messageDTO);
    }

    /**
     * 上交工资
     */
    @Override
    public void sendWageMsg() {
        String wageMedal = MessageMedal.WAGE_MEDAL;
        String wageMsg = String.format(wageMedal, new DateTime().toDateStr());
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTitle("工资单");
        messageDTO.setOpenid(testOpenId);
        messageDTO.setDesp(wageMsg);
        apiUtil.sendMessage(sendUrl, messageDTO);
    }
}
