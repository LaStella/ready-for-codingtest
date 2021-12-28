// 배달
// https://programmers.co.kr/learn/courses/30/lessons/12978?language=java

import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        // 1번 마을까지 최단 시간 값을 저장하는 배열입니다.
        int[] shortest = new int[N];
        // 각 마을을 방문했는지 확인하는 배열입니다.
        boolean[] visited = new boolean[N];
   
        answer = dijkstra(visited, shortest, road, K);

        return answer;
    }
    
    // 다익스트라 알고리즘을 사용하여 최단 거리를 저장하는 함수입니다.
    public int dijkstra(boolean[] visited, int[] shortest, int[][] road, int K) {
        int count = 0;
        // 최단 시간 배열을 초기화 해줍니다.
        // 시작마을은 0으로, 나머지 마을까지의 거리는 최대치로 초기화합니다.
        Arrays.fill(shortest, 500001);
        shortest[0] = 0;
        
        // 모든 마을을 방문하면 종료됩니다.
        // 여기서 간선의 방향은 존재하지 않으므로 마지막 마을은 방문하지 않아도 됩니다. (양방향 간선)
        for(int i = 0 ; i < visited.length-1 ; i++) {
            // 방문하지 않은 마을 중 최단 시간을 가지는 마을의 번호를 가져옵니다. (이하 최단 마을이라 함)
            // 1번 마을은 0, 2번 마을은 1, n번 마을은 n-1의 index를 나타냅니다.
            int minIndex = getMinIndex(visited, shortest);
            // 최단 마을을 방문 처리합니다.
            visited[minIndex] = true;
            // 최단 마을과 연결된 모든 간선의 시간을 업데이트합니다.
            for(int[] arr : road) {
                // 문제에서 주어진 간선은 두 마을의 번호가 정렬되어 있지 않으므로 간선의 두 마을 번호 모두 확인합니다.
                if(arr[0]-1 == minIndex) {
                    // 최소 마을과 연결된 간선의 거리를 비교합니다.
                    // 기존의 최단 시간 보다 작을 경우에 저장합니다.
                    // 기존 최단 시간 > 최단 마을 시간 + 간선 시간
                    if(shortest[arr[1]-1] > shortest[minIndex]+arr[2]) {
                        shortest[arr[1]-1] = shortest[minIndex]+arr[2];
                    }
                }
                // 위와 동일하며 간선의 두 마을 번호가 역순으로 되어있는 경우입니다.
                else if(arr[1]-1 == minIndex) {
                    if(shortest[arr[0]-1] > shortest[minIndex]+arr[2]) {
                        shortest[arr[0]-1] = shortest[minIndex]+arr[2];
                    }
                }
            }
        }
        
        // 최단 시간 배열에서 문제에서 요구한 K시간 이하의 마을의 개수를 셉니다.
        for(int i : shortest) {
            if(i <= K) {
                count++;
            }
        }
        
        return count;
    }
    
    // 방문하지 않은 마을 중 최단 시간 마을의 번호를 return하는 함수입니다.
    public int getMinIndex(boolean[] visited, int[] shortest) {
        int min = 500001;
        int index = 0;
        
        for(int i = 0 ; i < shortest.length ; i++) {
            if(!visited[i] && shortest[i] < min) {
                min = shortest[i];
                index = i;
            }
        }
        return index;
    }
}