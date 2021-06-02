package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.BoardDTO;
import mybatis.config.DBService;

public class BoardDAO {

	
	private SqlSessionFactory factory;
	
	// Singleton
	private static BoardDAO instance = new BoardDAO();
	private BoardDAO() {
		factory = DBService.getInstance().getFactory();
	}
	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	
	/* 1.게시글 작성  */
	public int insert(BoardDTO dto) {
		SqlSession ss = factory.openSession(false); // insert 후 수동커밋하겠다. : prepardStatment >> AUTO커밋이였기 때문에 작성 하지 않았던것
		int result = ss.insert("mybatis.mapper.board.insertBoard", dto);  // ss.insert("SQL'id", "인수")
		
		if (result > 0 ) { // ss.insert() 성공하면
			ss.commit();
		}
		ss.close();
		
		
		return result;
	}
	/* 
	 * SqlSession ss = factory.openSession(arg0:boolean타입))
	 * select 경우만,  factory.openSession()으로 작성
	 * < insert 후 수동커밋하겠다. >  >>>  prepardStatment >> AUTO커밋이였기 때문에 작성 하지 않았던것
	 */
	
	final String NAMESPACE = "mybatis.mapper.board";
	
	/* 2. 전체 게시글 개수 반환 (레코드 개수) */
	public int getTotalRecord() {
		SqlSession ss = factory.openSession(); // 커밋이 필요없는 Select문
						// "mybatis.mapper.board.getTotalRecord"
		int count = ss.selectOne(NAMESPACE + ".getTotalRecord");
		ss.close();
		
		return count;
	}
	
	
	/* 3. 전체 게시글 목록 */
	public List<BoardDTO> selectList(Map<String, Integer> map) {
		SqlSession ss = factory.openSession();
		List<BoardDTO> list = ss.selectList(NAMESPACE + ".selectList", map);
		ss.close();
		
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
}
