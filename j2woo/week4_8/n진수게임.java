package j2woo.week4_8;

public class n진수게임 {
    public String solution(int n, int t, int m, int p) {
        String answer = "";

        // 0 ~ 나열할 숫자
        int num = 0;

        // 출력할 숫자를 01234 이런식으로 붙일 것이다.
        String makeNum = "";

        // 튜브가 말해야하는 숫자의 개수가 0일때까지
        // 첫번째 반복문은 구할 num을 진수로 바꿔서 makeNum 뒤에 붙여준다.
        while (t != 0) {
            // num을 진수로 바꿔서 뒤에 붙여준다.
            makeNum += makeNumber(n, num);
            // makeNum의 길이
            int len = makeNum.length();

            // p는 튜브가 makeNum의 길이 전까지 makeNum에서 p 인덱스에 해당하는 것을 answer에 더해준다.
            loop:
            while (p <= len) {
                // p에 해당하는 인덱스를 answer에 더해준다.
                answer += makeNum.charAt(p - 1);
                // t를 하나 줄여준다.
                t--;
                // t가 0이면 전체 반복문 종료
                if (t == 0) break loop;
                // 다음 인덱스를 구해준다. p에 전체 인원 더해준다.
                p += m;
            }
            // p 인덱스를 못찾으면 다음 숫자를 makeNum에 다음 숫자를 더해준다.
            num++;
        }
        return answer;
    }

    // number을 i진법의 수 만들기
    private static String makeNumber (int i, int number) {
        if (i == 10) return number + "";
        else if (number == 0) return "0";
        else {
            String result = "";
            while (number != 0) {
                if (number % i >= 10) {
                    result = (char)('A' + number % i - 10) + result;
                } else {
                    result = number % i + result;
                }
                number /= i;
            }
            return result;
        }
    }
}
