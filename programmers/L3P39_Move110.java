// 110 옮기기
// https://programmers.co.kr/learn/courses/30/lessons/77886?language=java

import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        
        for(int i = 0 ; i < s.length ; i++) {
            String x = s[i];
            
            // 만들어질 수 있는 모든 110의 개수를 셉니다.
            // 연속하는 1의 개수를 세며, 0과 연속하는 1이 2개 이상일 경우 110이 됩니다.
            // 110이 만들어지는 경우를 제외한 나머지는 스택에 저장됩니다.
            Stack<Character> stack = new Stack<>();
            int count = 0;
            int count_one = 0;
            
            for(int j = 0 ; j < x.length() ; j++) {
                if(x.charAt(j) == '1') {
                    count_one++;
                    stack.push(x.charAt(j));
                }
                else {
                    if(count_one >= 2) {
                        stack.pop();
                        stack.pop();
                        count_one -= 2;
                        count++;
                    }
                    else {
                        count_one = 0;
                        stack.push(x.charAt(j));
                    }
                }
            }
            
            // 스택에 남아있는 문자를 가져와 문자열(sb)을 만듭니다.
            StringBuilder sb = new StringBuilder();
            while(!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            sb.reverse();
            
            // 만든 문자열(sb)에 마지막으로 나오는 0의 뒤에 110을 삽입합니다. (0보다 앞에 삽입하면 사전 순으로 더 앞에오는 문자열이 아니므로)
            // 마지막으로 나오는 0의 뒤에 계속해서 110을 삽입하게 되면 결국 110을 count만큼 반복한 문자열을 삽입하게 됩니다.
            // 문자열(sb)에 0이 없다면, 문자열의 제일 앞에 110을 반복한 문자열을 삽입합니다.
            int index = sb.lastIndexOf("0");
            if(index != -1) {
                sb.insert(index+1, "110".repeat(count));
            }
            else {
                sb.insert(0, "110".repeat(count));
            }
            
            answer[i] = sb.toString();
        }
        
        return answer;
    }
}