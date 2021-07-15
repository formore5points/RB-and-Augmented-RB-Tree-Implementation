import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class A_RedBlackTree {
	private final int RED = 0;
	private final int BLACK = 1;

	public class A_Node<K> {
		K key=(K) "a";
		//int key = -1;
		int Age = -1;
		int AgeNumber = 0;
		Date date;
		int color = BLACK;
		A_Node left = nil;
		A_Node right = nil;
		A_Node parent = nil;

		A_Node(K key, Date Date) {
			this.date = Date;
			this.key = key;
			if (this.date != null) {
				Age = (2018 - date.getYear()) * 365 + (12 - this.date.getMonth()) * 31 + 31 - this.date.getDay();
			}
		}

		public void UPDATE() {
			AgeNumber = AGENUMBERFINDER(key);
		}

		public Date getDate() {
			return date;
		}
	}

	private final A_Node nil = new A_Node(-1, null);
	private A_Node root = nil;

	public A_Node findA_NodeID(A_Node findA_Node, A_Node node) {

		if (!A_FindFlag) {

			if (node.Age!=-1) {
				findA_NodeID(findA_Node, node.left);
				if (findA_Node.key.equals(node.key)) {
					A_FindFlag = true;
					A_FindNode = node;
					return A_FindNode;
				}

				findA_NodeID(findA_Node, node.right);
			}
		}
		return A_FindNode;
	}

	public A_Node findA_NodeAge(A_Node findA_Node, A_Node node) {

		if (!A_FindFlag) {

			if (node.Age != -1) {
				findA_NodeAge(findA_Node, node.left);
				if (findA_Node.Age == node.Age) {
					A_FindFlag = true;
					A_FindNode = node;
					return A_FindNode;
				}

				findA_NodeAge(findA_Node, node.right);
			}
		}
		return A_FindNode;
	}

	public void insert(A_Node node) {
		A_Node temp = root;
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
			FIXTREE();
		}
	}

	public void fixTree(A_Node node) {
		while (node.parent.color == RED) {
			A_Node uncle = nil;
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

	public void rotateLeft(A_Node node) {
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
			A_Node right = root.right;
			root.right = right.left;
			right.left.parent = root;
			root.parent = right;
			right.left = root;
			right.parent = nil;
			root = right;
		}
	}

	public void rotateRight(A_Node node) {
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
			A_Node left = root.left;
			root.left = root.left.right;
			left.right.parent = root;
			root.parent = left;
			left.right = root;
			left.parent = nil;
			root = left;
		}
	}

	public A_Node treeMinimum(A_Node subTreeRoot) {
		while (subTreeRoot.left != nil) {
			subTreeRoot = subTreeRoot.left;
		}
		return subTreeRoot;
	}

	public A_Node treeMaximum(A_Node subTreeRoot) {
		while (subTreeRoot.right != nil) {
			subTreeRoot = subTreeRoot.right;
		}
		return subTreeRoot;
	}

	public void PRINT() {
		INORDERPRINT(root);
		INITILAIZER();
	}

	public A_Node GETMAX() {
		return treeMaximum(root);
	}

	public A_Node GETMIN() {
		return treeMinimum(root);
	}

	public void INORDERPRINT(A_Node node) {
		if (node.Age!=-1) {
			INORDERPRINT(node.left);
			if (A_CounterForPrint > 9)
				System.out.print(A_CounterForPrint + "   ");
			else
				System.out.print(A_CounterForPrint + "    ");
			A_CounterForPrint++;
			System.out.println((2018 - (node.getDate().getYear())) + "    " + node.key + "    " + node.AgeNumber);
			INORDERPRINT(node.right);
		}
	}

	public int INORDERNUMBER(A_Node node) {
		int A_counter = 1;
		if (node.Age==-1) {
			return 0;
		} else {
			A_counter += INORDERNUMBER(node.left);
			A_counter += INORDERNUMBER(node.right);
			return A_counter;
		}
	}

	public int GETNUMSMALLERINORDER(A_Node node, int Age) {
		if (node.Age!=-1) {
			return 0;
		} else {
			if (node.Age > Age)
				A_GetNumSmallerCounter++;
			GETNUMSMALLERINORDER(node.left, Age);
			GETNUMSMALLERINORDER(node.right, Age);

			return A_GetNumSmallerCounter;
		}
	}

	public int GETNUM() {
		return INORDERNUMBER(root);
	}

	public<K> int GETNUMSMALLER(K ID) {// 2
		A_Node node;
		A_Node node1;
		node = new A_Node(ID, null);
		node1=findA_NodeID(node, root);
		if(node1==null)
			return 999;
		else
		return GETNUM()-node1.AgeNumber-1;

	}

	public<K>int AGENUMBERFINDER(K ID) {
		A_Node node;
		int a=0;
		node = new A_Node(ID, null);
		if (findA_NodeID(node, root) != null) {
			if (findA_NodeID(node, root).key == node.key) {
				a = AGENUMBERFINDERHELPER(root, findA_NodeID(node, root).Age);
			}
		} else
			System.out.println("A_ID does not exist.");

		A_FindFlag = false;
		INITILAIZER();
		return a;
	}

	public int AGENUMBERFINDERHELPER(A_Node node, int Age) {
		if (node.Age==-1) {
			return 0;
		} else {
			if (node.Age < Age)
				A_GetNumSmallerCounter++;
			AGENUMBERFINDERHELPER(node.left, Age);
			AGENUMBERFINDERHELPER(node.right, Age);

			return A_GetNumSmallerCounter;
		}
	}

	public int GETNUMSMALLER(Date d) {
		A_Node node;
		A_Node node1;
		node = new A_Node("a", d);
		node1=findA_NodeAge(node, root);
		if(node1==null) {
			System.out.println("A_This person does not exist.");
			return 0;
		}
			
		else
		return GETNUM()-node1.AgeNumber-1;		
	}

	public void INITILAIZER() {
		A_FindFlag = false;
		A_FindNode = null;
		A_CounterForPrint = 1;
		A_GetNumSmallerCounter = 0;
	}

	public void FIXAGENUMBERS(A_Node node) {
		if (node.Age!=-1) {
			FIXAGENUMBERS(node.left);
			node.UPDATE();
			FIXAGENUMBERS(node.right);
		}
	}

	public void FIXTREE() {
		FIXAGENUMBERS(root);
	}

	public void A_FILEREADER(String dosya) throws NumberFormatException, IOException {
		File file = new File(dosya);
		FileReader fileReader = new FileReader(file);
		String line;
		BufferedReader br = new BufferedReader(fileReader);
		while ((line = br.readLine()) != null) {
			String[] Satýr = line.split(",");
			String[] Date = Satýr[1].split("/");
			Date date;
			date = new Date(Date[0], Date[1], Date[2]);
			A_Node node;
			node = new A_Node(Satýr[0], date);
			insert(node);
		}
		br.close();
	}

	public void Start() throws NumberFormatException, IOException {
		A_FILEREADER("people.txt");
		System.out.println("------ Augmented RB-Tree ------");
		System.out.println("All items were inserted.");
		Date ExampleDate = new Date("7", "6", "1991");
		System.out.println("The result of GETNUMSMALLER1 for the node with birthdate " + ExampleDate.getDay() + "/"
				+ ExampleDate.getMonth() + "/" + ExampleDate.getYear() + " is " + GETNUMSMALLER(ExampleDate));
		System.out.println("The result of GETNUMSMALLER2 for the node with id " + 9988 + " is " + GETNUMSMALLER(9988));
		System.out.println("The maximum age of all people is " + (2018 - GETMAX().getDate().getYear()) + ", id :"
				+ GETMAX().key + ", Birthdate :" + GETMAX().getDate().getYear());
		System.out.println("The minimum age of all people is " + (2018 - GETMIN().getDate().getYear()) + ", id :"
				+ GETMIN().key + ", Birthdate :" + GETMIN().getDate().getYear());
		System.out.println("The number of all people is " + GETNUM());
	}

	public<K> void Menu(int choice) throws NumberFormatException, IOException {
		K k;
		INITILAIZER();
		String item = RedBlackTree.MenuItem;
		Date Date = RedBlackTree.MenuDate;
		String date = RedBlackTree.Date;
		A_Node node = RedBlackTree.MenuNode;
		String[] dater = RedBlackTree.MenuSplittedDate;
		switch (choice) {
		case 1:
			dater = date.split("/");
			Date = new Date(dater[0], dater[1], dater[2]);
			node = new A_Node(item, Date);
			insert(node);
			System.out.println("A_Item insterted.");
			break;
		case 2:
			if (RedBlackTree.MenuDecision == 1) {
				if (GETNUMSMALLER(Date) != 0) {
					System.out.println("The result of A_GETNUMSMALLER1 for the node with birthdate " + Date.getDay()
							+ "/" + Date.getMonth() + "/" + Date.getYear() + " is " + GETNUMSMALLER(Date));
				}
			} else if (RedBlackTree.MenuDecision == 2) {
				k=(K) item;
				if (GETNUMSMALLER(k) != 999) {
					System.out.println("The result of A_GETNUMSMALLER2 for the node with id " + item + " is "
							+ GETNUMSMALLER((item)));
				}
				else {
					System.out.println("A_ID does not exist.");
				}
			} else {
				System.out.println("A_Invalid request.");
			}
			A_GetNumSmallerCounter = 0;
			break;
		case 3:
			System.out.println("The A_maximum age of all people is " + (2018 - GETMAX().getDate().getYear()) + ", id :"
					+ GETMAX().key + ", Birthdate :" + GETMAX().getDate().getYear());
			break;
		case 4:
			System.out.println("The A_minimum age of all people is " + (2018 - GETMIN().getDate().getYear()) + ", id :"
					+ GETMIN().key + ", Birthdate :" + GETMIN().getDate().getYear());
			break;
		case 5:
			System.out.println("The A_number of all people is " + GETNUM());
			break;
		case 6:
			System.out.println("------ Augmented RB-Tree ------");
			System.out.println("No   Age   ID   AgeNumber_A");
			PRINT();
			break;
		case 7:
			k=(K) item;
			node = new A_Node(k, null);
			if (findA_NodeID(node, root) != null) {
				if (findA_NodeID(node, root).key.equals(node.key)) {
					System.out.println("A_Found");
				}
			} else
				System.out.println("A_Not Found");
			A_FindFlag = false;
			break;
		}

	}

	public static boolean A_FindFlag = false;
	public static A_Node A_FindNode = null;
	public static int A_CounterForPrint = 1;
	public static int A_GetNumSmallerCounter = 0;
}
