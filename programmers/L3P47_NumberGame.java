// 숫자 게임
// https://programmers.co.kr/learn/courses/30/lessons/12987?language=java

import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        A = Arrays.stream(A).boxed().sorted(Collections.reverseOrder()).mapToInt(i->i).toArray();
        B = Arrays.stream(B).boxed().sorted(Collections.reverseOrder()).mapToInt(i->i).toArray();
        
        for(int i = 0 ; i < A.length ; i++) {
            if(A[i] < B[i]) {
                answer++;
            }
        }
        
        return answer;
    }
}