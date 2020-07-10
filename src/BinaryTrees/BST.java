package BinaryTrees;

import java.util.Comparator;

public class BST <E> extends BinaryTree<E> {
	
	private Comparator<E> comparator;
	
	public BST() {
		this(null);
	}
	public BST(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	//添加结点
	public void add(E element) {
		//结点不为空
		elementNotNullCheck(element);
		
		if(rootNode == null) {
			rootNode = new Node<E>(element,null);
			size++;
			return;
		}
		
		Node<E> parentNode = rootNode;
		Node<E> node = rootNode;
		int cmp=0;
		while(node != null) {
			cmp = compare(element, node.element);
			parentNode = node;
			if(cmp>0) {//添加的比当前结点值大
				node = node.rightNode;
			}else if(cmp<0){
				node = node.leftNode;
			}else {
				node.element=element;
				return;
			}
		}
		
		Node<E> newNode = new Node<E>(element, parentNode);
		if(cmp>0) {//比父节点大
			parentNode.rightNode = newNode;
		}else {
			parentNode.leftNode = newNode;
		}
		size++;
	}
			
	public boolean contains(E e) {
		return false;
	}
	//先根据节点值，找到被删对象，然后再通过remove方法删除节点
		public void remove(E element) {
			remove(node(element));
			
		}
		
		private void remove(Node<E> node) {
			if(node==null) return;
			size--;
			if(node.hasTwoChild()) {
				//找到后继节点
				Node<E> s=Predcessor(node);
				//用后继节点的值覆盖度为2的节点的值
				node.element=s.element;
				//删除后继节点
				node=s; 
			}
			//删除node节点(度为1或0)
			Node<E> replacementNode=node.leftNode!=null ? node.leftNode :node.rightNode;
			
			if(replacementNode!=null) {//node是度为1的节点
				//更改parent
				replacementNode.parentNode=node.parentNode;
				//更改parent的left和right
				if(node.parentNode==null) {
					rootNode=replacementNode;
				}else if(node==node.parentNode.leftNode) {
					node.parentNode.leftNode=replacementNode;
				}else if(node==node.parentNode.rightNode) {
					node.parentNode.rightNode=replacementNode;
				}	
			}else if(node.parentNode==null) {//叶子节点并且是根节点
				rootNode=null;
			}else {//node是叶子节点，但不是根节点
				if(node==node.parentNode.rightNode) {
					node.parentNode.rightNode=null;
				}else {
					node.parentNode.leftNode=null;
				}
			
			}
			
		}
		//找删除的节点
		private Node<E> node(E element){
			Node<E> node=rootNode;
			while(node!=null) {
			int cmp=compare(element,node.element);
			if(cmp==0) return node;
			if(cmp>0) {
				node=node.rightNode;
			}else {
				node=node.leftNode ;
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private int compare(E e1,E e2) {
		if(comparator != null) {
			return comparator.compare(e1, e2);
		}
		//没有比较器
		return ((Comparable<E>)e1).compareTo(e2);
	}
	
	private void elementNotNullCheck(E element) {
		if(element == null) {
			throw new IllegalAccessError("element is not null!");
		}
	}
	
}
	
	