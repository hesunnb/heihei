一: 
ASCII顺序: ' ' < '0' < '9' < 'A' < 'Z' < 'a' < 'z' 

二: 
Follow up questions: (Find Duplicate File in System的follow up, 题目自己已经一遍过, 重点看看这个follow up)
1. Imagine you are given a real file system, how will you search files? DFS or BFS ?
In general, BFS will use more memory then DFS. However BFS can take advantage of the locality of files in inside directories, 
and therefore will probably be faster

2. If the file content is very large (GB level), how will you modify your solution?
In a real life solution we will not hash the entire file content, since it's not practical. 
Instead we will first map all the files according to size. Files with different sizes are guaranteed to be different. 
We will than hash a small part of the files with equal sizes (using MD5 for example, 具体来说文件的MD5值就像是这个文件的“数字指纹”。
每个文件的MD5值是不同的，如果任何人对文件做了任何改动，其MD5值也就是对应的“数字指纹”就会发生变化。比如下载服务器针对一个文件预先提供一个MD5值，
用户下载完该文件后，用我这个算法重新计算下载文件的MD5值，通过比较这两个值是否相同，就能判断下载的文件是否出错，或者说下载的文件是否被篡改了。
MD5实际上一种有损压缩技术，压缩前文件一样MD5值一定一样，反之MD5值一样并不能保证压缩前的数据是一样的。在密码学上发生这样的概率是很小的，
所以MD5在密码加密领域有一席之地。但是专业的黑客甚至普通黑客也可以利用MD5值实际是有损压缩技术这一原理，
将MD5的逆运算的值作为一张表俗称彩虹表的散列表来破解密码。). Only if the md5 is the same, 
we will compare the files byte by byte

3. If you can only read the file by 1kb each time, how will you modify your solution?
This won't change the solution. We can create the hash from the 1kb chunks(就是在1kb的地方存哈希表), 
and then read the entire file if a full byte by byte comparison is required.

What is the time complexity of your modified solution? What is the most time consuming part and memory consuming part of it? 
How to optimize?
Time complexity is O(n^2 * k) since in worse case we might need to compare every file to all others. k is the file size

How to make sure the duplicated files you find are not false positive?
We will use several filters to compare: File size, Hash and byte by byte comparisons.

三: 取模运算规律:
模运算与基本四则运算有些相似，但是除法例外。其规则如下：
(a + b) % p = (a % p + b % p) % p （1）
(a - b) % p = (a % p - b % p) % p （2）
(a * b) % p = (a % p * b % p) % p （3）
a ^ b % p = ((a % p)^b) % p （4）
结合律：
((a+b) % p + c) % p = (a + (b+c) % p) % p （5）
((a*b) % p * c)% p = (a * (b*c) % p) % p （6）
交换律：
(a + b) % p = (b+a) % p （7）
(a * b) % p = (b * a) % p （8）
分配律：
(a+b) % p = ( a % p + b % p ) % p （9）
((a +b)% p * c) % p = ((a * c) % p + (b * c) % p) % p （10）

四:
Arrays.fill(nums, -1); //快捷填充数组

五:
Collections.sort(d, (a,b) -> a.length() != b.length() ? -Integer.compare(a.length(), b.length()) :  a.compareTo(b));
//长度按从大到小排, 相同长度按照字典序排, 注意Integer.compare前面有个负号

六:
PriorityQueue的时间复杂度：remove()方法和add()方法时间复杂度为O(logn), 
remove(Object obj)和contains()方法需要O(n)时间复杂度, 取队头则需要O(1)时间

七:
二维数组排序: Arrays.sort(pairs, (a,b) -> (a[0] - b[0]));
