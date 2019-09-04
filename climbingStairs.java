public class Solution {

    //n步就是n个台阶，就是斐波那契数列
    /* 0 1 2 3 4...
       1 1 2 3 5...*/
    public int climbStairs(int n) {

        if(n <= 1) {
            return 1;
        }
        
        int last = 1;
        int lastlast = 1;
        int now = 0;
        for(int i = 2; i <= n; i++) {
            now = last + lastlast;
            lastlast = last;
            last = now;
        }
        return now;
    }
    
    
    //通用方法: 比如要是迈2步与3步, 那么通项就是a(n) = a(n-2) + a(n-3), 公式从第四项有效, 所以把前三项的具体内容写出来, 后面用
    //通项就可以了
    public int climbStairs(int n) {
        
        int lastlast = 1;
        int last = 2;
        int now = 0;
        if(n == 1) {
            return lastlast;
        } else if(n == 2) {
            return last;
        }
        
        for(int i = 3; i <= n; i++) {
            now = lastlast + last;
            lastlast = last;
            last = now;
        }
        
        return now;
    }
    
    public static int shishi(int n) {
		
	//2步与3步
	int lll = 0; //人为写出头三项
	int ll = 1;
	int l = 1;
	int now = 0;
	if(n == 1) {
	    return lll;
	} else if(n == 2) {
	    return ll;
	} else if(n == 3) {
	    return l;
	}

	for(int i = 4; i <= n; i++) { //公式
	    now = lll + ll;
	    lll = ll;
	    ll = l;
	    l = now;
	}
	return now;
    }
    
    /* //递归会超时，每次都把之前的所有的都从新算了一遍
    public int climbStairs(int n) {
        if(n <= 1) {
            return 1;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    } */
}
