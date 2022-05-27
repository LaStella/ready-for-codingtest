// 단어 퍼즐
// https://programmers.co.kr/learn/courses/30/lessons/12983?language=java

import java.util.*;

class Solution {
    int answer;
    int length;
    public int solution(String[] strs, String t) {
        // 초기값은 제한사항에 따라 결과로 나올 수 없는 101로 합니다. (단어 조각은 최대 100개이므로)
        answer = 101;
        length = t.length();
        HashSet<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        
        for(String str : strs) {
            list.add(str);
            set.add(str);
        }
        
        Collections.sort(list, (o1, o2) -> o2.length()-o1.length());
        
        System.out.println(list);
        
        // String test = "nabnabn";
        // test = test.replaceAll("n", "");
        // System.out.println(test);
        // test = test.replaceAll("abab", "");
        // System.out.println(test);
        // getMin(0, t, set);
        dfs(0, 0, t ,list);
        
        // 초기값 그대로라면 주어진 단어를 조각들을 이용하여 문장을 완성할 수 없음을 의미합니다.
        return answer == 101 ? -1 : answer;
    }
    public void dfs(int result, int depth, String t, List<String> list) {
        System.out.println(t);
        if(t.equals(" ".repeat(length))) {
            System.out.println(result);
            answer = Math.min(answer, result);
        }
        else if(depth == list.size()) {
            
        }
        else {
            String str = list.get(depth);
            if(t.contains(str)) {
                String next_t = t.replaceAll(str, " ".repeat(str.length()));
                int count = (t.length()-t.replaceAll(str, "").length())/str.length();
                dfs(result+count, depth+1, next_t, list);
            }
            dfs(result, depth+1, t, list);
        }
    }
    // 주어진 단어t를 두 단어로 쪼개어 사용하는 단어 조각 개수의 최소값을 구하는 함수입니다.
    public void getMin(int result, String t, HashSet<String> set) {
        // 빈 단어인 경우 단어 조각을 찾을 필요가 없으므로 0을 반환합니다.
        if(t.equals("")) {
            answer = Math.min(answer, result);
        }
        
        // 주어진 단어 t를 최소 1개부터 t의 길이 만큼까지 두 단어로 분리합니다. (ex. t="abc", f_word = "abc", b_word = "")
        for(int i = 1 ; i <= t.length() ; i++) {
            String f_word = t.substring(0, i);
            // 분리한 앞단어의 단어 조각이 존재하는 경우에만 뒷단어의 조각 개수를 구합니다.
            if(set.contains(f_word)) {
                String b_word = t.substring(i, t.length());
                getMin(result+1, b_word, set);
            }
        }
    }
}

/*
단어를 쪼개어 확인하는 방법
주어진 단어 t의 길이가 n이라 할때
front 0~mid
back mid+1~end
front 단어 조각에 들어있으면 분리 가능하며 없다면 분리 불가능
시간초과
-----------------------------------------
단어 조각 목록을 기준으로 조합을 구함
단어 조각이 긴것부터 사용하는 것이 유리
["app","ap","p","l","e","ple","pp"]
apple
app,l,e보다 ap,ple가 적은 조각을 사용
app를 선택한 경우와 선택하지 않은 경우
ple를 선택한 경우와 선택하지 않은 경우 ...


*/