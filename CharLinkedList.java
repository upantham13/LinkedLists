package linked;

import java.util.*;
// Resources Used: Lecture Notes, Discord Group Chat, Labs

public class CharLinkedList 
{
	private CharNode		head;	// Empty if head and	
	private CharNode		tail;	// tail are null
	 
	public CharLinkedList()		{ }


	public CharLinkedList(CharNode head, CharNode tail)
	{
		this.head = head;
		this.tail = tail;
	}
	
	
	public CharLinkedList(String s)
	{
		for (int i=s.length()-1; i>=0; i--)
			insertAtHead(s.charAt(i));
	}
	
	
	public void insertAtHead(char ch)
	{
		assert hasIntegrity();		// Precondition
		
		CharNode node = new CharNode(ch);
		node.setNext(head);
		head = node;
		if (tail == null)
			tail = node;			// Corner case: inserting into empty node
		
		assert hasIntegrity();		// Postcondition
	}
	
	
	public String toString()
	{
		String s = "";
		CharNode node = head;
		while (node != null)
		{
			s += node.getData();
			node = node.getNext();
		}
		return s;
	}
	
	
	//
	// Returns true if this list has emptiness integrity, has tail integrity, has no loops,  
	// and tail is reachable from head.
	//
	// Caution: this checks for most but not all common integrity problems. 
	//
	boolean hasIntegrity()
	{
		// Check emptiness. If either head or tail is null, the other must
		// also be null. Different logic from what you saw in lecture. Returns
		// immediately if this list is empty.
		if (head == null  ||  tail == null)
			return head == null  &&  tail == null;
		
		// Check tail integrity (tail.next must be null).
		if (tail.getNext() != null)
			return false;
		
		// Check for loops.
		CharNode loop1 = head;
		CharNode loop2 = head;
		while (loop1 != null && loop1.getNext() != null) {
			loop1 = loop1.getNext().getNext();
			loop2 = loop2.getNext();
			if (loop1 == loop2) {
				return false;
			}
		}
		
		// Make sure tail is reachable from head.
		CharNode node1 = head;
		while (node1 != null && node1 != tail)
			node1 = node1.getNext();
		return node1 == tail;
	}
	
	public CharNode find(char ch) {
		CharNode find = head;
		while (find != null) {
			if (find.getData() == ch) {
				return find;
			}
			else {
				find = find.getNext();
			}
		}
		return null;		
	}
	
	public void duplicate(char ch) throws IllegalArgumentException {
			if (find(ch) == null) { 
				throw new IllegalArgumentException("The node does not exist.");				
			}
			else {
				CharNode found = find(ch);
				CharNode dupl = new CharNode(found.getData());
				CharNode next = found.getNext();
				dupl.setNext(next);
				found.setNext(dupl);

				if (dupl.getNext()==null) {
					this.tail = dupl;
				}
			}			
	}
	
	public static void main(String[] args) {
		
		String original = "ABCDE";
		for (int i=0; i<original.length(); i++)
		{
			// Find target.
//			list = new CharLinkedList(original);
			char target = original.charAt(i);
//			System.out.println("Searching for " + target + " in " + original + " ...");
//			CharNode node = list.find(target);
//			System.out.println(target);
//			System.out.println(list);
			CharLinkedList list = new CharLinkedList(original);
			//System.out.println(list);
			System.out.println("Wil duplicate " + target + " in " + original + " ...");
			System.out.println("OG Integrity: " + list.hasIntegrity());
			
			try {
				list.duplicate('X');
			} catch (IllegalArgumentException iae) {
				iae.printStackTrace();
			}
			
			System.out.println("List after duplication: "+ list);
		}
	}
}
