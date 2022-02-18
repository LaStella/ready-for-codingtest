// 리틀 프렌즈 사천성
// https://programmers.co.kr/learn/courses/30/lessons/1836?language=java

import java.util.*;

class Solution {
    public String solution(int m, int n, String[] board) {
        String answer = "";
        String[][] nboard = new String[m][n];
        // 타일의 좌표를 저장할 map입니다. 입력 형식에서 같은 글자의 타일은 두 개씩만 존재하므로 map에는 한 종류의 타일에 두 좌표값을 가지게됩니다.
        HashMap<String, ArrayList<int[]>> map = new HashMap<>();
        // 타일의 글자를 저장할 set입니다. TreeSet으로 만들어 타일의 글자를 저장할때마다 알파벳순으로 정렬이 됩니다.
        TreeSet<String> set = new TreeSet<>();
        
        // board를 2차 배열로 변환합니다.
        // 타일쌍의 좌표를 map에 저장하고, 타일의 종류를 set에 저장합니다.
        for(int r = 0 ; r < m ; r++) {
            nboard[r] = board[r].split("");
            for(int c = 0 ; c < nboard[0].length ; c++) {
                if(nboard[r][c].equals(".") || nboard[r][c].equals("*")) {
                    continue;
                }
                else {
                    // 빈칸이거나 막힌칸이 아닌 타일이라면 해당 타일의 글자와 좌표를 저장합니다.
                    ArrayList<int[]> list = map.getOrDefault(nboard[r][c], new ArrayList<>());
                    list.add(new int[] {r, c});
                    set.add(nboard[r][c]);
                    map.put(nboard[r][c], list);
                }
            }
        }
        
        // 삭제할 타일이 없을 때까지 반복합니다.
        while(set.size() != 0) {
            for(String key : set) {
                if(canDelete(map.get(key), nboard)) {
                    set.remove(key);
                    deleteTile(map.get(key), nboard);
                    answer += key;
                    break;
                }
                // 타일이 남아있지만 타일을 삭제할 수 없는 상태입니다.
                else {
                    if(key.equals(set.last())) {
                        return "IMPOSSIBLE";
                    }
                }
            }
        }

        return answer;
    }

    // 주어진 글자의 타일이 삭제가 가능한지 확인하는 함수입니다.
    public boolean canDelete(ArrayList<int[]> list, String[][] nboard) {
        // 두 타일의 행(row)과 열(column)을 저장합니다.
        // 처음 타일의 좌표를 저장할때 행(row)은 0에서부터 시작하므로 항상 r1 <= r2 가 됩니다.
        int r1 = list.get(0)[0];
        int c1 = list.get(0)[1];
        int r2 = list.get(1)[0];
        int c2 = list.get(1)[1];
        
        // 두 타일을 잇는 경로는 두 가지입니다.
        // 열(column)은 c1을 기준으로 행(row)방향 직선이동이 가능하고 행(row)은 r2를 기준으로 열(column)방향 직선이동이 가능한 경우
        if(canMoveRow(r1, r2, c1, nboard, nboard[r1][c1]) && canMoveCol(c1, c2, r2, nboard, nboard[r1][c1])) {
            return true;
        }
        // 열(column)은 c2을 기준으로 행(row)방향 직선이동이 가능하고 행(row)은 r1를 기준으로 열(column)방향 직선이동이 가능한 경우
        else if(canMoveRow(r1, r2, c2, nboard, nboard[r1][c1]) && canMoveCol(c1, c2, r1, nboard, nboard[r1][c1])) {
            return true;
        }
        // 위의 두 경우가 가능하지 않다면 삭제가 불가능합니다.
        else {
            return false;
        }
    }
    
    // 행(row)방향으로 이동이 가능한지 확인하는 함수입니다.
    public boolean canMoveRow(int r1, int r2, int c, String[][] nboard, String key) {
        for(int i = r1 ; i <= r2 ; i++) {
            // 빈 공간이거나 key와 같은 경우는 이동이 가능하지만 그 외에는 이동이 불가능합니다.
            if(nboard[i][c].equals(".") || nboard[i][c].equals(key)) {
                continue;
            }
            else {
                return false;
            }
        }
        
        return true;
    }
    
    // 열(column)방향으로 이동이 가능한지 확인하는 함수입니다.
    public boolean canMoveCol(int c1, int c2, int r1, String[][] nboard, String key) {
        if(c1 > c2) {
            for(int i = c2 ; i <= c1 ; i++) {
                if(nboard[r1][i].equals(".") || nboard[r1][i].equals(key)) {
                    continue;
                }
                else {
                    return false;
                }
            }
        }
        else {
            for(int i = c1 ; i <= c2 ; i++) {
                if(nboard[r1][i].equals(".") || nboard[r1][i].equals(key)) {
                    continue;
                }
                else {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    // 타일을 삭제하는 함수입니다.
    public String[][] deleteTile(ArrayList<int[]> list, String[][] nboard) {
        nboard[list.get(0)[0]][list.get(0)[1]] = ".";
        nboard[list.get(1)[0]][list.get(1)[1]] = ".";
        
        return nboard;
    }
}

/*
DBA
C*A
CDB

A B C   C B A
D * D   D * D
B C A   A C B
1. A->C->A
2. A->B->A

*/