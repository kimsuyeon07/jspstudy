DROP TABLE MEMBER;

CREATE TABLE MEMBER
(
	NO NUMBER PRIMARY KEY,
	ID VARCHAR2(32),
	NAME VARCHAR2(32),
	GENDER VARCHAR2(10),
	ADDRESS VARCHAR2(100)
);

DROP SEQUENCE MEMBER_SEQ;

CREATE SEQUENCE MEMBER_SEQ INCREMENT BY 1 START WITH 1 NOCYCLE NOCACHE;

INSERT INTO MEMBER VALUES (MEMBER_SEQ.NEXTVAL, 'user1', '제임스', '남', '서울');
INSERT INTO MEMBER VALUES (MEMBER_SEQ.NEXTVAL, 'user2', '앨리스', '여', '인천');
INSERT INTO MEMBER VALUES (MEMBER_SEQ.NEXTVAL, 'user3', '제시카', '여', '대전');
INSERT INTO MEMBER VALUES (MEMBER_SEQ.NEXTVAL, 'user4', '스미스', '남', '대구');
INSERT INTO MEMBER VALUES (MEMBER_SEQ.NEXTVAL, 'user5', '에이미', '여', '부산');
COMMIT