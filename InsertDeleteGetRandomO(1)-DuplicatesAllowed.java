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
