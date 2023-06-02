package j2woo.week1_6;

import java.util.Scanner;

public class 교수가된현우 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 테스트 케이스의 개수
        int T = sc.nextInt();

        // 정수 N의 모음 초기화
        int[] nums = new int[T];
        for (int i = 0; i < T; i++) {
            nums[i] = sc.nextInt();
        }

        // N!의 0의 개수 구하기
        // 5, 25, 125 ... 5의 k제곱들의 배수가 몇개 있는지 구하기
        // 0은 10의 배수가 몇개인지인데 5의 개수를 구하기 위하여
        for (int n : nums) {
            int cnt = 1;
            int answer = 0;
            // five : 5의 k제곱
            // 현재 n보다 five가 작은 경우 반복
            while (true) {
                int five = (int)Math.pow(5, cnt);
                if (n >= five) {
                    // five의 배수의 개수 답에 더해주기
                    answer += n / five;
                } else break;
                cnt++;
            }
            System.out.println(answer);
        }
    }
}
