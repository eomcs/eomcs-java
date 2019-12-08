package ch26.i;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

public class SqlSessionProxy implements SqlSession {
  
  SqlSession realSession;
  
  public SqlSessionProxy(SqlSession realSession) {
    this.realSession = realSession;
  }
  
  public void close() {
    // close() 메서드를 무력화시킨다.
    // => 대신 모든 작업이 완료된 후에는 realClose()를 호출하게 한다.
  }
  
  public void realClose() {
    realSession.close();
  }
  
  public <T> T selectOne(String statement) {
    return realSession.selectOne(statement);
  }

  public <T> T selectOne(String statement, Object parameter) {
    return realSession.selectOne(statement, parameter);
  }

  public <E> List<E> selectList(String statement) {
    return realSession.selectList(statement);
  }

  public <E> List<E> selectList(String statement, Object parameter) {
    return realSession.selectList(statement, parameter);
  }

  public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
    return realSession.selectList(statement, parameter, rowBounds);
  }

  public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
    return realSession.selectMap(statement, mapKey);
  }

  public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
    return realSession.selectMap(statement, parameter, mapKey);
  }

  public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey,
      RowBounds rowBounds) {
    return realSession.selectMap(statement, parameter, mapKey, rowBounds);
  }

  public <T> Cursor<T> selectCursor(String statement) {
    return realSession.selectCursor(statement);
  }

  public <T> Cursor<T> selectCursor(String statement, Object parameter) {
    return realSession.selectCursor(statement, parameter);
  }

  public <T> Cursor<T> selectCursor(String statement, Object parameter, RowBounds rowBounds) {
    return realSession.selectCursor(statement, parameter, rowBounds);
  }

  public void select(String statement, Object parameter, ResultHandler handler) {
    realSession.select(statement, parameter, handler);
  }

  public void select(String statement, ResultHandler handler) {
    realSession.select(statement, handler);
  }

  public void select(String statement, Object parameter, RowBounds rowBounds,
      ResultHandler handler) {
    realSession.select(statement, parameter, rowBounds, handler);
  }

  public int insert(String statement) {
    return realSession.insert(statement);
  }

  public int insert(String statement, Object parameter) {
    return realSession.insert(statement, parameter);
  }

  public int update(String statement) {
    return realSession.update(statement);
  }

  public int update(String statement, Object parameter) {
    return realSession.update(statement, parameter);
  }

  public int delete(String statement) {
    return realSession.delete(statement);
  }

  public int delete(String statement, Object parameter) {
    return realSession.delete(statement, parameter);
  }

  public void commit() {
    realSession.commit();
  }

  public void commit(boolean force) {
    realSession.commit(force);
  }

  public void rollback() {
    realSession.rollback();
  }

  public void rollback(boolean force) {
    realSession.rollback(force);
  }

  public List<BatchResult> flushStatements() {
    return realSession.flushStatements();
  }

  public void clearCache() {
    realSession.clearCache();
  }

  public Configuration getConfiguration() {
    return realSession.getConfiguration();
  }

  public <T> T getMapper(Class<T> type) {
    return realSession.getMapper(type);
  }

  public Connection getConnection() {
    return realSession.getConnection();
  }
  
  
}
