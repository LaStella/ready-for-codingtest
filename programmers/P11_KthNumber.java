import java.util.Arrays;

public class P11_KthNumber {
    // import java.util.Arrays;

    class Solution {
        public int[] solution(int[] array, int[][] commands) {
            int[] answer = new int[commands.length];
            
            for(int i = 0 ; i < commands.length ; i++) {
                int[] sliceArr = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
                Arrays.sort(sliceArr);
                answer[i] = sliceArr[commands[i][2]-1];
            }
            return answer;
        }
    }
}
