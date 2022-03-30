// 카드 짝 맞추기
// https://programmers.co.kr/learn/courses/30/lessons/72415?language=java

import java.util.*;

class Solution {
    HashMap<Integer, List<int[]>> map = new HashMap<>();
    List<Integer> card_list;
    public int solution(int[][] board, int r, int c) {
        int answer = 0;
        
        for(int i = 0 ; i < 4 ; i++) {
            for(int j = 0 ; j < 4 ; j++) {
                if(board[i][j] != 0) {
                    List<int[]> list = map.getOrDefault(board[i][j], new ArrayList<>());
                    list.add(new int[] {i ,j});
                    map.put(board[i][j], list);
                }
            }
        }
        
        card_list = new ArrayList<>(map.keySet());
        boolean[] visited = new boolean[card_list.size()];
        
        
        
        return answer;
    }
    
    // 삭제할 카드의 순열을 구하고 조작 횟수를 구하는 함수입니다.
    public void dfs(String test, int result, int depth, int r, int c, boolean[] visited, int[][] board) {
        if(depth == card_list.size()) {
            // System.out.println(result);
            // System.out.println(test);
        }
        else {
            for(int i = 0 ; i < card_list.size() ; i++) {
                if(!visited[i]) {
                    visited[i] = true;
                    List<int[]> list = map.get(card_list.get(i));
                    int[] card_1 = list.get(0);
                    int[] card_2 = list.get(1);

                    board[card_1[0]][card_1[1]] = 0;
                    board[card_2[0]][card_2[1]] = 0;
                    // dfs(test+card_list.get(i)+"!", result+count_1, depth+1, card_2[0], card_2[1], visited, board);
                    // dfs(test+card_list.get(i)+"!!", result+count_2, depth+1, card_1[0], card_1[1], visited, board);
                    board[card_1[0]][card_1[1]] = card_list.get(i);
                    board[card_2[0]][card_2[1]] = card_list.get(i);
                    visited[i] = false;
                }
            }
        }
    }
    
    // 커서를 (r1, c1) 에서 (r2, c2)로 이동하는 최소 이동 횟수를 구하는 함수입니다.
    public int bfs(int r1, int c1, int r2, int c2, int[][] board) {
        int[][] nextval = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[4][4];
        q.offer(new Node(r1, c1, 0));
        
        while(!q.isEmpty()) {
            Node p_node = q.poll();
            int pr = p_node.r;
            int pc = p_node.c;
            int pm = p_node.m;
            
            if(pr == r2 && pc == c2) {
                return pm;
            }
            // 상, 하, 좌, 우 커서 이동
            for(int i = 0 ; i < 4 ; i++) {
                int nr = pr+nextval[i][0];
                int nc = pc+nextval[i][0];
                if(nr < 0 || nr > 3 || nc < 0 || nc > 3) continue;
                if(!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new Node(nr, nc, pm+1))
                }
            }
            
            for(int i = 0 ; i < 4 ; i++) {
                int nr = pr+nextval[i][0];
                int nc = pc+nextval[i][0];
                if(nr < 0 || nr > 3 || nc < 0 || nc > 3) continue;
                if(!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new Node(nr, nc, pm+1))
                }
            }
        }
        
        return -1;
    }
}

class Node {
    int r;
    int c;
    int m;
    Node(int r, int c, int m) {
        this.r = r;
        this.c = c;
        this.m = m;
    }
}

/*
1.
board를 읽어 카드 짝들의 위치를 기록 - hashmap사용

2.
dfs를 이용하여 카드의 제거 순서를 결정

3.
bfs를 이용하여 최소 조작 회수 이동을 구함

*/