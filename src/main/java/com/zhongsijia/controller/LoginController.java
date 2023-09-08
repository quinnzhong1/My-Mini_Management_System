package com.zhongsijia.controller;

import com.zhongsijia.pojo.Emp;
import com.zhongsijia.pojo.Result;
import com.zhongsijia.service.EmpService;
import com.zhongsijia.utils.JwtUtils;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

  @Autowired
  private EmpService empService;

  @PostMapping
  public Result login(@RequestBody Emp emp) {
    log.info("Login: {}", emp);
    Emp e = empService.login(emp);

    if (e != null) {
      Map<String, Object> claims = new HashMap<>();
      claims.put("id", e.getId());
      claims.put("name", e.getName());
      claims.put("username", e.getUsername());
      String jwt = JwtUtils.generateJwt(claims);
      return Result.success(jwt);
    }

    return Result.error("Wrong username or password!");
  }

}
