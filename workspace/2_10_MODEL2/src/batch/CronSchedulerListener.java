package batch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CronSchedulerListener implements ServletContextListener {

	// field
	SchedulerFactory factory = null;
	Scheduler scheduler = null;
	
	public CronSchedulerListener() {
		try {
			factory = new StdSchedulerFactory();
			scheduler = factory.getScheduler();
		}catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// 웹 서비스가 종료
		try {
			scheduler.clear();
			scheduler.isShutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// 웹 서비스 시작
		try {
			JobDetail job = JobBuilder.newJob(CronSchedulerJob.class)
							. withIdentity("cronSchedulerJob", "group1")
							.build();
			CronTrigger trigger = TriggerBuilder.newTrigger()
					   			  .withIdentity("cronStudentTrigger","group1")
					   			  .withSchedule(CronScheduleBuilder.cronSchedule("0 46 17 ? * TUE *"))
					   			  .build();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}

}
