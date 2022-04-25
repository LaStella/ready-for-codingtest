// 스티커 모으기
// https://programmers.co.kr/learn/courses/30/lessons/12971?language=java

import java.util.*;

class Solution {
    int N;
    int answer;
    public int solution(int sticker[]) {
        answer = 0;
        N = sticker.length;
        boolean[] visited = new boolean[sticker.length];
        // 뜯어내거나 찢어진 스티커가 저장되는 set입니다.
        HashSet<Integer> set = new HashSet<>();
        
        dfs(0, set, sticker);
        
        return answer;
    }
    
    public void dfs(int result, HashSet<Integer> set, int[] sticker) {
        // 모든 스티커가 뜯어지거나 찢어졌다면 결과를 비교합니다.
        if(set.size() == N) {
            answer = Math.max(answer, result);
        }
        else {
            for(int i = 0 ; i < sticker.length ; i++) {
                // 뜯거나 찢어지지 않은 스티커는 뜯습니다.
                if(!set.contains(i)) {
                    // HashSet<Integer> set_clone = (HashSet)set.clone();
                    HashSet<Integer> set_clone = new HashSet<>(set);
                    // 뜯어낸 스티커와 양쪽으로 인접한 스티커를 저장합니다.
                    set_clone.add(i);
                    set_clone.add((i-1+N)%N);
                    set_clone.add((i+1)%N);
                    dfs(result+sticker[i], set_clone, sticker);
                }
            }
        }
    }
}