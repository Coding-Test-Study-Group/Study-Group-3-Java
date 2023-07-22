package j2woo.week3_7;

public class 기지국설치 {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        // 전파가 전될되지 않는 시작 아파트
        int start = 1;

        // 기지국들을 돌며 전파가 전달되지 않는 구간 찾아준다.
        // start : (처음 1시작 제외)전 기지국의 "오른쪽"으로 전파 전달되지 않는 아파트
        // end : 현재 기지국의 "왼쪽" 전파가 전달되지 않는 아파트
        // start가 현재 기지국의 왼쪽 전파가 전달되지 않는 구간보다 작거나 같으면
        // 구간의 기지국 개수 구해준다.


        // 다음 구간을 위해 start에 현재 기지국의 오른쪽 전파가 전달되지 않는 구간 아파트를 넣는다.(이때 n(아파트 개수)보다 크면 n으로 설정)
        // 기지국 전파 도달 구간의 길이
        int possible = 2 * w + 1;
        for (int num : stations) {
            int end = num - (w + 1);
            // start <= end일 때만 구간이 있으므로 기지국 개수 구해줌
            // possible로 나누어 떨어지면 나눈 개수, 아니면 + 1
            if (start <= end) {
                int l = end - start + 1;
                answer += l % possible > 0 ? l / possible + 1 : l / possible;
            }
            start = num + (w + 1) <= n ? num + (w + 1) : n + 1;
        }

        // 마지막 구간
        // 전파가 전달되지 않는 구간의 시작이 끝보다 작으면 마지막 구간도 기지국 개수 세어준다.
        if (start <= n) {
            answer += (n - start + 1) % possible > 0 ? (n - start + 1) / possible + 1 : (n - start + 1) / possible;
        }

        return answer;
    }
}
