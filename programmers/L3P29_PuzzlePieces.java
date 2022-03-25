// 퍼즐 조각 채우기
// https://programmers.co.kr/learn/courses/30/lessons/84021?language=java

import java.util.*;

class Solution {
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        int n = game_board.length;
        // board와 table의 조각을 탐색시 방문 여부를 저장할 배열입니다.
        boolean[][] visited_game_board = new boolean[n][n];
        boolean[][] visited_table = new boolean[n][n];
        // board와 table의 조각을 저장할 리스트입니다.
        List<List<int[]>> game_board_pieces = new ArrayList<>();
        List<List<int[]>> table_pieces = new ArrayList<>();
        
        // board와 table에서 각각 빈 공간(빈 공간 또한 조각으로 볼 수 있습니다.)과 조각을 찾아 저장합니다.
        for(int r = 0 ; r < n ; r++) {
            for(int c = 0 ; c < n ; c++) {
                if(game_board[r][c] == 0 && !visited_game_board[r][c]) {
                    bfs(r, c, game_board, visited_game_board, game_board_pieces, 0);
                }
                if(table[r][c] == 1 && !visited_table[r][c]) {
                    bfs(r, c, table, visited_table, table_pieces, 1);
                }
            }
        }
        
        // talbe의 조각 사용 여부를 저장할 배열입니다.
        boolean[] used_table_pieces = new boolean[table_pieces.size()];
        
        for(int i = 0 ; i < game_board_pieces.size() ; i++) {
            for(int j = 0 ; j < table_pieces.size() ; j++) {
                // 빈 공간과 퍼즐 조각의 칸의 개수가 같으며, 아직 사용하지 않은 퍼즐 조각인 경우
                // 퍼즐 조각을 회전시키면서 맞춰봅니다.
                List<int[]> game_board_piece = game_board_pieces.get(i);
                List<int[]> table_piece = table_pieces.get(j);
                if(game_board_piece.size() == table_piece.size() && !used_table_pieces[j]) {
                    boolean isMatch = false;
                    int rotateCount = 0;
                    while(rotateCount < 4) {
                        isMatch = matchPiece(game_board_piece, table_piece);
                        // 퍼즐 조각이 빈 공간에 맞다면 회전을 중단합니다.
                        if(isMatch) break;
                        // 회전은 3번만(90도, 180도, 270도) 하면되므로 마지막 반복에서 회전은 하지 않습니다.
                        if(rotateCount != 3) rotatePiece(table_piece);
                        rotateCount++;
                    }
                    // 퍼즐 조각이 빈 공간에 맞다면 퍼즐 조각의 칸만큼 정답에 더하며, 퍼즐 조각의 사용 여부를 변경합니다.
                    // 빈 공간이 채워졌으므로 이후의 퍼즐은 확인할 필요가 없으므로 중단합니다.
                    if(isMatch) {
                        answer += table_piece.size();
                        used_table_pieces[j] = true;
                        break;
                    }
                }
            }
        }
        
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
            // 조각을 이루는 칸의 좌표값을 저장합니다. (단, 조각의 시작 지점(r, c)을 원점으로 합니다.)
            piece.add(new int[] {p[0]-r, p[1]-c});
            // 상, 하, 좌, 우로 이동합니다.
            for(int i = 0 ; i < 4 ; i++) {
                int nr = pr + nextVal[i][0];
                int nc = pc + nextVal[i][1];
                // 새로운 좌표가 보드의 범위를 벗어나면 제외합니다.
                if(nr < 0 || nr >= board.length || nc < 0 || nc >= board.length) {
                    continue;
                }
                // 새로운 좌표가 조건(condition, game_board 에서는 0(빈 공간), table 에서는 1(조각))에 맞으며
                // 방문한 좌표가 아닐 경우 큐에 넣습니다.
                if(board[nr][nc] == condition && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc});
                }
            }
        }
        
        // 반복문이 종료되면 조각의 모든 좌표를 저장하였으므로 조각(piece)을 조각들(pieces)에 넣습니다.
        pieces.add(piece);
    }
    
    // 조각을 시계방향 90도 회전하는 함수입니다. (r, c) -> (c, -r)
    // 조각을 90도 회전하게 되면 좌표가 변경되어 조각의 시작 지점이 원점(0, 0)이 아니게 될 수 있습니다.
    // 따라서 회전 후 조각의 시작 지점이 원점이 되도록 평행이동합니다.
    // 조각의 시작 지점은 game_board나 table에서 조각을 bfs탐색시 첫 번째 칸을 말합니다.
    // 따라서 row가 가장 작으면서 column이 가장 작은 칸이 원점이 됩니다.
    public void rotatePiece(List<int[]> piece) {
        // 원점될 칸입니다. 90도 회전 후 row가 가장 작으면서 column이 가장 작은 좌표가 됩니다.
        int[] origin = piece.get(0);
        
        for(int i = 1 ; i < piece.size() ; i++) {
            // 모든 조각들에서 column이 가장 작은 (90도 회전시 column이 row가 되므로)좌표를 원점으로 저장합니다.
            if(piece.get(i)[1] < origin[1]) {
                origin = piece.get(i);
            }
            // 가장 작은 column이 같을 경우 row가 큰(90도 회전시 -row 가 column이 되므로) 좌표를 원점으로 저장합니다.
            else if(piece.get(i)[1] == origin[1]) {
                if(piece.get(i)[0] > origin[0]) {
                    origin = piece.get(i);
                }
            }
        }
        
        for(int i = 0 ; i < piece.size() ; i++) {
            int r = piece.get(i)[0];
            int c = piece.get(i)[1];
            // 90도 회전시킨 좌표를 원점에 맞추어 평행이동하여 좌표를 저장합니다.
            piece.set(i, new int[] {c-origin[1], -r+origin[0]});
        }
    }
    
    // 빈 공간(조각1)에 퍼즐 조각(조각2)을 맞추는 함수입니다.
    public boolean matchPiece(List<int[]> piece1, List<int[]> piece2) {
        // 각 조각이 가지고 있는 좌표가 같다면 빈 공간에 조각이 들어갈 수 있습니다.
        HashSet<String> set = new HashSet<>();

        for(int i = 0 ; i < piece1.size() ; i++) {
            set.add(Arrays.toString(piece1.get(i)));
            set.add(Arrays.toString(piece2.get(i)));
        }
        
        return set.size() == piece1.size();
    }
}