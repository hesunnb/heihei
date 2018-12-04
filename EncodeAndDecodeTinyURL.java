/*TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns 
a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. 
You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.*/

public class Codec {

    //solution1: 最接近实际使用情况的
    List<String> urls = new ArrayList<String>();
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        urls.add(longUrl); //不判断重复, 直接加入, 重复的longUrl也会有不同的短链接; 下面的解释说用哈希查长链接存不存在是个消耗, 因为它这个意思
        //是查找存不存在是在数据库中查, 这些长短链接都存在数据库中而不是一个哈希表中, 所以查数据库的select消耗很大, 不如直接放入数据库(即使重复)
        return String.valueOf(urls.size() - 1); //短链接在这里就是一个数
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int index = Integer.valueOf(shortUrl);
        return (index < urls.size()) ? urls.get(index) : "";
    }
    /*below is the tiny url solution in java, also this is the similar method in industry. In industry, most of shorten url service is 
    by database, one auto increasing long number as primary key. whenever a long url need to be shorten, append to the database, and 
    return the primary key number. (the database is very easy to distribute to multiple machine like HBase, or even you can use the 
    raw file system to store data and improve performance by shard and replica).
    Note, it's meaningless to promise the same long url to be shorten as the same short url. if you do the promise and use something 
    like hash to check existing, the benefit is must less than the cost.
    Note: if you want the shorted url contains '0-9a-zA-Z' instead of '0-9', then you need to use 62 number system, not 10 number 
    system(decimal) to convert the primary key number. like 123->'123' in decimal, 123->'1Z' in 62 number system 
    (or '0001Z' for align).*/
    
    
    //solution2: 这个方法不是很好, 虽然实现了, 用了两个哈希, 与实际有些区别
    Map<String, String> index = new HashMap<String, String>();
    Map<String, String> revIndex = new HashMap<String, String>();
    String BASE_HOST = "http://tinyurl.com/";
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (revIndex.containsKey(longUrl)) {
            return BASE_HOST + revIndex.get(longUrl);
        } //如果在revIndex已经有longUrl, 直接返回其相应的shortUrl
        
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String key = null;
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                int r = (int) (Math.random() * charSet.length());
                sb.append(charSet.charAt(r));
            }
            key = sb.toString();
        } while (index.containsKey(key)); //doWhile循环是while为真进行循环, 先加进去一个key, 如果这个生成的key已经存在就继续生成
        index.put(key, longUrl); //用于decode
        revIndex.put(longUrl, key); //用于encode
        return BASE_HOST + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return index.get(shortUrl.replace(BASE_HOST, "")); //把BASE_HOST替换掉取出longUrl
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
