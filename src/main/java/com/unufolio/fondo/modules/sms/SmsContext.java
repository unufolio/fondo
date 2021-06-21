package com.unufolio.fondo.modules.sms;

/**
 * @author Unufolio unufolio@gmail.com
 * @since 2021/05/03
 */
public class SmsContext {

    private ISmsSenderStrategy smsSender;

    public SmsContext(ISmsSenderStrategy smsSender) {
        this.smsSender = smsSender;
    }

    public void send(String taskId) {

    }

    private void getSmsDetails(String taskId) {

    }

    private void doSend(Object... objects) {

    }
}
