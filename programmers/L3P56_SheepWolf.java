// 양과 늑대
// https://programmers.co.kr/learn/courses/30/lessons/92343?language=java#

import java.util.*;

class Solution {
    int answer;
    public int solution(int[] info, int[][] edges) {
        answer = 0;
        
        boolean[][] edge = new boolean[info.length][info.length];
        boolean[] visited = new boolean[info.length];
        
        // 노드들의 연결관계를 boolean형으로 저장합니다.
        for(int i = 0 ; i < edges.length ; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            edge[u][v] = true;
        }
        
        // 루트노드를 방문하고 양 한마리를 더합니다.
        visited[0] = true;
        dfs(1, 0, visited, info, edge);
        
        
        return answer;
    }
    
    // 양과 늑대를 더하며 탐색하는 함수입니다.
    // s : 양의 수, w : 늑대의 수, visited : 방문한 노드
    public void dfs(int s, int w, boolean[] visited, int[] info, boolean[][] edge) {
        // 양과 늑대의 수가 동일하면 더 이상 탐색할 수 없으므로 종료합니다.
        // 양과 늑대의 수 합이 전체 노드의 수라면 더 이상 탐색할 노드가 없으므로 종료합니다.
        if(s == w || s+w == info.length) {
            answer = Math.max(answer, s);
        }
        
        else {
            for(int i = 0 ; i < visited.length ; i++) {
                if(visited[i]) {
                    // 방문한 노드와 연결된 간선을 찾습니다.
                    for(int j = 0 ; j < edge.length ; j++) {
                        // 간선으로 연결된 노드가 방문하지 않은 노드인 경우
                        if(edge[i][j] && !visited[j]) {
                            // 노드를 방문 처리합니다.
                            visited[j] = true;
                            // 양 또는 늑대의 수를 하나 늘려 다시 탐색합니다.
                            if(info[j] == 0) {
                                dfs(s+1, w, visited, info, edge);
                            }
                            else {
                                dfs(s, w+1, visited, info, edge);
                            }
                            visited[j] = false;
                        }
                    }
                }
            }
        }
    }
}