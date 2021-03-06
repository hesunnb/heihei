/*Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].*/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    Queue<Integer> queue = null;
    public NestedIterator(List<NestedInteger> nestedList) {
        queue = new LinkedList<Integer>();
        helper(queue, nestedList); //在flatten list那道题里面是把内容都存到了ArrayList里面, 这道题里都存到队列里面
    }

    private void helper(Queue<Integer> queue, List<NestedInteger> temp) {
        for(int i = 0; i < temp.size(); i++) {
            if(temp.get(i).isInteger()) {
                queue.offer(temp.get(i).getInteger());
            } else if(temp.get(i).getList() != null) {
                helper(queue, temp.get(i).getList());
            }
        }
    }

    @Override
    public Integer next() {
        if(hasNext()) { //队列中如果有元素
            return queue.poll(); //就弹出然后返回
        }
        return Integer.MIN_VALUE;
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
