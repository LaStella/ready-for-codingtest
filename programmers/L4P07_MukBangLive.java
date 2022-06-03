// 무지의 먹방 라이브
// https://programmers.co.kr/learn/courses/30/lessons/42891?language=java

import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        int answer = 0;
        
        // 남은 음식의 양에 따라 우선순위가 결정되는 큐입니다.
        PriorityQueue<Food> q = new PriorityQueue<>();
        
        long sum = 0;
        for(int i = 0 ; i < food_times.length ; i++) {
            q.add(new Food(i+1, food_times[i]));
            sum += food_times[i];
        }
        
        // 남은 음식의 총량이 k보다 작다면 모든 음식을 먹어 더 섭취해야할 음식이 없습니다.
        if(sum <= k) {
            return answer = -1;
        }
        
        // 이전 음식의 양을 저장합니다.
        int previous_food = 0;
        // 음식의 양이 적은 순서대로 제거합니다.
        // 한 음식이 제거되기 위해서는 해당 음식만큼 모든 음식을 먹어야합니다.
        // 따라서, (음식의 양 * 남은 음식의 수)가 해당 음식을 먹는데 걸리는 총 시간이 됩니다. (음식의 양 * 남은 음식의 수는 int형으로 하면 오버플로우가 될 수 있습니다)
        // 다음으로 먹을 음식은 이전에 먹은 음식의 양만큼 뺀 나머지를 먹게됩니다. (음식의 양-이전 음식의 양)
        while(k-(long)(q.peek().value-previous_food)*q.size() > 0) {
            k -= (long)(q.peek().value-previous_food)*q.size();
            previous_food = q.poll().value;
        }
        
        // 남은 음식을 모두 리스트에 저장합니다.
        List<Food> food_list = new ArrayList<>();
        while(!q.isEmpty()) {
            food_list.add(q.poll());
        }
        
        // 리스트를 음식의 index 순으로 정렬합니다.
        Collections.sort(food_list, (o1, o2) -> o1.index - o2.index);
        
        // 남은 시간 k를 남은 음식의 수로 나눈 나머지에 해당하는 순서의 음식의 index가 k초 후에 먹어야할 음식이 됩니다.
        answer = food_list.get((int)(k%food_list.size())).index;
        
        return answer;
    }
    
    // 음식을 나타내는 객체입니다.
    // 우선순위큐를 사용하기위해 Comparable 인터페이스에 상속받습니다.
    class Food implements Comparable<Food>{
        int index;
        int value;
        public Food(int index, int value) {
            this.index = index;
            this.value = value;
        }
        
        @Override
        public int compareTo(Food other) {
            return this.value-other.value;
        }
    }
}

/*
남은 음식이 적은 것부터 k의 값을 줄여나가면 됩니다.
ex. 남은 음식 [1003, 1001, 1002, 1004], k=4001이라 할때 정답은 2입니다.
1001짜리 음식을 다 먹게되면 k에서 1001*(남은 음식의 길이)를 빼줍니다.
4001-1001*4 = -3, 음수가 나오므로 1001짜리 음식은 다 먹지 못합니다.
즉, k=0이 될때까지 모든 음식이 남아있으므로 k%(남은 음식의 길이)의 값 1, 문제에서 result는 1번부터 시작하므로
1+1=2가 나오게됩니다.

k=5000일 경우
5000-1001*4 = 996
다음으로 적게 남은 음식인 1002를 확인합니다.
996-1002*3 = 음수
따라서 남은 음식은 1003, 1002, 1004가 되며, 996%3 = 0
0+1번째 가 result가 됩니다.
*/