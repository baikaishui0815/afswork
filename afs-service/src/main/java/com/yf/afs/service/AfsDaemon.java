package com.yf.afs.service;

import java.io.FileNotFoundException;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Log4jConfigurer;

/**
 * 基础的服务Daemon.
 */
public class AfsDaemon implements Daemon {
	
	private ConfigurableApplicationContext ctx;

	@Override
	public void init(DaemonContext context) throws Exception
	{
		initLogging();
		
		ctx = new ClassPathXmlApplicationContext(getContextFilename(context.getArguments()));
		
		ctx.registerShutdownHook();
	}

	private static void initLogging() throws FileNotFoundException {
		String logLocation = System.getProperty("log.config");
		if (StringUtils.isNotBlank(logLocation))
			Log4jConfigurer.initLogging(logLocation, 1000 * 60);//刷新间隔一分钟
	}

	@Override
	public void start() throws Exception
	{
	}

	@Override
	public void stop() throws Exception
	{
	}

	@Override
	public void destroy()
	{
		ctx.close();
	}

	/**
	 * 这样可以直接跑，要关闭就杀掉进程
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		initLogging();
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(getContextFilename(args));
		ctx.registerShutdownHook();
	}
	
	public static String getContextFilename(String args[])
	{
		String filename = "/service-context.xml";
		if (args.length > 1 )
			filename = "/" + args[0] + "-context.xml";
		return filename;
	}
}
