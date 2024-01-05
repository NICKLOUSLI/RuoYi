package com.ruoyi.project.system.user.controller;

import com.ruoyi.framework.aspectj.lang.annotation.MyAOP;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.Message;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Controller
public class LoginController extends BaseController
{

    @GetMapping("/login")
    //@MyAOP
    public String login()
    {   //这里的login是为了在地址栏输入login的时候进行跳转吗？
        return "login";
    }


    @PostMapping("/login")
    @ResponseBody
    @MyAOP
    public Message ajaxLogin(String username, String password, Boolean rememberMe)
    {
        System.out.println("执行login 操作");
        // *根据传输的用户名和密码建立一个Token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        // 这个subject干啥用的
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return Message.success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return Message.error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "/error/unauth";
    }


}
