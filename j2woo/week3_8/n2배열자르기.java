package j2woo.week3_8;

public class n2배열자르기 {
    public int[] solution(int n, long left, long right) {
        // 답 배열 (left ~ right)
        int[] answer = new int[(int)(right - left + 1)];


        // left~right 배열(answer)에 해당하는 인덱스
        int answerIdx = 0;

        // left와 right의 2차원 배열에서 위치
        int left_r = (int)(left / n);
        int left_c = (int)(left % n);

        int right_r = (int)(right / n);
        int right_c = (int)(right % n);

        // 2차원 배열의 값은 행과 열 중 큰 수 + 1
        // 첫번째 반복문은 행, 두번째 반복문은 열
        // (첫번째 반복문)행의 시작은 left의 행 시작부터
        for (int i = left_r; i <= right_r; i++) {
            // (두번째 반복문)열은 행의 시작일 경우는 left_c부터 시작하고 아니면 0부터

            int start_c = i == left_r ? left_c : 0;

            for (int j = start_c; j < n; j++) {
                answer[answerIdx++] = Math.max(i, j) + 1;

                // 열과 행이 right의 위치일 경우 반복문 종료
                if (i == right_r && j == right_c) break;
            }
        }
        return answer;
    }
}
