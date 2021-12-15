// 전화번호 목록
// https://programmers.co.kr/learn/courses/30/lessons/42577?language=java


import  java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        HashSet<String> set = new HashSet<>();
        
        // 전화번호를 HashSet에 저장합니다.
        for(String s : phone_book) {
            set.add(s);
        }
        
        // 각 전화번호를 첫번째부터 마지막전까지 잘라 set에 저장되어있는지 확인합니다.
        // ex) 119는 1, 11 로 잘라 set에서 저장되어있는지 확인
        for(int i = 0 ; i < phone_book.length ; i++) {
            for(int j = 0 ; j < phone_book[i].length() ; j++) {
                if(set.contains(phone_book[i].substring(0, j))) {
                    answer = false;
                    break;
                }
            }
            
            if(!answer) {
                break;
            }
        }
        
        return answer;
    }
}