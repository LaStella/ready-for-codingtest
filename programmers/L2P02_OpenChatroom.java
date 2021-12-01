import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};
        List<String> answerList = new ArrayList<>();
        HashMap<String, String> userMap = new HashMap<>(); // 유저 아이디는 고유 값이므로 HashMap의 key로 저장하기 적절하며, 닉네임은 value로 저장하면된다.
        
        for(String s : record) {
            String[] temp = s.split(" ");
            // 채팅방 입장 혹은 닉네임 변경일 경우 userMap에 추가하거나 새로운 value 값으로 업데이트한다.
            if(temp[0].equals("Enter")||temp[0].equals("Change")) {
                userMap.put(temp[1],temp[2]);
            }
        }
        
        for(String s : record) {
            String[] temp = s.split(" ");
            // 입장시와 퇴장시 유저 아이디를 통해 userMap에서 닉네임에 해당하는 value를 가져온다.
            if(temp[0].equals("Enter")) {
                answerList.add(userMap.get(temp[1])+"님이 들어왔습니다.");
            }
            else if(temp[0].equals("Leave")) {
                answerList.add(userMap.get(temp[1])+"님이 나갔습니다.");
            }
        }
        
        // ArrayList를 배열로 변경
        answer = new String[answerList.size()];
        int i = 0;
        for(String s : answerList) {
            answer[i++] = s;
        }
        
        return answer;
    }
}