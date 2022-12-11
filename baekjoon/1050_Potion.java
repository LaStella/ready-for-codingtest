// 물약
// https://www.acmicpc.net/problem/1050

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            map.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        Queue<String> queue = new LinkedList<>();
        for (int i = 0 ; i < M ; i++) {
            queue.add(br.readLine());
        }

        int cycleCount = 0;
        // 더이상 제조법이 없거나 제조할 수 있는 물약이 없는 경우 종료합니다.
        while(!queue.isEmpty() && queue.size() != cycleCount) {
            String recipe = queue.poll();
            String[] recipeArray = recipe.split("=");
            String recipeName = recipeArray[0];
            String[] material = recipeArray[1].split("\\+");

            int recipeCost = 0;
            boolean isComplete = true;
            for (String s : material) {
                int materialCount = Integer.parseInt(s.replaceAll("[^0-9]", ""));
                String materialName = s.replaceAll("[0-9]", "");
                // 찾는 재료가 없다면 제조를 중단하고 제조법을 다시 큐에 넣습니다.
                if (!map.containsKey(materialName)) {
                    isComplete = false;
                    break;
                }

                recipeCost += materialCount*map.get(materialName);
            }

            cycleCount++;

            // 새로 재료가 추가되는 경우
            if (isComplete) {
                // 이미 추가되어있는 재료라면 최소값을 저장으로 수정합니다.
                if (map.putIfAbsent(recipeName, recipeCost) != null) {
                    map.put(recipeName, Math.min(map.get(recipeName), recipeCost));
                }
                // 사이클횟수를 초기화합니다.
                cycleCount = 0;
            }
        }

    }
}


/*
재료를 key로 가격을 value로 가지는 map을 생성합니다.
각 재료들을 멥에 저장합니다.
제조법을 Queue에 모두 저장합니다.
queue에서 제조법을 하나씩 꺼내며 물약을 제조합니다.
물약이 제조되는 경우 cycleCount를 0으로 초기화하며 map에 새로 제도된 물약과 가격을 저장합니다.
물약이 제조될 수 없는 경우 제조법을 다시 queue에 넣고 cycleCount를 1 증가시킵니다.

 */
