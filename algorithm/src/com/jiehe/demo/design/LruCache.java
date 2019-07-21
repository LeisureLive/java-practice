package com.jiehe.demo.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jiehe, leisurehejie@sina.com
 * @Date: 2019/7/17 23:42
 */
public class LruCache {

  class LruNode {

    String key;

    Object value;

    LruNode prev;

    LruNode next;

    public LruNode(String key, Object value) {
      this.key = key;
      this.value = value;
    }
  }

  // 容量
  private int capacity;

  private LruNode head;

  private LruNode tail;

  private Map<String, LruNode> map = new HashMap<>();

  // 假设缓存最小为1.
  public LruCache(int capacity) {
    this.capacity = capacity >= 1 ? capacity : 1;
  }

  public void put(String key, Object value) {
    // 先考虑为空
    if (head == null) {
      head = new LruNode(key, value);
      tail = head;
      map.put(key, head);
      return;
    }
    LruNode node = map.get(key);
    if (node != null) {
      // 缓存中已经存在此key,更新值并将节点移到头结点
      node.value = value;
      map.put(key, node);
      removeAndInsertHead(node);
    } else {
      // 节点不存在，新建节点
      node = new LruNode(key, value);
      // 如果缓存已满，需要删掉尾部节点
      if (map.size() == capacity) {
        map.remove(tail.key);
        tail = tail.prev;
        tail.next = null;
      }
      // 将新节点插入map和头部
      node.prev = null;
      node.next = head;
      head.prev = node;
      head = node;
      map.put(key, node);
    }
  }

  /**
   * 移除节点并放到头部.
   */
  private void removeAndInsertHead(LruNode node) {
    // 移除节点，考虑节点为头结点或者尾结点
    if (node == head) {
      return;
    } else if (node == tail) {
      tail = node.prev;
      tail.next = null;
    } else {
      node.prev.next = node.next;
      node.next.prev = node.prev;
    }
    // 插入到头部
    node.next = head;
    node.prev = null;
    head.prev = node;
    head = node;
  }

  public Object get(String key) {
    LruNode node = map.get(key);
    if (node == null) {
      return -1;
    }
    // 将访问的节点移到头部.
    removeAndInsertHead(node);
    return node.value;
  }

}
