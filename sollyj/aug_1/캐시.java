package sollyj.aug_1;

import java.util.concurrent.ArrayBlockingQueue;

public class 캐시 {
	public static void main(String[] args) {
		System.out.println(solution(3,
			new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
		System.out.println(solution(3,
			new String[] {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}));
		System.out.println(solution(2,
			new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju",
				"NewYork", "Rome"}));
		System.out.println(solution(5,
			new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju",
				"NewYork", "Rome"}));
		System.out.println(solution(2,
			new String[] {"Jeju", "Pangyo", "NewYork", "newyork"}));
		System.out.println(solution(0,
			new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));

	}

	private static int solution(int cacheSize, String[] cities) {
		int answer = 0;

		if (cacheSize == 0) {    // 캐시사이즈가 0이면 항상 cache miss
			for (String city : cities) {
				answer += 5;
			}
		} else {
			ArrayBlockingQueue<String> cache = new ArrayBlockingQueue<>(cacheSize);    // 캐시사이즈 큐를 생성

			for (String city : cities) {
				city = city.toLowerCase();

				if (cache.isEmpty()) {    // cache miss
					cache.add(city);
					answer += 5;
				} else if (!cache.contains(city) && cache.remainingCapacity() > 0) {    // cache miss
					cache.add(city);
					answer += 5;
				} else if (!cache.contains(city) && cache.remainingCapacity() == 0) {    // cache miss
					cache.poll();    // 캐시가 꽉 찼으므로 가장 오래된 데이터를 뺀다.
					cache.add(city);
					answer += 5;
				} else if (cache.contains(city)) {    // cache hit
					// hit한 데이터는 head로 옮겨주어야한다.
					cache.remove(city);
					cache.add(city);
					answer += 1;
				}
			}
		}

		return answer;
	}
}
