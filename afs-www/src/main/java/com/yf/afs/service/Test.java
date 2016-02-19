package com.yf.afs.service;

import java.io.IOException;
import java.text.MessageFormat;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String filePath = "D:\\workspace_bos\\afs-www\\src\\main\\resources\\sms\\net_1.txt";
		Resource res = new FileSystemResource(filePath);
		System.out.print(MessageFormat.format(IOUtils.toString(res.getInputStream(), "utf-8"), ""));
	}

}
