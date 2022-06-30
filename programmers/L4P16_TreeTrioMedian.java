// 트리 트리오 중간값
// https://programmers.co.kr/learn/courses/30/lessons/68937?language=java

class Solution {
    public int solution(int n, int[][] edges) {
        int answer = 0;
        
        
        return answer;
    }
}

/*
주어진 정점 n개에서 3개의 정점을 선택하며, 각 정점간 거리가 길수록 함수값은 커집니다.
1. 주어진 정점중 트리의 끝에 있는 임의의 정점을 선택해야합니다.
    ex) 예제1번의 경우 1, 4 번 정점이 트리의 끝자락에 있습니다.
    > 따라서 임의의 정점(여기선 1로 지정)에서 가장 먼 정점을 찾아 a정점으로 정합니다.
2. 1에서 찾은 a정점에서 거리가 가장 먼 정점(b)을 찾습니다.
    > 가장 먼 정점의 개수가 1개라면 찾은 정점 b로 부터 가장 먼 정점을 다시 탐색합니다.
    > 가장 먼 정점의 개수가 2개 이상이라면 a와 찾은 정점 중 두개를 고르면 함수의 최대값입니다.

*/