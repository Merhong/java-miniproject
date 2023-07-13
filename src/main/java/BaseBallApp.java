import db.DBConnection;
import service.OutPlayerService;
import service.PlayerService;
import service.StadiumService;
import service.TeamService;

import java.sql.Connection;
import java.sql.Timestamp;

public class BaseBallApp {
    public static void main(String[] args) throws Exception {
        // DB 연결 (baseballdb)
        Connection connection = DBConnection.getInstance();
        // DI (의존성 주입)

        StadiumService stadiumService = new StadiumService();
        TeamService teamService = new TeamService();
        PlayerService playerService = new PlayerService();
        OutPlayerService outPlayerService = new OutPlayerService();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String[] inputParsing;  // 사용자의 Input을 Split해서 저장하는 배열

        /* 서비스 메뉴 */
//        // 2.1.1 콘솔에 출력되는 질문
//        System.out.println("어떤 기능을 요청하시겠습니까?");
//
//        // 2.1.2 클라이언트 입력
//        Scanner sc = new Scanner(System.in);  // 스캐너 sc
//        String input = sc.nextLine();         // 클라이언트로부터 키보드 입력을 받는다.
//        inputParsing = input.split("\\?");
//
//        System.out.println("호출한 서비스 이름 : " + inputParsing[0]);  // 서비스명 출력
//        System.out.println(inputParsing[0]);  // 서비스명 출력
//
//        // 3.1 야구장 등록 registerStadium(String name, Timestamp createdAt)
//        // input : 야구장등록?name=A야구장
//        stadiumService.registerStadium("ㅁㄴㅇㄹ", timestamp);
//
//        // 3.2 야구장 전체 목록보기 getAllStadiums()
//        // input : 야구장목록
//        stadiumService.getAllStadiums();
//
//        // 3.3 팀 등록 registerTeam(int stadiumId, String name)
//        // input : 팀등록?stadiumId=1&name=NC
//        teamService.registerTeam(3, "KT");
//
//        // 3.4 전체 팀 목록보기 getAllTeams()
//        // input : 팀목록
//        teamService.getAllTeams();
//
//        // 3.5 선수 등록 registerPlayer(int teamId, String name, String position)
//        // input : 선수등록?teamId=1&name=이대호&position=1루수
//        playerService.registerPlayer(2, "메시", "공격수");

//        // 3.6 팀별 선수 목록 getTeamPlayers(int teamId)
//        // input : 선수목록?teamId=1
//        playerService.getTeamPlayers(1);

        // 3.7
        // 선수 퇴출 등록 메소드 registerKickPlayer(int playerId, String reason)
        // 선수 퇴출 업데이트 메소드 updateKickPlayer()
//        outPlayerService.registerKickPlayer(2, "음주운전");
//        playerService.updateKickPlayer();

        // 3.8 선수 퇴출 목록
        outPlayerService.listKickPlayer();

    }
}
