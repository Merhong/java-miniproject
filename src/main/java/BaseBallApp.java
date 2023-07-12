import db.DBConnection;
import model.player.Player;
import model.player.PlayerDao;
import model.stadium.StadiumDao;
import model.team.Team;
import model.team.TeamDao;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.List;

public class BaseBallApp {
    public static void main(String[] args) {
        // DB 연결 (baseballdb)
        Connection connection = DBConnection.getInstance();
        // DI (의존성 주입)
        StadiumDao stadiumDao = new StadiumDao(connection);

        TeamDao teamDao = new TeamDao(connection);

        PlayerDao playerDao = new PlayerDao(connection);

        try {
            List<Player> players = playerDao.getTeamPlayers(2);
            System.out.println("선수목록 출력");
            System.out.println("=================");
            if (!players.isEmpty()) {
                for (Player player : players) {
                    System.out.println("선수이름: " + player.getName());
                    System.out.println("포지션: " + player.getPosition());
                }
            } else {
                System.out.println("더 이상 선수를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



//        System.out.println("원하시는 서비스 번호와 엔터를 눌러주세요.");
//        System.out.println("1 : 야구장 전체출력");
//        Scanner sc = new Scanner(System.in);    // 스캐너 sc
//        String keyboard = sc.nextLine();        // 키보드 입력을 받는다.

        /* 서비스 메뉴 입력  */
        // 현재 while문 없이 1회 시도 후 종료
        // 모든 서비스 구현 완료시 while문을 넣고 반복실행



        // 키보드 입력이 n이 들어오면, n번 서비스 실행
        if (keyboard.equals("1")) {
            System.out.println("1번이 입력 되었습니다.");
            // 1번_서비스(야구장 등록);
            try {
                String stadiumName = "한라산";
                Timestamp openingDate = Timestamp.valueOf("1990-08-10 00:00:00");
                stadiumDao.registerStadium(stadiumName, openingDate);
                System.out.println("경기장 이름: " + stadiumName);
                System.out.println("개장일: " + openingDate);
                System.out.println("=========================================");

            } catch (SQLException e) {
                System.out.println("야구장을 등록하지 못하였습니다 : " + e.getMessage());
                System.out.println("=========================================");
            }


        } else if (keyboard.equals("2")) {
            System.out.println("2번이 입력 되었습니다.");
            // 2번_서비스(전체 야구장 목록보기);
            try {
                List<Stadium> stadiums = stadiumDao.getAllStadiums();
                if (!stadiums.isEmpty()) {
                    for (Stadium stadium : stadiums) {
                        System.out.println("경기장이름: " + stadium.getName());
                        System.out.println("개장일: " + stadium.getCreatedAt());
                        System.out.println("----");

                    }
                } else {
                    System.out.println("야구장을 찾지 못하였습니다.");
                    System.out.println("=========================================");
                }
            } catch (SQLException e) {
                System.out.println("오류가 있습니다: " + e.getMessage());
                System.out.println("=========================================");
            }


        } else if (keyboard.equals("3")) {
            System.out.println("3번이 입력 되었습니다.");
            // 3번_서비스(팀 등록);
            try {
                int stadiumId = 6;
                String teamName = "제주";
                Timestamp createdAt = Timestamp.valueOf("2008-06-20 00:00:00");

                teamDao.registerTeam(stadiumId, teamName, createdAt);

                System.out.println("홈구장: " + stadiumId + "번");
                System.out.println("팀 이름: " + teamName);
                System.out.println("창단일: " + createdAt);
                System.out.println("팀 등록에 성공하였습니다");
                System.out.println("----");
            } catch (SQLException e) {
                System.out.println("팀 등록에 실패하였습니다: " + e.getMessage());
                System.out.println("=========================================");
            }


        } else if (keyboard.equals("4")) {
            System.out.println("4번이 입력 되었습니다.");
            // 4번_서비스(전체 팀 목록보기);
            try {
                List<Team> teams = teamDao.getAllTeams();
                if (!teams.isEmpty()) {
                    for (Team team : teams) {
                        System.out.println("홈구장 : " + team.getStadiumId());
                        System.out.println("구단이름: " + team.getName());
                        System.out.println("창단일: " + team.getCreatedAt());
                        System.out.println("----");
                    }
                } else {
                    System.out.println("팀을 찾지 못하였습니다.");
                    System.out.println("=========================================");
                }
            } catch (SQLException e) {
                System.out.println("오류가 있습니다: " + e.getMessage());
                System.out.println("=========================================");
            }
        }

//        // 키보드 입력이 n이 들어오면, n번 서비스 실행
//        if (keyboard.equals("1")) {
//            System.out.println("1번이 입력 되었습니다.");
//            // 1. 야구장 전체출력
//            try {
//                List<Stadium> stadiums = stadiumDao.getAllStadiums();
//                if (!stadiums.isEmpty()) {
//                    for (Stadium stadium : stadiums) {
//                        System.out.println("경기장이름: " + stadium.getName());
//                        System.out.println("개장: " + stadium.getCreatedAt());
//                        System.out.println();
//                    }
//                } else {
//                    System.out.println("못찾겠다.");
//                }
//            } catch (SQLException e) {
//                System.out.println("오류가 있습니다: " + e.getMessage());
//            }
//
//
//        } else if (keyboard.equals("2")) {
//            System.out.println("2번이 입력 되었습니다.");
//            // 2번_서비스();
//        }
//        else if (keyboard.equals("3")) {
//            System.out.println("3번이 입력 되었습니다.");
//            // 3번_서비스();
//        }
//        else if (keyboard.equals("4")) {
//            System.out.println("2번이 입력 되었습니다.");
//            // 4번_서비스();
//        }


        // 3.5 선수등록


//        else {
//            System.out.println("Input 오류 : 숫자 입력후 엔터를 눌러주세요!");
//        }
    }
}
