package com.example.crmdemo.common.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.crmdemo.modules.system.model.TUser;
import com.example.crmdemo.modules.system.service.TUserService;
import com.example.crmdemo.utils.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.util.List;

/**
 * <p>
 * 令牌拦截器 拦截非法请求
 * </p>
 *
 * @author wr
 * @since 2021-10-09
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private TUserService tUserService;

    @Autowired
    public void setTUserService(TUserService tUserService) {
        this.tUserService = tUserService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
//重写前置拦截方法preHandle 将在Controller方法处理前执行 返回true则允许请求 否则执行拦截
        String token;//URL编码转义后token
        String urlEncodeToken = null;//从请求头Cookies中获取到的token 未进行URL编码转义

        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {//允许浏览器自发起的preflight request(预检请求) 以检测实际请求是否可以被浏览器接受
            System.out.println("收到浏览器自发起的preflight request(预检请求) 允许该合法请求");
            return true;
        }

        Cookie[] cookies = request.getCookies();//获取请求中Cookies
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {//获取cookies中名为token的cookie
                    urlEncodeToken = cookie.getValue();
                }
            }
        }
//        System.err.println("请求头Cookies中token：" + urlEncodeToken);

        if (urlEncodeToken == null || "".equals(urlEncodeToken)) {//token不存在或为空时执行拦截
            System.out.println("收到未登录非法请求 执行拦截");
            return false;
        }

        token = URLDecoder.decode(urlEncodeToken, "UTF-8");
//        System.err.println("URL编码转义后token：" + token);

        if (token == null || "".equals(token)) {//token不存在或为空时执行拦截
            System.out.println("收到未登录非法请求 执行拦截");
            return false;
        }

        Integer userId = Integer.valueOf(AESUtil.decrypt(token));//AES解密token
//        System.out.println("解析token中用户编号为 "+userId);

        if (userId != null) {
            QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("id").eq("id", userId).eq("is_valid", 0);

            List<TUser> tUserNameList = tUserService.findAll(queryWrapper);
            if (tUserNameList == null && tUserNameList.isEmpty()) {
                System.out.println("数据库中查询不到与解析token中用户编号 "+userId+" 相对应的有效记录 收到恶意非法请求 执行拦截");
                return false;
            }
        }

        return true;//允许合法请求
    }
}
