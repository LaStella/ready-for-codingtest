// n^2 배열 자르기
// https://programmers.co.kr/learn/courses/30/lessons/87390?language=java#

import java.util.*;

class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = {};
        answer = new int[(int)(right-left+1)];
        
        long startRow = left/n;
        long startCol = left%n;
        long endRow = right/n;
        long endCol = right%n;
        
        int index = 0;
        long row = startRow;
        while(row <= endRow) {
            if(row == startRow) {
                if(startRow == endRow) {
                    for(long c = startCol ; c <= endCol ; c++) {
                        if(c < row) {
                            answer[index++] = (int)(row+1);
                            // System.out.print(row+1);
                        }
                        else {
                            answer[index++] = (int)(c+1);
                            // System.out.print(c+1);
                        }
                    }
                }
                else {
                    for(long c = startCol ; c < n ; c++) {
                        if(c < row) {
                            answer[index++] = (int)(row+1);
                            // System.out.print(row+1);
                        }
                        else {
                            answer[index++] = (int)(c+1);
                            // System.out.print(c+1);
                        }
                    }
                }
                
            }
            else if(row == endRow) {
                for(long c = 0 ; c <= endCol ; c++) {
                    if(c < row) {
                        answer[index++] = (int)(row+1);
                        // System.out.print(row+1);
                    }
                    else {
                        answer[index++] = (int)(c+1);
                        // System.out.print(c+1);
                    }
                }
            }
            else {
                for(long c = 0 ; c < n ; c++) {
                    if(c < row) {
                        answer[index++] = (int)(row+1);
                        // System.out.print(row+1);
                    }
                    else {
                        answer[index++] = (int)(c+1);
                        // System.out.print(c+1);
                    }
                }    
            }
            row++;
            System.out.println();
        }
        
        return answer;
    }
}