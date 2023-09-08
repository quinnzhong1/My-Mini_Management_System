package com.zhongsijia.filter;

import com.alibaba.fastjson.JSONObject;
import com.zhongsijia.pojo.Result;
import com.zhongsijia.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements jakarta.servlet.Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpServletResponse resp = (HttpServletResponse) servletResponse;

    String url = req.getRequestURL().toString();
    log.info("Requested url: {}", url);

    if (url.contains("login")) {
      log.info("Login...");
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }

    String jwt = req.getHeader("token");

    if (!StringUtils.hasLength(jwt)) {
      log.info("Header is null -> not login");
      Result error = Result.error("NOT_LOGIN");
      String notLogin = JSONObject.toJSONString(error);
      resp.getWriter().write(notLogin);
      return;
    }

    try {
      JwtUtils.parseJWT(jwt);
    } catch (Exception e) {
      e.printStackTrace();
      log.info("Fail to parse the token, return not login");
      Result error = Result.error("NOT_LOGIN");
      String notLogin = JSONObject.toJSONString(error);
      resp.getWriter().write(notLogin);
      return;
    }

    log.info("Valid token");
    filterChain.doFilter(servletRequest,servletResponse);
  }
}
