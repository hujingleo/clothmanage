package com.moxi.dao;



import java.sql.SQLException;

import com.moxi.model.User;

public interface UserDao {

//    @Select("select * from user where id = #{id}")
    User findById(Integer id) throws SQLException;

    void insert(User user) throws SQLException;
}