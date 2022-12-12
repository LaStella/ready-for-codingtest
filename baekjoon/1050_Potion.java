// 물약
// https://www.acmicpc.net/problem/1050

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, String> map = new HashMap<>();
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            map.put(st.nextToken(), st.nextToken());
        }

        Queue<String> queue = new LinkedList<>();
        for (int i = 0 ; i < M ; i++) {
            queue.add(br.readLine());
        }

        map = makePotion(queue, map);

        System.out.println(map.getOrDefault("LOVE", "-1"));
    }

    // 큐에 저장된 제조법에 따라 재료를 만드는 함수입니다.
    public static Map<String, String> makePotion(Queue<String> queue, Map<String, String> map) {
        int cycleCount = 0;
        // 더이상 제조법이 없거나 제조할 수 있는 물약이 없는 경우 종료합니다.
        while(!queue.isEmpty() && queue.size() != cycleCount) {
            String recipe = queue.poll();
            String[] recipeArray = recipe.split("=");
            String recipeName = recipeArray[0];
            String[] material = recipeArray[1].split("\\+");

            String recipeCost = calcRecipeCost(material, map);

            // 제조가 불가능한 경우 제조법을 다시 큐에 넣습니다.
            if (recipeCost.equals("-1")) {
                cycleCount++;
                queue.add(recipe);
                continue;
            }

            // 이미 추가되어있는 재료라면 최소값으로 수정합니다.
//            if (map.putIfAbsent(recipeName, recipeCost) != null) {
//                map.put(recipeName, Math.min(map.get(recipeName), recipeCost));
//            }
            if (map.containsKey(recipeName)) {
                int min = Math.min(Integer.parseInt(map.get(recipeName)), Integer.parseInt(recipeCost));
                map.put(recipeName, String.valueOf(min));
            }
            else {
                map.put(recipeName, recipeCost);
            }

            // 사이클횟수를 초기화합니다.
            cycleCount = 0;
        }

        return map;
    }

    public static String calcRecipeCost(String[] material, Map<String, String> map) {
        BigInteger recipeCost = new BigInteger("0");
        for (String s : material) {
            BigInteger materialCount = new BigInteger(s.replaceAll("[^0-9]", ""));
            String materialName = s.replaceAll("[0-9]", "");
            // 찾는 재료가 없다면 계산을 중단하고 -1을 리턴합니다.
            if (!map.containsKey(materialName)) {
                recipeCost = new BigInteger("-1");
                break;
            }

            recipeCost = recipeCost.add(materialCount.multiply(new BigInteger(map.get(materialName))));
            if (recipeCost.compareTo(new BigInteger("1000000000")) > 0) return "1000000001";
        }

        return recipeCost.toString();
    }
}


/*
재료를 key로 가격을 value로 가지는 map을 생성합니다.
각 재료들을 멥에 저장합니다.
제조법을 Queue에 모두 저장합니다.
queue에서 제조법을 하나씩 꺼내며 물약을 제조합니다.
물약이 제조되는 경우 cycleCount를 0으로 초기화하며 map에 새로 제도된 물약과 가격을 저장합니다.
물약이 제조될 수 없는 경우 제조법을 다시 queue에 넣고 cycleCount를 1 증가시킵니다.

문제발생 >
A 1
B 1
AA=1A+1A
BB=1B+1B
LOVE=1AA+1BB
BB=1A
위와 같은 경우 LOVE를 제조한 이후 BB를 더 싸게 제조가 가능하여 LOVE를 더 싸게 제조할 수 있으나 확인하지 못하는 문제가 발생

 */
