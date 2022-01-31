package icu.kandx.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 获取微信token
 * @Author Shaodi.kou
 * @Date 2022/1/31 14:25
 */
@NoArgsConstructor
@Data
public class WeChatTokenDTO {
    /**
     * 状态码
     */
    private Integer errcode;

    /**
     * 返回信息
     */
    private String errmsg;

    /**
     * 重要：接口需要使用
     */
    private String accessToken;

    /**
     * token过期时间
     */
    private Long expiresIn;
}
