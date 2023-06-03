package j2woo.week2_6;

import java.util.*;

public class 혼자놀기의달인 {
    public int solution(int[] cards) {
        int len = cards.length;
        boolean[] checked = new boolean[len];
        // 우선순위큐에 상자 그룹에 상자 수를 넣는다.
        // 내림차순 우선순위큐로 출력할 큰 수 출력 가능
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        // 상자를 모두 열려있는지 확인해주기 위하여
        for (int i = 0; i < len; i++) {
            // 현재 상자가 열려있는지 확인
            if (!checked[i]) {
                int cnt = 1;
                int now = i;
                // 상자 그룹을 시작
                // 열려있는 상자를 만날 때까지 상자의 개수(cnt) 세어준다.
                while(true) {
                    // 현재 상자 선택 확인
                    checked[now] = true;
                    // 현재 상자에 들어있는 숫자를 다음 상자로 선택
                    int next = cards[now] - 1;

                    // 다음 번호 상자가 열려있으면 상자 그룹 탐색 종료
                    if (checked[next]) {
                        break;
                    }
                    // 다음 번호 상자가 닫혀있더라면 상자의 수 cnt++해주고 now = next로 변경
                    else {
                        cnt++;
                        now = next;
                    }
                }
                pq.add(cnt);
            } else continue;
        }
        // 상자 그룹이 하나라면 획득 점수 0 출력
        if (pq.size() == 1) return 0;
        // 상자 그룹이 두개 이상이라면 높은 숫자 두개 출력 (내림차순 우선순위큐)
        return pq.poll() * pq.poll();
    }
}
