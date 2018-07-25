/*Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();*/


//solution1: O(1)的好方法
class RandomizedSet {

    ArrayList<Integer> list;
    HashMap<Integer, Integer> map;
    java.util.Random rand = new java.util.Random();
    /** Initialize your data structure here. */
    public RandomizedSet() {
        list = new ArrayList<Integer>();
        map = new HashMap<Integer, Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val); //先加入值
        map.put(val, list.size() - 1); //加入的值都是在list中的最后一个值, 就相当于把这个值的下标放入了map中
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val);
        if (index < list.size() - 1) { // not the last one than swap the last one with this val
            int lastone = list.get(list.size() - 1); //取list的最后一个值
            list.set(index, lastone); //替换掉要remove的值
            map.put(lastone, index); //更新最后一个值的index
        }
        map.remove(val);
        list.remove(list.size() - 1); //直接删除list的最后一个值; 这个交换为的就是要调用ArrayList的remove方法去删除list中的最后一个值, 
        //因为ArrayList的remove方法是O(n), 但是删除最后一个值是O(1), 可以参考ArrayList的源码
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
        //return list.get((int)(Math.random()*list.size())); //Math.random()和nextInt差不多吧, 稍微复杂点儿
        //nextInt方法的作用是生成一个随机的int值, 该值介于[0,n)的区间, 也就是0到n之间的随机int值, 包含0而不包含n.
	//nextInt方法是O(1)的
    }
}


//solution2: (own), ArrayList的add方法是O(1), remove方法是O(n)
//HashSet底层如果是HashMap实现的, 那么add和remove是O(1); 如果底层是红黑树, 那么就是O(logN)
class RandomizedSet {

    Set<Integer> set;
    Integer[] setArray;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        set = new HashSet<>();
        setArray = new Integer[0];
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        return set.add(val);
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        return set.remove(val);
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return set.toArray(setArray)[(int)(Math.random()*set.size())];
        //用set以及toArray, 配合随机数; 如果HashSet底层是用HashMap实现, 那么insert和remove都没有问题, 但是Hashset没有直接的get方法,
	//HashSet用iterator, 是需要遍历的, 所以getRandom()这里慢了
    }
}


//solution3: (own)
class RandomizedSet {

    List<Integer> list;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        list = new ArrayList<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(list.contains(val)) {
            return false;
        }
        return list.add(val); //add方法返回boolean
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!list.contains(val)) {
            return false;
        }
        list.remove(new Integer(val)); //remove返回被删除的值; 一个新的用法就是ArrayList的remove如果直接传int默认就是下标, 
        //传对象才是ArrayList中的值, 所以要想删除ArrayList中的值的话得传入相应的对象才行
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get((int)(Math.random()*list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
