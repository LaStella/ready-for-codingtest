// 미로 탈출
// https://programmers.co.kr/learn/courses/30/lessons/81304?language=java

import java.util.*;

class Solution {
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = 0;
        
        int[][] route = new int[n+1][n+1];
        
        for(int[] road : roads) {
            route[road[0]][road[1]] = road[2];
        }
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, 0));
        // System.out.println(q);
        // q.poll();
        // System.out.println(q);
        // System.out.println(q.peek().index);
        while(!q.isEmpty()) {
            Node p_node = q.poll();
            int p_index = p_node.index;
            int p_time = p_node.time;
            
            if(p_index == end) {
                System.out.println(p_time);
                break;
            }
            
            for(int i = 1 ; i < n+1 ; i++) {
                if(route[p_index][i] != 0) {
                    q.offer(new Node(i, p_time+route[p_index][i]));
                }
            }
        }
        
        
        
        return answer;
    }
    
    public boolean isTrap(int index, int[] traps) {
        for(int i = 0 ; i < traps.length ; i++) {
            if(index == traps[i]) {
                return true;
            }
        }
        
        return false;
    }
    
    class Node {
        int index;
        int time;
        public Node(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }
}

/*
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


*/