// 단어 퍼즐
// https://programmers.co.kr/learn/courses/30/lessons/12983?language=java

import java.util.*;

class Solution {
    public int solution(String[] strs, String t) {
        // 초기값은 제한사항에 따라 결과로 나올 수 없는 101로 합니다. (단어 조각은 최대 100개이므로)
        int answer = 101;
        
        int[] dp = new int[20000];
        
        // t의 첫번째 문자부터 최소 조각 수를 계산합니다.
        for(int i = 0 ; i < t.length() ; i++) {
            // i번째에 해당하는 문자를 가져옵니다.
            char c = t.charAt(i);
            
            for(int j = 0 ; j < strs.length ; j++) {
                // 조각의 마지막 문자가 c와 같으면 조각을 넣을 수 있습니다.
                // 
                if(strs[j].charAt(strs[j].length()-1) == c) {
                    // 조각 하나로 완성이 되는 경우
                    if(i+1-strs[j].length() == 0) {
                        dp[i] = 1;
                    }
                    // 조각의 길이가 i번째 문자까지의 문자열보다 긴 경우 조각을 사용할 수 없습니다.
                    // ex. t="apple", i=1, c='p'일때, "app"는 길이3, i번째 까지의 문자열은 길이2(초과)
                    else if(i+1-strs[j].length() < 0) {
                        
                    }
                    
                    
                }
            }
        }
        
        // 초기값 그대로라면 주어진 단어를 조각들을 이용하여 문장을 완성할 수 없음을 의미합니다.
        return answer;
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
dp[0] = b = invalid (b로끝나는 문자가없음)
dp[1] = b+a or ba = dp[0]+1 or 1 (a로 끝나는 문자는 a, ba 두 개)
dp[2] = ban = ba+n = 2 (n으로 끝나는 문자는 n 한 개)
dp[3] = bana = ban+a or ba+na = dp[2]+1 or dp[1]+1 최소값 2 (a로 끝나는 문자는 na, a 두 개, 각 길이만큼 뺀 dp값과 더하여 비교)
dp[4] = banan = bana+n 

*/