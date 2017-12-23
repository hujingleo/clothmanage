package com.moxi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.moxi.model.User;
import com.moxi.service.UserService;
import com.moxi.utils.SimpleListObject;

@RestController
@RequestMapping("/admin")
public class TestadminController {
    @Autowired
    private UserService userService;
	@Value("${export.weblocation}")
	private String exportWebRoot;
	@RequestMapping("index")
	public ModelAndView page1() {
		return new ModelAndView("/index.html");
	}

	@RequestMapping("map")
	public Map<String, String> map1() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "老张");
		map.put("age", "24");
		map.put("sex", "男");
		map.put("feature", "青春痘很多");
		map.put("dream", "没梦想啊");
		return map;
	}
//    @RequestMapping("/testinsert")
//    public SimpleNetObject testinsert(){
//    	SimpleNetObject sno = new SimpleNetObject();
//        int count = 0;
//		List<User> list = new ArrayList<User>();
//        for (int i = 0; i <100; i++) {
//        	User user = new User();
////			user.setId(i);
//			String str = Integer.toString(i);
//			String name = "name"+str;
//			user.setName(name);
//			list.add(user);
//			count++;
//		}
//        try {
//			userService.insertList(list);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        	sno.setMessage("新增了"+count+"条记录");
//        	
//        return sno;
//    }
    
    @RequestMapping("/testgetusers")
    public SimpleListObject<User> testgetusers(int page , int limit ){
    	SimpleListObject<User> slo =new  SimpleListObject<User>();
    	if (limit==0) {
			limit=10;
		}
    	if (page==0) {
    		page=1;
		}
    	int startat = 0;
    	if (page>1) {
    		startat = (page-1)*limit;
		}
    	List<User> userlist = userService.getuserlist(startat, limit);
    	int total = userService.countTotal();
        	slo.setRows(userlist);
        	slo.setPage(page);
        	slo.setLimit(limit);
        	slo.setTotal(total);
        return slo;
    }
    /**
	 * 导出当前页的所有查询记录
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/grouponExportCurrentQuery", produces = "text/plain")
	public String exportcurrentquery(int page , int limit) {
    	if (limit==0) {
			limit=10;
		}
    	if (page==0) {
    		page=1;
		}
    	int startat = 0;
    	if (page>1) {
    		startat = (page-1)*limit;
		}
    	List<User> userlist = userService.getuserlist(startat, limit);
		String filename = this.userService.exportCurrenPage2xls(startat, limit);
		return this.exportWebRoot + filename;
	}
}