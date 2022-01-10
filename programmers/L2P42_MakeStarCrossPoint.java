import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        String[] answer = {};
        ArrayList<double[]> list = new ArrayList<>();
        ArrayList<Integer> xlist = new ArrayList<>();
        ArrayList<Integer> ylist = new ArrayList<>();
        // double a = 1.2;
        // int b = 1;
        // System.out.println((int)a == a);
        int[] a = {2, -1, 4};
        int[] b = {-2, -1, 4};
        // System.out.println(getCrossing(a, b)[1]);
        // list.add(getCrossing(a, b));
        for(int i = 0 ; i < line.length ; i++) {
            for(int j = i+1 ; j < line.length ; j++) {
                double[] cross = getCrossing(line[i], line[j]);
                if(isInteger(cross)) {
                    // System.out.println("i : "+i+" j : "+j);
                    // System.out.println(cross[0]+" "+cross[1]);
                    xlist.add((int)cross[0]);
                    ylist.add((int)cross[1]);
                }
            }
        }
        int width = Collections.max(xlist)-Collections.min(xlist)+1;
        int height = Collections.max(ylist)-Collections.min(ylist)+1;
        int xmin = Math.abs(Collections.min(xlist));
        int ymax = Math.abs(Collections.max(ylist));
        
        for(int i = 0 ; i < xlist.size() ; i++) {
            xlist.set(i, xlist.get(i)+xmin);
            ylist.set(i, ylist.get(i)-ymax);
            // System.out.println(xlist.get(i)+" "+ylist.get(i));
        }
        // System.out.println(width+" "+height);
        String[][] matrix = new String[height][width];
        for(String[] m : matrix) {
            Arrays.fill(m, ".");
        }
        // System.out.println(ylist.get(0)+ymin);
        
        // matrix[ylist.get(0)+ymin][xlist.get(0)+xmin] = "*";
        
        for(int i = 0 ; i < xlist.size() ; i++) {
            matrix[Math.abs(ylist.get(i))][xlist.get(i)] = "*";
            // System.out.println(xlist.get(i)+", "+ylist.get(i));
        }
        
        // for(String[] m : matrix) {
        //     System.out.println(String.join("", m));
        //     // for(String s : m) {
        //     //     System.out.print(s);
        //     // }
        // }
        
        
        answer = new String[height];
        for(int i = 0 ; i < height ; i++) {
            answer[i] = String.join("", matrix[i]);
        }
        
        return answer;
    }
    
    
    public double[] getCrossing(int[] line1, int[] line2) {
        double[] result = {0.1, 0.1};
        double a = line1[0];
        double b = line1[1];
        double c = line1[2];
        double a2 = line2[0];
        double b2 = line2[1];
        double c2 = line2[2];
        if(a == 0) {
            if(a2 == 0) {
                
            }
            else {
                result[1] = -c/b;
                result[0] = (-b2*result[1]-c2)/a2;
            }
        }
        else if(b == 0) {
            if(b2 == 0) {
                
            }
            else {
                result[0] = -c/a;
                result[1] = (-a2*result[0]-c2)/b2;
            }
        }
        else {
            if(a2 == 0) {
                result[1] = -c2/b2;
                result[0] = (-b*result[1]-c)/a;
            }
            else if(b2 == 0) {
                result[0] = -c2/a2;
                result[1] = (-a*result[0]-c)/a;
            }
            else {
                // System.out.println("!");
                result[0] = (c2-c*(b2/b))/(a*(b2/b)-a2);
                result[1] = -a/b*result[0]-c/b;
            }
        }
        
        // System.out.println(x);
        // System.out.println(y);
        return result;
    }
    
    public boolean isInteger(double[] result) {
        if((int)result[0] != result[0] || (int)result[1] != result[1]) {
            return false;
        }
        return true;
    }
}