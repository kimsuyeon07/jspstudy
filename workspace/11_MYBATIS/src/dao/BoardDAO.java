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
	/* getInstance()메서드를 호출하면, "factory"를 받아올 수 있도록 작업.  */
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
	
	// board.xml의 경로 <final>타입으로 작성!
	final String NAMESPACE = "mybatis.mapper.board";
	
	
	/* 2. 전체 게시글 개수 반환 (레코드 개수) */
	public int getTotalRecord() {
		SqlSession ss = factory.openSession(); // 커밋이 필요없는 Select문
						// "mybatis.mapper.board.getTotalRecord" [.getTotalRecord : SQL's id]
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
	
	
	/* 4. 같은 그룹 기존 댓글들의 groupord 증가 */
	public int increseGroupordPerviousReply(long groupno) {
		SqlSession ss = factory.openSession(false);  // 직접 커밋하겠다._성공,실패 여부에 따라 커밋하겠다.
		int result = ss.update(NAMESPACE+".increseGroupordPerviousReply", groupno);
		if (result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
	/* 5. 댓글 삽입하기 */
	public int insertReply(BoardDTO replyDTO) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert(NAMESPACE + ".insertReply", replyDTO);
		if (result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
	/* 6. 검색결과 개수 반환 */
	public int getFindRecordCount(Map<String, Object> map) {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne(NAMESPACE+".getFindRecordCount", map);
		// select는 알아서 커밋하기 때문에 따로 if문을 작성할 필요가 없다.
		ss.close();
		
		return count;
				
	}
	
	
	/* 7. 검색 결과(리스트) 반환 */
	public List<BoardDTO> findList(Map<String, Object> map) {
		SqlSession ss = factory.openSession();
		List<BoardDTO> list = ss.selectList(NAMESPACE+".findList", map);
		ss.close();
		
		return list;
	}
	
	
	/* 8. 삭제 */
	public int delete(long no) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete(NAMESPACE+".delete", no);
		if (result > 0) {
			ss.commit();
		}
		ss.close();
		
		return result;
	}
	
	
	
	
	// 대댓글 게시판
	
	/* 1. 전체 게시글 목록 */
	public List<BoardDTO> selectList3(Map<String, Integer> map) {
		SqlSession ss = factory.openSession();
		List<BoardDTO> list = ss.selectList(NAMESPACE + ".selectList3", map);
		ss.close();
		
		return list;
	}
	
	/* 2. 원글 가져오기 */
	public BoardDTO selectBoard(long no) {
		SqlSession ss = factory.openSession();
		BoardDTO boardDTO = ss.selectOne(NAMESPACE+".selectBoard", no);
		ss.close();
		return boardDTO;
	}
	
	
	/* 3. 원글의 groupord보다 큰 groupord를 가진 댓글의 groupord 증가 */
	public int increseGroupordPerviousReply(BoardDTO boardDTO) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update(NAMESPACE+".increseGroupordPerviousReply3", boardDTO);
		if (result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
	
	
	
}
