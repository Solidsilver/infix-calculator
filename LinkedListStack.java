import java.util.EmptyStackException;

public class LinkedListStack {

	private class Node {
		private Object data;
		private Node next;

		private Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node top;
	private int size;

	public LinkedListStack() {
		this.top = null;
		this.size = 0;
	}


	public void push(Object data) {
		this.top = new Node(data, this.top);
		this.size++;
	}

	public Object peak() {
		if (this.size == 0) 
			throw new EmptyStackException();
		return this.top.data;
	}

	public Object pop() {
		if (this.size == 0) {
			throw new EmptyStackException();
		} else {
			Object ret = this.top.data;
			this.top = this.top.next;
			this.size--;
			return ret;
		}
	}
	
	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return (this.size < 1);
	}

	public String toString() {
		String comb = "--STACK--\n";
		Node cur = this.top;
		while (cur != null) {
			comb += "[" + cur.data + "]\n";
			cur = cur.next;
		}
		return "\n" + comb;
	}
}