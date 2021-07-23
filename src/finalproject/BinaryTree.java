package finalproject;

public interface BinaryTree<T> extends Tree<T> {
	public Position<T> left(Position<T> position);
	public Position<T> right(Position<T> position);
	public Position<T> sibling(Position<T> position);


}

