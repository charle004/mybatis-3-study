package org.apache.ibatis.cy;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.ibatis.cy.mapper.UserMapper;
import org.apache.ibatis.cy.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLConfigTest {

  @Test
  public void qs() throws IOException {
    String resource = "org/apache/ibatis/cy/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      UserMapper mapper = sqlSession.getMapper(UserMapper.class);
      Map<String,Object> map = new HashMap<>();
      map.put("name","tom");
      map.put("age",26);
      List<User> user = mapper.selectByMap(map);
      System.out.println(user);
    }

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      UserMapper mapper = sqlSession.getMapper(UserMapper.class);
      Map<String,Object> map = new HashMap<>();
      map.put("name","tom");
      map.put("age",26);
      List<User> user = mapper.selectByMap(map);
      System.out.println(user);
    }
  }



}
