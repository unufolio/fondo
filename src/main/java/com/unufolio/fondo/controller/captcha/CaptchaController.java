package com.unufolio.fondo.controller.captcha;

import com.unufolio.fondo.modules.captcha.CaptchaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/05/25
 */
@RestController
@RequestMapping("captcha")
public class CaptchaController {

    private final CaptchaService captchaManager;

    public CaptchaController(CaptchaService captchaManager) {
        this.captchaManager = captchaManager;
    }

    public String emailCaptcha() {
        return "";
    }

    public String phoneCaptcha() {
        return "";
    }


}
