DROP TABLE REPLY;
DROP TABLE BOARD ;

CREATE TABLE BOARD
(
	NO NUMBER,						-- 인덱스
	AUTHOR VARCHAR2(32),			-- 작성자
	TITLE VARCHAR2(1000) NOT NULL,	-- 제목
	CONTENT VARCHAR2(4000),			-- 내용
	IP VARCHAR2(20),				-- ip주소
	HIT NUMBER,						-- 조회수
	POSTDATE DATE,					-- 작성일
	LASTMODIFIED DATE,				-- 최종수정일
	STATS NUMBER,					-- 삭제여부 (정상:0, 삭제:-1)
	GROUPNO NUMBER,					-- 게시글의 그룹 여부(어떤 게시글과 거기에 달린 댓글은 모두 같은 그룹)
	GROUPORD NUMBER,				-- 그룹 내 표시 순서
	DEPTH NUMBER					-- 게시글 / 댓글 여부 (게시글:0, 댓글: 1이상)
);

DROP SEQUENCE BOARD_SEQ;
CREATE SEQUENCE BOARD_SEQ INCREMENT BY 1 START WITH 1 NOCYCLE NOCACHE;