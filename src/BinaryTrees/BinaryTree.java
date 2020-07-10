package BinaryTrees;

import java.util.LinkedList;
import java.util.Queue;
import printer.BinaryTreeInfo;

public class BinaryTree<E> implements BinaryTreeInfo{
	
	protected Node<E> rootNode;
	protected int size;
	
	public int size() {
		return size();
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		rootNode = null;
		size = 0;
	}
	//树状打印前序遍历
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		toString(rootNode,sb," ");
		return sb.toString();
	}
	private void toString(Node<E>node,StringBuilder sb,String prefix) {
		if(node==null)return;
		sb.append(prefix).append(node.element).append("\n");
		toString(node.leftNode,sb,prefix+"【L】");
		toString(node.rightNode,sb,prefix+"【L】");
	}
	
	public void preorderTraversal() {
		preorderTraversal(rootNode);
	}
	private void preorderTraversal(Node<E> node) {
		if(node==null) return;
		System.out.println(node.element);
		preorderTraversal(node.leftNode);
		preorderTraversal(node.rightNode);
	}
	public void inorderTraversal(){
		inorderTraversal(rootNode);
	}
	private void inorderTraversal(Node<E> node) {
		if(node==null) return;
		inorderTraversal(node.leftNode);
		System.out.println(node.element);
		inorderTraversal(node.rightNode);
	}
	//后序遍历：左子树、右子树、根节点
	public void postorderTraversal(){
		postorderTraversal(rootNode);
	}
	private void postorderTraversal(Node<E> node) {
		if(node==null) return;
		postorderTraversal(node.leftNode);
		postorderTraversal(node.rightNode);
		System.out.println(node.element);
	}
	
	//层序遍历：使用队列：父节点出队的同时，其左右子节点入队，循环直到队列为空
		public void levelOrderTraversal() {
			if(rootNode==null)return;
			Queue<Node<E>> queue=new LinkedList<>();
			queue.offer(rootNode);
			while(!queue.isEmpty()) {
				//出队
				Node<E> node=queue.poll();
				System.out.println(node.element);
				
				if(node.leftNode!=null) {
					queue.offer(node.leftNode);
				}
				if(node.rightNode!=null) {
					queue.offer(node.rightNode);
				}
			}
		}
	
	/*
	 public void postorderTraversal(Visitor<E> visitor){
		if(visitor==null)return;
		postorderTraversal(rootNode, visitor);
	}
	private void postorderTraversal(Node<E> node,Visitor<E> visitor) {
		if(node==null || visitor.stop) return;//递归终止
		postorderTraversal(node.leftNode, visitor);
		postorderTraversal(node.rightNode, visitor);
		if(visitor.stop)return;
		visitor.stop=visitor.visit(node.element);//打印层面停止
	}
	//访问器
	public static abstract class Visitor<E> {
		boolean stop;//抽象类可以定义成员变量
		abstract boolean visit(E element);
	}
	
	public static interface Visitor<E>{
		void visit(E element);
	}
	 */
	
	//前驱节点：中序遍历前一个节点
	protected Node<E> Predcessor(Node<E> node){
		if(node==null) return null;
		
		//前驱节点在左子树中（node.left.right.right...）
		Node<E> p=node.leftNode;
		if(p!=null) {
			while(p.rightNode!=null) {
				p=p.rightNode;
			}
			return p;
		}
		
		//左子树为空：前驱节点（node.parent.parent...）
		while(node.parentNode!=null && node==node.parentNode.leftNode) {
			node=node.parentNode;
		}
		//node.parentNode=null;
		//node==node.parentNode.rightNode;
		return node.parentNode;
	}
	    //递归求二叉树高度
	
	//后驱节点：后序遍历的后一个节点
		protected Node<E> Successor(Node<E> node){
			if(node==null) return null;
			
			//node.right.left.left...）
			Node<E> p=node.rightNode;
			if(p!=null) {
				while(p.leftNode!=null) {
					p=p.leftNode;
				}
				return p;
			}
			//右子树为空：后继节点（node.parent.parent...）
			while(node.parentNode!=null && node==node.parentNode.rightNode) {
				node=node.parentNode;
			}
			//node.parentNode=null;
			//node==node.parentNode.leftNode;
			return node.parentNode;
		}
		//递归二叉树的高度
		public int height1() {
			return height1(rootNode);
		}
		private int height1(Node<E> node) {
			if(node==null) return 0; 
			return 1+Math.max(height1(node.leftNode), height1(node.rightNode));
		}
		//非递归求二叉树高度
		public int height() {
			//非递归求二叉树高度
			int height=0;
			//存储每一层的元素数量
			int levelSize=1;
			Queue<Node<E>> queue=new LinkedList<>();
			queue.offer(rootNode);
			while(!queue.isEmpty()) {
				//出队
				Node<E> node=queue.poll();
				levelSize--;
				
				if(node.leftNode!=null) {
					queue.offer(node.leftNode);
				}
				if(node.rightNode!=null) {
					queue.offer(node.rightNode);
				}
				if(levelSize==0) {//意味着要访问下一层
					levelSize=queue.size();
					height++;
					
				}
			}
			return height;
		}
		
		
		//判断是否是完全二叉树
		public  boolean  isComplete1() {
			if(rootNode==null) return false;
			Queue<Node<E>> queue=new LinkedList<>();
			queue.offer(rootNode);
			boolean leaf=false;
			while(!queue.isEmpty()) {
				//出队
				Node<E> node=queue.poll();
				//要求你是叶子节点，但是不是叶子节点
				if(leaf && !node.isLeaf()) return false;
				if(node.hasTwoChild()) {
					queue.offer(node.leftNode);
					queue.offer(node.rightNode);
				}else if(node.leftNode==null&&node.rightNode!=null) {
					return false;
				}else {//后面遍历的节点都是叶子节点
					leaf=true;
					if(node.leftNode!=null) {
						queue.offer(node.leftNode);
					}
				}
			}
			return true;	
		}
		
		public boolean  isComplete2() {
			if(rootNode==null) return false;
			Queue<Node<E>> queue=new LinkedList<>();
			queue.offer(rootNode);
			boolean leaf =false;
			while(!queue.isEmpty()) {
				//出队
				Node<E> node=queue.poll();
				if(leaf&&!node.isLeaf()) return false;
				if(node.leftNode!=null) {
					queue.offer(node.leftNode);
				}else if(node.rightNode!=null) {//node.left==null
					return false;
				}
				
				if(node.rightNode!=null) {
					queue.offer(node.rightNode);
				}else {
					//node.left==null && node.right==null;
					//node.left!=null && node.right==null;
				leaf=true;
				}
			}
			return true;
		}
		public class BinarySearchTree{
			int val ;
			BinarySearchTree leftNode;
			BinarySearchTree rightNode;
			BinarySearchTree(int x){val = x;};
		}
		public Node<E> inverseTree() {
			if(rootNode == null) return rootNode;
			inverseTree(rootNode.leftNode);
			inverseTree(rootNode.rightNode);
			return rootNode;
		}
	   private void inverseTree(Node<E> leftNode) {
		    Node<E> tmpNode = rootNode.leftNode;
		    rootNode.leftNode =  rootNode.rightNode;
		    rootNode.rightNode = tmpNode;	
		}
	protected static class Node<E>{
		E element;
		Node<E> leftNode;
		Node<E> rightNode;
		Node<E> parentNode;
		public Node(E element,Node<E> parentNode) {
			this.element = element;
			this.parentNode = parentNode;
		}
	public boolean isLeaf() {
		return leftNode==null&&rightNode==null;
	}
	public boolean hasTwoChild() {
		return leftNode!=null&&rightNode!=null;
	}
}
	@Override
	public Object rootNode() {
		return rootNode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object leftNode(Object node) {
		return ((Node<E>)node).leftNode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object rightNode(Object node) {
		return ((Node<E>)node).rightNode;
	}

	@Override
	public Object string(Object node) {
		@SuppressWarnings("unchecked")
		Node<E> myNode = (Node<E>)node;
		@SuppressWarnings("unused")
		String parentString = "null";
		if(myNode.parentNode != null) {
			parentString = myNode.parentNode.element.toString();
		}
		return myNode.element;
	}
	
}
