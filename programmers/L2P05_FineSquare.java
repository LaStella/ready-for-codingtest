class Solution {
    public long solution(int w, int h) {
        long answer = 1;
        int factor = Gcd(w, h);
        answer = (long) w * h - (w + h - factor);
        // answer = (long) w * h - (w/factor + h/factor - 1) * factor;

        return answer;
    }
    
    public int Gcd(int a, int b) {
        int r = a % b;
        if(r == 0) {
            return b;
        }
        else {
            return Gcd(b, r);
        }
    }
}