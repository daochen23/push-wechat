package icu.kandx.api;

import com.dtflys.forest.annotation.GetRequest;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.PostRequest;
import com.dtflys.forest.annotation.Var;
import icu.kandx.entity.LifeDTO;
import icu.kandx.entity.MessageDTO;
import icu.kandx.entity.WeChatTokenDTO;
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
    void sendMessage(@Var("sendUrl") String sendUrl, @JSONBody MessageDTO messageDTO);

    @GetRequest("{lifeUrl}")
    LifeDTO getLifeInfo(@Var("lifeUrl") String lifeUrl);

    /**
     * 获取weChat token
     * @param tokenUrl tokenUrl
     * @return 返回token
     */
    @GetRequest("{tokenUrl}")
    WeChatTokenDTO getWeChatToken(@Var("tokenUrl") String tokenUrl);
}
