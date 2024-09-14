package org.apache.ibatis.cy;

import com.mysql.cj.PreparedQuery;
import com.mysql.cj.Query;
import com.mysql.cj.ServerPreparedQuery;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.jdbc.ServerPreparedStatement;
import org.apache.ibatis.cy.mapper.UserMapper;
import org.apache.ibatis.cy.pojo.User;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

public class CodeConfigTest {

  @Test
  public void qs(){
    DataSource dataSource = createDataSouce();
    TransactionFactory transactionFactory = new JdbcTransactionFactory();
    Environment environment = new Environment("development", transactionFactory, dataSource);
    Configuration configuration = new Configuration(environment);
    configuration.addMapper(UserMapper.class);
    configuration.addInterceptor(new SqlExecuteTimeConsolePlugin());
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      UserMapper mapper = sqlSession.getMapper(UserMapper.class);
      User user = mapper.selectById(1);
      System.out.println(user);
    }
  }


  //自定义一个打印SQL执行时间的插件 (SQL执行 + 结果集处理的时间)
  //@Intercepts标识当前为 Mybatis的插件
  //@Signature 注解指定 插件作用的 对象和方法
  @Intercepts(@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}))
  static class SqlExecuteTimeConsolePlugin implements Interceptor{
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
      long l = System.currentTimeMillis();
      Object result = invocation.proceed();
      long milliSec = System.currentTimeMillis() - l;
      ServerPreparedStatement statement  = (ServerPreparedStatement)invocation.getArgs()[0];
      PreparedQuery query = (PreparedQuery) statement.getQuery();
      System.out.println("SQL :"+query.asSql() +" 耗时："+milliSec+" ms");
      return result;
    }
  }






  private DataSource createDataSouce(){
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowMultiQueries=true&useCursorFetch=true&defaultFetchSize=5000&allowPublicKeyRetrieval=true");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("19951004");
    return mysqlDataSource;
  }

}
