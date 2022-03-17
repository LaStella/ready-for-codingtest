// 아이템 줍기
// https://programmers.co.kr/learn/courses/30/lessons/87694?language=java

import java.util.*;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        // 사각형의 좌표값을 그대로 사용하게 되면 'ㅁ' 형태와 'ㄷ' 형태를 구분하지 못하는 문제가 발생합니다.
        // 따라서 모든 좌표값을 x2하여 0.5칸 단위로 계산할 수 있게합니다.
        
        // 길의 유무를 표시할 배열입니다.
        // 주어지는 사각형의 좌표는 50이하 51x2를 하여 102를 크기로 2차배열을 만듭니다.
        boolean[][] map = new boolean[102][102];
        
        // 사각형 테두리에 해당하는 좌표에 true를 넣습니다.
        for(int[] r : rectangle) {
            // 사각형의 각 좌표에 x2를 합니다.
            int x1 = r[0]*2;
            int y1 = r[1]*2;
            int x2 = r[2]*2;
            int y2 = r[3]*2;
            
            // 사각형의 위, 아래 변
            for(int i = x1 ; i <= x2 ; i++) {
                map[y1][i] = true;
                map[y2][i] = true;
            }
            // 사각형의 왼, 오른 변
            for(int i = y1 ; i <= y2 ; i++) {
                map[i][x1] = true;
                map[i][x2] = true;
            }
        }
        
        // 사각형의 내부에 해당하는 좌표에 false를 넣습니다.
        for(int[] r : rectangle) {
            int x1 = r[0]*2;
            int y1 = r[1]*2;
            int x2 = r[2]*2;
            int y2 = r[3]*2;
            for(int i = x1+1 ; i <= x2-1 ; i++) {
                for(int j = y1+1 ; j <= y2-1 ; j++) {
                    map[j][i] = false;
                }
            }
        }
        
        answer = bfs(characterX*2, characterY*2, itemX*2, itemY*2, map);
        
        return answer;
    }
    
    public int bfs(int characterX, int characterY, int itemX, int itemY, boolean[][] map) {
        // 상, 하, 좌, 우로 이동하는 가중치입니다.
        int[][] nextVal = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[102][102];
        visited[characterY][characterX] = true;
        
        q.offer(new Node(characterX, characterY, 0));
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            // x, y좌표값이 아이템이 위치한 곳이면 이동 거리를 return합니다.
            if(node.x == itemX && node.y == itemY) {
                // 좌표의 크기를 2배로 하였기에 실제 이동한 거리는 /2를 해야합니다.
                return node.d/2;
            }

            // 상, 하, 좌, 우로 이동합니다.
            for(int i = 0 ; i < 4 ; i++) {
                int ny = node.y + nextVal[i][0];
                int nx = node.x + nextVal[i][1];
                // 멥에 길이 존재하며, 방문하지 않은 좌표는 큐에 넣습니다.
                if(map[ny][nx] && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    q.offer(new Node(nx, ny, node.d+1));
                }
            }
        }
        
        return -1;
    }
}

// 경로의 정보를 저장할 객체입니다.
// x = x좌표, y = y좌표, d = 이동거리
class Node {
    int x;
    int y;
    int d;
    public Node(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}