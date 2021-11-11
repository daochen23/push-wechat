package icu.kandx.task;

import icu.kandx.service.SendService;
import icu.kandx.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GirlFriendTask {
    @Autowired
    private SendService service;

    @Scheduled(cron = "0 0 7 * * ?")
    public void send() {
        log.info("start send weather msg...");
        service.sendWeatherMsg();
        log.info("end send weather msg...");
    }

    @Scheduled(cron = "0 0 8 * * ?")
    public void sendWage() {
        long limitWageDay = DateUtils.getLimitWageDay();
        if (limitWageDay == 0) {
            log.info("start send wage msg...");
            service.sendWageMsg();
            log.info("end send wage msg...");

        }
    }

    @Scheduled(cron = "0 0 23 * * ?")
    public void sendSleep() {
        log.info("start send sleep msg...");
        service.sendSleepMsg();
        log.info("end send sleep msg...");
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void sendBirthday() {
        if (DateUtils.getLimitBirthday() == 0) {
            log.info("start send birthday msg...");
            service.sendBirthDayMsg();
            log.info("end send birthday msg...");
        }
    }
}
