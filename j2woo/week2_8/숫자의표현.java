package j2woo.week2_8;

public class 숫자의표현 {
    public int solution(int n) {
        int answer = 0;
        int left = 1;
        int right = 1;
        // left ~ right 연속된 구간의 합
        int num = 1;
        while (right <= n) {
            // right가 n이라면 탐색의 끝이므로 answer++
            if (right == n) {
                answer++;
                break;
            }
            // 현재 left~ right 구간의 합 num이 n이면 더 탐색하기 위해 right와 left를 오른쪽으로 하나씩 옮겨준다.
            // num에는 늘린 right를 더해주고, 늘리기 전 left를 빼준다.
            else if (num == n) {
                answer++;
                right++;
                num = num + right - left;
                left++;
            }
            // num이 n보다 작으면 right를 늘려주고, num에도 늘린 right를 더해준다.
            else if (num < n) {
                right++;
                num += right;
            }
            // num이 n보다 크다면 left를 빼주고 left를 하나 늘려준다.
            else if (num > n) {
                num -= left;
                left++;
            }
        }
        return answer;
    }
}
