/*Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly 
related to the number of same value the collection contains.
Example:

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();*/

/*基于Insert Delete GetRandom O(1)的想法, 用HashMap<Integer, ArrayList<Integer>> map, 但是ArrayList<Integer>会出现你新装入的index不是这个
元素的最后一个index, 比如开始是11233对应1[0,1] 2[2] 3[4,5]; 删掉一个1, 变成1323对应1[0] 2[2] 3[4,1]; 当你再删一个1的时候就会出现把index为
1的3给删掉了, 但是list删除的是最后一个3, 对不上了, 如果最后一个元素不是3就直接出错了, 所以考虑换成优先级队列, 这样就可以每次都删除元素的最后一个
下标, 但是优先级队列的操作是O(logn), 不是O(1), 所以不能用优先级队列*/

/*An iterator over a normal HashSet is actually O(h/n), where h is table capacity. HashSet底层是HashMap, h就是HashMap的size, n是HastSet
的size, 所以就算是用next()取HashSet中的第一个元素, 也是这个复杂度, 和ArrayList直接用下标取的那种是不同的; LinkedHashSet的next就取链表的第一个, 
所以是O(1)*/
class RandomizedCollection {

    ArrayList<Integer> list;
    HashMap<Integer, Set<Integer>> map;
    java.util.Random rand = new java.util.Random();
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contain = map.containsKey(val);
        if (!contain) {
            map.put(val, new LinkedHashSet<Integer>());
        }
        list.add(val); //先加入值
        map.get(val).add(list.size() - 1); //加入的值都是在list中的最后一个值, 就相当于把这个值的下标放入了map中; 因为直接get的就是对应值的HashSet
        //所以就不用再put了
        return !contain;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val).iterator().next();
        map.get(val).remove(index); //因为HashSet的底层是HashMap实现的, 所以HashSet的remove方法是O(1), remove方法返回boolean, ArrayList的
        //remove方法返回删除的那个值
        if (index < list.size() - 1) { // not the last one than swap the last one with this val
            int lastone = list.get(list.size() - 1); //取list的最后一个值
            list.set(index, lastone); //替换掉要remove的值
            map.get(lastone).remove(list.size() - 1); //list用最后一个值的下标来取到那个值, 而这个值的下标也一定在它自己的HashSet中并且也是
            //最后的下标
            map.get(lastone).add(index); //更新最后一个值的index
        }
        
        if(map.get(val).isEmpty()) {
            map.remove(val);
        }
        
        list.remove(list.size() - 1);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
