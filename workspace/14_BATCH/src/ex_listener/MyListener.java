package ex_listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Application Lifecycle Listener implementation class MyListener
 *
 */
//@WebListener // 나는 리스너이다.
public class MyListener implements ServletContextListener {

	
	/* 스케쥴러 만들기 */ 
	// field
	SchedulerFactory factory = null;
	Scheduler scheduler = null;
	// (동일한 결과값)
	// Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
	
	
    //constructor
    public MyListener() {
    		try {
    			/* 스케쥴러 만들기 */ 
        		factory = new StdSchedulerFactory();
        		scheduler = factory.getScheduler();
    		} catch (SchedulerException e) {
    			e.printStackTrace();
    		}
    	
    }

	
    public void contextDestroyed(ServletContextEvent sce)  { 
         // 웹 서비스가 종료될 때 (톰캣에서 프로젝트가 제거될 때)
    	System.out.println("======= WebService Stop! ========");
    	/* 스케쥴러 종료 */ 
    	try {
    		scheduler.shutdown();
    	} catch (SchedulerException e) {
    		e.printStackTrace();
    	}
    		
    }

	
    public void contextInitialized(ServletContextEvent sce)  { 
         // 웹 서비스가 시작되었을 때(톰캣에 프로젝트가 올라갈 때)
    	System.out.println("======= WebService Start! ========");
    	try {
    		// 스케쥴러는 여기서 만들어주지 않는다 
    		
    		// Job (스케쥴러가 할 일)
    		JobDetail job = JobBuilder.newJob(MyJob.class)
    				.withIdentity("myJob", "group2")
    				. build();
    		
    		
    		// Trigger (일하는 시간)
    		CronTrigger trigger = TriggerBuilder.newTrigger()
    				.withIdentity("myTrigger", "group2")
    				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")) // cronSchedule()에 전달할 인수는 cron식(cronMaker)에서 생성)
    				.build();
    		
    		/* 스케쥴러 시작 */ 
    		scheduler.start();
    		
    		/* 스케쥴렁 job, trigger 전달 */
    		scheduler.scheduleJob(job, trigger);
    		
    	} catch (SchedulerException e) {
    		e.printStackTrace();
    	}
    		
    		
    }
	
}
