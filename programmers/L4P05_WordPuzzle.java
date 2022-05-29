// 단어 퍼즐
// https://programmers.co.kr/learn/courses/30/lessons/12983?language=java

import java.util.*;

class Solution {
    public int solution(String[] strs, String t) {
        int answer = -1;
        
        // dp[i]는 0부터 i번째 문자열까지 완성할 수 있는 최소 조각 개수를 의미합니다.
        int[] dp = new int[t.length()];
        // t를 완성하는 조각의 최소 개수는 t의 길이보다 클 수 없습니다.
        Arrays.fill(dp, t.length()+1);
        
        // t의 첫번째 문자부터 최소 조각 수를 계산합니다.
        for(int i = 0 ; i < t.length() ; i++) {
            // t의 부분 문자열을 가져옵니다.
            String substr = t.substring(0, i+1);
            
            for(int j = 0 ; j < strs.length ; j++) {
                int str_length = strs[j].length();
                // 조각의 길이가 t의 부분 문자열(substr)보다 긴 경우 조각을 사용할 수 없습니다.
                // 부분 문자열의 길이는 i+1입니다.
                if(i+1 < str_length) {
                    continue;
                }
                
                // 사용할 수 있는 조각인지 확인합니다.
                if(canUse(substr, strs[j])) {
                    // 길이가 같으면 조각 하나로 완성되는 것을 의미합니다.
                    if(i+1-str_length == 0) {
                        dp[i] = 1;
                    }
                    // 길이가 다르다면 조각을 사용하고 남은 문자열이 존재합니다.
                    // 남은 문자열 최소 조각 사용 개수(dp[i-조각의길이])에 1을 더한 값(현재 조각을 사용하므로)과 비교하여 최소값을 저장합니다.
                    // (부분 문자열의 길이) - (조각의 길이) -1 = i+1 - str_length -1 = i-str_length, 마지막 -1은 dp배열의 값은 0부터 시작하기때문입니다.
                    else {
                        dp[i] = Math.min(dp[i], dp[i-str_length]+1);
                    }
                }
            }
        }
        answer = dp[t.length()-1];
        
        // 초기값 그대로라면 주어진 단어를 조각들을 이용하여 문장을 완성할 수 없음을 의미합니다.
        return answer == t.length()+1 ? -1 : answer;
    }
    
    // 사용할 수 있는 조각인지 확인하는 함수입니다.
    // 두 문자열의 각 끝에서 부터 한 문자씩 비교합니다.
    public boolean canUse(String t, String str) {
        int t_length = t.length()-1;
        int str_length = str.length()-1;
        for(int i = 0 ; i <= str_length ; i++) {
            if(t.charAt(t_length-i) != str.charAt(str_length-i)) {
                return false;
            }
        }
        
        return true;
    }
}

/*
1.
단어를 쪼개어 확인하는 방법
주어진 단어 t의 길이가 n이라 할때
front 0~mid
back mid+1~end
front 단어 조각에 들어있으면 분리 가능하며 없다면 분리 불가능
시간초과
-----------------------------------------
2.
단어 조각 목록을 기준으로 조합을 구함
단어 조각이 긴것부터 사용하는 것이 유리
["app","ap","p","l","e","ple","pp"]
apple
app,l,e보다 ap,ple가 적은 조각을 사용
app를 선택한 경우와 선택하지 않은 경우
ple를 선택한 경우와 선택하지 않은 경우 ...
반례 존재 nabnabn
-----------------------------------------
3.
1번 과정을 DP로 접근
["ba","na","n","a"]
banana
dp[0] = b = invalid (알맞는 문자가 없음)
dp[1] = b+a or ba = dp[0]+1 or 1 (a, ba 가능, 끝에서부터 한문자씩 비교하여 가능한지 확인)
dp[2] = ban = ba+n = 2 (n 가능)
dp[3] = bana = ban+a or ba+na = dp[2]+1 or dp[1]+1 최소값 2 (na, a 가능, 각 길이만큼 뺀 dp값과 더하여 비교)
dp[4] = banan = bana+n 

*/