package batch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dao.StudentDAO;
import dto.StudentDTO;

public class StudentJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		List<StudentDTO> list = StudentDAO.getInstance().selectStudentGradeThree();
		
		// 파일 만들어주기
		try {
			String filename = "포상자명단.txt";
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			for (int i = 0 ; i < list.size(); i++) {
				bw.write((i + 1) + ", ");
				bw.write(list.get(i).getSno()+ ", ");
				bw.write(list.get(i).getName()+ ", ");
				bw.write(list.get(i).getKor()+ ", ");
				bw.write(list.get(i).getEng()+ ", ");
				bw.write(list.get(i).getMat()+ ", ");
				bw.write(list.get(i).getAve()+ ", ");
				bw.write(list.get(i).getGrade()+ "\n ");
			}
			bw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
