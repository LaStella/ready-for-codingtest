// 올바른 괄호
// https://programmers.co.kr/learn/courses/30/lessons/12909?language=java

import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> stack = new Stack<>();
        
        // 올바른 괄호라면 짝이 맞아야 하므로 길이가 짝수인지 확인합니다.
        if(s.length() % 2 != 0) {
            answer = false;
        }
        else {
            for(int i = 0 ; i < s.length() ; i++) {
                char p = s.charAt(i);
                // p가 열린 괄호면 stack에 넣으며 stack의 크기가 남은 문자열보다 크다면 괄호짝이 맞지 않으므로 반복문을 종료합니다.
                if(p == '(') {
                    stack.push(p);
                    if(stack.size() > s.length()-1-i) {
                        answer = false;
                        break;
                    }
                }
                // p가 닫힌 괄호면 stack에서 열린 괄호를 하나씩 제거합니다. stack이 비어있다면 닫힌괄호로 시작되므로 올바른 괄호가 될 수 없으므로 반복문을 종료합니다.
                else {
                    if(!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                    }
                    else {
                        answer = false;
                        break;
                    }
                }
            }
        }

        return answer;
    }
}