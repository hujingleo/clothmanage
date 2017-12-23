package com.moxi.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.moxi.dao.UserDao;
import com.moxi.model.User;
import com.moxi.service.UserService;
import com.moxi.utils.DateUtil;
import com.moxi.utils.ExcelUtil;
import com.moxi.utils.ExportInfo;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PhychoLee on 2016/11/4 17:18.
 * Description: 用户service实现类
 */
@Service
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
	@Value("${exportfile}")
	private String exportfile;
    @Override
    public User findById(Integer id) throws SQLException {
        return userDao.findById(id);
    }

    @Override
    public void insert(User user) throws SQLException {
        userDao.insert(user);
    }

    /**
     * 测试事务
     * @param userList
     * @throws SQLException
     */
    @Transactional
    public void insertList(List<User> userList) throws SQLException {
        insert(userList.get(0));
        int i = 1/0;
        insert(userList.get(1));
    }

	@Override
	public List<User> getuserlist(int startat, int limit) {
		// TODO Auto-generated method stub
		return userDao.getusers(startat,limit);
	}

	@Override
	public int countTotal() {
		// TODO Auto-generated method stub
		return userDao.countTotal();
	}

	@Override
	public String exportCurrenPage2xls(int startat, int limit) {
		// TODO Auto-generated method stub

		ExcelUtil excelUtil = new ExcelUtil();

		List<User> list = userDao.getusers(startat,limit);
		try {
			String filename = "/user" + DateUtil.getFormatDate(new Date(), "yyyyMMddHHmmss") + ".xlsx";
			String filepath = exportfile + filename;
			Map<String, ExportInfo> header = new LinkedHashMap<String, ExportInfo>();
			header.put("id", new ExportInfo("用户id"));
			header.put("name", new ExportInfo("用户名"));
			excelUtil.export2XlsWithInfo(list, header, filepath);
			return filename;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	
	}
}
