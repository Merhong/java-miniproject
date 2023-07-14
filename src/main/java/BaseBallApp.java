import service.OutPlayerService;
import service.PlayerService;
import service.StadiumService;
import service.TeamService;

import java.sql.Timestamp;
import java.util.Scanner;

public class BaseBallApp {

    public static void main(String[] args) throws Exception {
        // DB 연결 (baseballdb) - DAO에서 연결
        StadiumService stadiumService = new StadiumService();
        TeamService teamService = new TeamService();
        PlayerService playerService = new PlayerService();
        OutPlayerService outPlayerService = new OutPlayerService();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());    // createdAt에 now()
        String[] inputParsing;  // 사용자의 Input을 Split해서 저장하는 배열

        // 데몬 프로그램
        while (true) {
            /* 서비스 메뉴 */
            // 2.1.1 콘솔에 출력되는 질문
            System.out.println("어떤 기능을 요청하시겠습니까? (프로그램 종료는 : 종료 또는 0을 입력해주세요.)");

            // 2.1.2 클라이언트 입력
            Scanner sc = new Scanner(System.in);  // 스캐너 sc
            String input = sc.nextLine();         // 클라이언트로부터 키보드 입력을 받는다.
            inputParsing = input.split("\\?"); // input 값을 "?" 기준으로 split.

            System.out.println("호출한 서비스 이름 : " + inputParsing[0]);  // 서비스명 출력

            /* 스플릿할 필요가 없는 메소드 */
            // 3.2 야구장 전체 목록보기 getAllStadiums()
            // input : 야구장목록
            if (input.equals("야구장목록")) {
                System.out.println("야구장 목록을 출력합니다.");
                stadiumService.getAllStadiums();
            }

            // 3.4 전체 팀 목록보기 getAllTeams()
            // input : 팀목록
            else if (input.equals("팀목록")) {
                System.out.println("팀 목록을 출력합니다.");
                teamService.getAllTeams();
            }

            // 3.8 선수 퇴출 목록
            // input : 퇴출목록
            else if (input.equals("퇴출목록")) {
                System.out.println("퇴출 목록을 출력합니다.");
                outPlayerService.listKickPlayer();
            }

            // 3.10 포지션별 팀 야구 선수 페이지
            // input : 포지션별목록
            else if (input.equals("포지션별목록")) {
                System.out.println("포지션별 목록을 출력합니다.");
                playerService.getPositionList();
            }

            // 종료
            else if (input.equals("0") || input.equals("종료")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }


            /* 스플릿 2번 이상 필요한 서비스 목록들 */
            // 3.1 야구장 등록 registerStadium(String name, Timestamp createdAt)
            // input : 야구장등록?name=잠실야구장
            // String temp[] = inputParsing[1].split("="); // [1] 잠실야구장
            if (inputParsing[0].equals("야구장등록")) {
                String split[] = inputParsing[1].split("="); // [1] 잠실야구장
                System.out.println("야구장 등록 서비스를 실행합니다.");
                stadiumService.registerStadium(split[1], timestamp);
                System.out.println("야구장 등록을 완료 했습니다.");
            }

            // 3.3 팀 등록 registerTeam(int stadiumId, String name)
            // input : 팀등록?stadiumId=4&name=NC
            // String temp2[] = temp[0].split("&");        // [0]stadiumId [1]1 / [2]name [3]NC 모두 문자열
            else if (inputParsing[0].equals("팀등록")) {
                System.out.println("팀 등록 서비스를 실행합니다.");
                String[] temp = splitInputParams(inputParsing[1]);
                teamService.registerTeam(Integer.valueOf(temp[1]), temp[3]); // [0]stadiumId [1]1 / [2]name [3]NC 모두 문자열
                System.out.println("팀 등록을 완료 했습니다.");

            }

            // 3.5 선수 등록 registerPlayer(int teamId, String name, String position)
            // input : 선수등록?teamId=3&name=호날두&position=공격수
            else if (inputParsing[0].equals("선수등록")) {
                System.out.println("선수 등록을 시작합니다.");
                String[] temp = splitInputParams(inputParsing[1]);
                playerService.registerPlayer(Integer.parseInt(temp[1]), temp[3], temp[5]);   // [0]teamId [1]1 [2]name [3]이대호
            }

            // 3.6 팀별 선수 목록 getTeamPlayers(int teamId)
            // input : 선수목록?teamId=1
            else if (inputParsing[0].equals("선수목록")) {
                String split[] = inputParsing[1].split("="); // [0]teamId [1]1
                playerService.getTeamPlayers(Integer.parseInt(split[1]));
            }

            // 3.7
            // 선수 퇴출 등록 메소드 registerKickPlayer(int playerId, String reason)
            // 선수 퇴출 업데이트 메소드 updateKickPlayer()
            // input : 퇴출등록?playerId=1&reason=도박
            else if (inputParsing[0].equals("퇴출등록")) {
                System.out.println("퇴출 등록 서비스를 실행합니다.");
                String[] temp = splitInputParams(inputParsing[1]);
                outPlayerService.registerKickPlayer(Integer.parseInt(temp[1]), temp[3]);  // [0]playerId [1]1
                playerService.updateKickPlayer();                                         // [2]reason [3]도박
            }
        }
    }

    // 문자열 스플릿 메소드
    private static String[] splitInputParams(String inputParams) {
        String[] temp = inputParams.split("&");
        String[] temp2 = new String[temp.length * 2];

        for (int i = 0; i < temp.length; i++) {
            String[] pair = temp[i].split("=");
            temp2[i * 2] = pair[0];
            temp2[i * 2 + 1] = pair[1];
        }
        return temp2;
    }
}
