package com.zhongsijia.controller;

import com.zhongsijia.anno.Log;
import com.zhongsijia.pojo.Emp;
import com.zhongsijia.pojo.PageBean;
import com.zhongsijia.pojo.Result;
import com.zhongsijia.service.EmpService;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
  @Autowired
  private EmpService empService;
  @GetMapping
  public Result page(@RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer pageSize,
                      String name, Short gender, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                      @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end) {
    log.info("Page search, param: {}, {}, {}, {}, {}, {}", page, pageSize, name, gender, begin, end);
    PageBean pageBean= empService.page(page, pageSize, name, gender, begin, end);
    return Result.success(pageBean);
  }

  @Log
  @DeleteMapping("/{ids}")
  public Result delete(@PathVariable List<Integer> ids) {
    log.info("Delete, ids: {}", ids);
    empService.delete(ids);
    return Result.success();
  }

  @Log
  @PostMapping
  public Result add(@RequestBody Emp emp) {
    log.info("Add, emp: {}", emp);
    empService.add(emp);
    return Result.success();
  }

  @GetMapping("/{id}")
  public Result getById(@PathVariable Integer id) {
    log.info("Get by ID: {}", id);
    Emp emp = empService.getById(id);
    return Result.success(emp);
  }

  @Log
  @PutMapping
  public Result update(@RequestBody Emp emp) {
    log.info("Update: {}", emp);
    empService.update(emp);
    return Result.success();
  }

}
