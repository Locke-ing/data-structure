package BinaryTrees;

import printer.BinaryTrees;

public class Main{
		static void Test1(){
			Integer data[] = new Integer[] {4,3,76,23,1,5,6,9,10};
			BST<Integer> bst1 = new BST<Integer>();
			for(int i = 0;i < data.length;i++) {
				bst1.add(data[i]);
			}
			BinaryTrees.println(bst1);bst1.remove(6);
			System.out.println("删除结点后的二叉树");BinaryTrees.println(bst1);
			System.out.println("翻转二叉树"+bst1.inverseTree());
			System.out.println("前序遍历：");bst1.preorderTraversal();
			System.out.println("中序遍历：");bst1.inorderTraversal();
			System.out.println("后序遍历：");bst1.postorderTraversal();
			
			
			BST<Integer> bst2=new BST<>();
			for(int i=0;i<30;i++) {
				bst2.add((int)(Math.random()*100));
			}
			BinaryTrees.println(bst2);
			System.out.println("树状打印二叉树："+"\n"+bst2);
			System.out.print("非递归求二叉树高度："+bst2.height()+"递归求二叉树高度："+bst2.height1()+"\n");
			System.out.println("是否是完全二叉树："+bst2.isComplete1()+" "+bst2.isComplete2()+"\n");		
			
		}
	public static void main(String[] args) {
		Test1();
	}
	
}