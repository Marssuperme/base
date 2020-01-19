package com.op.base.core.controller.login;

import com.op.base.core.entity.common.BaseUser;
import com.op.base.core.entity.authority.token.UserLoginInfoDTO;
import com.op.base.core.service.login.LoginService;
import com.op.base.core.util.VerifyCodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;

/**
 * @author impact
 */
@RestController
public class LoginController {

    private String sessionValue = "verCode";
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 登录
     * @param loginInfo 用户信息
     * @return 用户信息
     */
    @ResponseBody
    @PostMapping("/login")
    public BaseUser login(@RequestBody @Validated UserLoginInfoDTO loginInfo, HttpSession session) {
        String verCode = String.valueOf(session.getAttribute(sessionValue));
        if (StringUtils.isBlank(verCode)) {
            return null;
        }
        if (!StringUtils.isBlank(verCode) && !StringUtils.isBlank(loginInfo.getVerificationCode()) && verCode.equalsIgnoreCase(loginInfo.getVerificationCode())) {
            session.removeAttribute(sessionValue);
            return loginService.selectByAccountAndPasswordAndRoleId(loginInfo.getUserAccount(), loginInfo.getUserPwd(), loginInfo.getRoleType());
        }
        return null;
    }

    /**
     * 获取验证码
     * @param request 请求
     * @param response 响应数据
     * @throws IOException 文件有误
     */
    @GetMapping(value = "/getImage")
    public void authImage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 存入会话session
        HttpSession session = request.getSession(true);
        // 删除以前的
        session.removeAttribute(sessionValue);
        session.removeAttribute("codeTime");
        session.setAttribute(sessionValue, verifyCode.toLowerCase());
        session.setAttribute("codeTime", LocalDateTime.now());
        // 生成图片
        int width  = 100;
        int height = 30;
        OutputStream out = response.getOutputStream();
        VerifyCodeUtils.outputImage(width, height, out, verifyCode);
    }
}
