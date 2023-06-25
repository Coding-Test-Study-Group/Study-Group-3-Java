package j2woo.week4_6;

public class 주식가격 {
    public int[] solution(int[] prices) {
        int len = prices.length;
        int[] answer = new int[len];
        for (int i = 0; i < len; i++) {
            int now = prices[i]; // 현재 가격
            int s = 0;
            // 다음 주식 가격들 비교하기
            for (int j = i + 1; j < len; j++) {
                s++;
                if (now > prices[j]) {
                    // 다음 시간에 값이 기준보다 작다면 끝내기
                    break;
                }
            }
            answer[i] = s;
        }
        return answer;
    }
}
