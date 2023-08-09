package j2woo.week2_8;

public class k진수에서소수개수구하기 {
    public int solution(int n, int k) {
        int answer = 0;

        // changeBinary 메서드를 이용해서 n을 k진수로 변환한다. String으로 받는다.
        String num = changeBinary(n, k);
        // num의 길이
        int len = num.length();

        // 소수 p
        String now = "";

        // 현재 문자를 확인하며 num에서 p를 찾는다.
        for (int i = 0; i < len; i++) {
            // 0을 만난다면 전 까지의 p가 소수인지 확인한다.
            if (num.charAt(i) == '0') {
                // checkPrime 메서드를 사용하여 소수를 확인한다.
                // 소수라면 answer ++;
                if (checkPrime(now)) {
                    answer++;
                }
                // 0 다음 새로운 p를 위해 ""로 초기화
                now = "";
            }
            // 현재 문자가 0이 아니라면
            else if (num.charAt(i) != '0') {
                //  now에 현재 문자를 붙인다.
                now += num.charAt(i);

                // 현재 문자가 끝이라면 소수인지 확인해준다.
                if (i == len - 1) {
                    if (checkPrime(now)) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }

    private static boolean checkPrime (String n) {

        if (n == "") return false;
        Long num = Long.parseLong(n);
        Long len = (long)Math.sqrt(num) + 1;

        if (num == 1) return false;
        else {
            for (long i = 2; i < len; i++) {
                if (num % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static String changeBinary (int num, int b) {
        String result = "";

        while (num > 0) {
            result = num % b + result;
            num /= b;
        }

        return result;
    }
}
