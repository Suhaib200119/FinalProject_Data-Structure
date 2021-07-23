package finalproject;

import java.util.Iterator;

import finalproject.Position;

public class BTree<T> extends AbstractBinaryTree<T> {
	private static class Node<T> implements Position<T>{
		private T element;
		private Node<T> left;
		private Node<T> right;
		private Node<T> parent;
		
		public void setElement(T element) {
			this.element=element;
		}
                		@Override
		public T getElement() {
			return this.element;
		}
		public void setLeft(Node<T> left) {
			this.left=left;
		}
		public Node<T> getLeft() {
			return this.left;
		}
		public void setRight(Node<T> right) {
			this.right=right;
		}
		public Node<T> getRight() {
			return this.right;
		}
		public void setParent(Node<T> parent) {
			this.parent=parent;
		}
		public Node<T> getParent() {
			return this.parent;
		}
		public Node(T element,Node<T> parent,Node<T> left,Node<T> right) {
			this.element=element;
			this.parent=parent;
			this.left=left;
			this.right=right;
		}
		public Node(T element) {
			this(element,null,null,null);
		}

	}//inner class
	private Node<T> root;
	private int size=0;
	
	private Node<T> validate(Position<T> position){
		if(!(position instanceof Node))
			throw new IllegalArgumentException("Invalid Position");
		Node<T> node=(Node<T>) position;
		if(node.getParent()==node)
			throw new IllegalArgumentException("Node no longer in the tree");
		return node;
	}
	@Override
	public Position<T> left(Position<T> position) {
		Node<T> node=this.validate(position);
		return node.getLeft();
	}

	@Override
	public Position<T> right(Position<T> position) {
		Node<T> node=this.validate(position);
		return node.getRight();
	}

	@Override
	public Position<T> root() {
		return root;
	}

	@Override
	public Position<T> parent(Position<T> position) throws IllegalArgumentException {
		Node<T> node=this.validate(position);
		return node.getParent();
	}

	@Override
	public int size() {
		return size;
	}
	public Position<T> addRoot(T element){
		if(!isEmpty())
			throw new IllegalArgumentException("Tree is not Empty");
		root=new Node(element);
		size=1;
		return root;
	}
	public T remove(Position<T> position){
		if(numOfChildren(position)==2)
			throw new IllegalArgumentException("node has two children");
		Node<T> node=this.validate(position);
		Node<T> child=(node.getLeft()!=null)? node.getLeft():node.getRight();
		if(child!=null)
			child.setParent(node.getParent());
		if(node==root)
			root=child;
		else {
			Node<T> parent=node.getParent();
			if(node==parent.getLeft())
				parent.setLeft(child);
			else
				parent.setRight(child);
		}
		size--;
		T element=node.getElement();
		node.setLeft(null);
		node.setRight(null);
		node.setElement(null);
		node.setParent(node);
		return element;	
	}
	public Position<T> addLeft(Position<T>p,T element){
		Node<T> parent=this.validate(p);
		if(parent.getLeft()!=null)
			throw new IllegalArgumentException("The node already has left child");
                     //   System.out.println("The node already has left child");
  
		Node<T>left=new Node(element,parent,null,null);
		parent.setLeft(left);
		size++;
		return left;
	}
	public Position<T> addRight(Position<T>p,T element){
		Node<T> parent=this.validate(p);
		if(parent.getRight()!=null)
			throw new IllegalArgumentException("The node already has right child");
                      // System.out.println("The node already has left child");
		Node<T>right=new Node(element,parent,null,null);
		parent.setRight(right);
		size++;
		return right;
	}
	public T set(Position<T> p,T element) {
		Node<T> node=this.validate(p);
		T temp=node.getElement();
		node.setElement(element);
		return element;
		
	}
	public void attach(Position<T> p,BTree<T> t1,BTree<T> t2) {
		Node<T> node=this.validate(p);
		if(isInternal(node))
			throw new IllegalArgumentException("The node has 2 children");
		size+=t1.size()+t2.size();
		if(!t1.isEmpty()) {
			t1.root.setParent(node);
			node.setLeft(t1.root);
			t1.root=null;
			t1.size=0;
		}
		if(!t2.isEmpty()) {
			t2.root.setParent(node);
			node.setRight(t2.root);
			t2.root=null;
			t2.size=0;
		}

	}
	public void inOrder(Position<T> p) {
		if(p!=null) {
			Node<T> node=this.validate(p);
			inOrder(node.getLeft());
			System.out.println(node.getElement()+" ");
			inOrder(node.getRight());
		}
	}
	public void preOrder(Position<T> p) {
		if(p!=null) {
			Node<T> node=this.validate(p);
			System.out.println(node.getElement());
			preOrder(node.getLeft());
			preOrder(node.getRight());
		}
	}
	public void postOrder(Position<T> p) {
		if(p!=null) {
			Node<T> node=this.validate(p);
			postOrder(node.getLeft());
			postOrder(node.getRight());
			System.out.println(node.getElement()+" ");
		}
	}
	@Override
	public Iterable<Position<T>> positions() {
		return preOrder();
	}
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Iterator<Position<T>> positionsIterator=positions().iterator();
			@Override
			public boolean hasNext() {
				return positionsIterator.hasNext();
			}

			@Override
			public T next() {
				return positionsIterator.next().getElement();
			}
		};
		
	}
	public Position<T> getPosition(T element){
		Iterable<Position<T>> positions=this.positions();
		for(Position<T> pos:positions) {
			if(pos.getElement()==element)
				return pos;
		}
		return null;
	}
        
      

	
	

}
