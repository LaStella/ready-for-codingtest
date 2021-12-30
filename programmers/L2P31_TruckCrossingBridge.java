import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        int totalWeight = 0;

        // truck_weights를 대기 트럭을 나타내는 Queue로 변환합니다.
        Queue<Integer> truckQueue = new LinkedList<>(Arrays.stream(truck_weights).boxed().collect(Collectors.toList()));
        // 다리를 건너는 트럭을 나타내는 Truck객체 Queue입니다.
        Queue<Truck> bridgeQueue = new LinkedList<>();

        // 대기 트럭에서 하나를 뽑아 다리를 건너는 트럭에 넣습니다.
        // 현재 다리가 견디고 있는 무게에 뽑은 트럭의 무게를 더하고 시간을 늘려줍니다.
        totalWeight += truckQueue.peek();
        bridgeQueue.add(new Truck(truckQueue.poll(), 1));
        time++;
        
        // 다리를 건너는 트럭이 모두 건널 때까지 반복하게 됩니다.
        // 문제에서 모든 트럭의 무게는 다리가 견디는 무게를 초과하지 않으므로 대기 트럭은 볼 필요가 없습니다.
        while(!bridgeQueue.isEmpty()) {
            // 다리를 건너는 맨 앞의 트럭이 다리를 다 건너면 제거해줍니다.
            if(bridgeQueue.peek().distance == bridge_length) {
                totalWeight -= bridgeQueue.peek().weight;
                bridgeQueue.poll();
            }
            
            // 다리를 건너는 트럭들이 모두 이동합니다.
            for(Truck t : bridgeQueue) {
                t.distance += 1;
            }
            
            // 대기 트럭이 남아있으며 
            if(!truckQueue.isEmpty()) {
                // 대기 트럭의 무게를 더하여도 다리가 무게를 견딜 수 있다면 다리를 건너는 트럭에 추가합니다.
                if(weight >= truckQueue.peek()+totalWeight) {
                    totalWeight += truckQueue.peek();
                    bridgeQueue.add(new Truck(truckQueue.poll(), 1));
                }    
            }
            
            // 시간이 지납니다.
            time++;
        }
        
        return time;
    }
    

}

//  무게와 거리를 가지고 있는 Truck 객체입니다.
class Truck {
    int weight;
    int distance;
    public Truck(int w, int d) {
        this.weight = w;
        this.distance = d;
    }
}