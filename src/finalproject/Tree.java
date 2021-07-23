package finalproject;

import java.util.Iterator;

import finalproject.Position;;

public interface Tree<T> extends Iterable<T>{
	// Accessor methods:
	// Allowing the user to navigate the various positions of a tree.
	public Position<T> root();
	public Position<T> parent(Position<T> position)throws IllegalArgumentException;
	public Iterable<Position<T>> children(Position<T> parent)throws IllegalArgumentException;
	public int numOfChildren(Position<T> parent)throws IllegalArgumentException;
	
	// Query methods:These make programming with trees easier and more 
	// readable, we can use them in if statement and while loops.
	public boolean isRoot(Position<T> node)throws IllegalArgumentException;
	public boolean isInternal(Position<T> node)throws IllegalArgumentException;
	public boolean isExternal(Position<T> node)throws IllegalArgumentException;

	// Generic Methods : is unrelated to the specific structure of the tree
	public int size();
	public boolean isEmpty();
	public Iterator<T> iterator();
	public Iterable<Position<T>> positions();
	
}

