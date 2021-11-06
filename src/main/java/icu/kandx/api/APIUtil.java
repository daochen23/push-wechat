package icu.kandx.api;

import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.GetRequest;
import com.dtflys.forest.annotation.PostRequest;
import com.dtflys.forest.annotation.Var;
import icu.kandx.entity.MessageDTO;
import icu.kandx.entity.weather.WeatherEntity;
import org.springframework.stereotype.Component;

/**
 * 第三方API
 */
@Component
public interface APIUtil {
    /**
     * 获取天气信息
     * @param weatherUrl
     * @return
     */
    @GetRequest("{weatherUrl}")
    WeatherEntity getWeatherInfo(@Var("weatherUrl") String weatherUrl);

    @PostRequest("{sendUrl}")
    void sendMessage(@Var("sendUrl") String sendUrl, @Body MessageDTO messageDTO);
}
