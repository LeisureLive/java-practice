package com.jiehe.demo.leetcode.base;

import java.util.Deque;

/**
 * @Author: Jiehe, leisurehejie@sina.com
 * @Date: 2019/6/30 14:36
 */
public class MinStack {

  private Deque<Integer> stack;
  private Deque<Integer> minStack;

  /**
   * initialize your data structure here.
   */
  public MinStack() {
  }

  public void push(int x) {
    stack.push(x);
    if (minStack.isEmpty() || minStack.peek() >= x) {
      minStack.push(x);
    }
  }

  public void pop() {
    int val = stack.pop();
    if (val == minStack.peek()) {
      minStack.pop();
    }
  }

  public int top() {
    return stack.peek();
  }

  public int getMin() {
    return minStack.peek();
  }

}
