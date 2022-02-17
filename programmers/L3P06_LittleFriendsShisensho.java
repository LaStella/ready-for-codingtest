// 리틀 프렌즈 사천성
// https://programmers.co.kr/learn/courses/30/lessons/1836?language=java

class Solution {
    public String solution(int m, int n, String[] board) {
        String answer = "";
        boolean[][] deleted = new boolean[m][n];
        String[][] nboard = new String[m][n];
        int totalcount = 0;
        for(int i = 0 ; i < m ; i++) {
            nboard[i] = board[i].split("");
            // System.out.println(board[i].replaceAll("[.]", "").replaceAll("[*]",""));
            totalcount += board[i].replaceAll("[.]", "").replaceAll("[*]","").length();
        }
        System.out.println(totalcount);
        // printa(nboard);
        
        
        
        return answer;
    }
    
    public void dfs(String[][] nboard, boolean[][] deleted, int count, String result, int totalcount) {
        if(count == totalcount/2) {
            
        }
        else {
            for(int i = 0 ; i < nboard.length ; i++) {
                for(int j = 0 ; j < nboard[0].length ; j++) {
                    
                }
            }
        }
    }
    
    
    public void printa(String[][] nboard) {
        for(int i = 0 ; i < nboard.length ; i++) {
            for(int j = 0 ; j< nboard[0].length ; j++) {
                System.out.print(nboard[i][j]+" ");
            }
            System.out.println();
        }
    }
}




/*
DBA
C*A
CDB
D > D로 가는 경로는 두가지
오른 > 아래방향, 아래 > 오른방향의 경로





*/