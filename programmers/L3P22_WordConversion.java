// 단어 변환
// https://programmers.co.kr/learn/courses/30/lessons/43163?language=java

class Solution {
    int min = Integer.MAX_VALUE;
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        boolean[] visited = new boolean[words.length];
        
        dfs(begin, target, words, visited, 0);
        
        // min값이 최대값 그대로라면 타겟 단어로 바꿀 수 없는 것이므로 0을 리턴합니다.
        if(min == Integer.MAX_VALUE) {
            answer = 0;
        }
        else {
            answer = min;
        }
        
        return answer;
    }
    
    public void dfs(String begin, String target, String[] words, boolean[] visited, int depth) {
        // 타겟 단어와 일치하는 경우 변환 횟수를 비교하여 더 작은 값을 최소 변환 횟수로 저장합니다.
        if(begin.equals(target)) {
            min = Math.min(min, depth);
        }
        // words의 모든 단어를 사용하여도 타겟 단어와 일치하지 않는 경우 중단합니다.
        else if(depth == words.length) {
        }
        // words의 단어 중 하나로 변환합니다.
        else {
            for(int i = 0 ; i < words.length ; i++) {
                int count = 0;
                if(!visited[i]) {
                    // words의 단어와 begin을 비교하여 다른 글자의 개수를 셉니다.
                    for(int j = 0 ; j < words[i].length() ; j++) {
                        if(words[i].charAt(j) != begin.charAt(j)) {
                            count++;
                            // 다른 글자가 2개 이상이라면 변환할 수 없는 단어이므로 중단합니다.
                            if(count >= 2) {
                                break;
                            }
                        }
                    }
                    // 다른 글자가 1개인 단어는 변환할 수 있는 단어이므로 변환합니다.
                    if(count == 1) {
                        visited[i] = true;
                        dfs(words[i], target, words, visited, depth+1);
                        visited[i] = false;
                    }
                }
            }    
        }
    }
}

/*
begin 문자열의 길이만큼 가지수가 생성
ex) hit
*it , h*t, hi* 로 변환가능

aaa > zzz
aaa > aba > abz > acz > zcz > zzz
aaa aba abb bbb bbc bcc ccc ccd cdd ddd 


*/