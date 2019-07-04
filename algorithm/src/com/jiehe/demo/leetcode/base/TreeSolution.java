package com.jiehe.demo.leetcode.base;

import java.util.ArrayList;
import java.util.Collections;
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
   * 对称二叉树(递归).
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
    return isSymmer(left.right, right.left) && isSymmer(left.left, right.right);
  }

  /**
   * 对称二叉树(非递归).
   */
  public boolean isSymmetricV2(TreeNode root) {
    if (root == null) {
      return true;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root.left);
    queue.add(root.right);
    while (!queue.isEmpty() && queue.size() >= 2) {
      TreeNode node1 = queue.poll();
      TreeNode node2 = queue.poll();
      if (node1 == null && node2 == null) {
        continue;
      }
      if (node1 == null || node2 == null) {
        return false;
      }
      if (node1.val != node2.val) {
        return false;
      }
      queue.add(node1.left);
      queue.add(node2.right);
      queue.add(node1.right);
      queue.add(node2.left);
    }
    return queue.isEmpty();
  }

  /**
   * 根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和.
   */
  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) {
      return false;
    }
    if (root.left == null && root.right == null) {
      return sum == root.val;
    }
    return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
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

  /**
   * 前序遍历(递归).
   */
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    preorderTravelTree(root, res);
    return res;
  }

  private void preorderTravelTree(TreeNode node, List<Integer> list) {
    if (node == null) {
      return;
    }
    list.add(node.val);
    preorderTravelTree(node.left, list);
    preorderTravelTree(node.right, list);
  }

  /**
   * 前序遍历(非递归).
   */
  public List<Integer> preorderTraversalV2(TreeNode root) {
    Deque<TreeNode> stack = new LinkedList<>();
    List<Integer> list = new ArrayList<>();
    TreeNode node = root;
    while (!stack.isEmpty() || node != null) {
      if (node != null) {
        stack.push(node);
        list.add(node.val);
        node = node.left;
      } else {
        node = stack.pop();
        node = node.right;
      }
    }
    return list;
  }

  /**
   * 中序遍历(递归).
   */
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    inorderTravelTree(root, list);
    return list;
  }

  private void inorderTravelTree(TreeNode node, List<Integer> list) {
    if (node == null) {
      return;
    }
    inorderTravelTree(node.left, list);
    list.add(node.val);
    inorderTravelTree(node.right, list);
  }

  /**
   * 中序遍历(非递归).
   */
  public List<Integer> inorderTraversalV2(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    Deque<TreeNode> stack = new LinkedList<>();
    TreeNode node = root;
    while (node != null || !stack.isEmpty()) {
      if (node != null) {
        stack.push(node);
        node = node.left;
      } else {
        node = stack.pop();
        list.add(node.val);
        node = node.right;
      }
    }
    return list;
  }

  /**
   * 后序遍历(递归).
   */
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    postorderTravelTree(root, list);
    return list;
  }

  private void postorderTravelTree(TreeNode node, List<Integer> list) {
    if (node == null) {
      return;
    }
    postorderTravelTree(node.left, list);
    postorderTravelTree(node.right, list);
    list.add(node.val);
  }

  /**
   * 后序遍历(非递归),一个记录了根右左之后的列表逆序输出.
   */
  public List<Integer> postorderTraversalV2(TreeNode root) {
    Deque<TreeNode> stack = new LinkedList<>();
    List<Integer> list = new ArrayList<>();
    TreeNode node = root;
    while (!stack.isEmpty() || node != null) {
      if (node != null) {
        stack.push(node);
        list.add(node.val);
        node = node.right;
      } else {
        node = stack.pop();
        node = node.left;
      }
    }
    Collections.reverse(list);
    return list;
  }

  /**
   * 二叉树的层次遍历.
   */
  public List<List<Integer>> levelOrder(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    List<List<Integer>> res = new ArrayList<>();
    // 边缘条件，直接是一个空树
    if (root == null) {
      return res;
    }
    queue.add(root);
    int count = 1;
    while (!queue.isEmpty()) {
      List<Integer> list = new ArrayList<>();
      TreeNode node;
      int temp = 0;
      while (count-- > 0) {
        node = queue.poll();
        list.add(node.val);
        if (node.left != null) {
          queue.add(node.left);
          temp++;
        }
        if (node.right != null) {
          queue.add(node.right);
          temp++;
        }
      }
      res.add(list);
      count = temp;
    }
    return res;
  }

}
