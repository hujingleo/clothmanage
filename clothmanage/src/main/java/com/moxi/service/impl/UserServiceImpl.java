package com.moxi.service.impl;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

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

	@Override
    public void export(String[] titles, ServletOutputStream out) {                
        try{
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow hssfRow = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
            //居中样式
            hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFCell hssfCell = null;
            for (int i = 0; i < titles.length; i++) {
                hssfCell = hssfRow.createCell(i);//列索引从0开始
                hssfCell.setCellValue(titles[i]);//列名1
                hssfCell.setCellStyle(hssfCellStyle);//列居中显示                
            }
            
            // 第五步，写入实体数据 
            List<User> users = userDao.getusers(1,10);         

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            if(users != null && !users.isEmpty()){
                for (int i = 0; i < users.size(); i++) {
                    hssfRow = hssfSheet.createRow(i+1);                
                    User user = users.get(i);
                    
                    // 第六步，创建单元格，并设置值
                    int userid = 0;
                    if(user.getId() != 0){
                        userid = user.getId();
                    }
                    hssfRow.createCell(0).setCellValue(userid);
                    String username = "";
                    if(user.getName() != null){
                        username = user.getName();
                    }
                    hssfRow.createCell(1).setCellValue(username);
                   
                }
            }
            
            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
           
        }    
    }
}
