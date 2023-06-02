package j2woo.week2_6;

public class 예상대진표 {
    public int solution(int n, int a, int b)
    {
        int answer = 0;

        // 1을 제외한 참가자 번호는 짝수 번호의 반으로 줄어든다.
        // 반으로 줄어들며 참가자 번호가 같아진다는 것은 서로 붙은 것이기 때문에 같아질 때까지 반복문을 돌려준다.
        // 반복 수 = 라운드 수
        while (a != b) {
            if (a > 1) a = (a + 1) / 2;
            if (b > 1) b = (b + 1) / 2;
            answer++;
        }

        return answer;
    }
}
