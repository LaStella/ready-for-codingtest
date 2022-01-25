// 파일명 정렬
// https://programmers.co.kr/learn/courses/30/lessons/17686?language=java#

import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = {};
        
        Arrays.sort(files, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String[] s1arr = getHeadNumber(s1);
                String[] s2arr = getHeadNumber(s2);
                // 파일명의 HEAD부분은 대,소문자 구분없이 알파벳 순으로 정렬하므로, HEAD를 소문자로 바꾸어 비교합니다.
                if(s1arr[0].toLowerCase().equals(s2arr[0].toLowerCase())) {
                    // 파일명의 NUMBER부분은 parseInt메소드에 의해 숫자 앞의 0이 제거된 Integer값을 가져오므로 이를 비교합니다.
                    if(Integer.parseInt(s1arr[1]) == Integer.parseInt(s2arr[1])) {
                        // 파일명의 HEAD와 NUMBER가 같을 경우 정렬하지 않습니다.(입력된 순서 유지)
                        return 0;
                    }
                    else {
                        return Integer.parseInt(s1arr[1]) - Integer.parseInt(s2arr[1]);
                    }
                }
                return s1arr[0].toLowerCase().compareTo(s2arr[0].toLowerCase());
            }
        });
        
        answer = files;
        
        return answer;
    }
    
    // 문자열의 HEAD와 NUMBER를 분리하여 저장한 배열을 return하는 함수입니다.
    public String[] getHeadNumber(String s) {
        String[] result = new String[2];
        String head = "";
        String numb = "";

        for(int i = 0 ; i < s.length() ; i++) {
            // 문자열s를 한글자씩 비교하며 숫자가 들어오기 전까지 head에 더해지며
            // 숫자가 들어오면 숫자입력이 끝나거나 문자열이 끝에 도달할때까지 numb에 더해집니다.
            while(i < s.length() && Character.isDigit(s.charAt(i))) {
                numb += Character.toString(s.charAt(i));
                i++;
            }
            // numb를 구한경우 반복문을 중단합니다.(파일명의 TAIL은 정렬에 사용하지 않으므로 구할 필요가 없습니다.)
            if(!numb.equals("")) break;
            head += Character.toString(s.charAt(i));
        }

        result[0] = head;
        result[1] = numb;

        return result;
    }
}