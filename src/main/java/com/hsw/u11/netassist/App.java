package com.hsw.u11.netassist;

import org.apache.log4j.Logger;

import com.hsw.u11.netassist.view.Workspace;


/**
 * Hello world!
 *
 */
public class App 
{
	private static Logger logger = Logger.getLogger("main");
    public static void main( String[] args )
    {
    	logger.debug("v1.0");
		Workspace win=new Workspace();  
    }
}

