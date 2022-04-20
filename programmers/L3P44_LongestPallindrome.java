// 가장 긴 팰린드롬
// https://programmers.co.kr/learn/courses/30/lessons/12904?language=java#

import java.util.*;

class Solution{
    public int solution(String s) {
        int answer = 0;
        
        // 주어진 문자열에서 시작(start_index)을 정합니다.
        // 문자열의 시작(start_index)과 끝(end_index)에서 문자를 비교합니다.
        // 같은 문자라면 팰린드롬의 길이를 증가시킨 후 다음 순서에 있는 문자를 비교합니다.
        // 다른 문자라면 시작과 팰린드롬의 길이를 초기화하고 반복문을 중단합니다.
        for(int i = 0 ; i < s.length() ; i++) {
            int start_index = i;
            
            for(int j = s.length()-1 ; j >= i ; j--) {
                int end_index = j;
                int pallin_length = 0;
                
                while(end_index >= start_index) {
                    if(s.charAt(start_index) == s.charAt(end_index)) {
                        pallin_length++;
                        start_index++;
                        end_index--;
                    }
                    else {
                        start_index = i;
                        pallin_length = 0;
                        break;
                    }
                }
                
                // 팰린드롬의 길이가 홀수인 경우입니다.
                if(start_index-end_index == 2) {
                    pallin_length = pallin_length*2-1;
                }
                // 팰린드롬의 길이가 짝수인 경우입니다.
                else {
                    pallin_length = pallin_length*2;
                }
                
                answer = Math.max(answer, pallin_length);
            }
        }
        
        return answer;
    }
}

/*
문자열의 양끝에서부터 한자리씩 비교 
*/