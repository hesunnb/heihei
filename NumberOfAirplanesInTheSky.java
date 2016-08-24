/*Given an interval list which are flying and landing time of the flight. How many airplanes are on the sky at most?

 Notice

If landing and flying happens at the same time, we consider landing should happen at first.

Example
For interval list

[
  [1,10],
  [2,3],
  [5,8],
  [4,7]
]
Return 3*/

/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */

class Solution {
    /**
     * @param intervals: An interval array
     * @return: Count of airplanes are in the sky.
     */
     
    //时间(O(nlogn))
    public int countOfAirplanes(List<Interval> airplanes) { 
        // write your code here
        
        if(airplanes == null || airplanes.size() == 0) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < airplanes.size(); i++) {
            if(!map.containsKey(airplanes.get(i).start)) { //start的地方要+1, 说明有飞机起飞
                map.put(airplanes.get(i).start, 1);
            } else {
                map.put(airplanes.get(i).start, map.get(airplanes.get(i).start) + 1);
            }
            
            if(!map.containsKey(airplanes.get(i).end)) { //end的地方要-1, 说明有飞机降落
                 map.put(airplanes.get(i).end, -1);
            } else {
                map.put(airplanes.get(i).end, map.get(airplanes.get(i).end) - 1);
            }
        }
        
        Integer[] values = map.keySet().toArray(new Integer[0]); //keySet返回Set, 转成数组要指定类型, 只能是Integer的数组
		Arrays.sort(values); //排序key, 一定要排序, 要按时间模拟飞机起飞降落
		
		for(int i = 0; i < values.length; i++) { //把key的值替换成相应的value
		    if(map.containsKey(values[i])) {
		        values[i] = map.get(values[i]);
		    }
		}
		
		int sum = 0;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < values.length; i++) { //按照顺序加减求出里面最大的sum
		    sum += values[i];
		    max = Math.max(max, sum);
		}
		
		return max;
    }
}
