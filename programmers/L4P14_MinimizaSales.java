// 매출 하락 최소화
// https://programmers.co.kr/learn/courses/30/lessons/72416?language=java

class Solution {
    public int solution(int[] sales, int[][] links) {
        int answer = 0;
        return answer;
    }
}

/*
dfs를 이용하여 루트에서 자식으로 내려갑니다.
팀장인 노드는 서브트리를 가집니다.
예제1을 예시로
1번 노드에서 시작합니다.
9번 노드를 방문하고 팀장노드를 워크숍에 보내는 경우와 보내지 않는 경우의 최저값을 구합니다.
    > 팀장 노드를 워크숍에 보내는 경우 28, 보내지 않는 경우 13
5번 노드를 방문하고 9번노드와 마찬가지로 두 경우의 값을 구합니다.

자식이 팀장인 마지막 노드는 10번 노드로 10번노드에서 역순으로 최적값을 더합니다.


*/