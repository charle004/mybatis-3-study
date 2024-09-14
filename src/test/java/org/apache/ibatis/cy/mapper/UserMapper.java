package org.apache.ibatis.cy.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.cy.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

  User selectById(@Param("id") int id);

  List<User> selectByMap(Map<String,Object> map);
  List<User> selectAll();

  List<User> selectByStatment(Map<String,Object> map);

}
