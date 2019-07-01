package com.jiehe.demo.leetcode.base;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: Jiehe, leisurehejie@sina.com
 * @Date: 2019/6/29 13:31
 */
public class ChainTableSolution {

  /**
   * 链表节点定义.
   */
  public static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }

    @Override
    public String toString() {
      return String.valueOf(val);
    }
  }

  public static ListNode removeNthFromEnd(ListNode head, int n) {
    Deque<ListNode> stack = new LinkedList<>();
    stack.push(head);
    while (head.next != null) {
      stack.push(head.next);
      head = head.next;
    }
    // 要删除的节点
    ListNode node = stack.peek();
    while (n-- > 0) {
      node = stack.pop();
    }
    ListNode prev = stack.peek();
    if (prev == null && node.next == null) {
      return null;
    } else if (prev == null) {
      ListNode next = node.next;
      node.next = null;
      return next;
    } else if (node.next == null) {
      prev.next = null;
    } else {
      node.val = node.next.val;
      node.next = node.next.next;
    }
    while (stack.peek() != null) {
      head = stack.pop();
    }
    return head;
  }

  public static ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode cur = head;
    while (cur != null) {
      ListNode temp = cur.next;
      cur.next = prev;
      prev = cur;
      cur = temp;
    }
    return cur;
  }

  /**
   * 合并两个有序链表.
   */
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode preHead = new ListNode(-1);
    ListNode prev = preHead;
    while (l1 != null && l2 != null) {
      if (l1.val <= l2.val) {
        prev.next = l1;
        l1 = l1.next;
      } else {
        prev.next = l2;
        l2 = l2.next;
      }
      prev = prev.next;
    }
    prev.next = l1.next == null ? l2 : l1;
    return preHead.next;
  }

  /**
   * 回文链表.
   */
  public static boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }
    if (head.next.next == null) {
      return head.next.val == head.val;
    }
    // 确定链表中点
    ListNode slow = head;
    ListNode fast = head;
    ListNode temp = null;
    while (fast.next != null && fast.next.next != null) {
      temp = slow;
      fast = fast.next.next;
      slow = slow.next;
    }
    ListNode mid;
    if (fast.next != null) {
      mid = slow.next;
    } else {
      mid = slow;
    }
    temp.next = null;
    // 中点是后部分的头结点
    ListNode prev = null;
    // 将后半部分倒转
    while (mid.next != null) {
      ListNode temp2 = mid.next;
      mid.next = prev;
      prev = mid;
      mid = temp2;
    }
    mid.next = prev;
    // 同时遍历两端.
    while (mid.next != null && head.next != null) {
      if (mid.val != head.next.val) {
        return false;
      }
      mid = mid.next;
      head = head.next;
    }
    return true;
  }

  /**
   * 环形链表
   */
  public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }
    ListNode slow = head;
    ListNode fast = head;
    while (true) {
      fast = fast.next;
      if (fast == null) {
        return false;
      }
      fast = fast.next;
      if (fast == null) {
        return false;
      }
      slow = slow.next;
      if (slow == fast) {
        return true;
      }
    }
  }

  public static void main(String[] args) {
    ListNode listNode1 = new ListNode(1);
    ListNode listNode2 = new ListNode(1);
    ListNode listNode3 = new ListNode(2);
    ListNode listNode4 = new ListNode(1);
    listNode1.next = listNode2;
    listNode2.next = listNode3;
    listNode3.next = listNode4;
    isPalindrome(listNode1);
  }


}
