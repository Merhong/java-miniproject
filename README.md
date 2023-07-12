# java-miniproject
## 미니프로젝트 1 - 야구 관리 프로그램

# 1. DB생성
## /* 1.1 스타디움(야구장) -> 3개 */ <br>
INSERT INTO stadium(name, created_at) VALUES('사직야구장', '1985-10-14');<br>
INSERT INTO stadium(name, created_at) VALUES('고척스카이돔', '2015-09-15');<br>
INSERT INTO stadium(name, created_at) VALUES('광주기아챔피언스필드', '2014-03-08');<br>


## /* 1.2 팀 -> 3개 */<br>
INSERT INTO team(stadium_id, name, created_at) VALUES(1, '롯데', '1975-01-01');<br>
INSERT INTO team(stadium_id, name, created_at) VALUES(2, '키움', '2008-01-01');<br>
INSERT INTO team(stadium_id, name, created_at) VALUES(3, '기아', '1982-01-01');<br>


## /* 1.3 플레이어(선수) 9명 -> 3개 */
-- 1번팀 롯데<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(1, '손성빈', '포수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(1, '고승민', '1루수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(1, '박승욱', '2루수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(1, '한동희', '3루수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(1, '최준용', '투수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(1, '노진혁', '유격수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(1, '황성빈', '좌익수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(1, '김민석', '중견수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(1, '윤동희', '우익수', now());<br>

-- 2번팀 키움<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(2, '이지영', '포수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(2, '이원석', '1루수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(2, '김혜성', '2루수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(2, '김휘집', '3루수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(2, '정창헌', '투수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(2, '에디슨러셀', '유격수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(2, '임병욱', '좌익수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(2, '이정후', '중견수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(2, '이형종', '우익수', now());<br>

-- 3번팀 기아<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(3, '신범수', '포수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(3, '김석환', '1루수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(3, '김선빈', '2루수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(3, '류지혁', '3루수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(3, '임기영', '투수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(3, '박찬호', '유격수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(3, '고종욱', '좌익수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(3, '김호령', '중견수', now());<br>
INSERT INTO player(team_id, name, position, created_at) VALUES(3, '이우성', '우익수', now());<br>

## /* 1.4 퇴출선수 */



# 2. Java 클래스 생성
## 2.1 BaseBallApp 생성
## 2.2 DBConnection 생성
## 2.3 Model 생성
## 2.4 Dao 생성
## 2.5 Service 생성


# 3. 구현할 기능
## 3.1 야구장 등록
## 3.2 전체 야구장 목록보기
---
## 3.3 팀 등록
## 3.4 전체 팀 등록
---
## 3.5 선수 등록
## 3.6 팀별 선수 목록
## 3.7 선수 퇴출 등록
## 3.8 선수 퇴출 목록
## 3.9 포지션별 팀 야구 선수 페이지
