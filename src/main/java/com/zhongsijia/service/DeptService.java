package com.zhongsijia.service;

import com.zhongsijia.pojo.Dept;
import java.util.List;

/**
 * Department Service
 */
public interface DeptService {
  public List<Dept> list();
  public void delete(Integer id);

  public void add(Dept dept);

  public Dept search(Integer id);

  public void modify(Dept dept);
}
