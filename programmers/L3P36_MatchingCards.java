// 카드 짝 맞추기
// https://programmers.co.kr/learn/courses/30/lessons/72415?language=java

class Solution {
    public int solution(int[][] board, int r, int c) {
        int answer = 0;
        return answer;
    }
}

/*
1.
board를 읽어 카드 짝들의 위치를 기록 - hashmap사용

2.
dfs를 이용하여 카드의 제거 순서를 결정

3.
카드를 제거하는 데 필요한 키 조작
row와 column을 이동 - 이동시 0이 아닌 카드를 만나면 횟수+1, 0이며 보드의 끝이면 횟수+1


*/