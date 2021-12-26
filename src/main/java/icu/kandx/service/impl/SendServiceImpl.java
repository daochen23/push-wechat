package icu.kandx.service.impl;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import icu.kandx.api.APIUtil;
import icu.kandx.entity.LifeDTO;
import icu.kandx.entity.MessageDTO;
import icu.kandx.entity.MessageModel;
import icu.kandx.entity.enums.CityEnum;
import icu.kandx.entity.weather.WeatherEntity;
import icu.kandx.service.SendService;
import icu.kandx.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Value("${life.lifeUrl}")
    private String lifeUrl;

    @Override
    public void sendWeatherMsg() {
        String weatherMedal = MessageModel.WEATHER_MODEL;
        WeatherEntity weatherInfo = apiUtil.getWeatherInfo(weatherUrl);
        LifeDTO lifeInfo = apiUtil.getLifeInfo(lifeUrl);
        if (weatherInfo == null || lifeInfo == null) {
            log.error("[SendService]: getWeatherInfo failed");
            return;
        }
        List<WeatherEntity.ForecastsDTO> forecasts = weatherInfo.getForecasts();
        List<WeatherEntity.ForecastsDTO.CastsDTO> weatherDTO = forecasts.get(0).getCasts();
        String cityName = CityEnum.getCityName(Integer.parseInt(forecasts.get(0).getAdcode()));
        LifeDTO.ResultsDTO.SuggestionDTO suggestion = lifeInfo.getResults().get(0).getSuggestion();
        String weatherMsg = String.format(weatherMedal, forecasts.get(0).getReporttime(), DateUtils.getLoveDay(),
                DateUtils.getLimitBirthday(), DateUtils.getLimitLoveDay().get(0), DateUtils.getLimitLoveDay().get(1),
                DateUtils.getLimitWageDay(), cityName, weatherDTO.get(0).getDayweather(), weatherDTO.get(0).getDayweather(),
                weatherDTO.get(0).getNightweather(), weatherDTO.get(0).getNighttemp(), weatherDTO.get(0).getDaytemp(),
                suggestion.getUv().getBrief(), suggestion.getDressing().getBrief(), suggestion.getFlu().getBrief(),
                suggestion.getSport().getBrief());
        log.info("weatherInfo: {}", weatherMsg);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTitle("晓可爱的天气提醒");
        messageDTO.setDesp(weatherMsg);
        messageDTO.setOpenid(openId);
        log.info("sendWeatherInfo: {}", JSON.toJSONString(messageDTO));
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
        log.info("sendWageMsg: {}", JSON.toJSONString(messageDTO));
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
        log.info("sendBirthDayMsg: {}", JSON.toJSONString(messageDTO));
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
        log.info("sendSleepMsg: {}", JSON.toJSONString(messageDTO));
        apiUtil.sendMessage(sendUrl, messageDTO);
    }
}
