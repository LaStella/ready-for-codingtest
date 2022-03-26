// 모두 0으로 만들기
// https://programmers.co.kr/learn/courses/30/lessons/76503?language=java#

import java.util.*;

class Solution {
    long answer = 0;
    long[] global_a;
    List<Integer>[] edge_list;
    boolean[] visited;
    
    public long solution(int[] a, int[][] edges) {
        // 각 정점의 가중치를 long타입으로 저장할 배열입니다.
        global_a = new long[a.length];
        // 간선을 저장할 리스트입니다.
        edge_list = new ArrayList[a.length];
        // 정점의 방문 여부를 저장할 배열입니다.
        visited = new boolean[a.length];
        
        long sum = 0;
        
        for(int i = 0 ; i < a.length ; i++) {
            sum += a[i];
            global_a[i] = a[i];
            edge_list[i] = new ArrayList<Integer>();
        }
        
        // 모든 정점의 가중치합이 0이 아닐 경우 모든 정점을 0으로 만드는 것이 불가능합니다.
        if(sum != 0) return answer = -1;
        
        // 간선을 양방향으로 저장합니다.
        // boolean[][] 배열을 통해 저장할 수 있으나, 정점이 많아 메모리 크기를 초과할 수 있습니다.
        for(int i = 0 ; i < edges.length ; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            edge_list[u].add(v);
            edge_list[v].add(u);
        }
        
        // 0번 정점을 head로 하는 트리입니다.
        // 한 정점을 기준으로 트리를 만들어 자식에서 부모로 가중치를 더해가므로 어느 정점을 head로 하든 상관없습니다.
        dfs(0);
        
        return answer;
    }
    
    // 주어진 index번 정점의 가중치를 리턴하는 함수입니다.
    // index의 가중치는 index의 모든 자식 정점의 가중치에 자신의 가중치까지 더해야합니다.
    public long dfs(int index) {
        // 정점을 방문처리합니다.
        visited[index] = true;
        
        // 정점과 이어진 다른 정점들을 찾습니다.
        for(int i = 0 ; i < edge_list[index].size() ; i++) {
            int child_index = edge_list[index].get(i);
            // 방문하지 않은 정점이라면 index정점의 자식이 됩니다.
            // 따라서 자식 정점의 가중치를 더하여야 합니다.
            if(!visited[child_index]) {
                long child_value = dfs(child_index);
                global_a[index] += child_value;
                // 자식 정점의 가중치를 더한만큼 행동 횟수가 증가합니다.
                answer += Math.abs(child_value);
            }
        }
        
        return global_a[index];
    }
}


/*
트리 형성
자식들부터 차례로 순회 
*/