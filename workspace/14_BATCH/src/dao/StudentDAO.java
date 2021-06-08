package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.StudentDTO;
import mybatis.config.DBService;

public class StudentDAO {

	private SqlSessionFactory factory;
	
	private static StudentDAO instance;
	private StudentDAO() {
		factory = DBService.getInstance().getFactory();
	}
	
	public static StudentDAO getInstance() {
		if (instance == null) {
			instance = new StudentDAO();
		}
		return instance;
	}
	
	
	
	/* 회원 전체 조회 */
	public List<StudentDTO> selectStudentList(){
		SqlSession ss = factory.openSession();
		List<StudentDTO> list = ss.selectList("dao.student.selectStudentList");
		ss.close();
		return list;
	}
	
	
}
