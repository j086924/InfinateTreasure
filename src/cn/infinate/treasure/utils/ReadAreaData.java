package cn.infinate.treasure.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ReadAreaData {
	
	/**
	 * 从city.sql文件中读取地区资源
	 * @param fileName
	 */
	public void readMySql(String fileName) {
		
		InputStream in = null;
		BufferedReader r=null;
		
		try {
			in = ReadAreaData.class.getResourceAsStream("city.sql");
			r = new BufferedReader(new InputStreamReader(in,
					"UTF-8"));
			read(r);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		

	}

	private void read(BufferedReader r) {
		String line=null;
		
		try {
			while (null!=(line=r.readLine())) {
				line=line.trim();
				if (line.length()==0) {
					continue;
				}
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	

}
