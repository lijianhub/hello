
package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 *
 */
public class Interview {

	public Interview() {
		printf("Interview()");
	}

	private static void printf(String format, Object... args) {
		System.out.printf(format, args);
	}
	
	/*gcd*/
	private int gcd(int m, int n) {
		int rem = 0;
		if (0 == m || 0 == n) {
			return 0;
		}
		while (n > 0) {
			rem = m % n;
			m = n;
			n = rem;
		}
		return m;
	}
	
	/*binary search*/
	private int binarySearch(int [] x, int t) {
		int n = x.length;
		printf("n="+n+"\n");
		int l = 0, u = n-1, m = 0;
		if (l > u) {
			return -1;
		}
		while (l <= u) {
			m = (l + u) / 2;
			//printf("m=%d\n", m);
			if (x[m] > t) {
				u = m - 1;
			} else if (x[m] == t) {
				return m;
			} else {// x[m] < t
				l = m + 1;
			}
		}
		return -1;
	}
	
	String FILE = "c://lijian.log";
	private void saveFile() {
		try {
			BufferedWriter buf = new BufferedWriter(new FileWriter(FILE));
			buf.write("hello\n");	    	
	    	buf.close();
		} catch (IOException e) {	
		}
	}

	private void readFile() {
		String inputLine;
		try {
			BufferedReader buf = new BufferedReader(new FileReader(FILE), 8192);
			while((inputLine = buf.readLine()) != null){
				printf(inputLine+"\n");
			}
			buf.close();
		} catch (IOException e) {
		}		
	}
	
	private void tail(int K) {
		String inputLine;
		try {
			if (K <= 0) 
				return;
			String FILE = "c://bsmain_runtime.log";
			String[] loop_buf = new String[K];
			BufferedReader buf = new BufferedReader(new FileReader(FILE), 8192);
			int lineid = 0;
			while((inputLine = buf.readLine()) != null){
				loop_buf[lineid%K] = new String(inputLine);
				lineid++;
			}
			buf.close();
			
			int start = 0;
			int count = 0;
			if (lineid < K) {
				start = 0;
				count = lineid;
			} else {
				start = lineid % K; 
				count = K;
			}
			
			printf("The last %d lines:\n", count);
			for (int i = 0; i < count; i++) {
				printf(loop_buf[(start+i) % K] + "\n");
			}
		} catch (IOException e) {
		}		
	}
	
	public class MapNode {
		public Integer key;
		public int data;
		
		public MapNode(int k, int d) {
			key = new Integer(k);
			data = d;
		}
	}
	
	public Map<Integer, MapNode> writeMap(MapNode[] node) {
		Map<Integer, MapNode> map = new HashMap<Integer, MapNode>();
		for (MapNode m : node){
			map.put(m.key, m);
		}
		return map;
	}
	
	public void readMap(Map<Integer, MapNode> map) {
		for (Map.Entry<Integer, MapNode> en: map.entrySet()){
			printf("key=%d, data=%s\n", en.getKey(), en.getValue().data);
		}
	}
	
	public class Node {
		private Node next;
		private int id;
		
		public Node(int value) {
			this.id = value;
			this.next = null;
		}
		
		public int getId() {
			return id;
		}		
	}
	
	public void listAdd(Node head, int value) {
		if (null != head.next) {
			Node n = head;
			while (null != n.next) {
				n = n.next;
			}
			n.next = new Node(value);
		} else {
			head.next = new Node(value);
		}
	}
	
	public int listDelete(Node head, int value) {
		if (null == head.next)
			return -1;

		Node n = head;
		while (null != n.next) {
			//find it
			if (value == n.next.getId()) {
				n.next = n.next.next;
				return 0;
			}
			n = n.next;
		}
		return -1;
	}
	
	public void listAddHead(Node head, int value) {
		Node node = new Node(value);
		node.next = head.next;
		head.next = node;
	}
	
	public void listPrint(Node head) {
		for (Node n = head; n != null; n = n.next) {
			printf("id=%d\n", n.getId());
		}
	}
	
	private class Stack {
		private Node top;		
		
		public Stack() {
			top = new Node(-1);
			top.next = null;
		}

		public void push(int value) {
			Node node = new Node(value);
			node.next = top.next;
			top.next = node;
		}

