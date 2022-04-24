// 숫자 게임
// https://programmers.co.kr/learn/courses/30/lessons/12987?language=java

import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int a_index = 0;
        int b_index = 0;

        // 오름차순 정렬된 두 배열을 비교합니다.
        // B의 값이 큰 경우 승점을 추가하며, 두 배열을 가리키는 index를 하나씩 늘립니다.
        // B의 값이 작거나 같다면, 더 큰 값을 비교하도록 배열B를 가리키는 index만 늘립니다.
        // 모든 배열B의 원소를 사용하였다면 더 이상 승점을 얻을 수 없으므로 종료합니다.
        while(b_index < B.length) {
            if(A[a_index] < B[b_index]) {
                a_index++;
                b_index++;
                answer++;
            }
            else {
                b_index++;
            }
        }
        
        return answer;
    }
}