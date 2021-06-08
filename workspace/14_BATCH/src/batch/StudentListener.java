package batch;

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


@WebListener
public class StudentListener implements ServletContextListener {

	// field
	SchedulerFactory factory = null;
	Scheduler scheduler = null;
	
	
    public StudentListener() {
        // 스케쥴러 만들기
    	try {
    		factory = new StdSchedulerFactory();
    		scheduler = factory.getScheduler();
    	} catch (SchedulerException e) {
    		e.printStackTrace();
    	}
    }

	
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // 웹 서비스가 종료될 때
    	try {
    		scheduler.clear();
    		scheduler.isShutdown();
    	} catch(SchedulerException e) {
    		e.printStackTrace();
    	}
    }


    public void contextInitialized(ServletContextEvent arg0)  { 
         // 웹 서비스가 시작될 떄
    	try {
    		
    		// Job
    		JobDetail job = JobBuilder.newJob(StudentJob.class)
    				.withIdentity("studentJob", "group3")
    				.build();
    		
    		// Trigger
    		CronTrigger trigger = TriggerBuilder.newTrigger()
    				.withIdentity("studentTrigger", "group3")
    				.withSchedule(CronScheduleBuilder.cronSchedule("0 46 17 ? * TUE *"))
    				.build();
    		
    		// 시작
    		scheduler.start();
    		
    		// 전달
    		scheduler.scheduleJob(job, trigger);
    		
    	} catch (SchedulerException e) {
    		e.printStackTrace();
    	}
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }
	
}
