package com.moxi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moxi.dao.TomcalogDao;
import com.moxi.dao.UserDao;
import com.moxi.service.TomcatlogService;

@Service
public class TomcatlogServiceImpl implements TomcatlogService{
    @Autowired
    private TomcalogDao tomcalogDao;
	@Override
	public int copylog(String file) {
		
		return tomcalogDao.copylog(file);
	}

}
