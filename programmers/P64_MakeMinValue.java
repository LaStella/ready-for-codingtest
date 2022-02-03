// 최솟값 만들기
// https://programmers.co.kr/learn/courses/30/lessons/12941?language=java

import java.util.*;

class Solution{
    public int solution(int []A, int []B){
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        // 두 배열을 정렬하여 한쪽 배열의 최소값과 반대쪽 배열의 최대값순서로 곱하여 더합니다.
        for(int i = 0 ; i < A.length ; i++) {
            answer += A[i]*B[A.length-1-i];
        }
        

        return answer;
    }
}