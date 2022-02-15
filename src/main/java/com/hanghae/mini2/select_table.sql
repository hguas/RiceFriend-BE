############### 조회 쿼리 ###############
## 유저_테이블 조회 ##
SELECT * FROM PRAC.USER;
## 지역_테이블 조회 ##
SELECT * FROM PRAC.LOCATION;
## 음식점_테이블 조회 ##
SELECT * FROM PRAC.RESTAURANT;
## 모임_테이블 조회 ##
SELECT * FROM PRAC.MEETING;
## 모임_유저_테이블 조회 ##
SELECT * FROM PRAC.MEETING_USER;
## 댓글_테이블 조회 ##
SELECT * FROM PRAC.COMMENT;

############### 삭제 쿼리 ###############
## 유저_테이블 삭제 ##
DELETE FROM PRAC.USER;
## 지역_테이블 삭제 ##
DELETE FROM PRAC.LOCATION;
## 음식점_테이블 삭제 ##
DELETE FROM PRAC.RESTAURANT;
## 모임_테이블 삭제 ##
DELETE FROM PRAC.MEETING;
## 모임_유저_테이블 삭제 ##
DELETE FROM PRAC.MEETING_USER;
## 댓글_테이블 삭제 ##
SELECT * FROM PRAC.COMMENT;

############### 모임 등록 후 조회 쿼리 ###############
SELECT *
FROM MEETING M
INNER JOIN RESTAURANT R ON M.RESTUARANT_ID = R.ID
INNER JOIN USER U ON M.USER_ID = U.ID
LEFT OUTER JOIN LOCATION L ON R.LOCATION_ID = L.ID ORDER BY M.CREATED_AT DESC;

############### 모임 참여자 조회 쿼리 ###############
SELECT *
FROM MEETING_USER
WHERE MEETING_ID = 2