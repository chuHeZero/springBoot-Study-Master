package com.example.manage.common.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 通知事件
 * @author abs
 * @date 2020/12/25 14:05
 */
@Getter
@Setter
public class NoticeEvent extends ApplicationEvent {

    private String notice;

    public NoticeEvent(Object source, String notice) {
        super(source);
        this.notice = notice;
    }
}
