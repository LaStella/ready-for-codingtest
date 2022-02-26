// 표 편집
// https://programmers.co.kr/learn/courses/30/lessons/81303?language=java#

import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        Stack<Integer> removedRow = new Stack<>();
        // 현재 표의 크기를 나타낼 변수입니다.
        int size = n;
        
        for(String c : cmd) {
            String[] c_arr = c.split(" ");
            // 선택된 행의 위치를 x만큼 빼서 위로 올려줍니다.
            if(c_arr[0].equals("U")) {
                int x = Integer.parseInt(c_arr[1]);
                k -= x;
            }
            // 선택된 행의 위치를 x만큼 더해서 아래로 내려줍니다.
            else if(c_arr[0].equals("D")) {
                int x = Integer.parseInt(c_arr[1]);
                k += x;
            }
            // 삭제된 행의 위치를 스택에 저장하고 현재의 표 크기를 줄여줍니다.
            else if(c_arr[0].equals("C")) {
                removedRow.push(k);
                size--;
                // 선택된 행이 표의 마지막 행이었다면 선택된 행의 위치를 위로 올려줍니다.
                if(k == size) {
                    k = size-1;
                }
            }
            // 삭제된 행을 복구하므로 현재의 표 크기를 늘려줍니다.
            else {
                size++;
                // 이때 복구된 행의 위치가 현재 선택된 행보다 위거나 같다면 선택된 행의 위치를 아래로 내려줍니다.
                if(k >= removedRow.pop()) {
                    k++;
                }
            }
        }
        
        // 남은 표의 크기만큼 O를 반복합니다.
        StringBuffer str = new StringBuffer("O".repeat(size));
        // 삭제된 행의 개수만큼 X를 더하며
        // 삭제된 행의 위치에 해당하는 곳에 X를 넣습니다.
        while(!removedRow.isEmpty()) {
            int index = removedRow.pop();
            str.insert(index, 'X');
        }
        answer = str.toString();

        return answer;
    }
}