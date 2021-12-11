// 뉴스 클러스터링
// https://programmers.co.kr/learn/courses/30/lessons/17677?language=java

import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        answer = J(getMultiset(str1),getMultiset(str2));
        
        return answer;
    }
    
    // 다중집합을 만드는 함수입니다.
    public ArrayList getMultiset(String s) {
        ArrayList<String> list = new ArrayList<>();
        
        for(int i = 1 ; i < s.length() ; i++) {
            // 연속된 두 글자가 영문자인 경우에는 글자 쌍을 저장합니다.
            if(Character.isLetter(s.charAt(i-1)) && Character.isLetter(s.charAt(i))) {
                list.add(s.substring(i-1,i+1));
            }
        }
        
        return list;
    }
    
    // 자카드 유사도를 구하는 함수입니다.
    public int J(ArrayList a, ArrayList b) {
        ArrayList<String> list1 = a;
        ArrayList<String> list2 = b;
        // 교집합을 저장하는 ArrayList
        ArrayList<String> intersection = new ArrayList<>();
        
        // 문제에서 집합 A와 집합 B 모두 공집합일 경우에는 J(A, B) = 1로 정의하므로
        // 1*65536 값을 return합니다.
        if(list1.size() == 0 && list2.size() == 0) {
            return 65536;
        }
        
        // 교집합을 구하는 과정으로 집합 A(list1)의 각 원소들을 집합 B(list2)와 비교하며 같은 경우 각 집합에서 해당 원소를 제거합니다.
        // 제거된 원소는 intersection 에 저장됩니다.
        for(Iterator<String> list1It = list1.iterator(); list1It.hasNext();) {
            String str = list1It.next();
            for(Iterator<String> list2It = list2.iterator(); list2It.hasNext();) {
                String str2 = list2It.next();
                if(str.equals(str2)) {
                    list1It.remove();
                    list2It.remove();
                    intersection.add(str);
                    break;
                }
            }
        }
        
        // 위의 과정을 거치게 되면 교집합의 원소가 저장된 intersection과 교집합 원소가 제거된 집합 A(list1), 집합 B(list2)가 됩니다.
        // 자카드 유사도 = 교집합의 원소 개수 / 합집합의 원소 개수
        // 교집합의 원소 개수 = intersection.size()
        // 합집합의 원소 개수 = intersection.size() + list1.size() + list2.size()
        double jaccardSimilarity = (double)intersection.size()/(intersection.size()+list1.size()+list2.size());

        return (int)(jaccardSimilarity*65536);
    }
}