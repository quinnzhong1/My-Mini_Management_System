package com.zhongsijia.controller;

import com.zhongsijia.anno.Log;
import com.zhongsijia.pojo.Dept;
import com.zhongsijia.pojo.Result;
import com.zhongsijia.service.DeptService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // Logger
@RestController
@RequestMapping("/depts")
public class DeptController {
  @Autowired
  private DeptService deptService;

//  private static Logger log = LoggerFactory.getLogger(DeptController.class);

  /**
   * Check departments
   * @return All information about departments
   */
//  @RequestMapping(value = "/depts", method = RequestMethod.GET)
  @GetMapping
  public Result list() {
    log.info("Check all information about departments.");

    List<Dept> deptList = deptService.list();

    return Result.success(deptList);
  }

  /**
   * Delete the department with the specified ID
   * @param id the ID of the department
   * @return the information of the deleted department
   */
  @Log
  @DeleteMapping("/{id}")
  public Result delete(@PathVariable Integer id) {
    log.info("Delete the department with the specified ID: {}", id);

    deptService.delete(id);
    return Result.success();
  }

  /**
   * Add a new department
   * @param dept the new department
   * @return
   */
  @Log
  @PostMapping
  public Result add(@RequestBody Dept dept) {
    log.info("Add a department: {}", dept);
    deptService.add(dept);
    return Result.success();
  }

  @GetMapping("/{id}")
  public Result searchById(@PathVariable Integer id) {
    log.info("Search the department with the specified ID: {}", id);

    Dept dept = deptService.search(id);
    return Result.success(dept);
  }

  @Log
  @PutMapping
  public Result modify(@RequestBody Dept dept)  {
    log.info("Modify a department: {}", dept.getID());
    deptService.modify(dept);
    return Result.success();
  }
}
