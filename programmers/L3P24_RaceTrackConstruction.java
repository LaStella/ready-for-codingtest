// 경주로 건설
// https://programmers.co.kr/learn/courses/30/lessons/67259?language=java

import java.util.*;

class Solution {
    int minCost = Integer.MAX_VALUE;
    public int solution(int[][] board) {
        int answer = 0;
        
        bfs(0, 0, board);
        
        answer = minCost;
        
        return answer;
    }
    
    public void bfs(int r, int c, int[][] board) {
        // 상, 하, 좌, 우로 좌표를 이동할 때 사용하는 가중치입니다.
        int[][] nextVal = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int n = board.length;
        boolean[][] visited = new boolean[n][n];
        Queue<Node> q = new LinkedList<>();
        visited[r][c] = true;
        // 출발지는 방향을 정하지 않으므로 -1을 넣어줍니다.
        // 0 = 상, 1 = 하, 2 = 좌, 3 = 우
        q.offer(new Node(r, c, 0, -1));
    
        while(!q.isEmpty()) {
            Node node = q.poll();
            // 도착지(n-1,n-1)에 도착할 때 건설 비용을 저장합니다.
            if(node.r == n-1 && node.c == n-1) {
                minCost = Math.min(minCost, node.cost);
            }
            // 도착지에 도착할 때 까지 좌표를 상, 하, 좌, 우로 움직입니다.
            else {
                for(int i = 0 ; i < 4 ; i++) {
                    int nr = node.r+nextVal[i][0];
                    int nc = node.c+nextVal[i][1];
                    // 새로운 좌표가 경주로 부지를 넘어서는 경우를 제외합니다.
                    if(nr < 0 || nr > n-1 || nc < 0 || nc > n-1) {
                        continue;
                    }
                    // 새로운 좌표의 경주로 부지가 벽이 아니라면 경주로 건설 비용을 계산합니다.
                    // 이전 경주로의 방향에 따라 건설할 경주로 비용이 달라집니다.
                    // 이전 경주로와 방향이 동일하면 직선도로 건설비용 100이 추가됩니다.
                    // 이전 경주로와 방향이 다르다면 직선도로 + 코너 건설비용 600이 추가됩니다.
                    if(board[nr][nc] != 1) {
                        int nCost = 0;
                        if(node.direction == i || node.direction == -1) {
                            nCost = node.cost+100;
                        }
                        else {
                            nCost = node.cost+600;
                        }
                        // 경주로 부지에 방문 여부를 갱신합니다.
                        // 새 경주로 건설 비용과 기존 경주로 건설 비용의 차이가 400보다 작다면 큐에 넣습니다.
                        // 기존 경주로가 도착지로 가기위해 코너를 만들어야 하나, 새 경주로는 코너를 만들 필요가 없을 경우
                        // 차액 500이 발생합니다. 새 경주로가 400원 더 비싸도 코너의 유무에 따라 역전 될 수 있습니다.
                        if(!visited[nr][nc] || nCost - board[nr][nc] <= 400) {
                            visited[nr][nc] = true;
                            board[nr][nc] = nCost;
                            q.offer(new Node(nr, nc, nCost, i));
                        }
                    }
                }
            }
        }
    }
}

// 경주로의 정보를 저장할 객체입니다.
// r = 행, c = 열, cost = 건설 비용, direction = 마지막 경주로 방향
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
}

/*
예외발생 삼거리
        2200
2600    2700
        3300
2600 > 2700 > 3300 보다
2200 > 2800 > 2900 이 저렴

2600 > 3200 > 3300 와 같을 경우 동일
2500 > 3100 > 3200 이 최소차이 저렴
즉, 기존 비용과 nCost의 차액이 400보다 작을 경우 
다음 코너의 유무에 따라 두 건설 비용은 역전될 수 있다. (코너의 유무에 따른 건설 비용 차액은 500이므로)
*/

//------------------오답------------------------------
/* DFS(시간초과)
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
*/


/* BFS(정답 틀림)
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