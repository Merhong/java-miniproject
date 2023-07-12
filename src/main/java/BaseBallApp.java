import db.DBConnection;
import model.player.Player;
import model.player.PlayerDao;
import model.stadium.StadiumDao;
import model.team.TeamDao;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class BaseBallApp {
    public static void main(String[] args) {
        // DB 연결 (baseballdb)
        Connection connection = DBConnection.getInstance();
        // DI (의존성 주입)
        StadiumDao stadiumDao = new StadiumDao(connection);
        TeamDao teamDao = new TeamDao(connection);
        PlayerDao playerDao = new PlayerDao(connection);

        String[] inputParsing;
        int teamNum = 0;


        /* 서비스 메뉴 */
        // 2.1.1 콘솔에 출력되는 질문
        System.out.println("어떤 기능을 요청하시겠습니까?");

        // 2.1.2 클라이언트 입력
        Scanner sc = new Scanner(System.in);  // 스캐너 sc
        String input = sc.nextLine();         // 클라이언트로부터 키보드 입력을 받는다.
        inputParsing = input.split("\\?");

        // 2.1.3 main메소드에서 할 일 (서비스 구현)
        // 입력받은 값(input) : 선수등록?teamId=1&name=이대호&position=1루수
        // "?"로 파싱하면 [0] = 선수등록, [1] = teamId=1&name=이대호&position=1루수
        // PlayerService의 선수등록() 메서드에서 해당 값을 받아서 PlayerDao의 insert() 메서드를 호출
        // 값이 DB에 잘 들어갔다면, 결과가 1로 리턴될 것이다. 1이 리턴되면 Console에 성공이라는 메시지를 출력


        // 3.1 야구장 등록

        // 3.2 전체 야구장 목록보기

        // 3.3 팀 등록

        // 3.4 전체 팀 목록

        // 3.5 선수 등록


        // 3.6 팀별 선수 목록
        // input : 선수목록?teamId=1 라 했을때,
        if (inputParsing[0].equals("선수목록")) {  // "?"를 기준으로 split하면 [0]에는 서비스 이름, [1]에는 teamId=1 이 문자열로 들어감.
            teamNum = Integer.parseInt(inputParsing[1].split("=")[1]); // teamId=1을 "="으로 split해서 [0]에는 teamID, [1]에는 1이 문자열로 들어가는데
            try {                                                             // 문자열 1을 정수 1로 바꿔서 teamNum에 넣어서 동적으로 만들어 줌.
                List<Player> players = playerDao.getTeamPlayers(teamNum);     // 1번팀이 들어가므로 롯데의 모든 선수 목록을 출력한다.
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
        }

        // 3.7 선수 퇴출 등록

        // 3.8 선수 퇴출 목록

        // 3.9 포지션별 팀 야구 선수 페이지

    }
}
