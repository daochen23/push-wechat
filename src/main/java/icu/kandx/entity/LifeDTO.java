package icu.kandx.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Shaodi.kou
 * @Date 2021/12/26 16:15
 */
@NoArgsConstructor
@Data
public class LifeDTO {

    private List<ResultsDTO> results;

    @NoArgsConstructor
    @Data
    public static class ResultsDTO {
        private SuggestionDTO suggestion;

        @NoArgsConstructor
        @Data
        public static class SuggestionDTO {
            private CarWashingDTO carWashing;
            private DressingDTO dressing;
            private FluDTO flu;
            private SportDTO sport;
            private TravelDTO travel;
            private UvDTO uv;

            @NoArgsConstructor
            @Data
            public static class CarWashingDTO {
                private String brief;
                private String details;
            }

            @NoArgsConstructor
            @Data
            public static class DressingDTO {
                private String brief;
                private String details;
            }

            @NoArgsConstructor
            @Data
            public static class FluDTO {
                private String brief;
                private String details;
            }

            @NoArgsConstructor
            @Data
            public static class SportDTO {
                private String brief;
                private String details;
            }

            @NoArgsConstructor
            @Data
            public static class TravelDTO {
                private String brief;
                private String details;
            }

            @NoArgsConstructor
            @Data
            public static class UvDTO {
                private String brief;
                private String details;
            }
        }
    }
}
