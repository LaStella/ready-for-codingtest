// 스티커 모으기
// https://programmers.co.kr/learn/courses/30/lessons/12971?language=java

import java.util.*;

class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        int N = sticker.length;
        
        int[] max = new int[N];
        int[] n_max = new int[N];
        
        if(N == 1) return sticker[0];
        
        // 첫번째 스티커를 뜯으므로 max[1]은 sticker[1]를 뜯을 수 없기 때문에 max[0]의 값이 됩니다.
        max[0] = sticker[0];
        max[1] = max[0];
        // 첫번째 스티커와 마지막 스티커는 같이 뜯을 수 없으므로 N-2까지 반복합니다.
        for(int i = 2 ; i < N-1 ; i++) {
            max[i] = Math.max(max[i-1], max[i-2]+sticker[i]);
        }
        
        // 첫번째 스티커를 뜯지 않으므로 max[0]은 0이며, max[1]은 sticker[1]을 뜯을 수 있기 때문에 max[1]의 값이 됩니다.
        n_max[0] = 0;
        n_max[1] = sticker[1];
        // 마지막 스티커를 뜯을 수 있으므로 N-1까지 반복합니다.
        for(int i = 2 ; i < N ; i++) {
            n_max[i] = Math.max(n_max[i-1], n_max[i-2]+sticker[i]);
        }
        
        answer = Math.max(max[N-2], n_max[N-1]);
        
        return answer;
    }
}

/*
첫번째 스티커를 뜯는 경우와 그렇지 않은 경우로 나누어서 계산
max[i]는 i번째 스티커에서의 최대값이라고 한다면 max[i] 는 i번째 스티커를 뜯지 않는 경우(max[i-1])와 i번째 스티커를 뜯는 경우(max[i-2]+sticker[i]) 중 더 큰값을 가지게 된다.
*/