package com.unufolio.fondo.modules.sms;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/05/03
 */
public interface ISmsSenderStrategy {

    void send(SmsTask smsTask);
}
