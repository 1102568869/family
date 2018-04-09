package tech.washmore.family.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * @author Washmore
 * @version V1.0
 * @summary 继承自SqlSessionDaoSupport的基础Dao
 * @Copyright (c) 2018, washmore.tech All Rights Reserved.
 * @since 2018/1/18
 */
@Repository
public class BaseDao extends SqlSessionDaoSupport {
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
