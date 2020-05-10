package com.wht.demo.leetCode.mock;

import java.util.Map;
import java.util.TreeMap;

/**
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内没有其他安排，则可以存储这个新的日程安排。
 * <p>
 * MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end。
 * <p>
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生重复预订。
 * <p>
 * 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。
 * <p>
 * 请按照以下步骤调用 MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * <p>
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(15, 25); // returns false
 * MyCalendar.book(20, 30); // returns true
 * 解释:
 * 第一个日程安排可以添加到日历中.  第二个日程安排不能添加到日历中，因为时间 15 已经被第一个日程安排预定了。
 * 第三个日程安排可以添加到日历中，因为第一个日程安排并不包含时间 20 。
 * <p>
 * 思路说明：用链表来存放时间数据。节点以时间升序排序。
 * <p>
 * 调用方法时，首先检查参数是否合法，不合法则return {@code false}。
 * 之后检查输入时间范围是否小于最小值，是则直接插入或是否大于最大值
 *
 * @author wht
 * @date 2020/5/2 16:00
 */
public class MyCalendar {
    private Map<Integer, Integer> map;
    private int max = 1000000000;

    public MyCalendar() {
        map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        if (start < 0 || start > this.max || end < 0 || end > this.max) {
            return false;
        }

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            count++;
            //如果输入的时间范围在最后一组之后
            if (entry.getKey() >= end) {
                map.put(start, end);
                return true;
            }
            if (entry.getValue() <= start ){
                if (count == map.size()){
                    map.put(start, end);
                    return true;
                } else {
                    continue;
                }
            } else {
                return false;
            }
        }

        map.put(start, end);
        return true;
    }
}
