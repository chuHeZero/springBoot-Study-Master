package com.example.manage.common.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 通知事件
 *
 * @author zzm
 * @date 2022/4/3 18:10
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
