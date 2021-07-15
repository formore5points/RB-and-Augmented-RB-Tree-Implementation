import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RedBlackTree {

	private final int RED = 0;
	private final int BLACK = 1;

	private class Node<K>  {
		K key=(K) "a";
		//int key = -1;
		int Age = -1;
		Date date;
		int color = BLACK;
		Node left = nil;
		Node right = nil;
		Node parent = nil;

		Node(K key, Date Date) {
			this.date = Date;
			this.key = key;
			if (this.date != null) {
				Age = (2018 - date.getYear()) * 365 + (12 - this.date.getMonth()) * 31 + 31 - this.date.getDay();
			}
		}

		public Date getDate() {
			return date;
		}

		
	}

	private final Node nil = new Node(-1, null);
	private Node root = nil;

	/*public Node findNodeID(Node findNode, Node node) { //FindFlag ve FindNode

		if (!FindFlag) {

			if (node.Age!=-1) {
				findNodeID(findNode, node.left);
				if (findNode.key == node.key ) {
					
					FindFlag = true;
					FindNode = node;
					return FindNode;
				}

				findNodeID(findNode, node.right);
			}
		}
		return FindNode;
	}*/
	
	public Node findNodeID(Node findA_Node, Node node) {

		if (!FindFlag) {

			if (node.Age!=-1) {
				findNodeID(findA_Node, node.left);
				if (findA_Node.key.equals(node.key)) {
					FindFlag = true;
					FindNode = node;
					return FindNode;
				}

				findNodeID(findA_Node, node.right);
			}
		}
		return FindNode;
	}

	public Node findNodeAge(Node findNode, Node node) { //FindFlag ve FindNode

		if (!FindFlag) {

			if (node.Age != -1) {
				findNodeAge(findNode, node.left);
				if (findNode.Age == node.Age) {
					FindFlag = true;
					FindNode = node;
					return FindNode;
				}

				findNodeAge(findNode, node.right);
			}
		}
		return FindNode;
	}

	public void insert(Node node) {
		Node temp = root;
		if (root == nil) {
			root = node;
			node.color = BLACK;
			node.parent = nil;
		} else {
			node.color = RED;
			while (true) {
				if (node.Age < temp.Age) {
					if (temp.left == nil) {
						temp.left = node;
						node.parent = temp;
						break;
					} else {
						temp = temp.left;
					}
				} else if (node.Age >= temp.Age) {
					if (temp.right == nil) {
						temp.right = node;
						node.parent = temp;
						break;
					} else {
						temp = temp.right;
					}
				}
			}
			fixTree(node);
		}
	}

	public void fixTree(Node node) {
		while (node.parent.color == RED) {
			Node uncle = nil;
			if (node.parent == node.parent.parent.left) {
				uncle = node.parent.parent.right;

				if (uncle != nil && uncle.color == RED) {
					node.parent.color = BLACK;
					uncle.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
					continue;
				}
				if (node == node.parent.right) {
					node = node.parent;
					rotateLeft(node);
				}
				node.parent.color = BLACK;
				node.parent.parent.color = RED;
				rotateRight(node.parent.parent);
			} else {
				uncle = node.parent.parent.left;
				if (uncle != nil && uncle.color == RED) {
					node.parent.color = BLACK;
					uncle.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
					continue;
				}
				if (node == node.parent.left) {
					node = node.parent;
					rotateRight(node);
				}
				node.parent.color = BLACK;
				node.parent.parent.color = RED;
				rotateLeft(node.parent.parent);
			}
		}
		root.color = BLACK;
	}

	public void rotateLeft(Node node) {
		if (node.parent != nil) {
			if (node == node.parent.left) {
				node.parent.left = node.right;
			} else {
				node.parent.right = node.right;
			}
			node.right.parent = node.parent;
			node.parent = node.right;
			if (node.right.left != nil) {
				node.right.left.parent = node;
			}
			node.right = node.right.left;
			node.parent.left = node;
		} else {
			Node right = root.right;
			root.right = right.left;
			right.left.parent = root;
			root.parent = right;
			right.left = root;
			right.parent = nil;
			root = right;
		}
	}

	public void rotateRight(Node node) {
		if (node.parent != nil) {
			if (node == node.parent.left) {
				node.parent.left = node.left;
			} else {
				node.parent.right = node.left;
			}

			node.left.parent = node.parent;
			node.parent = node.left;
			if (node.left.right != nil) {
				node.left.right.parent = node;
			}
			node.left = node.left.right;
			node.parent.right = node;
		} else {
			Node left = root.left;
			root.left = root.left.right;
			left.right.parent = root;
			root.parent = left;
			left.right = root;
			left.parent = nil;
			root = left;
		}
	}

	public Node treeMinimum(Node subTreeRoot) {
		while (subTreeRoot.left != nil) {
			subTreeRoot = subTreeRoot.left;
		}
		return subTreeRoot;
	}

	public Node treeMaximum(Node subTreeRoot) {
		while (subTreeRoot.right != nil) {
			subTreeRoot = subTreeRoot.right;
		}
		return subTreeRoot;
	}

	public void PRINT() {
		INORDERPRINT(root);
		INITILAIZER();
	}

	public Node GETMAX() {
		return treeMaximum(root);
	}

	public Node GETMIN() {
		return treeMinimum(root);
	}

	public void INORDERPRINT(Node node) { //counter
		if (node.Age!=-1) {
			INORDERPRINT(node.left);
			if (CounterForPrint  > 9)
				System.out.print(CounterForPrint  + "   ");
			else
				System.out.print(CounterForPrint  + "    ");
			CounterForPrint++;
			System.out.println((2018 - (node.getDate().getYear())) + "    " + node.key);
			INORDERPRINT(node.right);
		}
	}

	public int INORDERNUMBER(Node node) {
		int counter = 1;
		if (node.Age==-1) {
			return 0;
		} else {
			counter += INORDERNUMBER(node.left);
			counter += INORDERNUMBER(node.right);
			return counter;
		}
	}

	public int GETNUMSMALLERINORDER(Node node, int Age) { //GetNumSmallerCounter
		if (node.Age==-1) {
			return 0;
		} else {
			if (node.Age > Age)
				GetNumSmallerCounter++;
			GETNUMSMALLERINORDER(node.left, Age);
			GETNUMSMALLERINORDER(node.right, Age);

			return GetNumSmallerCounter;
		}
	}

	public int GETNUM() {
		return INORDERNUMBER(root);
	}

	public <K>int GETNUMSMALLER(K ID) {// 2
		Node node;
		int a=0;
		node = new Node(ID, null);
		if (findNodeID(node, root) != null) {
			if (findNodeID(node, root).key.equals(node.key)) {
				a = GETNUMSMALLERINORDER(root, findNodeID(node, root).Age);
			}
		} else
			System.out.println("ID does not exist.");

		INITILAIZER();
		return a;

	}

	public int GETNUMSMALLER(Date d) {
		int a = 0;
		Node node;
		node = new Node("a", d);
		if (findNodeAge(node, root) == null) {
			System.out.println("This person does not exist.");
		} else {
			a = GETNUMSMALLERINORDER(root, findNodeAge(node, root).Age);
		}
		INITILAIZER();
		return a;
	}

	public void FILEREADER(String dosya) throws NumberFormatException, IOException {
		File file = new File(dosya);
		FileReader fileReader = new FileReader(file);
		String line;
		BufferedReader br = new BufferedReader(fileReader);
		while ((line = br.readLine()) != null) {
			String[] Satýr = line.split(",");
			String[] Date = Satýr[1].split("/");
			Date date;
			date = new Date(Date[0], Date[1], Date[2]);
			Node node;			
			node = new Node(Satýr[0], date);
			insert(node);
		}
		br.close();
	}

	public void INITILAIZER() {
		FindFlag = false;
		FindNode = null;
		CounterForPrint  = 1;
		GetNumSmallerCounter = 0;
	}

	public <K>void Start() throws NumberFormatException, IOException {
		K k=(K) "9988";
		FILEREADER("people.txt");
		System.out.println("------ RB-Tree ------");
		System.out.println("All items were inserted.");
		Date ExampleDate = new Date("7", "6", "1991");
		System.out.println("The result of GETNUMSMALLER1 for the node with birthdate " + ExampleDate.getDay() + "/"
				+ ExampleDate.getMonth() + "/" + ExampleDate.getYear() + " is " + GETNUMSMALLER(ExampleDate));
		System.out.println("The result of GETNUMSMALLER2 for the node with id " + 9988 + " is " + GETNUMSMALLER(k));
		System.out.println("The maximum age of all people is " + (2018 - GETMAX().getDate().getYear()) + ", id :"
				+ GETMAX().key + ", Birthdate :" + GETMAX().getDate().getYear());
		System.out.println("The minimum age of all people is " + (2018 - GETMIN().getDate().getYear()) + ", id :"
				+ GETMIN().key + ", Birthdate :" + GETMIN().getDate().getYear());
		System.out.println("The number of all people is " + GETNUM());
	}

	public<K> void Menu(int choice) throws NumberFormatException, IOException {
		K k;
		Scanner scan = new Scanner(System.in);
		INITILAIZER();
		String item=null;
		//int item = 0;
		Date Date = null;
		String date = null; 
		Node node;
		String[] dater = null;
		switch (choice) {
		case 1:
			System.out.print("Please enter the ID : ");
			item = scan.next();
			System.out.print("Please enter the Date (Ex.5/7/1998) : ");
			date = scan.next();
			dater = date.split("/");
			Date = new Date(dater[0], dater[1], dater[2]);
			node = new Node(item, Date);
			insert(node);
			System.out.println("Item insterted.");
			break;
		case 2:
			System.out.print("Please choose Function 1 or Function 2 :");
			item = scan.next();
			RedBlackTree.MenuDecision=Integer.parseInt(item);
			if (item.equals("1")) {
				System.out.print("Please enter the Date (Ex.5/7/1998) : ");
				date = scan.next();
				dater = date.split("/");
				try{
					Date = new Date(dater[0], dater[1], dater[2]);
					if (GETNUMSMALLER(Date) != 0) {
						System.out.println("The result of GETNUMSMALLER1 for the node with birthdate " + dater[0] + "/"
								+ dater[1] + "/" + dater[2] + " is " + GETNUMSMALLER(Date));
					}
				}
				catch(Exception e) {
					System.out.println("Invalid request.");
				}
				
			} else if (item.equals("2")) {
				System.out.print("Please enter the ID : ");
				item = scan.next();
				if (GETNUMSMALLER(item) != 0) {
					System.out.println(
							"The result of GETNUMSMALLER2 for the node with id " + item + " is " + GETNUMSMALLER(item));
				}
			} else {
				System.out.println("Invalid request.");
			}
			GetNumSmallerCounter = 0;
			break;
		case 3:
			System.out.println("The maximum age of all people is " + (2018 - GETMAX().getDate().getYear()) + ", id :"
					+ GETMAX().key + ", Birthdate :" + GETMAX().getDate().getYear());
			break;
		case 4:
			System.out.println("The minimum age of all people is " + (2018 - GETMIN().getDate().getYear()) + ", id :"
					+ GETMIN().key + ", Birthdate :" + GETMIN().getDate().getYear());
			break;
		case 5:
			System.out.println("The number of all people is " + GETNUM());
			break;
		case 6:
			System.out.println("------ RB-Tree ------");
			System.out.println("No   Age   ID");
			PRINT();
			break;
		case 7:
			System.out.print("Please enter the ID : ");
			item = scan.next();
			k=(K) item;
			node = new Node(k, null);
			if (findNodeID(node, root) != null) {
				if (findNodeID(node, root).key.equals(node.key)) {
					System.out.println("Found");
				}
			} else
				System.out.println("Not Found");
			FindFlag = false;
			break;
		}
		 RedBlackTree.MenuItem = item;	 
		 RedBlackTree.MenuDate = Date;
		 RedBlackTree.Date = date;	 
		 RedBlackTree.MenuSplittedDate = dater;
	}

	public static boolean FindFlag = false;
	public static Node FindNode = null;
	public static int CounterForPrint = 1;
	public static int GetNumSmallerCounter = 0;

	public static String MenuItem = "0";
	public static int MenuDecision = 0;
	public static Date MenuDate = null;
	public static String Date = null;
	public static A_RedBlackTree.A_Node MenuNode = null;
	public static String[] MenuSplittedDate = null;

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		RedBlackTree RedBlackTree = new RedBlackTree();
		A_RedBlackTree A_RedBlackTree = new A_RedBlackTree();
		RedBlackTree.Start();
		A_RedBlackTree.Start();
		while (true) {
			System.out.println("\n1.- Insert a new person\n" + "2.- GETNUMSMALLER\n" + "3.- GETMAX\n" + "4.- GETMIN\n"
					+ "5.- GETNUM\n" + "6.- PRINT\n" + "7.- FIND\n");
			choice = scan.nextInt();
			RedBlackTree.Menu(choice);
			A_RedBlackTree.Menu(choice);
		}

	}
}