import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        
        int[] answer1 = {1,2,3,4,5};
        int[] answer2 = {2,1,2,3,2,4,2,5};
        int[] answer3 = {3,3,1,1,2,2,4,4,5,5};
        
        int[] correctAnswer = new int[3];
        int max = 0;

        for(int i = 0 ; i < answers.length ; i++) {
            if(answers[i] == answer1[i%answer1.length]) {
                correctAnswer[0]++;
            }
            if(answers[i] == answer2[i%answer2.length]) {
                correctAnswer[1]++;
            }
            if(answers[i] == answer3[i%answer3.length]) {
                correctAnswer[2]++;
            }
        }
        
        max = Math.max(Math.max(correctAnswer[0], correctAnswer[1]), correctAnswer[2]);

        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i =0 ; i < correctAnswer.length ; i++) {
            if(max == correctAnswer[i]) {
                list.add(i+1);
            }
        }
        answer = new int[list.size()];
        
        for(int i =0; i<answer.length; i++) {
        	answer[i] = list.get(i);
        }
        
        return answer;
    }
}