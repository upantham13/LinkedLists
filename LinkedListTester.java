package linked;

public class LinkedListTester 
{
	private static void pass()
	{
		System.out.println("... PASSED.\n");
	}
	
	
	private static void fail(String message)
	{
		if (!message.endsWith("."))
			message += ".";
		System.out.println("... FAIL: " + message);
		System.exit(0);
	}
	
	
	public static void main(String[] args)
	{
		System.out.println("Searching for A in empty list ...");
		CharLinkedList list = new CharLinkedList();
		if (list.find('A') != null)
			fail("Found a node in an empty list.");
		pass();
		
		String original = "ABCDE";
		for (int i=0; i<original.length(); i++)
		{
			// Find target.
			list = new CharLinkedList(original);
			char target = original.charAt(i);
			System.out.println("Searching for " + target + " in " + original + " ...");
			CharNode node = list.find(target);
			if (node == null)
				fail("Couldn't find it.");
			if (node.getData() != target)
				fail("Found node containing wrong char: " + node.getData());
			if (!list.hasIntegrity())
				fail("Found " + target + " but list has broken integrity");
			if (!list.toString().equals(original))
				fail("Found " + target + " but list has changed to " + list);
			pass();
			
			// Duplicate target.
			list = new CharLinkedList(original);
			System.out.println("Wil duplicate " + target + " in " + original + " ...");
			list.duplicate(target);
			if (!list.hasIntegrity())
				fail("After duplicating, list doesn't have integrity");
			String expected = original.substring(0, i+1) + original.substring(i);
			if (!list.toString().equals(expected))
				fail("After duplicating, list is " + list + ", expected " + expected);
			pass();
		}
		
		// Duplicate non-existent char.
		list = new CharLinkedList(original);
		System.out.println("Will try to duplicate non-existent X in " + original + ", should see an exception ...");
		try
		{
			list.duplicate('X');
			fail("... no exception was thrown.");
		}
		catch (IllegalArgumentException x)
		{
			
		}
		if (!list.hasIntegrity())
			fail("After duplicating, list doesn't have integrity");
		if (!list.toString().equals(original))
			fail("List should not have changed, became " + list);
		
		// Check integrity of a list with a loop.
		System.out.println("\nWill check integrity of a list with a loop ...");
		CharNode n1 = new CharNode('1');
		CharNode n2 = new CharNode('2');
		CharNode n3 = new CharNode('3');
		CharNode n4 = new CharNode('4');
		n1.setNext(n2);
		n2.setNext(n3);
		n3.setNext(n1);
		list = new CharLinkedList(n1, n4);
		if (list.hasIntegrity())
			fail("List with a loop passed the integrity checker. See lines 77-85 in LinkedListTester.java.");
		
		System.out.println("\nYOU PASSED ... full marks.");		
	}
}
