package icu.kandx.task;

import icu.kandx.service.SendService;
import icu.kandx.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author Shaodi.kou
 * @Date 2021/10/30 17:58
 */
@Component
@EnableScheduling
public class GirlFriendTask {
    @Autowired
    private SendService service;

    @Scheduled(cron = "0 0 7 * * ?")
    public void send() {
        service.sendWeatherMsg();
    }

    @Scheduled(cron = "0 0 8 * * ?")
    public void sendK() {
        long limitWageDay = DateUtils.getLimitWageDay();
        if (limitWageDay == 0) {
            service.sendWageMsg();
        }
    }
}
