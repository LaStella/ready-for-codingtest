import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
        
        answer = getBFS(0, 0, maps);
        
        return answer;
    }
    
    // BFS알고리즘을 이용하여 최단거리를 구하는 함수입니다.
    public int getBFS(int r, int c, int[][] maps) {
        // 상, 하, 좌, 우로 이동하는 가중치입니다.
        int[][] nextVal = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        visited[r][c] = true;
        
        q.offer(new Node(r, c, 1));
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            // row와 column이 상대방 진영이 위치한 곳이면 이동 거리를 return합니다.
            if(node.r == maps.length-1 && node.c == maps[0].length-1) {
                return node.d;
            }

            // 상, 하, 좌, 우로 이동합니다.
            for(int i = 0 ; i < 4 ; i++) {
                int nr = node.r + nextVal[i][0];
                int nc = node.c + nextVal[i][1];
                // map을 벗어나는 경우는 제외합니다.
                if(nr < 0 || nr > maps.length-1 || nc < 0 || nc > maps[0].length-1) {
                    continue;
                }
                if(maps[nr][nc] == 1 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new Node(nr, nc, node.d+1));
                }
            }
        }
        
        return -1;
    }
    
    
}

// row와 column, 이동거리를 저장할 Node 객체입니다.
class Node {
    int r;
    int c;
    int d;
    public Node(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}