drop database baseballdb;
create database baseballdb;
use baseballdb;
create table stadium (
                         id int primary key auto_increment,
                         name varchar(250) not null,
                         created_at timestamp
) DEFAULT CHARACTER SET utf8mb4;
create table team (
                      id int primary key auto_increment,
                      stadium_id int,
                      name varchar(250) not null,
                      created_at timestamp,
                      FOREIGN KEY (stadium_id) REFERENCES stadium(id)
) DEFAULT CHARACTER SET utf8mb4;
create table player (
                        id int primary key auto_increment,
                        team_id int,
                        name varchar(250) not null,
                        position varchar(250) not null,
                        created_at timestamp,
                        FOREIGN KEY (team_id) REFERENCES team(id),
                        UNIQUE (team_id, position)
) DEFAULT CHARACTER SET utf8mb4;
create table out_player (
                            id int primary key auto_increment,
                            player_id int,
                            reason varchar(250) not null,
                            created_at timestamp,
                            FOREIGN KEY (player_id) REFERENCES player(id)
) DEFAULT CHARACTER SET utf8mb4;
INSERT INTO stadium(name, created_at) VALUES('사직야구장', '1985-10-14');
INSERT INTO stadium(name, created_at) VALUES('고척스카이돔', '2015-09-15');
INSERT INTO stadium(name, created_at) VALUES('광주기아챔피언스필드', '2014-03-08');
INSERT INTO team(stadium_id, name, created_at) VALUES(1, '롯데', '1975-01-01');
INSERT INTO team(stadium_id, name, created_at) VALUES(2, '키움', '2008-01-01');
INSERT INTO team(stadium_id, name, created_at) VALUES(3, '기아', '1982-01-01');
INSERT INTO player(team_id, name, position, created_at) VALUES(1, '손성빈', '포수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(1, '고승민', '1루수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(1, '박승욱', '2루수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(1, '한동희', '3루수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(1, '최준용', '투수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(1, '노진혁', '유격수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(1, '황성빈', '좌익수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(1, '김민석', '중견수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(1, '윤동희', '우익수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(2, '이지영', '포수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(2, '이원석', '1루수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(2, '김혜성', '2루수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(2, '김휘집', '3루수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(2, '정창헌', '투수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(2, '에디슨', '유격수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(2, '임병욱', '좌익수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(2, '이정후', '중견수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(2, '이형종', '우익수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(3, '신범수', '포수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(3, '김석환', '1루수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(3, '김선빈', '2루수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(3, '류지혁', '3루수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(3, '임기영', '투수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(3, '박찬호', '유격수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(3, '고종욱', '좌익수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(3, '김호령', '중견수', now());
INSERT INTO player(team_id, name, position, created_at) VALUES(3, '이우성', '우익수', now());