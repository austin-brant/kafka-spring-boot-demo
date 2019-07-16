package com.austin.brant.kafka.demo.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * 消息类容
 *
 * @author austin-brant
 * @since 2019/7/15 19:42
 */
@Builder
@Data
public class Message {

    private Long id;
    private String msg;
    private Date sendTime;
}
