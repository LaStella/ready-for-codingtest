// 미로 탈출
// https://programmers.co.kr/learn/courses/30/lessons/81304?language=java

import java.util.*;

class Solution {
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = 0;
        
        int[] dist = new int[n+1];
        
        
        
        return answer;
    }
}

/*
1.
2차 배열에 모든 경로를 저장
int[][] route, route[a][b]는 a에서 b로 연결되는 경로 

trap을 밟으면 route[a][b] 를 route[b][a]로 전환
route[b][a]가 존재할 경우 둘의 소모값만 바꿔준다.
int temp = route[a][b];
route[a][b] = route[b][a];
route[b][a] = temp;

bfs를 이용하여 탐색
문제발생> 트랩에 의해 바뀌는 간선때문에 bfs를 이용할 수 없다.
------------------------------
2.
다익스트라 이용
출발 정점에서 연결된 정점을 방문
> 방문 시 출발지에서 목적지 정점까지 걸리는 시간이 작은 값을 저장, int[] dist배열 이용, dist[2] = 출발지에서 2번 정점까지 걸리는 시간
> 누적 시간은 이동한 정점에 저장합니다. Node 객체 이용
> 누적 시간이 가장 적은 정점을 뽑아 다음 정점으로 이동합니다.
> 정점이 end에 도달하면 dist[end]와 비교하여 최소값을 저장합니다.
> trap의 발동 여부를 항상 저장하여 다음 정점으로 정보를 넘겨줍니다.
    > ex. 입출력예2번에서 2번 트랩을 밟을 경우, 1번으로 이동하는 경우와 3번으로 이동하는 경우가 존재, 3번으로 이동할 경우 트랩이 발동하며, 1번으로 이동한 경우 트랩은 발동하지 않습니다. 서로 다른 경우이므로 트랩의 발동 유무는 공유하지 않아야합니다.
*/