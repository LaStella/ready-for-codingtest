// 모두 0으로 만들기
// https://programmers.co.kr/learn/courses/30/lessons/76503?language=java#

import java.util.*;

class Solution {
    long answer = 0;
    public long solution(int[] a, int[][] edges) {
        boolean[][] exist_edges = new boolean[a.length][a.length];
        ArrayList<Node> node_list = new ArrayList<>();
        boolean[] visited = new boolean[a.length];
        
        for(int[] edge : edges) {
            exist_edges[edge[0]][edge[1]] = true;
            exist_edges[edge[1]][edge[0]] = true;
        }
        
        int max_index = 0;
        int max_value = 0;
        
        for(int i  = 0 ; i < a.length ; i++) {
            node_list.add(new Node(i, a[i]));
            if(Math.abs(a[i]) > max_value) {
                max_index = i;
                max_value = Math.abs(a[i]);
            }
        }
        
        
        
        Queue<Node> q = new LinkedList<>();
        q.add(node_list.get(max_index));
        
        while(!q.isEmpty()) {
            Node p_node = q.poll();
            visited[p_node.index] = true;
            for(int i = 0 ; i < a.length ; i++) {
                if(exist_edges[p_node.index][i] && !visited[i]) {
                    q.add(node_list.get(i));
                    p_node.child_list.add(node_list.get(i));
                }
            }
        }
        
        if(postorder(node_list.get(max_index)) != 0) {
            answer = -1;
        }
        
        return answer;
    }
    
    public int postorder(Node head) {
        if(head.child_list.size() != 0) {
            for(Node child : head.child_list) {
                int child_weight = postorder(child);
                answer += Math.abs(child_weight);
                head.weight += child_weight;
            }
        }
        
        return head.weight;
    }
}

class Node {
    int index;
    int weight;
    ArrayList<Node> child_list;
    
    public Node(int index, int weight) {
        this.index = index;
        this.weight = weight;
        child_list = new ArrayList<>();
    }
}



/*
트리 형성
자식들부터 차례로 순회 
헤드는 절대값이 가장큰것


*/