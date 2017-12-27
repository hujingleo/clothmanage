package com.moxi.controller;
 
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moxi.service.TomcatlogService;
import com.moxi.service.UserService;
import com.moxi.utils.SimpleNetObject;
 

 
@Controller
public class UserController {
 
    @Autowired
    private UserService userService;
    @Autowired
    private TomcatlogService tomcatlogService;
    @RequestMapping("/export.do")
    public  String export(HttpServletResponse response){    
        response.setContentType("application/binary;charset=UTF-8");
        try{
            ServletOutputStream out=response.getOutputStream();
            String fileName=new String(("UserInfo "+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getBytes(),"UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
            String[] titles = { "用户编号", "用户姓名", "用户密码", "用户年龄" }; 
            userService.export(titles, out);
            return "success";
        } catch(Exception e){
            e.printStackTrace();
            return "导出信息失败";
        }
    }
    public static void main(String[] args) {
		SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date =sfDateFormat.format(new Date());
		String uri = "/usr/software/ymall/test/apache-tomcat-7.0.53/logs/localhost_access_log."+date+".txt";
		System.out.println(uri);
	}
    
	@RequestMapping("/testcopylog")
	@ResponseBody
	public SimpleNetObject testcopylog() {
		String file = "/usr/software/ymall/test/apache-tomcat-7.0.53/logs";
		tomcatlogService.copylog(file);
		return null;
		
	}
	
	@RequestMapping("/addLog")
	public SimpleNetObject add(HttpSession session) throws IOException {
		SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date =sfDateFormat.format(new Date());
		String uri = "/usr/software/ymall/test/apache-tomcat-7.0.53/logs/localhost_access_log."+date+".txt";



		return null;
	}
}