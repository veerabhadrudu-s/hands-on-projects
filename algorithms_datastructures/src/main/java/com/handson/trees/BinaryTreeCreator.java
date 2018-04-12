/**
 * 
 */
package com.handson.trees;

import static com.handson.trees.BinaryTreeUtil.createTreeNode;
import static com.handson.trees.BinaryTreeUtil.linkLeftChildren;
import static com.handson.trees.BinaryTreeUtil.linkRightChildren;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.copyOfRange;

/**
 * @author sveera
 *
 */
public class BinaryTreeCreator {

	public BinaryTreeNode<Integer, Integer> constructTree(String preOrderTraversal, String inOrderTraversal) {
		String[] preOrderTraversalValues = preOrderTraversal.split(",");
		String[] inOrderTraversalValues = inOrderTraversal.split(",");
		return constructTree(preOrderTraversalValues, inOrderTraversalValues);
	}

	private BinaryTreeNode<Integer, Integer> constructTree(String[] preOrderTraversalValues,
			String[] inOrderTraversalValues) {
		if (preOrderTraversalValues.length == 1 && inOrderTraversalValues.length == 1
				&& preOrderTraversalValues[0].equals(inOrderTraversalValues[0]))
			return createTreeNode(parseInt(inOrderTraversalValues[0]));
		int rootNodeIndexInInOrderValues = calculateRootNodeIndexValue(inOrderTraversalValues,
				preOrderTraversalValues[0]);
		BinaryTreeNode<Integer, Integer> rootNode = createTreeNode(
				parseInt(inOrderTraversalValues[rootNodeIndexInInOrderValues]));
		constructLeftSubTree(preOrderTraversalValues, inOrderTraversalValues, rootNodeIndexInInOrderValues, rootNode);
		constructRightSubTree(preOrderTraversalValues, inOrderTraversalValues, rootNodeIndexInInOrderValues, rootNode);
		return rootNode;

	}

	private void constructRightSubTree(String[] preOrderTraversalValues, String[] inOrderTraversalValues,
			int rootNodeIndexInInOrderValues, BinaryTreeNode<Integer, Integer> rootNode) {
		if (inOrderTraversalValues.length - 1 > rootNodeIndexInInOrderValues) {
			BinaryTreeNode<Integer, Integer> rightChild = constructTree(
					copyOfRange(preOrderTraversalValues, rootNodeIndexInInOrderValues + 1,
							preOrderTraversalValues.length),
					copyOfRange(inOrderTraversalValues, rootNodeIndexInInOrderValues + 1,
							inOrderTraversalValues.length));
			linkRightChildren(rootNode, rightChild);
		}
	}

	private void constructLeftSubTree(String[] preOrderTraversalValues, String[] inOrderTraversalValues,
			int rootNodeIndexInInOrderValues, BinaryTreeNode<Integer, Integer> rootNode) {
		if (rootNodeIndexInInOrderValues > 0) {
			BinaryTreeNode<Integer, Integer> leftChild = constructTree(
					copyOfRange(preOrderTraversalValues, 1, rootNodeIndexInInOrderValues + 1),
					copyOfRange(inOrderTraversalValues, 0, rootNodeIndexInInOrderValues));
			linkLeftChildren(rootNode, leftChild);
		}
	}

	private int calculateRootNodeIndexValue(String[] inOrderTraversalValues, String rootValueOfTree) {
		int rootValueIndexOfTree = -1;
		for (int i = 0; i < inOrderTraversalValues.length; i++)
			if (inOrderTraversalValues[i].equals(rootValueOfTree)) {
				rootValueIndexOfTree = i;
				break;
			}
		if (rootValueIndexOfTree == -1)
			throw new RuntimeException();
		return rootValueIndexOfTree;
	}

}
