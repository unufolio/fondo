package com.unufolio.fondo.modules.sms;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/05/03
 */
public enum SmsProvider {

    ALI,
    BAIDU,
    TENCENT,
    QINIU;

    public SmsProvider ofName(String name) throws Exception {
        SmsProvider[] smsProviders = SmsProvider.values();
        SmsProvider provider = null;
        for (SmsProvider smsProvider : smsProviders) {
            if (smsProvider.name().equals(name)) {
                provider = smsProvider;
                break;
            }
        }
        if (provider == null) {
            throw new Exception();
        }
        return provider;
    }

}
