/**
 * 
 */
package com.handson.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author sveera
 *
 */
public class BinaryTreeReverserUsingPostOrderTraversalTest {

	private BinaryTreeReverserUsingPostOrderTraversal binaryTreeReverser;

	@BeforeEach
	public void setUp() {
		binaryTreeReverser = new BinaryTreeReverserUsingPostOrderTraversal();
	}

	@Test
	@DisplayName("test Reverse Root Only Tree")
	public void testReverseRoot() {
		BinaryTreeNode<Integer, Integer> root = createTreeNode(1);
		binaryTreeReverser.reverseTree(root);
		assertEquals(root, root);
	}

	@Test
	@DisplayName("test Reverse Tree With Single Right Node")
	public void testReverseTreeWithSingleRightNode() {
		BinaryTreeNode<Integer, Integer> expectedTree = createTreeNode(1);
		BinaryTreeNode<Integer, Integer> leftNode = createTreeNode(2);
		expectedTree.leftChild = leftNode;

		BinaryTreeNode<Integer, Integer> root = createTreeNode(1);
		BinaryTreeNode<Integer, Integer> rightNode = createTreeNode(2);
		root.rightChild = rightNode;
		binaryTreeReverser.reverseTree(root);
		assertEquals(expectedTree, root);
	}

	@Test
	@DisplayName("test Reverse Tree With Single Left Node")
	public void testReverseTreeWithSingleLeftNode() {
		BinaryTreeNode<Integer, Integer> expectedTree = createTreeNode(1);
		BinaryTreeNode<Integer, Integer> rightNode = createTreeNode(2);
		expectedTree.rightChild = rightNode;

		BinaryTreeNode<Integer, Integer> root = createTreeNode(1);
		BinaryTreeNode<Integer, Integer> leftNode = createTreeNode(2);
		root.leftChild = leftNode;
		binaryTreeReverser.reverseTree(root);
		assertEquals(expectedTree, root);
	}

	@Test
	@DisplayName("test Reverse Tree With Two Nodes")
	public void testReverseTreeWithTwoNode() {
		BinaryTreeNode<Integer, Integer> expectedTree = createTwoChildrenNode(1, 3, 2);
		BinaryTreeNode<Integer, Integer> root = createTwoChildrenNode(1, 2, 3);
		binaryTreeReverser.reverseTree(root);
		assertEquals(expectedTree, root);
	}

	@Test
	@DisplayName("test Reverse Tree With Height 2")
	public void testReverseTreeWithHeightTwo() {
		BinaryTreeNode<Integer, Integer> expectedTreeRoot = createTreeNode(1);
		BinaryTreeNode<Integer, Integer> expectedLeftChild = createTwoChildrenNode(3, 7, 6);
		BinaryTreeNode<Integer, Integer> expectedRightChild = createTwoChildrenNode(2, 5, 4);
		linkParentChildren(expectedTreeRoot, expectedLeftChild, expectedRightChild);
		BinaryTreeNode<Integer, Integer> root = createTreeNode(1);
		BinaryTreeNode<Integer, Integer> leftChild = createTwoChildrenNode(2, 4, 5);
		BinaryTreeNode<Integer, Integer> rightChild = createTwoChildrenNode(3, 6, 7);
		linkParentChildren(root, leftChild, rightChild);
		binaryTreeReverser.reverseTree(root);
		assertEquals(expectedTreeRoot, root);
	}

	private BinaryTreeNode<Integer, Integer> createTwoChildrenNode(int nodeKeyAndValue, int leftChildKeyAndValue,
			int rightChildKeyAndValue) {
		BinaryTreeNode<Integer, Integer> node = createTreeNode(nodeKeyAndValue);
		BinaryTreeNode<Integer, Integer> leftChild = createTreeNode(leftChildKeyAndValue);
		BinaryTreeNode<Integer, Integer> rightChild = createTreeNode(rightChildKeyAndValue);
		linkParentChildren(node, leftChild, rightChild);
		return node;
	}

	private BinaryTreeNode<Integer, Integer> createTreeNode(int keyAndValue) {
		BinaryTreeNode<Integer, Integer> treeNode = new BinaryTreeNodeForReverseTest<>();
		treeNode.key = keyAndValue;
		treeNode.value = keyAndValue;
		return treeNode;
	}

	private void linkParentChildren(BinaryTreeNode<Integer, Integer> node, BinaryTreeNode<Integer, Integer> leftChild,
			BinaryTreeNode<Integer, Integer> rightChild) {
		node.leftChild = leftChild;
		leftChild.parentNode = node;
		node.rightChild = rightChild;
		rightChild.parentNode = node;
	}

}
