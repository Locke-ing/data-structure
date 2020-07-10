package printer;

public interface BinaryTreeInfo {
	/**
	 * who is the root node
	 */
	Object rootNode();
	/**
	 * how to get the left child of the node
	 */
	Object leftNode(Object node);
	/**
	 * how to get the right child of the node
	 */
	Object rightNode(Object node);
	/**
	 * how to print the node
	 */
	Object string(Object node);
}