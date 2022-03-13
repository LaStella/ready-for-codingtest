// 경주로 건설
// https://programmers.co.kr/learn/courses/30/lessons/67259?language=java

class Solution {
    // 상, 하, 좌, 우로 좌표를 이동할 때 사용하는 가중치입니다.
    int[][] nextVal = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int n;
    int minCost = Integer.MAX_VALUE;
    
    public int solution(int[][] board) {
        int answer = 0;
        n = board.length;
        boolean[][] visited = new boolean[n][n];
        
        dfs(0, 0, 0, -1, board, visited);
        
        answer = minCost;
        
        return answer;
    }
    
    public void dfs(int r, int c, int cost, int direction, int[][] board, boolean[][] visited) {
        if(cost > minCost) return;
        // 도착지(n-1,n-1)에 도착할 때 건설 비용을 저장합니다.
        if(r == n-1 && c == n-1) {
            minCost = Math.min(minCost, cost);
        }
        // 도착지에 도착할 때 까지 좌표를 상, 하, 좌, 우로 움직입니다.
        else {
            for(int i = 0 ; i < 4 ; i++) {
                int nr = r+nextVal[i][0];
                int nc = c+nextVal[i][1];
                // 좌표의 범위가 경주로 부지를 넘어서는 경우를 제외합니다.
                if(nr < 0 || nr > n-1 || nc < 0 || nc > n-1) {
                    continue;
                }
                // 경주로 부지가 벽이아니며, 아직 건설되지 않은 부지인 경우
                else if(board[nr][nc] != 1 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    // 이전 좌표에서 현재 좌표로 이동한 방향을 비교하여 비용을 더합니다.
                    // 이동 방향이 같다면 직선 도로의 비용 100을 더하며, 다르다면 직선 도로의 비용+코너의 비용 600을 더합니다.
                    if(direction == i || direction == -1) {
                        dfs(nr, nc, cost+100, i, board, visited);
                    }
                    else {
                        dfs(nr, nc, cost+600, i, board, visited);
                    }
                    visited[nr][nc] = false;
                }
            }
        }
    }
}


/*--------------------------bfs 풀이--------------------------
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

*/