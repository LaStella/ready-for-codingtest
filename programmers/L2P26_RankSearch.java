import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        String[][] infoArray = new String[info.length][];
        String[][] queryArray = new String[query.length][];
        
        ArrayList<Integer> applicant = new ArrayList<>();
        HashMap<String, HashSet> map = new HashMap<>();
        // ArrayList<Integer> score = new ArrayList<>();
        // map.put("score", score);
        int[] scoree = new int[info.length];
        
        int index = 0;
        for(String s : info) {
            applicant.add(index);
            for(String t : s.split(" ")) {
                if(Character.isDigit(t.charAt(0))) {
                    scoree[index] = Integer.parseInt(t);
                    // map.get("score").add(Integer.parseInt(t));
                    continue;
                }
                if(map.containsKey(t)) {
                    map.get(t).add(index);
                }
                else {
                    HashSet<Integer> temp = new HashSet<>();
                    temp.add(index);
                    map.put(t, temp);
                }
            }
            index++;
        }
        
        // System.out.println(map);
        
        index = 0;
        for(String s : query) {
            s = s.replaceAll("and ", "");
            queryArray[index++] = s.split(" ");
        }
        
        index = 0;
        for(int i = 0 ; i < queryArray.length ; i++) {
            HashSet<Integer> list = new HashSet<>(applicant);
            
            for(int j = 0 ; j < 4 ; j++) {
                if(queryArray[i][j].equals("-")) {
                    continue;
                }
                list.retainAll(map.get(queryArray[i][j]));
            }
            
            Iterator<Integer> it = list.iterator();
            while(it.hasNext()) {
                int idx = it.next();
                // System.out.println(map.get("score").get(j));
                // System.out.println(scoree[j]);
                // System.out.println(queryArray[i][4]);
                // System.out.println("!");
                
                
                if(scoree[idx] < Integer.parseInt(queryArray[i][4])) {
                    // System.out.println(scoree[j]);
                    // System.out.println("!"+queryArray[i][4]);
                    // System.out.println(j);
                    it.remove();
                }
            }
            // System.out.println(list);
            answer[index++] = list.size();
        }
        
//         for(int i = 0 ; i < info.length ; i++) {
//             HashSet<String> set = new HashSet<>();
//             for(int j = 0 ; j < 4 ; j++) {
//                 set.add(infoArray[i][j]);
//             }
//             infoList.add(set);
//         }
        
        
        
        // for(int i = 0 ; i < queryArray.length ; i++) {
        //     int count = 0;
        //     for(int j = 0 ; j < infoArray.length ; j++) {
        //         boolean check = true;
        //         for(int k = 0 ; k < 4 ; k++) {
        //             if(queryArray[i][k].equals("-")) {
        //                 continue;
        //             }
        //             if(!infoList.get(j).contains(queryArray[i][k])) {
        //                 check = false;
        //                 break;
        //             }
        //         }
        //         if(check) {
        //             if(Integer.parseInt(queryArray[i][4]) <= Integer.parseInt(infoArray[j][4])) {
        //                 count++;    
        //             }
        //             else {
        //                 check = false;
        //             }
        //         }
        //     }
        //     answer[i] = count;
        // }
        
        // for(String[] s : infoArray) {
        //     for(String t : s) {
        //         System.out.println(t);
        //     }
        // }
        
        // for(String[] s : queryArray) {
        //     for(String t : s) {
        //         System.out.println(t);
        //     }
        // }
        
        
        
        return answer;
    }
}