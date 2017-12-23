package com.moxi.dao;



import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moxi.model.User;

public interface UserDao {

//    @Select("select * from user where id = #{id}")
    User findById(Integer id) throws SQLException;

    void insert(User user) throws SQLException;
    
    List<User> getusers(@Param("offset") int offset, @Param("limit") int limit);
    int countTotal();
}