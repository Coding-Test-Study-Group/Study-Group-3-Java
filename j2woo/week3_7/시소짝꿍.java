package j2woo.week3_7;

import java.util.*;
public class 시소짝꿍 {
    public long solution(int[] weights) {
        long answer = 0;

        // 무게 오름차순 정렬
        Arrays.sort(weights);

        int count = 0;
        for(int i = 0; i < weights.length-1; i++){
            if(i != 0){
                // 같은 무게는 전에서 check해주었기 때문에
                // cnt는 같은 무게일 때 쌍의 개수이다.
                // 1:1인경우 i-1일 때 i의 수를 세주어서 -1 해주고 결과값 더해준다.
                if(weights[i]==weights[i-1]){
                    count--;
                    answer += count;
                    continue;
                }
            }

            count = 0;

            // 가능한 시소 쌍 찾아주기
            // 1:1, 1:2, 2:3, 3:4
            // x : y = a : b
            // x * b = y * a
            for(int j=i+1; j<weights.length; j++){
                if(weights[i]==weights[j] ||
                    weights[i]*4==weights[j]*3 ||
                    weights[i]*3==weights[j]*2 ||
                    weights[i]*2==weights[j] ) {
                    count++;
                }
            }

            answer += count;
        }

        return answer;
    }

}
