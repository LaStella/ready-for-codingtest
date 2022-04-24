// 숫자 게임
// https://programmers.co.kr/learn/courses/30/lessons/12987?language=java

import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        A = Arrays.stream(A).boxed().sorted(Collections.reverseOrder()).mapToInt(i->i).toArray();
        B = Arrays.stream(B).boxed().sorted(Collections.reverseOrder()).mapToInt(i->i).toArray();
        
        int a_index = 0;
        int b_index = 0;

        // A와 B를 내림차순 정렬한 후 B값이 큰 경우 점수를 더합니다.
        // B값이 작은 경우 A의 다음 숫자와 비교합니다.
        while(a_index < A.length) {
            if(A[a_index] < B[b_index]) {
                a_index++;
                b_index++;
                answer++;
            }
            else {
                a_index++;
            }
        }
        
        return answer;
    }
}