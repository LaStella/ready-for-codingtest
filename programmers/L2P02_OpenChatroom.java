import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};
        List<String> answerList = new ArrayList<>();
        HashMap<String, String> userMap = new HashMap<>();
        
        for(String s : record) {
            String[] temp = s.split(" ");
            if(temp[0].equals("Enter")||temp[0].equals("Change")) {
                userMap.put(temp[1],temp[2]);
            }
        }
        
        for(String s : record) {
            String[] temp = s.split(" ");
            if(temp[0].equals("Enter")) {
                answerList.add(userMap.get(temp[1])+"님이 들어왔습니다.");
            }
            else if(temp[0].equals("Leave")) {
                answerList.add(userMap.get(temp[1])+"님이 나갔습니다.");
            }
        }
        
        answer = new String[answerList.size()];
        int i = 0;
        for(String s : answerList) {
            answer[i++] = s;
        }
        
        return answer;
    }
}