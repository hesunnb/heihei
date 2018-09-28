/*Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most 
recent tweets in the user's news feed. Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users 
who 
the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.
Example:

Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);*/

class Twitter {

    class Message { //用message就能用time, 可以区分时间, 并且添加和移除都用的对象, 不会出现重复的情况, 一个message对象就是一个, Integer就不行了
        int tweetId;
        int time;
        Message(int tweetId, int time) {
            this.tweetId = tweetId;
            this.time = time;
        }
    }
    
    Map<Integer, Set<Integer>> followMap; //followeeId, followerId; 被follow的人作为key, 主动follow的人作为value
    Map<Integer, List<Message>> ownTwitter; //userId, 每个user自己主动发的twitter存在这个list中
    Map<Integer, List<Message>> ownFollowTwitter; //userId, 每个user自己主动发的twitter与follow人的twitter的合集
    int count = 0;
    /** Initialize your data structure here. */
    public Twitter() {
        followMap = new HashMap<>();
        ownTwitter = new HashMap<>();
        ownFollowTwitter = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Message message = new Message(tweetId, count++); //new一个message
        if(!ownTwitter.containsKey(userId)) { //向ownTwitter放入自己的推文
            List<Message> list = new ArrayList<>();
            list.add(message);
            ownTwitter.put(userId, list);
        } else {
            ownTwitter.get(userId).add(0, message); //注意要加在index为0处, 因为最新发的推文往前排
        }
        
        if(!ownFollowTwitter.containsKey(userId)) { //同理
            List<Message> list = new ArrayList<>();
            list.add(message);
            ownFollowTwitter.put(userId, list);
        } else {
            ownFollowTwitter.get(userId).add(0, message);
        }
        
        if(followMap.get(userId) == null) { //比如最开始只发了推文, 没有加入follow关系的时候, 那么就初始化, 否则下面获取到的set就是null了
        	followMap.put(userId, new HashSet<Integer>());
        }
        Set<Integer> set = followMap.get(userId);
    	for(int i : set) { //一个人发了推文, follow这个人的所有人都要加入这条推文; 如果set的size()是0, 那么下面这条for循环都不会执行
            ownFollowTwitter.get(i).add(0, message);
        }
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the 
    user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        
        if(!ownFollowTwitter.containsKey(userId)) {
            return result;
        }
        int sum = 0;
        for(Message m : ownFollowTwitter.get(userId)) {
            if(sum == 10) { //只返回最新的10条
                break;
            }
            result.add(m.tweetId);
            sum++;
        }
        return result;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(followerId == followeeId) { //自己不用follow自己, follow[1,1]
            return;
        }
        
        if(ownFollowTwitter.get(followerId) == null) { //也得初始化, 比如最开始不postTweet, 直接就follow, 那么如果不初始化, 下面followerList
            //followeeList就有可能为null了
        	ownFollowTwitter.put(followerId, new ArrayList<Message>());
        }
        if(ownTwitter.get(followeeId) == null) {
        	ownTwitter.put(followeeId, new ArrayList<Message>());
        }
        
        if(!followMap.containsKey(followeeId)) {
            Set<Integer> set = new HashSet<>();
            set.add(followerId);
            followMap.put(followeeId, set);
        } else {
            if(followMap.get(followeeId).contains(followerId)) {
                return; //防止重复follow, 比如follow[1,2], follow[1,2]
            }
            followMap.get(followeeId).add(followerId);
        }

        List<Message> followerList = ownFollowTwitter.get(followerId);
        List<Message> followeeList = ownTwitter.get(followeeId);
    	int i = 0, j = 0;
    	while(i < followerList.size() && j < followeeList.size()) { //time越大越是后放的, 后放的应该在前面
            if(followerList.get(i).time > followeeList.get(j).time) {
                i++;
            } else {
                followerList.add(i, followeeList.get(j));
                j++;
            }
        }
        while(j < followeeList.size()) {
            followerList.add(followeeList.get(j));
            j++;
        }
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!followMap.containsKey(followeeId)) {
            return;
        }
        Set<Integer> set = followMap.get(followeeId);
        if(!set.contains(followerId)) {
            return;
        }
        
        followMap.get(followeeId).remove(followerId); //unfollow注意, followMap也要解除相关关系
        List<Message> followerList = ownFollowTwitter.get(followerId);
        List<Message> followeeList = ownTwitter.get(followeeId);
        for(Message m : followeeList) { //删除相关follow的message
            followerList.remove(m); //message好处就是直接就删除了, 因为是对象, 地址不重复
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
