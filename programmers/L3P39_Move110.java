// 110 옮기기
// https://programmers.co.kr/learn/courses/30/lessons/77886?language=java

import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        
        for(int i = 0 ; i < s.length ; i++) {
            String x = s[i];
            int count = 0;
            
            // 만들어질 수 있는 모든 110을 제거합니다.
            while(x.contains("110")) {
                count++;
                int index = x.indexOf("110");
                x = x.substring(0, index)+x.substring(index+3, x.length());
            }
            
            while(count > 0) {
                int count_one = 0;
                for(int j = 0 ; j < x.length() ; j++) {
                    if(x.charAt(j) == '1') {
                        count_one++;
                        if(count_one == 3) {
                            System.out.println(j);
                            x = x.substring(0, j-2)+"110"+x.substring(j-2, x.length());
                            count--;
                            break;
                        }
                    }
                    else {
                        count_one = 0;
                    }
                    if(j == x.length()-1) {
                        x = x.substring(0, j-(count_one-1))+"110"+x.substring(j-(count_one-1), x.length());
                        count--;
                        break;
                    }
                }
            }
            
            answer[i] = x;
        }
        
        return answer;
    }
}

/*
110을 찾아서 분리
분리된 문자열에서 앞에서부터 차례로 1을 찾음
1이 연속으로 3번 나오는 경우 해당 자리에 110을 삽입
1이 연속으로 3번 나오는 경우가 없는 경우 마지막에서 

01100111
01101100111
01100110111


010011
010011110
010011011
*/