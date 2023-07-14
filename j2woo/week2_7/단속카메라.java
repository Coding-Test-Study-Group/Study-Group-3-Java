package j2woo.week2_7;

import java.util.*;
public class 단속카메라 {
    public int solution(int[][] routes) {
        int answer = 0;
        // 각 차량의 단속용 카메라의 위치를 구한다.
        // 이때 나가는 지점을 오름차순으로 정렬한다.
        Arrays.sort(routes, new Comparator<int[]>(){
            @Override
            public int compare(int[] e1, int[] e2) {
                return e1[1] - e2[1];
            }
        });

        int cam = Integer.MIN_VALUE;

        for (int[] car: routes) {
            // 현재 카메라 위치가 비교할 차량의 시작 위치보다 더 전에 있으면
            if (cam < car[0]) {
                cam = car[1];
                answer++;
            }
        }
        return answer;
    }
}
