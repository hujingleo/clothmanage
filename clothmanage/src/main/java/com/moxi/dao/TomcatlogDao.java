package com.moxi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moxi.model.Tomcatlog;

public interface TomcatlogDao {
	int copylog(@Param("file") String file);
	List<Tomcatlog> testget();
}
