/**
 * 
 */
package com.handson.trees;

/**
 * @author sveera
 *
 */
public abstract class EularTourTreeTraversal<R> {

	protected <K, V> R treeTraversal(BinaryTreeNode<K, V> tree) {
		if (tree == null)
			return null;
		if (tree.leftChild == null && tree.rightChild == null)
			return handleForLeafNode(tree);
		else {
			beforeLeftChild(tree);
			R leftNodeResult = treeTraversal(tree.leftChild);
			fromBottom(tree);
			R rightNodeResult = treeTraversal(tree.rightChild);
			afterRight(tree);
			return combineResult(leftNodeResult, rightNodeResult);
		}

	}

	protected abstract <K, V> R handleForLeafNode(BinaryTreeNode<K, V> tree);

	protected abstract <K, V> void beforeLeftChild(BinaryTreeNode<K, V> tree);

	protected abstract <K, V> R fromBottom(BinaryTreeNode<K, V> tree);

	protected abstract <K, V> void afterRight(BinaryTreeNode<K, V> tree);

	protected abstract R combineResult(R leftNodeResult, R rightNodeResult);

}
