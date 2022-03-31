// 카드 짝 맞추기
// https://programmers.co.kr/learn/courses/30/lessons/72415?language=java

import java.util.*;

class Solution {
    HashMap<Integer, List<int[]>> map = new HashMap<>();
    List<Integer> card_list;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] board, int r, int c) {
        // 전체 보드를 순회하며 카드종류와 카드의 좌표를 저장합니다.
        for(int i = 0 ; i < 4 ; i++) {
            for(int j = 0 ; j < 4 ; j++) {
                if(board[i][j] != 0) {
                    List<int[]> list = map.getOrDefault(board[i][j], new ArrayList<>());
                    list.add(new int[] {i ,j});
                    map.put(board[i][j], list);
                }
            }
        }
        
        // 카드의 종류만큼 리스트를 만듭니다.
        card_list = new ArrayList<>(map.keySet());
        boolean[] visited = new boolean[card_list.size()];
        
        dfs(0, 0, r, c, visited, board);
        
        return answer;
    }
    
    // 삭제할 카드의 순열을 구하고 조작 횟수를 구하는 함수입니다.
    public void dfs(int result, int depth, int r, int c, boolean[] visited, int[][] board) {
        if(depth == card_list.size()) {
            answer = Math.min(answer, result);
        }
        else {
            // 카드의 종류가 담긴 리스트에서 한종류씩 제거합니다.
            for(int i = 0 ; i < card_list.size() ; i++) {
                if(!visited[i]) {
                    visited[i] = true;
                    List<int[]> list = map.get(card_list.get(i));
                    int[] card_1 = list.get(0);
                    int[] card_2 = list.get(1);
                    
                    // 출발지 > 카드1 > 카드2 순으로 이동 조작한 횟수와 카드를 뒤집는 횟수 2번을 더합니다.
                    int count_1 = 0;
                    count_1 += bfs(r, c, card_1[0], card_1[1], board);
                    count_1 += bfs(card_1[0], card_1[1], card_2[0], card_2[1], board);
                    count_1 += 2;
                    // 출발지 > 카드2 > 카드1 순으로 이동 조작한 횟수와 카드를 뒤집는 횟수 2번을 더합니다.
                    int count_2 = 0;
                    count_2 += bfs(r, c, card_2[0], card_2[1], board);
                    count_2 += bfs(card_2[0], card_2[1], card_1[0], card_1[1], board);
                    count_2 += 2;
                    // 보드에서 카드를 제거합니다.
                    board[card_1[0]][card_1[1]] = 0;
                    board[card_2[0]][card_2[1]] = 0;
                    // 출발지 > 카드1 > 카드2 순으로 이동한 경우 최종 좌표는 카드2의 좌표입니다.
                    dfs(result+count_1, depth+1, card_2[0], card_2[1], visited, board);
                    dfs(result+count_2, depth+1, card_1[0], card_1[1], visited, board);
                    board[card_1[0]][card_1[1]] = card_list.get(i);
                    board[card_2[0]][card_2[1]] = card_list.get(i);
                    visited[i] = false;
                }
            }
        }
    }
    
    // 커서를 (r1, c1) 에서 (r2, c2)로 이동하는 최소 이동 조작 횟수를 구하는 함수입니다.
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
            
            // 목적지에 도달시 조작 횟수를 리턴합니다.
            if(pr == r2 && pc == c2) {
                // System.out.println(pm);
                return pm;
            }
            // 방향키 커서 이동
            for(int i = 0 ; i < 4 ; i++) {
                int nr = pr+nextval[i][0];
                int nc = pc+nextval[i][1];
                // 게임 보드를 넘어서는 경우 제외합니다.
                if(nr < 0 || nr > 3 || nc < 0 || nc > 3) continue;
                if(!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new Node(nr, nc, pm+1));
                }
            }
            
            // Ctrl 방향키 커서 이동
            for(int i = 0 ; i < 4 ; i++) {
                int count = 1;
                int nr;
                int nc;
                while(true) {
                    nr = pr+nextval[i][0]*count;
                    nc = pc+nextval[i][1]*count;
                    // 게임 보드를 넘어서는 경우 넘어서기 전의 좌표를 가져옵니다.
                    if(nr < 0 || nr > 3 || nc < 0 || nc > 3) {
                        count--;
                        nr = pr+nextval[i][0]*count;
                        nc = pc+nextval[i][1]*count;
                        break;
                    }
                    // 방향키 이동 중 카드를 만나게되면 멈춥니다.
                    if(board[nr][nc] != 0) break;
                    count++;
                }
                
                if(!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new Node(nr, nc, pm+1));
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