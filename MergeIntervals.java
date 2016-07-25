/*Given a collection of intervals, merge all overlapping intervals.
Example

Given intervals => merged intervals:

[                     [
  [1, 3],               [1, 6],
  [2, 6],      =>       [8, 10],
  [8, 10],              [15, 18]
  [15, 18]            ]
]
*/

/**
 * Definition of Interval:
 * public class Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */

class Solution {
    /**
     * @param intervals, a collection of intervals
     * @return: A new sorted interval list.
     */
     
    
    //先排序, 然后逐个比较合并, O(nlogn), 排序然后扫一遍
    public List<Interval> merge(List<Interval> intervals) {
        
        if(intervals == null || intervals.size() <= 1) {
            return intervals;
        }
        
        Collections.sort(intervals, new IntervalComparator()); //先排个序, 这样intervals里面的内容有序之后就可以顺序比较了
        
        List<Interval> result = new ArrayList<Interval>();
        Interval last = intervals.get(0);
        for(int i = 1; i < intervals.size(); i++) {
            Interval cur = intervals.get(i);
            if(cur.start <= last.end) { //这道题里是两个两个元素进行比较, 第二个元素的头一定比第一个元素的头要大, 所以不用比较头了; 而insert interval那个的newInterval跟intervals里面的元素是无序的, 要和所有的元素进行比较, 所以头尾都要比
                last.end = Math.max(last.end, cur.end);
            } else {
                result.add(last);
                last = cur;
            }
        }
        result.add(last); //最后一次要么就是last与cur合并完退出, 要么就是result添加完了上一个last, last变成了新的cur退出, 所以循环结束的时候总要加一个last
        return result;
    }
    
    //自定义比较器
    private class IntervalComparator implements Comparator<Interval> { //加了泛型
        public int compare(Interval interval1, Interval interval2) { //重写的compare方法, 需要两个参数
            return interval1.start - interval2.start; //小于0是interval1小于interval2
        }
    }
    
    
    
    
    
    
    
    //插入排序法, 基于insert Interval那道题, 每次从intervals里面取出一个, 然后放入result中进行插入排序, O(n2)
    public List<Interval> merge(List<Interval> intervals) {
        // write your code here
        
        if(intervals == null || intervals.size() == 0 || intervals.size() == 1) {
            return intervals;
        }
        
        List<Interval> result = new ArrayList<Interval>();
        for(int i = 0; i < intervals.size(); i++) {
            result = insert(result, intervals.get(i));
        }
        return result;
    }
    
    private List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int insertPos = 0;
        List<Interval> result = new ArrayList<Interval>();
        for(int i = 0; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if(interval.end < newInterval.start) {
                result.add(interval);
                insertPos++;
            } else if(interval.start > newInterval.end) {
                result.add(interval);
            } else {
                newInterval.start = Math.min(newInterval.start, interval.start);
                newInterval.end = Math.max(newInterval.end, interval.end);
            }
        }
        
        result.add(insertPos, newInterval);
        return result;
    }

}
