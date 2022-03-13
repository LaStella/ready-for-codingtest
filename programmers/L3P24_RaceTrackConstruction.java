// 경주로 건설
// https://programmers.co.kr/learn/courses/30/lessons/67259?language=java

import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        // int n = board.length;
        // boolean[][] visited = new boolean[n][n];
        
        bfs(0, 0, board);
        
        
        return answer;
    }
    
    public void bfs(int r, int c, int[][] board) {
        int[][] nextVal = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int n = board.length;
        boolean[][] visited = new boolean[n][n];
        Queue<Node> q = new LinkedList<>();
        visited[r][c] = true;
        q.offer(new Node(r, c, 0, -1));
    
        while(!q.isEmpty()) {
            Node node = q.poll();
            // node.print();
            if(node.r == n-1 && node.c == n-1) {
                System.out.println(node.cost);
            }
            
            else {
                for(int i = 0 ; i < 4 ; i++) {
                    int nr = node.r+nextVal[i][0];
                    int nc = node.c+nextVal[i][1];
                    if(nr < 0 || nr > n-1 || nc < 0 || nc > n-1) {
                        continue;
                    }
                    else if(board[nr][nc] != 1 && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        if(node.direction == i || node.direction == -1) {
                            // System.out.println("?");
                            q.offer(new Node(nr, nc, node.cost+100, i));
                        }
                        else {
                            q.offer(new Node(nr, nc, node.cost+600, i));
                        }
                    }
                }
            }
            // System.out.println(q);
        }
    }
    
    
//     public void dfs(int r, int c, int cost, int[][] board, boolean[][] visited) {
        
//     }
}

class Node {
    int r;
    int c;
    int cost;
    int direction;
    public Node(int r, int c, int cost, int direction) {
        this.r = r;
        this.c = c;
        this.cost = cost;
        this.direction = direction;
    }
    public void print() {
        System.out.println("r:"+r+" c:"+c+" cost:"+cost+" direction:"+direction);
    }
}