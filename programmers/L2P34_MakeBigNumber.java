// 큰 수 만들기
// https://programmers.co.kr/learn/courses/30/lessons/42883?language=java#

import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        Stack<Character> stack = new Stack<>();
        
        // number 문자열을 하나씩 stack의 값과 비교하며 stack에 넣습니다.
        for(int i = 0 ; i < number.length() ; i++) {
            Character c = number.charAt(i);
            // stack이 비어있지 않고 k 개의 수를 제거하지 않았다면
            while(!stack.isEmpty() && k > 0) {
                // stack의 가장 위에 있는 값보다 작거나 같을때 까지 stack에 담겨있는 수를 제거합니다.
                if(stack.peek() < c) {
                    stack.pop();
                    k--;
                }
                else {
                    break;
                }
            }
            // stack에 현재위치의 수를 넣습니다.
            stack.push(c);
        }
        
        // number의 모든 수를 조건에 따라 stack에 넣었으나 k개의 수가 제거되지 않았다면
        // 남은 k개의 수를 제거해줍니다.
        while(k > 0) {
            stack.pop();
            k--;
        }
        
        answer = stack.toString().replaceAll(", ", "").substring(1, stack.size()+1);
        
        return answer;
    }
}
// ex) 1924
// i = 0 stack = [1]
// i = 1 stack = [9]
// i = 2 stack = [9, 2]
// i = 3 stack = [9, 4]




/*
import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";  
        // System.out.println(getComb(number, k, 0, ""));
        
        for(int i = 0 ; i < k ; i++) {
            for(int j = 0 ; j < number.length() ; j++) {
                if(j == number.length()-1) {
                    answer = number.substring(0, number.length()-(k-i));
                    return answer;
                }
                
                if(number.charAt(j) == '9') {
                    continue;
                }
                
                int now = Integer.parseInt(number.substring(j, j+1));
                int next = Integer.parseInt(number.substring(j+1, j+2));

                if(now < next) {
                    number = number.substring(0, j)+number.substring(j+1, number.length());
                    break;
                }
            }
        }
        answer = number;
        
        return answer;
    }
}
*/

/* DFS를 이용하여 조합을 구하는 방식으로 구현한 코드입니다.
class Solution {
    public String solution(String number, int k) {
        String answer = "";  
        // System.out.println(getComb(number, k, 0, ""));
        answer = Integer.toString(getComb(number, k, 0, ""));
        return answer;
    }
    
    public int getComb(String number, int k, int depth, String result) {
        if(result.length() == number.length()-k) {
            return Integer.parseInt(result);
        }
        else if(depth == number.length()) {
            return 0;
        }
        else {
            int a = getComb(number, k, depth+1, result);
            int b = getComb(number, k, depth+1, result+number.substring(depth, depth+1));
            return Math.max(a, b);
        }
        
    }
}
*/