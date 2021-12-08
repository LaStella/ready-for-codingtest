// 행렬 테두리 회전하기
// https://programmers.co.kr/learn/courses/30/lessons/77485?language=java

import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] matrix = new int[rows][columns];
        int count = 1;
        
        // 행렬 초기화
        for(int x = 0 ; x < rows ; x++) {
            for(int y = 0 ; y < columns ; y++) {
                matrix[x][y] = count++;
            }
        }
        // answer에 사용될 count로 초기화
        count = 0;
        
        for(int[] i : queries) {
            // 회전좌표를 받아옴
            int x1 = i[0]-1;
            int y1 = i[1]-1;
            int x2 = i[2]-1;
            int y2 = i[3]-1;
            // 회전하기전 첫 번째 값을 임시로 저장
            int temp = matrix[x1][y1];
            // 회전하는 숫자들 중 가장 작은 값을 찾기 위한 TreeSet
            TreeSet<Integer> set = new TreeSet<>();
            
            // (x1, y1) ~ (x2, y1) 까지 회전 (회전하는 사각형의 왼쪽)
            for(int x = x1 ; x < x2 ; x++) {
                matrix[x][y1] = matrix[x+1][y1];
                set.add(matrix[x][y1]);
            }
            // (x2, y1) ~ (x2, y2) 까지 회전 (회전하는 사각형의 아래쪽)
            for(int y = y1 ; y < y2 ; y++) {
                matrix[x2][y] = matrix[x2][y+1];
                set.add(matrix[x2][y]);
            }
            // (x2, y2) ~ (x1, y2) 까지 회전 (회전하는 사각형의 오른쪽)
            for(int x = x2 ; x > x1 ; x--) {
                matrix[x][y2] = matrix[x-1][y2];
                set.add(matrix[x][y2]);
            }
            // (x1, y2) ~ (x1, y1+1) 까지 회전 (회전하는 사각형의 위쪽)
            // (x1, y1+1)은 첫 번째 값을 받아오므로 temp를 저장
            // 회전으로 인해 첫 번째 값 위치에 이미 회전한 숫자가 배치되었기때문
            for(int y = y2 ; y > y1+1 ; y--) {
                matrix[x1][y] = matrix[x1][y-1];
                set.add(matrix[x1][y]);
            }
            matrix[x1][y1+1] = temp;
            set.add(matrix[x1][y1+1]);
            answer[count++] = set.first();
        }
        
        return answer;
    }
}