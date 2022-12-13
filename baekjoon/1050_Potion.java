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

        // 재료의 이름과 비용을 저장합니다.
        Map<String, String> costMap = new HashMap<>();
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            costMap.put(st.nextToken(), st.nextToken());
        }

        // 주어진 레시피를 저장할 리스트입니다.
        List<String> recipeList = new ArrayList<>();
        // 제조하려는 레시피를 저장하는 큐입니다.
        Queue<String> queue = new LinkedList<>();
        
        for (int i = 0 ; i < M ; i++) {
            String recipe = br.readLine();
            recipeList.add(recipe);
            queue.add(recipe);
        }
        
        costMap = makePotion(queue, costMap, recipeList);

        // LOVE를 제조하는 비용을 출력합니다.
        // LOVE가 없다면 제조할 수 없으므로 -1을 출력합니다.
        System.out.println(costMap.getOrDefault("LOVE", "-1"));
    }

    // 큐에 저장된 제조법에 따라 재료를 만드는 함수입니다.
    public static Map<String, String> makePotion(Queue<String> queue, Map<String, String> costMap, List<String> recipeList) {
        int cycleCount = 0;
        // 더이상 제조법이 없거나 제조할 수 있는 물약이 없는 경우 종료합니다.
        while(!queue.isEmpty() && queue.size() != cycleCount) {
            String recipe = queue.poll();
            String[] recipeArray = recipe.split("=");
            String name = recipeArray[0];
            String[] components = recipeArray[1].split("\\+");

            String cost = calcCost(components, costMap);

            // 제조가 불가능한 경우 제조법을 다시 큐에 넣습니다.
            if (cost.equals("-1")) {
                cycleCount++;
                queue.add(recipe);
                continue;
            }

            // 이미 추가되어있는 재료라면 최소값으로 수정합니다.
            if (costMap.putIfAbsent(name, cost) != null) {
                if (Integer.parseInt(cost) < Integer.parseInt(costMap.get(name))) {
                    costMap.put(name, cost);
                    // 최소값으로 수정시 레시피 중 해당 재료가 포함된 모든 레시피를 다시 큐에 넣어 비용을 계산합니다.
                    for (String recipeWithComponent : findAllRecipeWithComponent(recipeList, name)) {
                        queue.add(recipeWithComponent);
                    }
                }
            }

            // 사이클횟수를 초기화합니다.
            cycleCount = 0;
        }

        return costMap;
    }

    // name재료가 들어가는 모든 레시피를 찾아 리턴하는 함수입니다.
    private static List<String> findAllRecipeWithComponent(List<String> recipeList, String name) {
        List<String> list = new ArrayList<>();
        for (String recipe : recipeList) {
            if (recipe.contains(name)) {
                list.add(recipe);
            }
        }
        return list;
    }

    // 주어지는 구성요소들로 제조하는 비용을 계산합니다.
    public static String calcCost(String[] components, Map<String, String> map) {
        BigInteger cost = new BigInteger("0");
        for (String component : components) {
            String componentName = component.replaceAll("[0-9]", "");
            // 찾는 재료가 없다면 계산을 중단하고 -1을 리턴합니다.
            if (!map.containsKey(componentName)) {
                cost = new BigInteger("-1");
                break;
            }

            BigInteger componentCost = new BigInteger(map.get(componentName));
            BigInteger componentCount = new BigInteger(component.replaceAll("[^0-9]", ""));

            cost = cost.add(componentCost.multiply(componentCount));
            if (cost.compareTo(new BigInteger("1000000000")) > 0) return "1000000001";
        }

        return cost.toString();
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

해결방법1 
    주어지는 recipe를 map으로 저장하여 처리
    문제발생 > 같은 이름의 제조법을 구분할 수 없는 문제가 발생
해결방법2
    주어지는 recipe를 List에 저장하며 costMap에 추가되는 재료가 있다면 해당 재료를 사용하는 모든 레시피를 큐에 넣습니다.    
 */
