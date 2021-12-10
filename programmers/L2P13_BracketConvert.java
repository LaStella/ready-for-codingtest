// 괄호 변환
// https://programmers.co.kr/learn/courses/30/lessons/60058?language=java

import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = "";
        answer = getCorrectString(p);
        return answer;
    }
    
    // 올바른 괄호 문자열인지 확인하는 함수입니다.
    public boolean checkCorrectness(String s) {
        Stack<Character> stack = new Stack<>();
        // stack에 문자열s의 각 문자를 넣으면서 '(' 와 ')' 가 만나면 pop하여 제거하였습니다.
        for(int i = 0 ; i < s.length() ; i++) {
            if(!stack.empty() && stack.peek() == '(' && s.charAt(i) == ')') {
                stack.pop();
            }
            else {
                stack.push(s.charAt(i));
            }
        }
        // 만약 stack이 비어있지 않다면 올바른 괄호 문자열이 아닙니다.
        if(!stack.empty()) {
            return false;
        }
        return true;
    }
    
    // 분리된 두 문자열을 만드는 함수입니다.
    public String[] getSplitedString(String s) {
        String u = "";
        String v = "";
        int count = 0;
        
        for(int i = 0 ; i < s.length() ; i++) {
            if(s.charAt(i) == '(') {
                u += String.valueOf(s.charAt(i));
                count++;
            }
            else {
                u += String.valueOf(s.charAt(i));
                count--;
            }
            if(count == 0) {
                v = s.substring(i+1, s.length());
                break;
            }
        }
        return new String[] {u, v};
    }
    
    // 문제에서 주어진 4-4과정을 하는 함수입니다.
    // 문자열의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집습니다.
    public String getFixedString(String s) {
        String result = "";
        
        // 길이가 2인 경우 첫 번째와 마지막 문자를 제거한 빈 문자열을 return합니다.
        if(s.length() != 2) {
            for(int i = 1 ; i < s.length()-1 ; i++) {
                if(s.charAt(i) == '(') {
                    result += ")";
                }
                else {
                    result += "(";
                }
            }
        } 
        
        return result;
    }
    
    
    // 올바른 괄호 문자열을 만드는 함수입니다.
    public String getCorrectString(String s) {
        // 올바른 괄호 문자열이면 s를 그대로 반환합니다.(빈 문자열도 올바른 괄호 문자열)
        if(checkCorrectness(s)) {
            return s;
        }
        else {
            // 문자열을 두 균형잡힌 괄호 문자열 u, v로 분리합니다.
            String[] splitedString = getSplitedString(s);
            // splitedString[0]을 바로 써도 되지만 직관적이지 못하여 u와 v변수를 만들어 저장하였습니다.
            String u = splitedString[0];
            String v = splitedString[1];
            if(checkCorrectness(u)) {
                return u+getCorrectString(v);
            }
            else {
                return "("+getCorrectString(v)+")"+getFixedString(u);
            }
        }
    }
}