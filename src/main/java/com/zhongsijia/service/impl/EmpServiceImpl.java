package com.zhongsijia.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongsijia.mapper.EmpMapper;
import com.zhongsijia.pojo.Emp;
import com.zhongsijia.pojo.PageBean;
import com.zhongsijia.service.EmpService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implements employee service
 */
@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
  @Autowired
  private EmpMapper empMapper;
//  @Override
//  public PageBean page(Integer page, Integer pageSize) {
//    Long count = empMapper.count();
//    Integer start = (page - 1) * pageSize;
//    List<Emp> empList = empMapper.page(start, pageSize);
//    PageBean pageBean = new PageBean(count, empList);
//
//    return pageBean;
//  }
  @Override
  public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin,
      LocalDate end) {
    PageHelper.startPage(page, pageSize);
    List<Emp> empList = empMapper.list(name, gender, begin, end);
    Page<Emp> p = (Page<Emp>) empList;

    PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

    return pageBean;
  }

  @Override
  public void delete(List<Integer> ids) {
    empMapper.delete(ids);
  }

  @Override
  public void add(Emp emp) {
    emp.setCreateTime(LocalDateTime.now());
    emp.setUpdateTime(LocalDateTime.now());
    empMapper.insert(emp);
  }

  @Override
  public Emp getById(Integer id) {
    return empMapper.getById(id);
  }

  @Override
  public void update(Emp emp) {
    emp.setUpdateTime(LocalDateTime.now());
    empMapper.update(emp);
  }

  @Override
  public Emp login(Emp emp) {
    return empMapper.getByUsernameAndPassword(emp);
  }
}
