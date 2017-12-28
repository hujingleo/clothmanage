package com.moxi.service;

import java.io.IOException;
import java.util.List;

import com.moxi.model.Tomcatlog;

public interface TomcatlogService {
	List<Tomcatlog> testget();
	int copylog(String file);
	long copytomcatlog(String readfilepath, String writefilepath);
	 long copytomcatloglastline(String readfilepath, String writefilepath,long lines) throws IOException;
}
