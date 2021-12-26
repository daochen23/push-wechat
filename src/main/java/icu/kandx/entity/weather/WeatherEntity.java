/**
  * Copyright 2021 bejson.com 
  */
package icu.kandx.entity.weather;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Auto-generated: 2021-10-27 23:34:32
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@NoArgsConstructor
@Data
@ToString
public class WeatherEntity {

    private String status;
    private String count;
    private String info;
    private String infocode;
    private List<ForecastsDTO> forecasts;

    @NoArgsConstructor
    @Data
    public static class ForecastsDTO {
        private String city;
        private String adcode;
        private String province;
        private String reporttime;
        private List<CastsDTO> casts;

        @NoArgsConstructor
        @Data
        public static class CastsDTO {
            private String date;
            private String week;
            private String dayweather;
            private String nightweather;
            private String daytemp;
            private String nighttemp;
            private String daywind;
            private String nightwind;
            private String daypower;
            private String nightpower;
        }
    }
}