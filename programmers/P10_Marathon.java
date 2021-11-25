import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class P10_Marathon {
    // import java.util.Arrays;
    // import java.util.ArrayList;
    // import java.util.Collections;

    class Solution {
        public String solution(String[] participant, String[] completion) {
            String answer = "";
            
            ArrayList<String> participantList = new ArrayList<String>(Arrays.asList(participant));
            ArrayList<String> completionList = new ArrayList<String>(Arrays.asList(completion));
            
            Collections.sort(participantList);
            Collections.sort(completionList);
            

            for(int i = 0 ; i < completionList.size() ; i++) {
                if(!completionList.get(i).equals(participantList.get(i))){
                    return answer = participantList.get(i);
                }
            }
            
            answer = participantList.get(participantList.size()-1);
            
            return answer;
        }
    }
        
}
