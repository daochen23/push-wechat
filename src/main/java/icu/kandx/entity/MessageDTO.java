package icu.kandx.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @Description:
 * @Author Shaodi.kou
 * @Date 2021/11/6 11:42
 */
@Data
@ToString
public class MessageDTO {
    /**
     * 标题
     */
    private String title;

    /**
     * 消息内容支持markdown
     */
    private String desp;

    /**
     * 接受方可以是多个用 “|”隔开
     */
    private String openid;
}
