// 가장 먼 노드
// https://programmers.co.kr/learn/courses/30/lessons/49189?language=java#

import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        boolean[][] edges = new boolean[n][n];

        // 1번노드에서 부터의 거리를 최대로 초기화하고, 1번노드에서 1번노드의 거리는 0으로 초기화합니다.
        int[] distance = new int[n];
        Arrays.fill(distance, 20001);
        distance[0] = 0;
        
        // 간선들을 양방향으로 저장합니다.
        for(int[] e : edge) {
            edges[e[0]-1][e[1]-1] = true;
            edges[e[1]-1][e[0]-1] = true;
        }
        
        // 1번노드와 연결된 간선들의 거리를 저장합니다.
        for(int i = 0 ; i < n ; i++) {
            if(edges[0][i]) distance[i] = 1;
        }
        
        int max = 0;
        // 다익스트라 알고리즘을 이용하여 최소 비용 간선을 저장합니다.
        // 첫 노드는 이미 순회하였으며 마지막 노드는 방문하지 않아도 연결된 양방향 간선으로 인해 알 수 있으므로 n-2번 반복하면됩니다.
        for(int i = 0 ; i < n-2 ; i++) {
            int index = getMinIndex(distance, visited);
            visited[index] = true;
            for(int j = 0 ; j < n ; j++) {
                if(!visited[j]) {
                    if(edges[index][j]) {
                        distance[j] = Math.min(distance[j], distance[index]+1);
                        // 거리를 갱신할때 최대거리를 같이 갱신하여 최대 거리를 갱신해줍니다.
                        max = Math.max(max, distance[j]);
                    }
                }
            }
        }
        
        // 간선이 연결되지 않은 노드가 존재할 경우 최대 거리를 찾는 방법입니다.
        // int max = 0;
        // for(int d : distance) {
        //     max = Math.max(max, d);
        // }
        
        // 최대 거리 노드의 갯수를 셉니다.
        for(int d : distance) {
            if(d == max) {
                answer++;
            }
        }
        
        return answer;
    }

    // 방문하지 않은 노드 중 최소 거리를 가지는 노드의 인덱스를 return하는 함수입니다.
    public int getMinIndex(int[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for(int i = 0 ; i < distance.length ; i++) {
            if(distance[i] < min && !visited[i]) {
                min = distance[i];
                index = i;
            }
        }
        return index;
    }
}