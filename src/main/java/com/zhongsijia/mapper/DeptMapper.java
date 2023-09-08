package com.zhongsijia.mapper;

import com.zhongsijia.pojo.Dept;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Department Mapper
 */
@Mapper
public interface DeptMapper {

  /**
   * Check all departments
   * @return
   */
  @Select("select * from dept")
  List<Dept> list();

  /**
   * Delete the department by ID
   * @param id the ID of the department
   */
  @Delete("delete from dept where id = #{id}")
  void deleteById(Integer id);

  @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
  void insert(Dept dept);

  @Select("select * from dept where id = #{id}")
  Dept searchById(Integer id);

  @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
  void modify(Integer id, String name, LocalDateTime updateTime);
}
