/*
 * Copyright (C) @2021 Webank Group Holding Limited
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package cn.webank.dockin.rm.database;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
public class WbSqlSession implements AutoCloseable {
    private org.apache.ibatis.session.SqlSession sqlSession;
    public WbSqlSession(org.apache.ibatis.session.SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    public void commit() throws SQLException {
        this.sqlSession.commit();
    }
    public void commit(boolean force) throws SQLException {
        this.sqlSession.commit(force);
    }
    public List<BatchResult> flushStatements() throws SQLException {
        return this.sqlSession.flushStatements();
    }
    public void clearCache() throws SQLException {
        this.sqlSession.clearCache();
    }
    public void rollback() throws SQLException {
        this.sqlSession.rollback();
    }
    public void rollback(boolean force) throws SQLException {
        this.sqlSession.rollback(force);
    }
    @Override
    public void close() throws SQLException {
        if (this.sqlSession != null) {
            this.sqlSession.close();
        }
    }
    public <T> T getMapper(Class<T> type) {
        return this.sqlSession.getMapper(type);
    }
    public <T> T selectOne(String statement) {
        return this.sqlSession.selectOne(statement);
    }
    public <T> T selectOne(String statement, Object parameter) {
        return this.sqlSession.selectOne(statement, parameter);
    }
    public <E> List<E> selectList(String statement) {
        return this.sqlSession.selectList(statement);
    }
    public <E> List<E> selectList(String statement, Object parameter) {
        return this.sqlSession.selectList(statement, parameter);
    }
    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
        return this.sqlSession.selectList(statement, parameter, rowBounds);
    }
    public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
        return this.sqlSession.selectMap(statement, mapKey);
    }
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
        return this.sqlSession.selectMap(statement, parameter, mapKey);
    }
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey,
                                      RowBounds rowBounds) {
        return this.sqlSession.selectMap(statement, parameter, mapKey, rowBounds);
    }
    public void select(String statement, Object parameter, ResultHandler<?> handler) {
        this.sqlSession.select(statement, parameter, handler);
    }
    public void select(String statement, ResultHandler<?> handler) {
        this.sqlSession.select(statement, handler);
    }
    public void select(String statement, Object parameter, RowBounds rowBounds,
                       ResultHandler<?> handler) {
        this.sqlSession.select(statement, parameter, rowBounds, handler);
    }
    public int insert(String statement) {
        return this.sqlSession.insert(statement);
    }
    public int insert(String statement, Object parameter) {
        return this.sqlSession.insert(statement, parameter);
    }
    public int update(String statement) {
        return this.sqlSession.update(statement);
    }
    public int update(String statement, Object parameter) {
        return this.sqlSession.update(statement, parameter);
    }
    public int delete(String statement) {
        return this.sqlSession.delete(statement);
    }
    public int delete(String statement, Object parameter) {
        return this.sqlSession.delete(statement, parameter);
    }
    public Configuration getConfiguration() {
        return this.sqlSession.getConfiguration();
    }
    public Connection getConnection() {
        return this.sqlSession.getConnection();
    }
}
