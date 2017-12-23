package com.moxi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.moxi.entity.Student;
import com.moxi.utils.SimpleListObject;
import com.moxi.utils.SimpleNetObject;

@RestController
@RequestMapping("/admin/factory")
public class FactoryController {

	@RequestMapping("index")
	public ModelAndView page1() {
		return new ModelAndView("/index.html");
	}

	@RequestMapping("testSimpleobject")
	public SimpleNetObject map1() {
		SimpleNetObject sno = new SimpleNetObject();
		Student student = new Student();
		student.setName("student");
		student.setAge(18);
		sno.setData(student);
		sno.setMessage("test message");
		sno.setResult(1);
		return sno;
	}
	@RequestMapping("testSimplelistobject")
	public SimpleListObject<Student> testSimplelistobject() {
		SimpleListObject<Student> slo = new SimpleListObject<>();
		Student student = new Student();
		student.setName("student");
		student.setAge(18);
		Student student2 = new Student();
		student2.setName("student2");
		student2.setAge(19);
		List<Student> studentlist = new ArrayList<Student>();
		studentlist.add(student);
		studentlist.add(student2);
		slo.setRows(studentlist);
		slo.setLimit(10);
		slo.setPage(1);
		slo.setTotal(studentlist.size());
		return slo;
	}
}