		public int pop() {
			if (null != top.next) {
				Node node = top.next;
				top.next = node.next;
				return (node.getId());
			}
			printf("stack is empty\n");
			return -1;
		}
	}
	
	private class Queue {
		private Node first, last;
		public Queue() {
			first = null;
			last = null;
		}
		
		public void enqueue(int value) {
			Node node = new Node(value);
			if (null != first) {
				last.next = node;
				last = node;
			} else {
				last = node;
				first = last;
			}
		}
		
		public int dequeue() {
			Node node = first;
			if (first != null) {
				first = first.next;
				return node.getId();
			} else {
				printf("Queue is empty\n");
				return -1;
			}
		}
	}
	
	private void prime() {
		int count = 0;
		boolean flag = false;   
		for (int m = 2; m <= 100; m++) {
			flag = true;
			for(int i = 2; i < m; i++){
			    if (m % i == 0) {
			    	flag = false;
			    	break;
			    }				  
			}
			if(flag == true) {
				System.out.println("prime:"+m);
				count++;
			}
		}
		System.out.println("100 inner: prime count="+count);
	}
	
	private void primefilter() {
		int flag[] = new int[101];
		for (int i = 0; i <= 100; i++) {
			flag[i] = 1;
		}

		for (int m = 2; m <= 100; m++) {
			if (0 == flag[m])
				continue;
			for (int n = m + m; n <= 100; n += m) {
				if (n % m == 0)
					flag[n] = 0;
			}
		}
		
		int count = 0;
		for (int k = 2; k <= 100; k++) {
			if (1 == flag[k]) {
				count++;
				System.out.println("prime number:"+k);
			}
		}
		System.out.println("primefilter: the number of prime ="+count);
	}
	
	private void primesqrt() {
		int count = 0;
		for (int i = 2; i <= 100; i++) {
			int sqt = (int)Math.sqrt(i);
			int j = 0;
			for (j = 2; j <= sqt; j++) {
				if (i % j == 0)
					break;
			}
			if (j > sqt) {
				count++;
				System.out.println("prime:"+i);
			}
		}		
		System.out.println("primesqrt: the number of prime ="+count);
	}
	
	/*
	//Binary tree
	class BinaryTree {
		int key;
		int data;
		BinaryTree parent;
		BinaryTree left;
		BinaryTree right;
		
		public BinaryTree(int k, int d) {
			this.key = k;
			this.data = d;
			parent = null;
			left = null;
			right = null;
		}		
	}
	
	
	private BinaryTree tree = null;
	
	public void insertTree(int key, int data) {
		if (null == tree) {
			tree = new BinaryTree(key, data);
		} else {
			BinaryTree newtree = new BinaryTree(key, data);
			BinaryTree parent = tree;
			
			while (true) {
				if (newtree.key < parent.key) {
					if (null == parent.left) {
						parent.left = newtree;
						return;
					} else {
						parent = parent.left;
					}
						
				} else if (newtree.key > parent.key) {
					if (null == parent.right) {
						parent.right = newtree;
						return;
					} else {
						parent = parent.right;
					}
				} else { //newtree.key == parent.key
					parent = parent.right;
				}
			}
		}
	}
	
	public BinaryTree findTree(int key) {
		if (null == tree) {
			return null;
		}
		BinaryTree parent = tree;
		while (true) {
			if (null == parent) {
				return null;
			}
			if (parent.key == key) {
				return parent;
			} else if (parent.key < key) {
				parent = parent.right;
			} else {
				parent = parent.left;
			}
		}
	}
	
	public void inOrderTree(BinaryTree tree) {
		if (tree != null) {
			inOrderTree(tree.left);
			System.out.println(tree+"key="+tree.key+",data="+tree.data);
			inOrderTree(tree.right);			
		}
	}
	
	public void preOrderTree(BinaryTree tree) {
		if (tree != null) {
			System.out.println(tree+"key="+tree.key+",data="+tree.data);
			preOrderTree(tree.left);
			preOrderTree(tree.right);
		}
	}

	public void postOrderTree(BinaryTree tree) {
		if (tree != null) {
			postOrderTree(tree.left);
			postOrderTree(tree.right);
			System.out.println(tree+"key="+tree.key+",data="+tree.data);
		}
	}*/
	
