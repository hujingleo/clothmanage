package com.moxi.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.moxi.model.Tomcatlog;

import jnr.ffi.types.time_t;

public class IoUtil {
	public static void conversionFile(String readfilepath, String writefilepath) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		long linenum =0;
		try {
			File file = new File(writefilepath);
			if (!file.exists()) {
				file.createNewFile();
			}
			StringBuffer sb = new StringBuffer();
			reader = new BufferedReader(new FileReader(readfilepath));
			String line = null;
			// 按行读取
			while ((line = reader.readLine()) != null) {
				// 先将日志用,分割
				String[] strings = line.split(",");
				// 分割后取第四个元素得到[27/Dec/2017:16:10:32 +0800]
				String calldatestr = strings[3];
				// 去除第一个[
				String str2 = calldatestr.substring(1, calldatestr.length());
				// 空格分割,取第一个得到27/Dec/2017:16:10:32
				String[] datestr = str2.split(" ");
				String calldatestring = datestr[0];
				// 将27/Dec/2017:16:10:32转换格式
				Date calldate = new Date();
				String newdatestr = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
				SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					calldate = sdf.parse(calldatestring);
					// 转换得到
					newdatestr = sdfnew.format(calldate);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 获取第一个数字，并加1

				sb.append(strings[0]).append(",").append(strings[1]).append(",").append(strings[2]).append(",")
						.append("\"").append(newdatestr).append("\"").append(",").append(strings[4]).append("\r\n");
				linenum++;
			}
			// 写入新的文件
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		long startat = System.currentTimeMillis();
		conversionFile("F:/test.txt", "F:/newtest.txt");
		long endat = System.currentTimeMillis();
		long time = endat - startat;
		System.out.println("读取转换耗时"+ time);
	}
}
