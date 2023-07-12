import db.DBConnection;
import model.outplayer.OutPlayer;
import model.outplayer.OutPlayerDao;
import model.player.Player;
import model.player.PlayerDao;
import model.player.PlayerService;
import model.stadium.Stadium;
import model.stadium.StadiumDao;
import model.stadium.StadiumService;
import model.team.Team;
import model.team.TeamDao;
import model.team.TeamService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BaseBallApp {
    public static void main(String[] args) throws SQLException {
        // DB 연결 (baseballdb)
        Connection connection = DBConnection.getInstance();
        // DI (의존성 주입)
        StadiumDao stadiumDao = new StadiumDao(connection);
        TeamDao teamDao = new TeamDao(connection);
        PlayerDao playerDao = new PlayerDao(connection);
        OutPlayerDao outPlayerDao = new OutPlayerDao(connection);

        StadiumService stadiumService = new StadiumService(connection);





        String[] inputParsing;

        /* 서비스 메뉴 */
        // 2.1.1 콘솔에 출력되는 질문
        System.out.println("어떤 기능을 요청하시겠습니까?");

        // 2.1.2 클라이언트 입력
        Scanner sc = new Scanner(System.in);  // 스캐너 sc
        String input = sc.nextLine();         // 클라이언트로부터 키보드 입력을 받는다.
        inputParsing = input.split("\\?");

        System.out.println("호출한 서비스 이름 : " + inputParsing[0]);  // 서비스명 출력

        // 2.1.3 main메소드에서 할 일 (서비스 구현)
        // 입력받은 값(input) : 선수등록?teamId=1&name=이대호&position=1루수
        // "?"로 파싱하면 inputParsing[0] = 선수등록, inputParsing[1] = teamId=1&name=이대호&position=1루수
        // PlayerService의 선수등록() 메서드에서 해당 값을 받아서 PlayerDao의 insert() 메서드를 호출
        // 값이 DB에 잘 들어갔다면, 결과가 1로 리턴될 것이다. 1이 리턴되면 Console에 성공이라는 메시지를 출력

        List<Stadium> allStadium = stadiumService.getAllStadiums();


//        // 3.1 야구장 등록
//        // input : 야구장등록?name=A야구장
//        if(inputParsing[0].equals("야구장등록")) {
//            System.out.println("야구장 등록 서비스를 실행합니다.");
//
//            String temp[] = inputParsing[1].split("="); // [1] 잠실야구장
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//            stadiumDao.registerStadium(temp[1], timestamp);
//        }

//        // 3.2 전체 야구장 목록보기
//        // input : 야구장목록
//        if (input.equals("야구장목록")) {
//            //System.out.println(stadiumDao.getAllStadiums());
//            try {
//                List<Stadium> stadiums = stadiumDao.getAllStadiums();
//                System.out.println("야구장 목록 출력");
//                System.out.println("=================");
//                if (!stadiums.isEmpty()) {
//                    for (Stadium stadium : stadiums) {
//                        System.out.println("선수이름: " + stadium.getName());
//                        System.out.println("포지션: " + stadium.getCreatedAt());
//                    }
//                } else {
//                    System.out.println("더 이상 야구장을 찾을 수 없습니다.");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        // 3.3 팀 등록
        // 팀등록?stadiumId=1&name=NC
//        if(inputParsing[0].equals("팀등록")) {
//            System.out.println("팀등록 서비스를 실행합니다.");
//
//            String temp[] = inputParsing[1].split("&"); // [0]stadiumId=1 [1]name=NC
//            String temp2[] = temp[0].split("=");        // [0]stadiumId [1]1 / [2]name [3]NC 모두 문자열
//
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//            teamDao.registerTeam(
//                    Integer.parseInt(temp2[0]), // teamId
//                    temp2[3],                   // name
//                    timestamp                   // createdAt
//            );
//        }

        // 3.4 전체 팀 목록
//        if (input.equals("팀목록")) {
//            //System.out.println(teamDao.getAllTeams());
//            try {
//                List<Team> teams = teamDao.getAllTeams();
//                System.out.println("팀 목록 출력");
//                System.out.println("=================");
//                if (!teams.isEmpty()) {
//                    for (Team team : teams) {
//                        System.out.println("홈 구장: " + team.getStadiumId() + "번 구장");
//                        System.out.println("팀 이름: " + team.getName());
//                        System.out.println("개장일: " + team.getCreatedAt());
//                    }
//                } else {
//                    System.out.println("더 이상 팀을 찾을 수 없습니다.");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        // 3.5 선수 등록
//        if (inputParsing[0].equals("선수등록")) {
//            // input : 선수등록?teamId=1&name=이대호&position=1루수
//            // inputParsing : "?"를 기준으로 [0]선수등록 [1]teamId=1&name=이대호&position=1루수
//            // temp     : "&" 기준으로 다시 split :  [0]teamId=1, [1]name=이대호, [2]position=1루수
//            // temp2    : "=" 기준으로 다시 split :  [0]teamId, [1]1, [2]name, [3]이대호, [4]position, [5]1루수
//            // temp2에서 분할시 인덱스 오류 발생! 반복문을 사용하여 split 후에  [0],[1]에 할당해줘야 함
//            String[] temp = inputParsing[1].split("&");
//            String[] temp2 = new String[temp.length * 2];     // temp2[0] = teamId , temp2[1] = 1
//
//            // temp2의 스플릿을 여러번 하기 위해 반복문 사용하여 값 할당.
//            for (int i = 0; i < temp.length; i++) {
//                String[] pair = temp[i].split("="); // i=1 : pair[0] = teamId / pair[1] = 1
//                temp2[i * 2] = pair[0];
//                temp2[i * 2 + 1] = pair[1];
//            }
//
//            try {
//                List<Player> players = playerDao.registerPlayer(
//                        Integer.parseInt(temp2[1]),             // teamId (int)
//                        temp2[3],   // name
//                        temp2[5]    // position
//                );
//                System.out.println("선수등록 성공");
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("선수등록 실패");
//
//            }
//        }
//
//        // 3.6 팀별 선수 목록
//        if (inputParsing[0].equals("선수목록")) {
//            // input : 선수목록?teamId=1 라 했을때,
//            // 첫번째 split : "?"를 기준으로  [0]에는 서비스 이름 "선수목록", [1]에는 "teamId=1" 이 문자열로 들어감.
//            // [1]을 다시 split : "=" 기준으로 split, [0]에는 "teamID", [1]에는 "1"이 문자열로 들어감.
//            try {
//                List<Player> players = playerDao.getTeamPlayers(
//                        Integer.parseInt(inputParsing[1].split("=")[1])  // getTeamPlayers( Index ) 넣는 부분, 정수가 필요하다.
//                        // [1][1]인 문자열 1을 정수 1로 바꿔서 동적으로 할당 가능하게 함.
//                );
//                System.out.println("선수목록 출력");
//                System.out.println("=================");
//                if (!players.isEmpty()) {
//                    for (Player player : players) {
//                        System.out.println("선수이름: " + player.getName());
//                        System.out.println("포지션: " + player.getPosition());
//                    }
//                } else {
//                    System.out.println("더 이상 선수를 찾을 수 없습니다.");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        // 3.7 선수 퇴출 등록
        // input : 퇴출등록?playerId=2&reason=도박
//        if (inputParsing[0].equals("퇴출등록")) {
//            // inputParsing : "?"를 기준으로 [0]퇴출등록 [1]playerId=1&reason=도박
//            // temp     : "&" 기준으로 다시 split :  [0]playerId=1, [1]reason=도박
//            // temp2    : "=" 기준으로 다시 split :  [0]playerId, [1]1, [2]reason, [3]도박
//            // temp2에서 분할시 인덱스 오류 발생! 반복문을 사용하여 split 후에  [0],[1]에 할당해줘야 함
//            String[] temp = inputParsing[1].split("&");
//            String[] temp2 = new String[temp.length * 2];     // temp2[0] = playerId , temp2[1] = 1
//
//            // temp2의 스플릿을 여러번 하기 위해 반복문 사용하여 값 할당.
//            for (int i = 0; i < temp.length; i++) {
//                String[] pair = temp[i].split("=");
//                System.out.println(pair[0]);
//                temp2[i * 2] = pair[0];         // i=0 : pair[0] = playerId / pair[1] = 1
//                System.out.println(pair[1]);
//                temp2[i * 2 + 1] = pair[1];     // i=1 : pair[2] = reason / pair[3] = 도박
//            }
//
//
//            try {
//                // 퇴출 명단에 등록
//                List<OutPlayer> outPlayers = outPlayerDao.registerKickPlayer(
//                        Integer.parseInt(temp2[1]),  // player_id
//                        temp2[3]                     // reason
//                );
//                // 명단에 등록됐다면 team_id를 삭제
//                List<Player> players = playerDao.updateKickPlayer();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


        // 3.8 선수 퇴출 목록
//        if (inputParsing[0].equals("퇴출목록")) {
//            try {
//                List<OutPlayer> outPlayers = outPlayerDao.listKickPlayer();
//                List<Player> players;
//                System.out.println("퇴출목록 출력");
//                System.out.println("===============");
//                System.out.println(outPlayers);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


        // 3.9 포지션별 팀 야구 선수 페이지
        // input : 포지션별목록

    }
}
