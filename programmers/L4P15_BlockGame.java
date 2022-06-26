// 블록 게임
// https://programmers.co.kr/learn/courses/30/lessons/42894?language=java

class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        return answer;
    }
}

/*
보드의 첫줄에서부터 탐색을 시작합니다.
0이 아닌 사각형을 찾으면 해당 사각형을 기준으로 같은 숫자를 가지는 사각형을 모두 찾습니다.(bfs)
찾은 블럭을 직사각형으로 만들어 삭제할 수 있는지 확인합니다.
    (4, 6), (5, 5), (5, 6), (6, 6)과 같은 좌표로 이루어져 있다면 지울수 없는 블럭입니다.
    지울수 있는 블럭과 아닌 블럭을 구분하여 삭제합니다.
    
*/