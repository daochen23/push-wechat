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
     * 接受方可以是多个用 “|”隔开
     */
    private String touser;

    /**
     * 消息类型 这里写死markdown
     */
    private String msgtype = "text";

    /**
     * 企业应用的id
     */
    private Integer agentid;

    private MessageContent text;


}
