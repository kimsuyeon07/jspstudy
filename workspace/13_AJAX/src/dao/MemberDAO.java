package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.Member;
import mybatis.config.DBService;

public class MemberDAO {

	private SqlSessionFactory factory;
	
	// singleton
	private static MemberDAO instance = new MemberDAO();
	private MemberDAO() {
		factory = DBService.getInstance().getFactory();
	}
	public static MemberDAO getInstance() {
		if (instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}
	
	
	/* 1. 회원목록가져오기 */
	public List<Member> selectMemberList() {
		SqlSession ss = factory.openSession();
		List<Member> list = ss.selectList("dao.member.selectMemberList");
		// [ select ]는 ss.commit()을 할 필요가 없다. "조회"업무 이기 때문에.
		ss.close();
		return list;
	}
	
	
	/* 2. 특정 회원 정보 가져오기 */
	public Member selectMemberByNo(long no) {
		SqlSession ss = factory.openSession();
		Member member = ss.selectOne("dao.member.selectMemberByNo", no);
		ss.close();
		return member;
	}
	
	
	/* 3. 회원정보 수정하기 */
	public int updateMember(Member member) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update("dao.member.updateMember", member);
		if (result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
	/* 4. 회원 등록(추가)하기 */
	public int insertMember(Member member) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert("dao.member.insertMember", member);
		if (result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
	/* 5. 회원 삭제 */
	public int deleteMember(long no) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete("dao.member.deleteMember", no);
		if (result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
	/* 6. 전체 회원 수 반환 */
	public int getMemberCount() {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne("dao.member.getMemberCount");
		ss.close();
		return count;
	}
	
	
	
	
	/* ------------------------------------------------------------ */
}
