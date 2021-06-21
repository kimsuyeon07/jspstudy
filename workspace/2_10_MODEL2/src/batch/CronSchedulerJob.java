package batch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CronSchedulerJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		// list 생성 (DTO_성적이 좋은 3명의 정보)
		
		try {
			String filename = "포상자명단.txt";
			File file = new File("D:"+File.separator + "jspstudy"+File.separator+"workspace"+File.separator+"2_10_MODEL2"+File.separator, filename);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i<6; i++) {
				bw.write("내용을 작성합니다.");
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
