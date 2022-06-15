// 동굴 탐험
// https://programmers.co.kr/learn/courses/30/lessons/67260?language=java

import java.util.*;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = true;
        boolean[] visited = new boolean[n];
        
        // 통로를 2차 배열로 저장합니다.
        boolean[][] route = new boolean[n][n];
        for(int[] p : path) {
            route[p[0]][p[1]] = true;
            route[p[1]][p[0]] = true;
        }
        
        // 모든 순서를 맵으로 저장합니다. (key : 방 번호, value : key를 들어가기 위해 먼저 방문해야하는 방번호)
        HashMap<Integer, Integer> order_map = new HashMap<>();
        for(int[] o : order) {
            order_map.put(o[1], o[0]);
        }
        
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int count = 0;
        
        while(!q.isEmpty()) {
            // count가 큐의 크기와 같다면 한바퀴를 순회하였으므로 탐험이 불가능한 상태입니다.
            if(count == q.size()) {
                return false;
            }
            int p_index = q.poll();
            
            // 입장 순서가 존재하는 방인지 확인합니다.
            if(order_map.containsKey(p_index)) {
                if(!visited[order_map.get(p_index)]) {
                    q.add(p_index);
                    count++;
                    continue;
                }
            }
            count = 0;
            visited[p_index] = true;
            
            for(int i = 0 ; i < n ; i++) {
                // 방문하지 않은 방이며 통로가 존재하는 방인지 확인합니다.
                if(!visited[i] && route[p_index][i]) {
                    q.add(i);
                }
            }
        }
        
        return answer;
    }
}

/*
order를 map에 저장합니다.

예제1번을 예시로
0번 방으로 시작
0번방을 방문할 수 있는지 확인합니다.
방문할 수 있다면 0번과 연결된 모든 방을 큐에 넣습니다. (1,3,7)
방문할 수 없다면 0번을 다시 큐에 넣습니다.


*/