// 매출 하락 최소화
// https://programmers.co.kr/learn/courses/30/lessons/72416?language=java

import java.util.*;

class Solution {
    public int solution(int[] sales, int[][] links) {
        int answer = 0;
        
        int n = sales.length;
        int[][] cost = new int[n][2];
        
        // 서로 연결된 링크를 리스트로 저장합니다.
        // link_list[i] : i번 노드와 연결된 자식이 담긴 리스트
        // link_list[i].get(j) : i번 노드의 j번째 자식
        ArrayList<Integer>[] link_list = new ArrayList[n];
        for(int i = 0 ; i < n ; i++) {
            link_list[i] = new ArrayList<>();
        }
        // System.out.println(link_list[sales.length-1]);
        // 1번 노드를 0번으로 저장하므로 모든 노드에 -1을 합니다.
        for(int[] link : links) {
            link_list[link[0]-1].add(link[1]-1);
        }
        
        dfs(0, cost, sales, link_list);
        
        return answer;
    }
    
    public void dfs(int index, int[][] cost, int[] sales, ArrayList<Integer>[] link_list) {
        // index번 직원이 참석할때 비용입니다.
        cost[index][0] = sales[index];
        // index번 직원이 참석하지 않을때 비용입니다.
        cost[index][1] = 0;
        
        // 자식이 없다면 리턴합니다.
        if(link_list[index].isEmpty()) {
            return;
        }
        
        int min_child_cost = Integer.MAX_VALUE;
        // 자식이 있다면 index번 직원을 선택하지 않는 비용은 자식을 선택하는 비용 중 최저 비용입니다.
        for(int child_index : link_list[index]) {
            dfs(child_index);
            
            if(min_child_cost > cost[child_index][0]) {
                
            }
        }
    }
}

/*
dfs를 이용하여 루트에서 자식으로 내려갑니다.
팀장인 노드는 서브트리를 가집니다.

각 노드별로 해당 노드를 선택할 때와 선택하지 않을 때를 나누어 값을 저장합니다.


예제1의 D조직을 예시로
(워크숍 참가시, 불참시)
6번  (14, 0) / 8번  (16, 0) / 2번  (17, 0)
10번 (17, 6,8,2번 노드 중 참가시 최저값)



*/