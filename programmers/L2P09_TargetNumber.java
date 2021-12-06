class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        answer = answerCount(numbers, target, 0);
        return answer;
    }
    
    public int answerCount(int[] numbers, int target, int index) {
        int sum = 0;
        int count = 0;
        
        // 배열의 끝까지 더하거나 빼지 않았을 때
        if(numbers.length != index) {
            // numbers[index]에 +, 다음 원소로 이동
            count += answerCount(numbers, target, index+1);
            // numbers[index]에 -. 다음 원소로 이동
            numbers[index] *= -1;
            count += answerCount(numbers, target, index+1);    
        }
        // 배열의 끝까지 더하거나 빼기를 다 했을 때
        else {
            for(int i : numbers) {
                sum += i;
            }
            if(sum == target) {
                count++;
            }
        }
        
        return count;
    }
}