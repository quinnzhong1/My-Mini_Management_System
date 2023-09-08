package com.zhongsijia.service;

import com.zhongsijia.pojo.Emp;
import com.zhongsijia.pojo.PageBean;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Employee Service
 */
public interface EmpService {

  PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin,
      LocalDate end);

  void delete(List<Integer> ids);

  void add(Emp emp);

  Emp getById(Integer id);

  void update(Emp emp);

  Emp login(Emp emp);
}
