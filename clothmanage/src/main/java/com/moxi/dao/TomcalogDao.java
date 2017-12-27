package com.moxi.dao;

import org.apache.ibatis.annotations.Param;

public interface TomcalogDao {
	int copylog(@Param("file") String file);
}
