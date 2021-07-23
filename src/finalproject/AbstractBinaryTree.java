package finalproject;

import java.util.ArrayList;

public abstract class AbstractBinaryTree<T> extends AbstractTree<T> implements BinaryTree<T> {
	@Override
	public Position<T> sibling(Position<T> position) {
		Position<T> parent=this.parent(position);
		if(parent==null)
			return null;
		if(position==left(parent))
			return right(parent);
		return left(parent);
	}
	@Override
	public int numOfChildren(Position<T> parent) throws IllegalArgumentException {
		int count=0;
		if(left(parent)!=null)
			count++;
		if(right(parent)!=null)
			count++;
		return count;
	}
	@Override
	public Iterable<Position<T>> children(Position<T> parent) throws IllegalArgumentException {
		ArrayList<Position<T>> snapshot=new ArrayList<>(2);
		if(left(parent)!=null)
			snapshot.add(left(parent));
		if(right(parent)!=null)
			snapshot.add(right(parent));
		return snapshot;
	}
}
