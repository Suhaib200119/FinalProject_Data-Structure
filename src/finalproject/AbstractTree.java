package finalproject;

import java.util.ArrayList;

import finalproject.Position;

public abstract class AbstractTree<T> implements Tree<T> {
	@Override
	public boolean isInternal(Position<T> node) throws IllegalArgumentException {
		return this.numOfChildren(node)>0;
	}
	@Override
	public boolean isExternal(Position<T> node) throws IllegalArgumentException {
		return this.numOfChildren(node)==0;
	}
	@Override
	public boolean isRoot(Position<T> node) throws IllegalArgumentException {
		return node==root();
	}
	@Override
	public boolean isEmpty() {
		return this.size()==0;
	}
	public int depth(Position<T> p) {
		if(p==root())
			return 0;
		else
			return 1+depth(parent(p));
	}
	public int height(Position<T> p) {
		int height=0;
		for(Position<T> child:this.children(p)) {
			height=Math.max(height, 1+height(child));
		}
		return height;	
	}
	public Iterable<Position<T>> preOrder() {
		ArrayList<Position<T>> snapshot=new ArrayList<Position<T>>();
		if(!isEmpty())
			this.preOderSubtree(root(), snapshot);
		return snapshot;
		
	}
	private void preOderSubtree(Position<T> p,ArrayList<Position<T>> snapshot) {
		snapshot.add(p);
		for(Position<T> child:children(p))
			preOderSubtree(child, snapshot);
	}
	
}
