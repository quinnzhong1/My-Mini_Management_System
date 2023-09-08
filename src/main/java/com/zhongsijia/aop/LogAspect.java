package com.zhongsijia.aop;

import com.alibaba.fastjson.JSONObject;
import com.zhongsijia.mapper.OperateLogMapper;
import com.zhongsijia.pojo.OperateLog;
import com.zhongsijia.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LogAspect {
  @Autowired
  private HttpServletRequest request;
  @Autowired
  private OperateLogMapper operateLogMapper;
  @Around("@annotation(com.zhongsijia.anno.Log)")
  public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

    String jwt = request.getHeader("token");
    Claims claims = JwtUtils.parseJWT(jwt);
    Integer operateUser = (Integer) claims.get("id");

    LocalDateTime operateTime = LocalDateTime.now();

    String className = joinPoint.getTarget().getClass().getName();

    String methodName = joinPoint.getSignature().getName();

    Object[] args = joinPoint.getArgs();

    String methodParams = Arrays.toString(args);

    Long begin = System.currentTimeMillis();
    Object result = joinPoint.proceed();
    long end = System.currentTimeMillis();

    String returnValue = JSONObject.toJSONString(result);

    Long costTime = end - begin;

    OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
    operateLogMapper.insert(operateLog);

    log.info("AOP record Operation: {}", operateLog);

    return result;
  }
}
