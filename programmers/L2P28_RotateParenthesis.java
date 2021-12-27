// 괄호 회전하기
// https://programmers.co.kr/learn/courses/30/lessons/76502?language=java

import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;

        // 주어진 문자열 s를 1칸씩 회전하며 총 s의 길이-1만큼 회전합니다.
        for(int i = 0 ; i < s.length()-1 ; i++) {
            if(isCorrect(s)) {
                answer++;
            }
            s = s.substring(1, s.length()) + s.substring(0, 1);
        }
        
        return answer;
    }
    
    // 올바른 괄호 문자열인지 확인하는 함수입니다.
    public boolean isCorrect(String s) {
        Stack<String> stack = new Stack<>();
        // 문자열 s를 한글자씩 stack에 넣으며 같은 종류의 여는 괄호와 닫는 괄호가 만나면 stack에서 제거됩니다.
        for(String str : s.split("")) {
            if(!stack.isEmpty() && isSameParenthesis(stack.peek(), str)) {
                stack.pop();
            }
            else {
                stack.push(str);
            }
        }
        // 최종적으로 stack이 비어있다면 올바른 괄호 문자열입니다.
        if(stack.isEmpty()) {
            return true;
        }
        
        return false;
    }
    
    // 같은 종류의 괄호인지 확인하는 함수입니다.
    public boolean isSameParenthesis(String begin, String close) {
        if(begin.equals("(")) {
            if(close.equals(")")) 
                return true;
            else 
                return false;
        }
        else if(begin.equals("{")) {
            if(close.equals("}")) 
                return true;
            else 
                return false;
        }
        else {
            if(close.equals("]"))
                return true;
            else 
                return false;
        }
    }
}