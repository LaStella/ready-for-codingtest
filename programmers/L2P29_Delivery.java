// 배달
// https://programmers.co.kr/learn/courses/30/lessons/12978?language=java

import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 최대 요금 = 지점갯수 최대(출발지 포함 200) 199 * 최대 비용 100000
        int answer = 19900001;
        // 간선을 양방향으로 저장합니다.
        int[][] nfares = new int[n][n];
        for(int[] fare : fares) {
            nfares[fare[0]-1][fare[1]-1] = fare[2];
            nfares[fare[1]-1][fare[0]-1] = fare[2];
        }
        
        // 출발 지점으로부터 모든 지점으로 가는 최소 요금 배열입니다.
        int[] distance_start = dijkstra(n, s-1, nfares);
        
        // 출발 지점 s로 부터 합승하여 이동한 지점을 i라고 할 때,
        // 총 요금 = (s -> i 요금) + (i -> a 요금) + (i -> b 요금) 이 됩니다.
        // 단, 지점은 0부터 시작하므로 s, a, b는 -1을 하게됩니다.
        for(int i = 0 ; i < n ; i++) {
            // 합승하여 이동한 지점 i로부터 모든 지점으로 가는 최소 요금 배열입니다.
            int[] distance_i = dijkstra(n, i, nfares);
            
            answer = Math.min(answer, distance_start[i]+distance_i[a-1] + distance_i[b-1]);
        }
        
        return answer;
    }
    
    
    // 출발지 s로부터 모든 지점으로의 최소 요금을 계산하는 함수입니다.
    public int[] dijkstra(int n, int s, int[][] nfares) {
        // 시작 지점s로부터 모든 지점으로 가는 최소 요금을 저장할 배열입니다.
        // 시작 지점은 비용을 0으로, 나머지 지점은 최대치로 초기화해줍니다.
        int[] distance = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(distance, 19900001);
        distance[s] = 0;
        
        // 모든 지점을 방문하면 종료되며 두 지점 사이의 경로는 일방통행이 아니므로 마지막 지점은 방문하지 않아도 됩니다.
        for(int i = 0 ; i < n - 1 ; i++) {
            // 최소 요금이 드는 지점을 찾습니다. (이하, 최소 요금 지점이라함)
            int minIndex = getMinIndex(distance, visited);
            // 찾은 지점을 방문처리합니다.
            visited[minIndex] = true;
            // 모든 간선들 중 찾은 지점과 연결된 간선을 찾습니다.
            // fare[0]-1 : 간선 시작 지점 / fare[1]-1 : 간선 도착 지점 / fare[2] : 간선 비용
            for(int j = 0 ; j < n ; j++) {
                if(nfares[minIndex][j] != 0) {
                    distance[j] = Math.min(distance[j], distance[minIndex] + nfares[minIndex][j]);
                }
            }   
        }
        
        return distance;
    }
    
    // 방문하지 않은 거점 중 최소 요금이 드는 지점을 리턴하는 함수입니다.
    public int getMinIndex(int[] distance, boolean[] visited) {
        int min = 19900001;
        int minIndex = 0;
        for(int i = 0 ; i < distance.length ; i++) {
            // 방문하지 않은 거점 중 최소 비용이 드는 거점을 찾습니다.
            if(!visited[i] && distance[i] < min) {
                min = distance[i];
                minIndex = i;
            }
        }
        
        return minIndex;
    }
}


/*
시작지점 s부터 합승구간을 k지점까지 감 (k는 시작 포함 모든 지점이 가능)
k지점에서 각자 도착지점까지 최소 거리를 더함 
*/