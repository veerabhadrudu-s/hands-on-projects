/**
 * 
 */
package com.handson.trees;

/**
 * @author sveera
 *
 */
public class BinaryTreeReverserUsingPostOrderTraversal extends EularTourTreeTraversal<Void> {

	public void reverseTree(BinaryTreeNode<Integer, Integer> tree) {
		treeTraversal(tree);
	}

	@Override
	protected <K, V> void afterRight(BinaryTreeNode<K, V> tree) {
		BinaryTreeNode<K, V> tmpNode = tree.leftChild;
		tree.leftChild = tree.rightChild;
		tree.rightChild = tmpNode;
	}

	@Override
	protected <K, V> Void handleForLeafNode(BinaryTreeNode<K, V> tree) {
		return null;
	}

	@Override
	protected <K, V> void beforeLeftChild(BinaryTreeNode<K, V> tree) {

	}

	@Override
	protected <K, V> Void fromBottom(BinaryTreeNode<K, V> tree) {
		return null;
	}

	@Override
	protected Void combineResult(Void leftNodeResult, Void rightNodeResult) {
		return null;
	}

}
