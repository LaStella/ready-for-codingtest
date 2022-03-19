// 퍼즐 조각 채우기
// https://programmers.co.kr/learn/courses/30/lessons/84021?language=java

import java.util.*;

class Solution {
    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;
        int n = game_board.length;
        boolean[][] visited_game_board = new boolean[n][n];
        boolean[][] visited_table = new boolean[n][n];
        List<List<int[]>> game_board_pieces = new ArrayList<>();
        List<List<int[]>> table_pieces = new ArrayList<>();
        
        for(int r = 0 ; r < n ; r++) {
            for(int c = 0 ; c < n ; c++) {
                if(game_board[r][c] == 0 && !visited_game_board[r][c]) {
                    bfs(r, c, game_board, visited_game_board, game_board_pieces, 0);
                }
            }
        }
        
        // System.out.println("test");
        // int t1 = 5;
        // int t2 = 5;
        // System.out.println(5>5);
        // HashSet<int[]> ts = new HashSet<>();
        // HashSet<int[]> ts2 = new HashSet<>();
        // int[] a = {1};
        // int[] b = {1};
        // ts.add(a);
        // ts2.add(b);
        // System.out.println(ts.contains(a));
        // System.out.println(ts.contains(b));
        
        return answer;
    }
    
    // 주어진 좌표(r, c)로 부터 시작되는 조각의 모든 좌표를 찾는 함수입니다.
    public void bfs(int r, int c, int[][] board, boolean[][] visited, List<List<int[]>> pieces, int condition) {
        // 상, 하, 좌, 우로 이동하는 가중치입니다.
        int[][] nextVal = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> q = new LinkedList<>();
        // 조각을 이루는 칸의 모든 좌표를 저장할 배열리스트입니다.
        List<int[]> piece = new ArrayList<>();
        visited[r][c] = true;
        q.offer(new int[] {r, c});
        
        while(!q.isEmpty()) {
            int[] p = q.poll();
            int pr = p[0];
            int pc = p[1];
            // p[0] -= r;
            // p[1] -= c;
            // puzzle_coordinates.add(p);
            // 조각을 이루는 칸의 좌표값을 저장합니다. (단, 좌표값은 (0, 0)을 기준으로 합니다.)
            piece.add(new int[] {p[0]-r, p[1]-c});
            // 상, 하, 좌, 우로 이동합니다.
            for(int i = 0 ; i < 4 ; i++) {
                int nr = pr + nextVal[i][0];
                int nc = pc + nextVal[i][1];
                // 새로운 좌표가 보드의 범위를 벗어나면 제외합니다.
                if(nr < 0 || nr >= board.length || nc < 0 || nc >= board.length) {
                    continue;
                }
                // 새로운 좌표가 조건(condition, game_board 에서는 0, table 에서는 1)에 맞으며
                // 방문한 좌표가 아닐 경우 큐에 넣습니다.
                if(board[nr][nc] == condition && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc});
                }
            }
        }
        
        // 반복문이 종료되면 조각의 모든 좌표를 저장하였으므로 조각(piece)를 조각들(pieces)에 넣습니다.
        pieces.add(piece);
        // System.out.println("!");
        // for(int[] co : puzzle_coordinates) {
        //     System.out.println(co[0]+" "+co[1]);
        // }
        rotatePiece(piece);
    }
    
    public List<int[]> rotatePiece(List<int[]> piece) {
        for(int[] a : piece) {
            System.out.println(a[0]+"/"+a[1]);
        }
        Collections.sort(piece, (o1, o2) -> o1.compareTo(o2));
        for(int[] a : piece) {
            System.out.println(a[0]+"/"+a[1]);
        }
        // for(int i = 0 ; i < piece.length ; i++) {
        //     piece.get(i)
        // }
        
        return piece;
    }
    
}