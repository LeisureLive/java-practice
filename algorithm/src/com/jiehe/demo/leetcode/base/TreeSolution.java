package com.jiehe.demo.leetcode.base;

import java.util.ArrayList;
import java.util.Arrays;
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

  public static class TreeNode {

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
   * 从中序与后序遍历序列构造二叉树.
   */
  public TreeNode buildTree(int[] inorder, int[] postorder) {
    assert inorder.length == postorder.length;
    if (inorder.length == 0) {
      return null;
    }
    if (inorder.length == 1) {
      return new TreeNode(inorder[0]);
    }
    int rootVal = postorder[postorder.length - 1];
    TreeNode root = new TreeNode(rootVal);
    int i = 0;
    for (; i < inorder.length; i++) {
      if (inorder[i] == rootVal) {
        break;
      }
    }
    root.left = buildTree(Arrays.copyOfRange(inorder, 0, i), Arrays.copyOfRange(postorder, 0, i));
    root.right = buildTree(Arrays.copyOfRange(inorder, i + 1, inorder.length),
        Arrays.copyOfRange(postorder, i + 1, postorder.length));
    return root;
  }

  /**
   * 从前序与中序遍历序列构造二叉树.
   */
  public static TreeNode buildTreeV2(int[] preorder, int[] inorder) {
    return buildHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
  }

  private static TreeNode buildHelper(int[] preorder, int pres, int pree, int[] inorder,
      int ins, int ine) {
    assert pree - pres == ine - ins;
    if (pree - pres < 0) {
      return null;
    }
    if (pree - pres == 0) {
      return new TreeNode(preorder[pres]);
    }
    int rootVal = preorder[pres];
    int i = ins;
    for (; i < ine; i++) {
      if (inorder[i] == rootVal) {
        break;
      }
    }
    TreeNode root = new TreeNode(rootVal);
    root.left = buildHelper(preorder, pres + 1, pres + i - ins, inorder, ins, i - 1);
    root.right = buildHelper(preorder, pres + 1 + i - ins, pree, inorder, i + 1, ine);
    return root;
  }

  /**
   * 二叉树的最近公共祖先.
   */
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root.val == p.val || root.val == q.val) {
      return root;
    }
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    // 两个节点一个在当前节点左子树一个在右子树，则当前节点为最小公共祖先
    if (left != null && right != null) {
      return root;
    }
    // 如果两个节点都在左子树，则递归处理左子树；如果两个节点都在右子树，则递归处理右子树
    return left == null ? right : left;
  }

  /**
   * 按层遍历二叉树，用一个队列保存每一层的节点.
   */
  public String serialize(TreeNode root) {
    if (root == null) {
      return "";
    }
    StringBuilder builder = new StringBuilder();
    Deque<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      int count = queue.size();
      while (count-- > 0) {
        TreeNode node = queue.removeFirst();
        //这个优化点很重要，当遍历到null时，不要往队列写入null子节点，减少生成的字符串大小
        if (node != null) {
          queue.add(node.left);
          queue.add(node.right);
          builder.append(",").append(node.val);
        } else {
          builder.append(",null");
        }
      }
    }
    //删除第一个逗号
    builder.deleteCharAt(0);
    return builder.toString();
  }

  /**
   * 反序列化的时候，用两个指针，第一个指向父节点，第二个指向子节点.
   */
  public TreeNode deserialize(String data) {
    if (data == null || data.length() == 0) {
      return null;
    }
    String[] values = data.split(",");
    List<TreeNode> list = new LinkedList<>();
    TreeNode head = createNode(values[0]);
    list.add(head);
    int rootIndex = 0;
    int valueIndex = 1;
    while (rootIndex < list.size()) {
      TreeNode root = list.get(rootIndex++);
      if (valueIndex < values.length) {
        root.left = createNode(values[valueIndex++]);
        root.right = createNode(values[valueIndex++]);
      }
      if (root.left != null) {
        list.add(root.left);
      }
      if (root.right != null) {
        list.add(root.right);
      }
    }
    return head;
  }

  private TreeNode createNode(String str) {
    if (str == null) {
      return null;
    }
    if (str.equalsIgnoreCase("null")) {
      return null;
    } else {
      int integer = Integer.parseInt(str);
      return new TreeNode(integer);
    }
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
