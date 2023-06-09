package j2woo.week3_6;

import java.util.*;
public class 구명보트 {
    public int solution(int[] people, int limit) {
        int answer = 0;
        // 사람 수
        int len = people.length;
        // 구명보트 오름차순 정렬하기
        // 최소와 최대를 비교하기 위하여
        Arrays.sort(people);
        // 투 포인터
        // left는 현재 비교할 최소
        // right는 현재 비교할 최대
        int left = 0;
        int right = len - 1;
        while(left <= right) {
            // 현재 최소와 최대를 태울 수 있는지 확인
            // 최소와 최대가 같을 경우도 가능
            // 가능하면 태워서 보내고(answer++) 최소와 최대 바꿔주기(left, right)
            if (left == right || people[left] + people[right] <= limit) {
                answer++;
                left++;
                right--;
            } else {
                // 최대를 혼자 보내고(answer++) 최대값을 바꿔주기
                answer++;
                right--;
            }
        }

        return answer;
    }
}
