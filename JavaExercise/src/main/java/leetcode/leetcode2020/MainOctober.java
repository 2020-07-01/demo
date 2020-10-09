package leetcode.leetcode2020;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : MainOctober
 * @Author : yq
 * @Date: 2020-10-01
 * @Description : 10月份
 */
public class MainOctober {


    /**
     * 已通过题目 139
     * 提交未通过题目 7
     * 未开始题目 1667
     * 提交总数 460
     * 通过的提交 227
     * 提交通过率 49.35%
     */


    /**
     * 70. 爬楼梯
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        /**
         * f(x) = f(x-1) + f(x-2)
         */
        int f1 = 1;
        int f2 = 2;
        int sum = 0;
        for (int i = 3; i <= n; i++) {
            sum = f1 + f2;
            f1 = f2;
            f2 = sum;
        }

        return sum;
    }

    /**
     * 746. 使用最小花费爬楼梯
     * 遍历数组+动态规划
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {

        int first = 0;
        int second = 0;
        for (int i = 0; i < cost.length; i++) {
            int temp = second;
            second = Math.min(cost[i] + first, second + cost[i]);

            first = temp;
        }

        return Math.min(first, second);
    }


    /**
     * 面试题 02.02. 返回倒数第 k 个节点
     * 双指针算法
     * 时间复杂度O(n)
     *
     * @param head
     * @param k
     * @return
     */
    public int kthToLast(ListNode head, int k) {

        if (head == null) {
            return -1;
        }

        if (k < 1) {
            return -1;
        }

        int right = 1;
        ListNode node = head;
        while (right <= k && head != null) {
            head = head.next;
            right++;
        }

        while (head != null) {
            head = head.next;
            node = node.next;
        }

        return node.val;
    }


    /**
     * 面试题 10.01. 合并排序的数组
     * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
     * 双指针法
     * 时间复杂度O(m+n)
     * 空间复杂度O(m+n)
     *
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge(int[] A, int m, int[] B, int n) {

        int[] C = new int[A.length];

        int a = 0;
        int b = 0;
        int c = 0;
        while (a < m || b < n) {
            if (a < m && b < n) {
                if (A[a] < B[b]) {
                    C[c] = A[a];
                    a++;
                } else {
                    C[c] = B[b];
                    b++;
                }
                c++;
            } else if (a == m) {
                C[c] = B[b];
                b++;
                c++;
            } else {
                C[c] = A[a];
                a++;
                c++;
            }

        }
        System.arraycopy(C, 0, A, 0, m + n);
    }


    /**
     * 面试题 10.01. 合并排序的数组
     * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
     * 从后往前进行遍历
     * 时间复杂度O(m+n)
     * 空间复杂度O(m+n)
     *
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge1(int[] A, int m, int[] B, int n) {

        while (m > 0 && n > 0) {
            /**
             * 从后往前遍历元素，将值较大者放在最后
             */
            A[m + n - 1] = A[m - 1] > B[n - 1] ? A[--m] : B[--n];
        }
        /**
         * 如果n > 0;则说还有元素未合并
         */
        while (n > 0) {
            A[n] = B[--n];
        }
    }

    /**
     * 面试题 08.01. 三步问题
     * 与楼梯两步走相同
     * dp[i] = dp[i-1]+dp[i-2]+dp[i-3]
     * 动态规划算法
     *
     * @param n
     * @return
     */
    public int waysToStep(int n) {

        if (n == 0) {
            return 1;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        if (n == 3) {
            return 4;
        }

        long one = 1;
        long two = 2;
        long three = 4;

        for (int i = 4; i <= n; i++) {
            long temp2 = two;
            long temp3 = three;
            three = (one + two + three) % 1000000007;
            one = temp2;
            two = temp3;
        }
        return (int) three;
    }

    /**
     * 345. 反转字符串中的元音字母
     * 双指针法 与快速排序法类似
     * 时间复杂度O(n)
     *
     * @param s
     * @return
     */
    public String reverseVowels(String s) {

        if (s == null || s.length() == 0) {
            return s;
        }

        HashMap hashMap = new HashMap();
        hashMap.put('a', 'a');
        hashMap.put('e', 'e');
        hashMap.put('i', 'i');
        hashMap.put('o', 'o');
        hashMap.put('u', 'u');
        hashMap.put('A', 'A');
        hashMap.put('E', 'E');
        hashMap.put('I', 'I');
        hashMap.put('O', 'O');
        hashMap.put('U', 'U');
        int start = 0;
        int end = s.length() - 1;
        char[] chars = s.toCharArray();
        while (start < end) {

            while (!hashMap.containsKey(chars[start]) && start < end) {
                start++;
            }

            while (!hashMap.containsKey(chars[end]) && start < end) {
                end--;
            }

            if (start < end) {
                char temp = chars[start];
                chars[start] = chars[end];
                chars[end] = temp;
            }
            end--;
            start++;

        }
        return String.valueOf(chars);
    }

    /**
     * 28. 实现 strStr()
     * 时间复杂度O(n) 会超时
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {

        if (haystack == null || haystack.length() == 0) {
            return -1;
        }
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        int index = 0;
        while (index < haystack.length() - needle.length() + 1) {

            //如果首字母相同则开始比较
            if (haystack.charAt(index) == needle.charAt(0)) {
                if (haystack.substring(index, index + needle.length()).equals(needle)) {
                    return index;
                }
            } else {
                index++;
            }
        }
        return -1;
    }

    /**
     * 28. 实现 strStr()
     * 时间复杂度O(n)
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr1(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();
        int start = 0;
        while (start < n - L + 1) {
            if (haystack.substring(start, start + L).equals(needle)) {
                return start;
            }
            start++;
        }

        return -1;
    }

    /**
     * 28. 实现 strStr()
     * 暴力算法
     * 时间复杂度O((n-l)l)
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr2(String haystack, String needle) {

        int N = haystack.length();
        int L = needle.length();
        int left = 0;
        int right = left + L;

        while (left < N - L + 1) {
            right = left + L;
            //此处可使用for循环进行比较
            if (haystack.substring(left, right).equals(needle)) {
                return left;
            }
            left++;
        }
        return -1;
    }


    /**
     * 1491. 去掉最低工资和最高工资后的工资平均值
     * 一次遍历获取最大值和最小值
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param salary
     * @return
     */
    public double average(int[] salary) {

        /**
         * 一趟遍历获取获取最大值和最小值
         * 并计算sum
         */
        int sum = 0;
        int max = salary[0];
        int min = salary[0];
        for (int i = 0; i < salary.length; i++) {

            sum = sum + salary[i];
            max = max > salary[i] ? max : salary[i];
            min = min < salary[i] ? min : salary[i];
        }
        BigDecimal bigDecimal = new BigDecimal(sum - max - min);

        bigDecimal.divide(new BigDecimal(salary.length - 2));
        return bigDecimal.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 976. 三角形的最大周长
     * 2，3，3，6 类似这种情况，直接舍弃最后一位
     *
     * @param A
     * @return
     */
    public int largestPerimeter(int[] A) {
        if (A.length == 3) {
            if (A[0] + A[1] > A[2] && A[0] + A[2] > A[1] && A[1] + A[2] > A[0]) {
                return A[0] + A[2] + A[1];
            } else {
                return 0;
            }
        }
        if (A.length < 3) {
            return 0;
        }
        /**
         * 冒泡排序
         */
        for (int i = A.length - 1; i >= 0; i--) {

            for (int j = 0; j < i; j++) {
                if (A[j] > A[j + 1]) {
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
            }
        }

        for (int i = A.length - 3; i >= 0; --i) {
            if (A[i] + A[i + 1] > A[i + 2]) {
                return A[i] + A[i + 1] + A[i + 2];
            }

        }

        return 0;
    }

    /**
     * 844. 比较含退格的字符串
     * 超出内存限制
     *
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare(String S, String T) {


        String string1 = new String();

        int s1 = S.length() - 1;
        while (s1 >= 0) {
            if (S.charAt(s1) == '#') {
                s1 = s1 - 1;
            } else {
                string1 = string1 + S.charAt(s1);
            }
            s1--;
        }

        String string2 = new String();
        int t2 = T.length() - 1;
        while (t2 >= 0) {
            if (T.charAt(t2) == '#') {
                t2 = t2 - 1;
            } else {
                string2 = string2 + T.charAt(t2);
            }
        }
        return string1.equals(string2);

    }

    /**
     * 844. 比较含退格的字符串
     * 未解决
     *
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare1(String S, String T) {

        int s = S.length() - 1;
        int t = T.length() - 1;

        while (s >= 0 && t >= 0) {
            while (S.charAt(s) == '#') {
                s = s - 2;
            }

            while (T.charAt(t) == '#') {
                t = t - 2;
            }

            if (S.charAt(s) != T.charAt(t)) {
                return false;
            }
        }

        if (s > 0 || t > 0) {
            return false;
        }
        return true;
    }


    /**
     * 1576. 替换所有的问号
     *
     * @param s
     * @return
     */
    public String modifyString(String s) {

        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                char ahead = i == 0 ? ' ' : chars[i - 1];
                char behind = i == chars.length - 1 ? ' ' : chars[i + 1];

                char j = 'a';
                while (j == ahead || j == behind) {
                    j++;
                }
                chars[i] = j;
            }
        }
        return new String(chars);
    }


    /**
     * 面试题 17.10. 主要元素
     * 时间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {

        int maxSum = 0;
        int item = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (maxSum < (hashMap.getOrDefault(nums[i], 0) + 1)) {
                maxSum = hashMap.getOrDefault(nums[i], 0) + 1;
                item = nums[i];
                hashMap.put(nums[i], maxSum);
            } else {
                hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);
            }
        }

        if (maxSum > nums.length / 2) {
            return item;
        } else {
            return -1;
        }

    }

    /**
     * 1450. 在既定时间做作业的学生人数
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param startTime
     * @param endTime
     * @param queryTime
     * @return
     */
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int sum = 0;
        int index = 0;
        while (index < startTime.length) {
            if (startTime[index] <= queryTime && endTime[index] >= queryTime) {
                sum++;
            }
        }
        return sum;
    }


    /**
     * 1512. 好数对的数目
     * 暴力算法时间复杂度O(n2)
     * 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public int numIdenticalPairs(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && i < j) {
                    sum++;
                }
            }
        }
        return sum;
    }

    /**
     * 1512. 好数对的数目
     * 借用赋值空间
     * 计算公式：v(v-1)/2
     * 世家复杂度O(n)
     * 孔家复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int numIdenticalPairs1(int[] nums) {

        Map<Integer, Integer> hashMap = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);
        }
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            int temp = entry.getValue() * (entry.getValue() - 1) / 2;
            sum = sum + temp;
        }

        return sum;
    }

    /**
     * 1365. 有多少小于当前数字的数字
     * 排序 + 映射
     * 时间复杂度 O(nlogn)
     * 空间复杂度 O(n)
     * <p>
     * 1.暴力算法
     * 2.计数排序
     *
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {

        int[] temp = new int[nums.length];
        System.arraycopy(nums, 0, temp, 0, nums.length);
        //排序nLogn
        Arrays.sort(nums);
        //遍历数组 n
        int[] arrays = new int[nums.length];
        arrays[0] = 0;
        HashMap<Integer, Integer> hashMap = new HashMap();
        hashMap.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                arrays[i] = arrays[i - 1];
                hashMap.put(nums[i], arrays[i]);
            } else {
                arrays[i] = i;
                hashMap.put(nums[i], arrays[i]);
            }
        }
        for (int i = 0; i < temp.length; i++) {
            temp[i] = hashMap.get(temp[i]);
        }

        return temp;
    }


    /**
     * 1365. 有多少小于当前数字的数字
     * 计数排序思想
     * 1.找到最大值
     * 2.计数每个元素出现的次数
     * 3.遍历通，计算小于当前元素的个数（dp）
     * 4.获取小于当前元素的元素个数
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent1(int[] nums) {

        if (nums == null || nums.length == 0) {
            return nums;
        }
        //获取最大值
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = max > nums[i] ? max : nums[i];
        }

        int[] bucket = new int[max + 1];

        for (int i = 0; i < nums.length; i++) {
            bucket[nums[i]] = bucket[nums[i]] + 1;
        }
        int temp = bucket[0];
        bucket[0] = 0;

        for (int i = 1; i < bucket.length; i++) {
            int temp1 = bucket[i];
            bucket[i] = temp + bucket[i - 1];
            temp = temp1;
        }

        for (int i = 0; i < nums.length; i++) {

            nums[i] = bucket[nums[i]];
        }
        return nums;
    }


    /**
     * 80. 删除排序数组中的重复项 II
     * <p>
     * 双指针法+原地删除
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {

        /**
         * 1.借助HashMap
         * 2.暴力解法
         * 3.计数法
         */
        int left = 0;
        int right = 2;

        int sum = nums.length;
        while (right < sum) {
            if (nums[left] == nums[right]) {
                System.arraycopy(nums, right + 1, nums, right, nums.length - right - 1);
                sum--;
            } else {
                left++;
                right++;
            }
        }

        return sum;
    }


    /**
     * 80. 删除排序数组中的重复项 II
     * 原地覆盖
     *
     * @param nums
     * @return
     */
    public int removeDuplicates1(int[] nums) {

        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i - 2]) {
                nums[i++] = n;
            }
        }
        return i;

    }


    /**
     * 287. 寻找重复数
     * 1.暴力解法 时间复杂度O(n2) 空间复杂度O(1)
     * 2.HashMap 时间复杂度O(n) 空间复杂度O(n)
     * 3.快慢指针法
     * HashMap
     * 时间复杂度O(1)
     * 空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {

        int repeatNum = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.get(nums[i]) != null) {//判断时间复杂度O(1)
                return nums[i];
            } else {
                hashMap.put(nums[i], nums[i]);
            }
        }

        return repeatNum;
    }

    /**
     * 287. 寻找重复数
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public int findDuplicate1(int[] nums) {

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;

    }

    /**
     * 287. 寻找重复数
     * 快慢指针法
     * 将数组看作一个链表
     *
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {

        int left = 0;
        int right = 0;
        while (left != right) {
            left = nums[left];
            right = nums[nums[right]];
        }
        //left = right时 存在环

        left = 0;
        while (left != right) {
            left = nums[left];
            right = nums[right];
        }
        return left;
    }

    /**
     * 剑指 Offer 47. 礼物的最大价值
     * 二维数据组问题
     * 动态规划思想
     * 时间复杂度O(m*n)
     * 空间复杂度O(1)
     *
     * @param grid
     * @return
     */
    public int maxValue(int[][] grid) {

        int row = grid.length;
        int col = grid[0].length;

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < col; j++) {

                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                } else if (j == 0) {
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                } else {
                    grid[i][j] = grid[i][j] + Math.max(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }

        return grid[row - 1][col - 1];
    }

    /**
     * 面试题 17.11. 单词距离
     * 双指针法
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param words
     * @param word1
     * @param word2
     * @return
     */
    public int findClosest(String[] words, String word1, String word2) {

        if (words == null || words.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int min = words.length;
        while (left < words.length && right < words.length) {
            while (left < words.length && !words[left].equals(word1)) {
                left++;
            }

            while (right < words.length && !words[right].equals(word2)) {
                right++;
            }
            if (left < words.length && right < words.length) {
                if (left > right) {
                    min = Math.min(min, (left - right));
                    right++;
                } else {
                    min = Math.min(min, (right - left));
                    left++;
                }
            } else {
                return min;
            }
        }

        return min;
    }


    public static void main(String[] args) {

        MainOctober mainOctober = new MainOctober();
        int[] array = new int[]{2, 1, 312, 312, 321, 3, 123, 213, 33, 54, 35, 6, 5676, 65, 3, 41, 2, 1, 3, 4, 54, 321, 2};
        int[] array1 = new int[]{1, 2, 2, 2, 2, 2, 2, 3, 3, 4, 5, 8, 9, 10};

        String[] strings = new String[]{"ig", "as", "dt", "hnv", "b", "vr", "wbh", "ga", "wjv", "sq", "zf", "lhu", "y", "j", "sn", "zr", "woo", "mtr", "gw", "erj", "xqe", "p", "bl", "amo", "j", "p", "awf", "rmk", "nx", "qkj", "ub", "apk", "v", "tuq", "vci", "rrs", "wt", "y", "m", "jtn", "vxr", "x", "c", "lmn", "hpr", "t", "ab", "uf", "kp", "e", "q", "o", "jpm", "u", "glv", "nbl", "fqr", "v", "z", "nzk", "f", "kzu", "fje", "tzk", "f", "v", "mbf", "r", "t", "x", "uj", "hye", "po", "a", "zo", "xz", "r", "y", "ct", "j", "ac", "ctq", "ol", "nz", "f", "mwd", "aoc", "ve", "vmd", "bf", "mu", "pu", "fz", "jl", "lv", "yrl", "hzg", "jx", "vfx", "mdi", "td", "mqm", "nc", "l", "ln", "w", "q", "h", "uk", "upd", "v", "zo", "fbj", "pnt", "he", "qm", "l", "feb", "w", "e", "g", "r", "w", "uq", "ks", "pf", "x", "f", "lf", "buq", "zo", "gwr", "t", "q", "k", "p", "meo", "j", "w", "z", "p", "ek", "n", "ynn", "xka", "kk", "x", "nux", "rk", "ho", "xhu", "gf", "w", "w", "ekr", "eha", "prq", "sk", "hxm", "q", "ovg", "t", "mt", "mjz", "fe", "e", "e", "a", "l", "j", "zt", "hy", "h", "i", "dvu", "o", "dt", "h", "ir", "u", "ys", "ae", "etr", "rc", "urw", "d", "jbj", "qh", "td", "qpt", "v", "p", "k", "lc", "k", "xk", "kr", "jb", "cq", "gg", "hv", "h", "a", "izm", "tzh", "gf", "by", "ktw", "qny", "am", "m", "cd", "fjb", "e", "uk", "c", "agb", "hfk", "s", "qkk", "ika", "wc", "zh", "pt", "z", "ecm", "zjf", "pv", "ik", "r", "el", "wc", "qv", "n", "eg", "jl", "d", "bx", "zf", "ndk", "qvv", "v", "rc", "ce", "bh", "gq", "lnk", "f", "ffz", "mb", "hc", "id", "wvu", "sp", "r", "j", "wix", "es", "avg", "s", "yl", "jj", "f", "st", "shb", "b", "cwt", "iqc", "u", "cz", "cs", "p", "xy", "y", "m", "vy", "sq", "tt", "t", "lx", "vwf", "pvk", "qb", "j", "jrw", "q", "sam", "ke", "aay", "z", "qwu", "dph", "wv", "jkq", "ify", "mnk", "ulq", "s", "huj", "k", "c", "f", "bpi", "i", "yak", "mi", "myr", "g", "bcf", "uo", "pyp", "urt", "eja", "mck", "c", "x", "xsd", "cwk", "p", "qb", "aj", "cxp", "gfe", "fz", "lcx", "g", "kb", "q", "kar", "rge", "epw", "l", "t", "wk", "v", "oh", "sw", "eqv", "mdl", "edx", "dqi", "y", "i", "z", "vqy", "lzj", "cl", "m", "pzl", "z", "kip", "oxw", "kmw", "mgq", "bnn", "pty", "vji", "d", "wcv", "ck", "s", "qwh", "u", "dpp", "o", "dy", "c", "rrz", "obm", "cr", "sap", "k", "j", "nzs", "sc", "a", "xn", "i", "omy", "ui", "szk", "cau", "pgv", "r", "p", "wz", "d", "sd", "kys", "jp", "fxi", "ioo", "b", "i", "vq", "lg", "oo", "k", "os", "rd", "tlf", "db", "a", "qj", "g", "pv", "dh", "zoc", "lv", "o", "ejm", "x", "gx", "xr", "wuh", "ur", "pw", "mza", "ne", "cb", "gc", "zvm", "mxt", "hhd", "f", "a", "ia", "ggt", "ir", "e", "gk", "k", "q", "okn", "e", "vp", "pcu", "nd", "ddo", "ia", "ck", "fx", "fhk", "obc", "b", "os", "x", "sm", "yfe", "uz", "dmo", "x", "s", "sw", "xnh", "c", "za", "bjb", "xhv", "sg", "r", "bz", "s", "wnb", "trv", "qhh", "v", "ycz", "ysa", "cjo", "yrf", "hho", "adi", "tg", "dmb", "j", "y", "zi", "sun", "f", "iq", "qvk", "rp", "fn", "zr", "e", "s", "fic", "v", "x", "yw", "a", "wt", "img", "s", "x", "cx", "gkz", "q", "o", "cu", "bdk", "wq", "ln", "r", "z", "vcu", "kbx", "d", "v", "pu", "cr", "y", "qt", "wv", "jsg", "fcp", "p", "v", "nw", "y", "h", "y", "li", "ad", "tuv", "sqd", "cki", "l", "sd", "xof", "k", "zza", "w", "muc", "e", "wwz", "i", "v", "zg", "pam", "ig", "zpb", "vl", "v", "pe", "m", "p", "vu", "utm", "kgl", "z", "qyn", "f", "op", "onh", "h", "yj", "zeu", "zgc", "g", "qr", "q", "lh", "eq", "d", "bg", "fdj", "cel", "jo", "qy", "ce", "xzd", "vwi", "exq", "miz", "yoa", "ofk", "szr", "s", "huf", "jv", "w", "fr", "eoj", "my", "zm", "j", "sf", "dfj", "e", "t", "raj", "r", "qm", "z", "v", "zn", "c", "ywz", "b", "ag", "x", "z", "a", "q", "xup", "wl", "njp", "x", "b", "ao", "h", "xa", "aq", "m", "g", "gsl", "t", "vya", "hag", "otz", "q", "rcq", "hj", "ng", "bzy", "dit", "h", "u", "cl", "oyb", "fuu", "qlt", "qdr", "hki", "ts", "y", "po", "ljb", "cx", "l", "gf", "p", "vr", "d", "rp", "pt", "j", "ar", "o", "uz", "r", "aj", "cd", "x", "sl", "iwd", "rgd", "b", "u", "t", "knn", "ba", "gl", "wek", "wgp", "v", "gb", "aml", "a", "kg", "dp", "fnm", "iru", "ga", "ght", "xd", "yf", "h", "rr", "go", "thi", "va", "h", "wtx", "p", "hbm", "g", "l", "k", "fp", "sn", "ph", "gz", "y", "e", "qg", "qgi", "v", "fzt", "y", "ztn", "rbe", "gq", "oc", "bga", "hi", "b", "j", "r", "wn", "m", "pa", "p", "b", "ch", "ul", "nf", "ye", "bnl", "t", "n", "e", "g", "rhk", "u", "h", "g", "dyz", "d", "vrd", "km", "z", "biq", "ya", "x", "tv", "um", "e", "n", "yay", "ywk", "jj", "di", "r", "m", "ll", "vaj", "o", "w", "uim", "tga", "lhf", "m", "smj", "eva", "l", "sn", "te", "hvh", "yxf", "i", "ak", "p", "x", "cfo", "kmp", "ffg", "a", "zm", "p", "ftz", "o", "cp", "arj", "oir", "to", "e", "cv", "hfb", "n", "nw", "dad", "uq", "lwo", "sl", "vpc", "wb", "p", "rs", "o", "hmd", "om", "cve", "j", "sqa", "o", "l", "sr", "zx", "pg", "iwi", "j", "hvf", "x", "hy", "ph", "dgd", "sv", "qc", "lpw", "ui", "k", "l", "wf", "wf", "jsb", "z", "ri", "qt", "qng", "ki", "o", "pfx", "sl", "v", "dmp", "h", "sp", "x", "vlb", "lyx", "tt", "u", "lsr", "uu", "x", "br", "iqo", "pj", "b", "k", "n", "fe", "jl", "zhr", "k", "a", "k", "zn", "ti", "ni", "gg", "f", "au", "bvm", "hi", "oc", "t", "un", "t", "xk", "qqg", "ger", "vyu", "tz", "mlj", "ui", "p", "jlr", "po", "jh", "yc", "t", "w", "l", "z", "ym", "ks", "co", "k", "hl", "x", "kh", "lnx", "vw", "u", "qtp", "zm", "kkk", "rm", "jj", "rnn", "ub", "ics", "w", "qr", "ke", "rw", "ywi", "vs", "p", "fq", "qy", "llt", "nx", "t", "o", "pu", "o", "kea", "dz", "x", "rw", "bpi", "hug", "gr", "xwf", "mo", "pbe", "d", "ov", "rs", "ltz", "ta", "l", "wal", "vsz", "ql", "eku", "j", "llf", "gfk", "uvg", "mv", "v", "kyx", "kvo", "a", "aha", "nr", "hja", "wo", "txu", "cp", "jxn", "s", "eo", "i", "ozd", "c", "d", "a", "ik", "vf", "bxt", "gxb", "zj", "bsb", "zqz", "jb", "n", "nm", "q", "m", "cy", "wwx", "ff", "xkp", "pfl", "fjo", "or", "go", "ol", "bf", "w", "bma", "vjc", "us", "u", "wag", "xqc", "av", "oqz", "k", "tqk", "n", "gk", "go", "e", "xae", "oh", "if", "t", "n", "xz", "fx", "wau", "g"};

        System.out.println(mainOctober.findClosest(strings, "jsb", "uvg"));
    }
}
