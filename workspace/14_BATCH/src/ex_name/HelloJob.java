package ex_name;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("HelloJob을 수행합니다.");
		// 대게, (DAO)의 호출이 오는 위치

	}

}
