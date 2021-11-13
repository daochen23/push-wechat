package icu.kandx;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import icu.kandx.api.APIUtil;
import icu.kandx.entity.weather.WeatherEntity;
import icu.kandx.service.SendService;
import icu.kandx.util.DateUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author Shaodi.kou
 * @Date 2021/10/17 15:17
 */
@SpringBootTest
@Slf4j
@Data
public class MainApplicationTests {
    @Autowired
    private APIUtil apiUtil;

    @Value("${weather.url}")
    private String weatherUrl;

    @Value("${server.sendUrl}")
    private String sendUrl;

    @Value("${server.testOpenId}")
    private String testOpenId;

    @Autowired
    private SendService service;

    @Test
    public void getWeatherInfo() {
        WeatherEntity weatherInfo = apiUtil.getWeatherInfo(weatherUrl);
        log.info("weatherInfo: {}", JSON.toJSONString(weatherInfo));
    }

    @Test
    public void sendMsg() {
        //service.sendWeatherMsg();
    }

    @Test
    public void getLove() {
        long loveDay = DateUtils.getLoveDay();
        System.out.println(loveDay);
    }

    @Test
    public void getLimitLove() {
        List<Integer> limitLoveDay = DateUtils.getLimitLoveDay();
        System.out.println(limitLoveDay);
    }

    @Test
    public void getWageDay() {
        long limitWageDay = DateUtils.getLimitWageDay();
        System.out.println(limitWageDay);
    }

    @Test
    public void getBirthDay() {
        long limitBirthday = DateUtils.getLimitBirthday();
        System.out.println(limitBirthday);
    }

    @Test
    public void test() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = simpleDateFormat.parse("2022-02-09 12:12:12");
        DateTime dateTime = new DateTime(parse);
        boolean after = dateTime.after(DateUtil.parse("2022-02-09"));
        System.out.println(after);
    }
}
