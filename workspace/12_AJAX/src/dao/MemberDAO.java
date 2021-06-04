package dao;

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
	
	/* 1. login */
	public Member login(Member member) {
		SqlSession ss = factory.openSession();
		Member loginUser = ss.selectOne("dao.member.login", member);
		ss.close();
		return loginUser;
	}
	
	/* 2. 아이디 체크 */
	public boolean idCheck(String id) {
		SqlSession ss = factory.openSession();
		Member member = ss.selectOne("dao.member.idCheck", id);
		ss.close();
		// ID 사용 가능:true , ID 사용 불가능:false
		// 일치하는 id를 가진 member가 없다 == 사용 가능한 이이디이다!
		return member == null ? true : false;
	}
	
	
	/* 3. join */
	public int join(Member member) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert("dao.member.join", member);
		if (result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
	/* 4. 비밀번호 변경 */
	public int updatePw(Member member) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update("dao.member.updatePw", member);
		if (result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
	/* 5. 회원 정보 수정 */
	public int updateUser(Member member) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update("dao.member.updateUser", member);
		if (result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
	
	/* 6. 회원 탈퇴 */
	public int deleteUser(long no) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete("dao.member.deleteUser", no);
		if (result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
	
	
	
	
	/* ------------------------------------------------------------ */
}
