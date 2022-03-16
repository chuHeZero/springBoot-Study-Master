package com.example.manage.common.component;

import com.example.manage.common.event.NoticeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 消息接受类
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class ReceiverNoticeMessage {

    private  final ApplicationContext applicationContext;

    /**
     * 队列消息接收方法
     *
     * @param notice
     */
    public void handleMessage(String notice) {
        log.info("接收通知：{}", notice);
        // 发送消息
        applicationContext.publishEvent(new NoticeEvent(this, notice));
    }

}