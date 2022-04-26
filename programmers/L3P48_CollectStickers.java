// 스티커 모으기
// https://programmers.co.kr/learn/courses/30/lessons/12971?language=java

import java.util.*;

class Solution {
    int N;
    int answer;
    public int solution(int sticker[]) {
        answer = 0;
        N = sticker.length;
        // 뜯어내거나 찢어진 스티커가 저장되는 set입니다.
        HashSet<Integer> set = new HashSet<>();
        
        dfs(0, 0, sticker, set, "");

        return answer;
    }
    
    public void dfs(int result, int depth, int[] sticker, HashSet<Integer> set, String r) {
        // 모든 스티커가 뜯어지거나 찢어졌다면 결과를 비교합니다.
        if(depth >= N) {
            answer = Math.max(answer, result);
        }
        else {
            // 뜯거나 찢어지지 않은 스티커는 뜯습니다.
            if(!set.contains(depth)) {
                HashSet<Integer> clone_set = new HashSet<>(set);
                // 뜯어낸 스티커와 양쪽으로 인접한 찢어진 스티커를 저장합니다.
                clone_set.add(depth);
                clone_set.add((depth-1+N)%N);
                clone_set.add((depth+1)%N);
                // 스티커를 뜯어내서 양쪽 스티커는 사용할 수 없으므로 depth+2를 하여 사용할 수 없는 스티커를 건너뜁니다.
                dfs(result+sticker[depth], depth+2, sticker, clone_set, r+depth);
            }
            // 스티커를 뜯지 않고 다음 스티커로 넘어갑니다.
            dfs(result, depth+1, sticker, set, r);
        }
    }
}