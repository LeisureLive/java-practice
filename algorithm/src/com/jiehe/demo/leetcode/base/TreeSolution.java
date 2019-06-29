package com.jiehe.demo.leetcode.base;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: Jiehe, leisurehejie@sina.com
 * @Date: 2019/6/29 15:57
 */
public class TreeSolution {

  public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  /**
   * 二叉树的最大深度.
   */
  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
  }


  /**
   * 验证二叉搜索树.
   */
  public boolean isValidBST(TreeNode root) {
    LinkedList<TreeNode> linkedList = new LinkedList<>();
    Deque<TreeNode> stack = new LinkedList<>();
    while (!stack.isEmpty() || root != null) {
      if (root != null) {
        stack.push(root);
        root = root.left;
      } else {
        root = stack.pop();
        if (!linkedList.isEmpty() && root.val < linkedList.getLast().val) {
          return false;
        }
        linkedList.add(root);
        root = root.right;
      }
    }
    return true;
  }

  /**
   * 对称二叉树.
   */
  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    return isSymmer(root.left, root.right);
  }

  private boolean isSymmer(TreeNode left, TreeNode right) {
    if (left == null && right == null) {
      return true;
    }
    if (left == null || right == null) {
      return false;
    }
    if (left.val != right.val) {
      return false;
    }
    return isSymmer(left.left, right.right) && isSymmer(left.right, right.left);
  }

  /**
   * 二叉树的层次遍历.
   */
  public List<List<Integer>> levelOrder(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    List<List<Integer>> result = new LinkedList<>();
    if (root == null) {
      return result;
    }
    queue.add(root);
    while (!queue.isEmpty()) {
      List<Integer> list = new LinkedList<>();
      int cur = queue.size();
      while (cur > 0) {
        cur--;
        TreeNode node = queue.poll();
        list.add(node.val);
        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
      }
      result.add(list);
    }
    return result;
  }

  /**
   * 将有序数组转换为二叉搜索树.
   */
  public TreeNode sortedArrayToBST(int[] nums) {
    return toBST(nums, 0, nums.length - 1);
  }

  private TreeNode toBST(int[] nums, int start, int end) {
    if (start > end) {
      return null;
    }
    int mid = (start + end) / 2;
    TreeNode node = new TreeNode(nums[mid]);
    node.left = toBST(nums, start, mid - 1);
    node.right = toBST(nums, mid + 1, end);
    return node;
  }

}
