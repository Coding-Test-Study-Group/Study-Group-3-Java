package soojik.week4;

public class 혼자놀기의달인 {

  static boolean[] visit;
  static int len;
  static int box1 = 0, box2 = 0;

  public static void main(String[] args) {
    System.out.println(solution(new int[]{8, 6, 3, 7, 2, 5, 1, 4}));
    System.out.println(solution(new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 1}));
  }

  /*
  모든 카드는 상자에 담겨있어 어떤 숫자가 적혀있는지 알 수 없다.
  처음 임의의 상자를 선택한 후, 상자 안 카드를 확인하고 해당 숫자 차례에 있는 상자 안 카드를 확인하고 .. 이어서

  예) 첫번째 상자로 시작했다면
  8 -> 4 -> 7 -> 1 -> 8 ... 과 같은 사이클이 만들어진다.
  이 사이클에 해당되는 카드의 수는 4이고, 이 카드를 첫번째 그룹이라고 한다.

  이렇게 한 차례의 사이클이 끝이 나면 첫번째 그룹의 카드를 뺀 카드 중 또 다시 남은 카드를 임의로 선택해 사이클(두번째 그룹)을 만든다.

  첫번째 그룹과 두번째 그룹에 있는 카드 수를 곱한 값의 최대를 구하는 문제이다.

  만약 첫번째 그룹에 모든 카드가 속하게되면 그대로 게임이 종료되며, 이때는 즉시 0점을 반환한다.
   */

  static int solution(int[] cards) {

    int answer = 0;

    len = cards.length;

    for (int i=0;i<len;i++) {
      // cards 배열 순회하며 dfs 로 방문 여부를 체크하고 첫번째 그룹, 두번째 그룹에 속하는 카드 숫자를 각 box1, box2 변수가 참조하도록 한다.
      visit = new boolean[len];
      // i번째 인덱스에서 시작한 첫번째 그룹(사이클) 만들어 길이를 box1 가 참조하도록 하는 메서드
      dfs(cards, i, 1, true);

      // 첫번째 그룹에 모두 속하는 경우가 있다면 바로 0을 반환한다.
      if (box1 == len) return 0;

      // 첫번째 그룹 방문 여부 체크 및 i 에서 시작된 사이클에 속한 카드 갯수(box1) 가 끝나면
      // 두번째 그룹 만들기
      for (int j=0;j<len;j++) {

        // 첫번째 그룹에 속하지 않은 상자부터 사이클을 찾는다.
        if (!visit[j]) {
          dfs(cards, j, 1, false);
        }

        // box1 과 box2 값의 곱을 기존 answer 와 비교해 큰 값을 answer 가 참조하도록 한다.
        answer = Math.max(answer, box1*box2);
      }

    }

    return answer;
  }


  /*
  재귀호출로 사이클을 만들어 visit 배열에 방문 여부를 체크해준다.
  다음 방문할 카드가 이미 방문 체크가 되어있다면 사이클이 만들어졌으므로 이제까지 거쳐왔던 카드 갯수(depth)를 box1(또는 box2) 변수에 업데이트 해준다.
  아니라면 다음 카드의 방문을 위해 num 에 cards[num] - 1(다음 방문할 상자의 인덱스)와 함께 dfs 메서드를 호출한다.

  int[] cards: 상자에 담긴 카드 배열
  int num: 현재 몇번째 상자를 열었는지 참조하는 변수
  int depth: 이제까지 거쳐왔던 카드 갯수를 참조하는 변수
  boolean isFirst: 첫번째 그룹 체크인지, 두번째인지 구분하기 위한 변수
   */
  static void dfs(int[] cards, int num, int depth, boolean isFirst) {
    visit[num] = true;

    if (!visit[cards[num] - 1]) {
      dfs(cards, cards[num] - 1, depth+1, isFirst);
    }
    else {
      if (isFirst) box1 = depth;
      else box2 = depth;
      return;
    }
  }
}
