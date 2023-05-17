package j2woo.week4_5;

import java.util.*;
import java.io.*;
public class NBA농구 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // records에 주어진 이긴팀 호출 정보를 {팀번호, hh, mm} 형식으로 넣어준다.
        int [][] records = new int[N][3];
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            records[i][0] = Integer.parseInt(st.nextToken());
            String [] hhmm = st.nextToken().split(":");
            records[i][1] = Integer.parseInt(hhmm[0]);
            records[i][2] = Integer.parseInt(hhmm[1]);
        }

        // 1번 팀과 2번 팀의 이긴 횟수, 이겼을 때 시작 인덱스(records에서 확인하기 위하여), 현재까지 이긴 시간
        // 1번 팀은 0으로 2번팀은 1로
        int winRecords [][] = new int[2][3]; // cnt, idx, 시간
        // nowWinner에는 현재 이기고 있는 팀 (무승부일 경우 -1) 저장
        int nowWinner = -1; // 무승부
        for (int i = 0 ; i < N; i++) {
            // 1번 팀은 0으로 2번팀은 1로 하기 위하여
            int winner = (records[i][0] + 1) % 2;
            int loser = winner == 0 ? 1 : 0;
            // 현재 이긴 사람의 이긴 횟수 ++
            winRecords[winner][0]++;
            // 전 까지 무승부였더라면 갱신된 이기고 있는 사람은 현재 이긴 winner
            // 위너의 이기고 있는 시작 시간의 index를 넣어준다.
            if (nowWinner == -1) {
                nowWinner = winner;
                winRecords[winner][1] = i;
            }
            // 다른 팀이 이기고 있다가 현재 무승부로 갱신된 경우
            // nowWinner를 무승부로 바꾸어주고
            // 다른 팀의 이긴 누적 시간을 다른 팀의 이기기 시작한 시간부터 현재까지의 시간을 구하여 넣어준다.
            else if (nowWinner == loser && winRecords[winner][0] == winRecords[loser][0]) {
                int lIdx = winRecords[loser][1];
                nowWinner = -1;
                winRecords[loser][2] += getWinningTime(records[i][1], records[i][2], records[lIdx][1], records[lIdx][2]);
            }
        }

        // 마지막에 무승부가 아니라면 마지막에 이기고 있는 사람이 48분까지 이긴 누적 시간을 구해준다.
        if (nowWinner != -1) {
            int wIdx = winRecords[nowWinner][1];
            winRecords[nowWinner][2] += getWinningTime(48, 00, records[wIdx][1], records[wIdx][2]);
        }

        changeMMSS(winRecords[0][2]);
        changeMMSS(winRecords[1][2]);

    }

    // 시간(분)을 MM:SS 형태로 출력하는 메서드
    static void changeMMSS(int mm) {
        String m = "0";
        if (mm / 60 == 0) m += "0";
        else if (mm / 60 < 10) m += mm / 60;
        else m = mm / 60 + "";
        String s = "0";
        if (mm % 60 == 0) s += "0";
        else if (mm % 60 < 10) s += mm % 60;
        else s = mm % 60 + "";
        System.out.println(m + ":" +s);
    }

    // mm:ss ~ mm:ss 사이의 시간(분) 구하기
    static int getWinningTime(int amm, int ass, int bmm, int bss) {
        int m = 0;
        int s = 0;
        s = ass - bss;
        if (s < 0) {
            s += 60;
            amm--;
        }
        m = amm - bmm;

        return m * 60 + s;
    }
}
