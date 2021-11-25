public class P09_MakingPrimeNumber {
    class Solution {
        public int solution(int[] nums) {
            int answer = 0;
    
            for(int i = 0 ; i < nums.length ; i++) {
                for(int j = i+1 ; j < nums.length ; j++) {
                    for(int k = j+1 ; k < nums.length ; k++) {
                        if(isPrimeNumb(nums[i]+nums[j]+nums[k])){
                            answer++;
                        }
                    }
                }
            }
            return answer;
        }
        
        public boolean isPrimeNumb(int n) {
            for(int count = 2 ; count < n ; count++) {
                if(n % count == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
