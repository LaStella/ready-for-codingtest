// 스킬트리
// https://programmers.co.kr/learn/courses/30/lessons/49993?language=java

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = skill_trees.length;
        
        for(String s : skill_trees) {
            // 주어진 유저가 만든 스킬트리에서 선행 스킬 순서에 있는 스킬들을 제외한 나머지 스킬은 제거합니다.
            s = s.replaceAll("[^"+skill+"]", "");
            // 유저가 만든 스킬트리가 선행 스킬 순서에 맞는지 확인합니다.
            for(int i = 0 ; i < s.length() ; i++) {
                if(s.charAt(i) != skill.charAt(i)) {
                    // 맞지 않을 경우 answer를 줄여줍니다.(answer는 초기값으로 유저들의 스킬트리 개수가 저장되어있습니다.)
                    answer--;
                    break;
                }
            }
        }
        
        
        return answer;
    }
}