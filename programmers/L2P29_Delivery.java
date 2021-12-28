import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int[] shortest = new int[N];
        boolean[] visited = new boolean[N];
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        
        // Arrays.sort(road);
        
        // for(int[] ar : road) {
        //     for(int i : ar) {
        //         System.out.print(i);
        //     }
        //     System.out.println();    
        // }
        
        answer = dijkstra(visited, shortest, road, K);
        
        // System.out.println("shortest");
        // for(int i : shortest) {
        //     System.out.println(i);
        // }
        

        return answer;
    }
    
    public int dijkstra(boolean[] visited, int[] shortest, int[][] road, int K) {
        int answer = 0;
        Arrays.fill(shortest, 2001);
        shortest[0] = 0;
        
        for(int i = 0 ; i < visited.length ; i++) {
            if(i == 0) {
                visited[i] = true;
                for(int[] arr : road) {
                    if(arr[0] == i+1) {
                        shortest[arr[1]-1] = arr[2];
                    }
                }
            }
            
            else {
                int minIndex = getMinIndex(visited, shortest);
                visited[minIndex] = true;
                // System.out.println("test");
                for(int[] arr : road) {
                    if(arr[0]-1 == minIndex) {
                        // System.out.println(arr[0]+""+arr[1]+""+arr[2]);
                        if(shortest[arr[1]-1] > shortest[minIndex]+arr[2]) {
                            shortest[arr[1]-1] = shortest[minIndex]+arr[2];
                        }
                    }
                    else if(arr[1]-1 == minIndex) {
                        if(shortest[arr[0]-1] > shortest[minIndex]+arr[2]) {
                            shortest[arr[0]-1] = shortest[minIndex]+arr[2];
                        }
                    }
                }
            }
            // pshort(shortest);
        }
        
        for(int i : shortest) {
            if(i <= K) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public int getMinIndex(boolean[] visited, int[] shortest) {
        int min = 2001;
        int index = 0;
        
        for(int i = 0 ; i < visited.length ; i++) {
            if(!visited[i] && shortest[i] <= min) {
                // System.out.println("min : "+shortest[i]+"index : "+i);
                min = shortest[i];
                index = i;
                // System.out.println("!");
            }
            // System.out.println("min : "+min+"index : "+index);
        }
        
        return index;
    }
    
    public void pshort(int[] shortest) {
        System.out.println("----shortest----");
        for(int i : shortest) {
            System.out.print(i+"  ");
        }
        System.out.println();
    }
}