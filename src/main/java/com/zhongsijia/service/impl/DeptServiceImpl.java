package com.zhongsijia.service.impl;

import com.zhongsijia.mapper.DeptMapper;
import com.zhongsijia.mapper.EmpMapper;
import com.zhongsijia.pojo.Dept;
import com.zhongsijia.pojo.DeptLog;
import com.zhongsijia.service.DeptLogService;
import com.zhongsijia.service.DeptService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implements department service
 */
@Slf4j
@Service
public class DeptServiceImpl implements DeptService {
  @Autowired
  private DeptMapper deptMapper;
  @Autowired
  private EmpMapper empMapper;
  @Autowired
  private DeptLogService deptLogService;

  @Override
  public List<Dept> list() {
    return deptMapper.list();
  }

//  @Transactional(rollbackFor = Exception.class)
  @Transactional
  @Override
  public void delete(Integer id) {
    try {
      deptMapper.deleteById(id);
      int i = 1/0;
      empMapper.deleteByDeptId(id);
    } finally {
      DeptLog deptLog = new DeptLog();
      deptLog.setCreateTime(LocalDateTime.now());
      deptLog.setDescription("The Department " + id + " is null now.");
      deptLogService.insert(deptLog);
    }

  }

  @Override
  public void add(Dept dept) {
    dept.setCreateTime(LocalDateTime.now());
    dept.setUpdateTime(LocalDateTime.now());
    deptMapper.insert(dept);
  }

  @Override
  public Dept search(Integer id) {
    return deptMapper.searchById(id);
  }

  @Override
  public void modify(Dept dept) {
    dept.setUpdateTime(LocalDateTime.now());
    deptMapper.modify(dept.getID(), dept.getName(), dept.getUpdateTime());
  }
}
