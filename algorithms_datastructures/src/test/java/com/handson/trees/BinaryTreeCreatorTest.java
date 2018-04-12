/**
 * 
 */
package com.handson.trees;

import static com.handson.trees.BinaryTreeUtil.createTreeNode;
import static com.handson.trees.BinaryTreeUtil.createTwoChildrenNode;
import static com.handson.trees.BinaryTreeUtil.linkLeftChildren;
import static com.handson.trees.BinaryTreeUtil.linkParentWithChildren;
import static com.handson.trees.BinaryTreeUtil.linkRightChildren;
import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author sveera
 *
 */
public class BinaryTreeCreatorTest {

	private BinaryTreeCreator binaryTreeCreator;

	@BeforeEach
	public void setUp() throws Exception {
		binaryTreeCreator = new BinaryTreeCreator();
	}

	@Test
	@DisplayName("test Construct Tree For Root Node")
	public void testConstructTreeForRootNode() {
		String preOrderTraversal = "1", inOrderTraversal = "1";
		BinaryTreeNode<Integer, Integer> actualBinaryTree = binaryTreeCreator.constructTree(preOrderTraversal,
				inOrderTraversal);
		assertEquals(createTreeNode(1), actualBinaryTree);
	}

	@Test
	@DisplayName("test Construct Tree For Root Node With Left Leaf")
	public void testConstructTreeForRootNodeWithLeftLeaf() {
		String preOrderTraversal = "1,2", inOrderTraversal = "2,1";
		BinaryTreeNode<Integer, Integer> actualBinaryTree = binaryTreeCreator.constructTree(preOrderTraversal,
				inOrderTraversal);
		BinaryTreeNode<Integer, Integer> rootNode = createTreeNode(1);
		BinaryTreeNode<Integer, Integer> leftChild = createTreeNode(2);
		linkLeftChildren(rootNode, leftChild);
		assertEquals(rootNode, actualBinaryTree);
	}

	@Test
	@DisplayName("test Construct Tree For Root Node With Right Leaf")
	public void testConstructTreeForRootNodeWithRightLeaf() {
		String preOrderTraversal = "1,2", inOrderTraversal = "1,2";
		BinaryTreeNode<Integer, Integer> actualBinaryTree = binaryTreeCreator.constructTree(preOrderTraversal,
				inOrderTraversal);
		BinaryTreeNode<Integer, Integer> rootNode = createTreeNode(1);
		BinaryTreeNode<Integer, Integer> rightChild = createTreeNode(2);
		linkRightChildren(rootNode, rightChild);
		assertEquals(rootNode, actualBinaryTree);
	}

	@DisplayName("test Construct Tree For Root Node With Two Leaves")
	@ParameterizedTest(name = "with Pre-order string {0} and In-order string {1}")
	@CsvSource({ "'1,2,3','2,1,3'", "'1,3,2', '3,1,2" })
	public void testConstructTreeForRootNodeWithWithTwoLeaves(String preOrderTraversal, String inOrderTraversal) {
		String[] inOrderTraversalValues = inOrderTraversal.split(",");
		BinaryTreeNode<Integer, Integer> rootNode = createTreeNode(parseInt(inOrderTraversalValues[1]));
		BinaryTreeNode<Integer, Integer> rightChild = createTreeNode(parseInt(inOrderTraversalValues[2]));
		BinaryTreeNode<Integer, Integer> leftChild = createTreeNode(parseInt(inOrderTraversalValues[0]));
		BinaryTreeNode<Integer, Integer> actualBinaryTree = binaryTreeCreator.constructTree(preOrderTraversal,
				inOrderTraversal);
		linkParentWithChildren(rootNode, leftChild, rightChild);
		assertEquals(rootNode, actualBinaryTree);
	}

	@Test
	@DisplayName("test Construct Complete Binary Tree of Height 2 ")
	public void testConstructCompleteBinaryTreeOfHeight2() {
		String preOrderTraversal = "1,2,4,5,3,6,7", inOrderTraversal = "4,2,5,1,6,3,7";
		BinaryTreeNode<Integer, Integer> actualBinaryTree = binaryTreeCreator.constructTree(preOrderTraversal,
				inOrderTraversal);
		BinaryTreeNode<Integer, Integer> rootNode = createTreeNode(1);
		BinaryTreeNode<Integer, Integer> leftSubTree = createTwoChildrenNode(2, 4, 5);
		BinaryTreeNode<Integer, Integer> rightSubTree = createTwoChildrenNode(3, 6, 7);
		linkParentWithChildren(rootNode, leftSubTree, rightSubTree);
		assertEquals(rootNode, actualBinaryTree);
	}

}