	private class BTree {
		int key;
		int data;
		private BTree parent, left, right;
		
		public BTree(int k, int d) {
			key = k;
			data = d;
			parent = null; 
			left = null;
			right = null;
		}
	}
	
	private BTree btree = null;
	
	public void treeInsert(int key ,int data) {
		if (null == btree) {
			btree = new BTree(key, data);
		} else {
			BTree parent = btree;
			BTree node = new BTree(key, data);
			
			while (true) {
				if (parent.key > node.key) {
					if (parent.left != null) {
						parent = parent.left;
					} else {
						parent.left = node;
						return;
					}
				} else if (parent.key == node.key) {
					if (parent.right != null ) {
						parent = parent.right;
					} else {
						parent.right = node;
						return;
					}
				} else if (parent.key < node.key) {
					parent = parent.right;
				}
			}
		}
	}
	
	public void treeInorder(BTree tree) {
		if (null == tree) 
			return;
		
		treeInorder(tree.left);
		System.out.println("node="+tree+" key="+tree.key+" data"+tree.data);
		treeInorder(tree.right);
	}
	
	public void treePreorder(BTree tree) {
		if (null == tree)
			return;
		System.out.println("node="+tree+" key="+tree.key+" data"+tree.data);
		treePreorder(tree.left);
		treePreorder(tree.right);
	}
	
	public void treePostorder(BTree tree) {
		if (null == tree)
			return;

		treePostorder(tree.left);
		treePostorder(tree.right);
		System.out.println("node="+tree+" key="+tree.key+" data"+tree.data);
	}

	public BTree treeFind(BTree tree, int key) {
		if (null == tree)
			return null;
		
		BTree parent = tree;
		while (true) {
			if (null == parent)
				return null;
			if (parent.key == key) {
				return parent;
			} else if (parent.key > key) {
				parent = parent.left;				
			} else {
				parent = parent.right;
			}
		}
	}
	
	public static void main(String[] args) {
		Interview view = new Interview();
		
		//gcd
		printf("gcd = %d\n", view.gcd(15, 7));
		
		//Binary serach
		int [] x = {10,20,30,40,50,60,70,80,90,100,110,120,130};
		int t = 61;
		printf("Binary search = %d\n", view.binarySearch(x, t));
		
		//File w/r
		view.saveFile();
		view.readFile();
		
		//Tail K lines
		view.tail(10);
		
		//Map
		MapNode[] node = {view.new MapNode(1,2), view.new MapNode(3,4), view.new MapNode(5,6)};
		Map <Integer, MapNode> map = view.writeMap(node);
		view.readMap(map);

		//List
		Node head = view.new Node(-1);
		view.listAdd(head, 1);
		view.listAdd(head, 2);
		view.listAdd(head, 3);
		view.listAdd(head, 4);
		view.listPrint(head);	
		view.listDelete(head, 4);
		view.listPrint(head);
		
		//Stack
		Stack stack = view.new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		printf("stack %d\n", stack.pop());
		printf("stack %d\n", stack.pop());
		printf("stack %d\n", stack.pop());
		printf("stack %d\n", stack.pop());
		printf("stack %d\n", stack.pop());

		//Queue
		Queue queue = view.new Queue();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		printf("queue %d\n", queue.dequeue());
		printf("queue %d\n", queue.dequeue());
		printf("queue %d\n", queue.dequeue());
		printf("queue %d\n", queue.dequeue());
		printf("queue %d\n", queue.dequeue());
		
		//Prime
		view.prime();
		view.primefilter();
		view.primesqrt();
		
		//Binary Tree
		/*view.insertTree(5, 55);
		view.insertTree(2, 22);
		view.insertTree(3, 33);
		view.insertTree(1, 11);
		view.insertTree(6, 66);
		
		view.inOrderTree(view.tree);
		view.preOrderTree(view.tree);
		view.postOrderTree(view.tree);*/
		
		view.treeInsert(5, 55);
		view.treeInsert(2, 22);
		view.treeInsert(1, 11);
		view.treeInsert(3, 33);
		view.treeInsert(6, 66);
				
		view.treeInorder(view.btree);
		view.treePreorder(view.btree);
		view.treePostorder(view.btree);
		
		
	}
	
	
}
