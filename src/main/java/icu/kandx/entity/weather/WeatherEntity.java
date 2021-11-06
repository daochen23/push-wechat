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

    private String countryEn;
    private String country;
    private String updateTime;
    private List<DataDTO> data;
    private String city;
    private String cityEn;
    private String cityid;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        private String date;
        private String winMeter;
        private String sunrise;
        private String airLevel;
        private String week;
        private String air;
        private String wea;
        private String weaDay;
        private String weaNight;
        private String winSpeed;
        private String day;
        private String tem;
        private List<String> win;
        private String tem2;
        private List<HoursDTO> hours;
        private String weaNightImg;
        private String tem1;
        private String visibility;
        private String weaDayImg;
        private List<IndexDTO> index;
        private String pressure;
        private String airTips;
        private String weaImg;
        private String sunset;

        @NoArgsConstructor
        @Data
        public static class HoursDTO {
            private String hours;
            private String wea;
            private String weaImg;
            private String winSpeed;
            private String tem;
            private String win;
        }

        @NoArgsConstructor
        @Data
        public static class IndexDTO {
            private String level;
            private String title;
            private String desc;
        }
    }
}