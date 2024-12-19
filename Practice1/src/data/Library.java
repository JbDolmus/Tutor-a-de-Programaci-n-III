package data;

import java.util.ArrayList;

// Generic class for Library
public class Library<T> {
	private ArrayList<T> items;

	public Library() {
		this.items = new ArrayList<>();
	}

	public void addItem(T item) {
		items.add(item);
	}

	public void removeItem(T item) {
		items.remove(item);
	}

	public ArrayList<T> listItems() {
		return items;
	}
}