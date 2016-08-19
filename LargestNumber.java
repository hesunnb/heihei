/*Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.*/

public class Solution {
    
    //时间复杂度: O(nlogn)
    public String largestNumber(int[] nums) {
        if(nums == null || nums.length == 0) {
            return "";
        }
        
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++) { //把nums中的整数都转成字符串
            strs[i] = Integer.toString(nums[i]);
        }
        
        Arrays.sort(strs, new StringComparator()); //按照比较器规定排序
        StringBuilder sb = new StringBuilder();
        
        int index = 0;
        for(int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
            if(strs[i].equals("0")) { //如果要都是0就只能返回一个0, 返回一堆0是不对滴
                index++;
            }
        }
        if(index == strs.length) {
            return "0";
        }
        return sb.toString();
    }
    
    //倒序比较器
    class StringComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return (s2 + s1).compareTo(s1 + s2);
        }
    }
}

/*testCase1:
	String s1 = "1";
	String s2 = "20";
	String s3 = "23";
	String s4 = "4";
	String s5 = "8";*/
	
	/*testCase2:
	String s1 = "3";
	String s2 = "30";
	String s3 = "34";
	String s4 = "5";
	String s5 = "9";*/
	
	/*testCase3:
	String s1 = "0"; //这种例子不允许返回一堆0, 只允许返回一个0, 所以要特殊处理一下
	String s2 = "0";
	String s3 = "0";
	String s4 = "0";
	String s5 = "0";*/
	
	//倒序比较器测试程序
    class StringComparatorDes implements Comparator<String> {
    
    	@Override
    	public int compare(String n1, String n2) {
    		// TODO Auto-generated method stub
    		System.out.println(n1 + " " + n2 + " " + (n2+n1) + " " + (n1+n2)); //打印出相应的数目
    		if(n1 == null || n2 == null) {
    			throw new NullPointerException();
    		}
    		int result = (n2 + n1).compareTo(n1 + n2); //比较的核心
    		System.out.println(result); //按字典序比较的结果
    		return result; 
    	}
    }

    //正序比较器测试程序
    class StringComparatorAsc implements Comparator<String> {
    
    	@Override
    	public int compare(String n1, String n2) {
    		// TODO Auto-generated method stub
    		System.out.println(n1 + " " + n2 + " " + (n1+n2) + " " + (n2+n1)); //打印出相应的数目
    		if(n1 == null || n2 == null) {
    			throw new NullPointerException();
    		}
    		int result = (n1 + n2).compareTo(n2 + n1); //比较的核心
    		System.out.println(result); //按字典序比较的结果
    		return result; 
    	}
    }
    
    /*倒序比较器测试结果:
    30 3 330 303       //比较器是小的结果应该在前, 303小, 所以它对应的3就在前, 30在后; 如果想要看largest number, 那么必须两个两个
    3                  //加和比较, 加和大的是我们想要的, 330大, 那么330对应的数要排在后面, 那就让30到后面, 所以掉个个儿
    34 30 3034 3430
    -4
    34 30 3034 3430
    -4
    34 3 334 343
    -1
    5 3 35 53
    -2
    5 34 345 534
    -2
    9 3 39 93
    -6
    9 34 349 934
    -6
    9 5 59 95
    -4
    [9, 5, 34, 3, 30]*/
