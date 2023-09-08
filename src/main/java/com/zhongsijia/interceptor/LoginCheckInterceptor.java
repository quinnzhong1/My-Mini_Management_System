package com.zhongsijia.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.zhongsijia.pojo.Result;
import com.zhongsijia.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
      throws Exception {

    String url = req.getRequestURL().toString();
    log.info("Requested url: {}", url);

    if (url.contains("login")) {
      log.info("Login...");
      return true;
    }

    String jwt = req.getHeader("token");

    if (!StringUtils.hasLength(jwt)) {
      log.info("Header is null -> not login");
      Result error = Result.error("NOT_LOGIN");
      String notLogin = JSONObject.toJSONString(error);
      resp.getWriter().write(notLogin);
      return false;
    }

    try {
      JwtUtils.parseJWT(jwt);
    } catch (Exception e) {
      e.printStackTrace();
      log.info("Fail to parse the token, return not login");
      Result error = Result.error("NOT_LOGIN");
      String notLogin = JSONObject.toJSONString(error);
      resp.getWriter().write(notLogin);
      return false;
    }

    log.info("Valid token");
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {

  }
}
