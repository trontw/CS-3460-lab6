import javax.lang.model.util.ElementScanner6;

public class BST {
	public Node root; // The root node of the tree.
	public Node noPred;// Used for Pred and Succ searches
	public Node noSucc;

	public BST() {
		root = null;
	}

	/**
	 * Inserts a time, along with the req_id. The tree is keyed on time,
	 * while req_id provides a pointer to the request.
	 * This is a public wrapper function that calls the recursive insert method.
	 * Note that the insert method should return the root node of the subtree in
	 * which we insert the key (and its value).
	 **/
	public  void insert(int time, int req_index) {
		root = insert(root, time, req_index);
	}

	public static Node insert(Node Tree, int time, int req_index) {
		if (Tree == null)
			return new Node(time, req_index);
		if (Tree.getTime() < time)
			Tree.setRight(insert(Tree.getRight(), time, req_index));
		else
			Tree.setLeft(insert(Tree.getLeft(), time, req_index));
		return Tree;
	}

	/**
	 * Returns a pointer to the Node that is the predecessor of time.
	 * The predecessor element is the largest element within the tree
	 * that is smaller or equal to time.
	 * This is the deepest ancestor with this property.
	 * Please return the predecessor element. You may return null if
	 * time does not have a predecessor.
	 **/
	public Node pred(int time) {
		noPred = null;
		return null; // Replace this line with returning the actual predecessor.
	}

	public Node pred(Node Tree, int time) {

		if (Tree == null) {
			return null;
		}
		if (Tree.getTime() == time) {
			if (Tree.getLeft() != null) {
				return max(Tree.getLeft());
			} else if (min().getTime() == Tree.getTime()) {
				return null;
			} else {
				return noPred;
			}
		} else if (time < Tree.getTime()) {
			return pred(Tree.getLeft(), time);
		} else {
			noPred = Tree;
			return pred(Tree.getRight(), time);
		}
	}

	/**
	 * Returns a pointer to the Node that is the successor of time. The successor
	 * element is the largest
	 * element within the tree that is larger or equal to time. This is the deepest
	 * ancestor with this property.
	 * Please return the successor element. You may return null if time does not
	 * have a successor.
	 **/
	public Node succ(int time) {
		noSucc = null;
		return null; // Replace this line with returning the actual successor.
	}

	public Node succ(Node Tree, int time) {
		if (Tree == null) {
			return null;
		}
		if (Tree.getTime() == time) {
			if (Tree.getRight() != null) {
				return min(Tree.getRight());
			} else if (max().getTime() == Tree.getTime()) {
				return null;
			} else {
				return noSucc;
			}
		} else if (time > Tree.getTime()) {
			return succ(Tree.getRight(), time);
		} else {
			noSucc = Tree;
			return succ(Tree.getLeft(), time);
		}
	}

	/**
	 * Returns the minimum element in the binary search tree or null if the tree is
	 * empty.
	 **/
	public Node min() {
		return min(root); // Replace this line with returning the actual minimum.
	}

	public Node min(Node Tree) {
		if (Tree.getLeft() == null) {
			return Tree;// was Tree, changing to null to test
		}
		return min(Tree.getLeft());
	}

	/**
	 * Returns the maximum element in the binary search tree or null if the tree is
	 * empty.
	 **/
	public Node max() {
		return max(root); // Replace this line with returning the actual maximum.
	}

	public Node max(Node Tree) {
		if (Tree.getRight() == null)
			return Tree;
		return max(Tree.getRight());
	}
	public Node find(int time) {
		return find(root, time);
	}
	public static Node find(Node node, int time) {
		if (node == null)
			return null;
		if (node.getTime() == time)
			return node;
		if (time < node.getTime())
			return find(node.getLeft(), time);
		return find(node.getRight(), time);
	} 

	public boolean verify(int time, int k){
		if (root == null)
			return true;
	return verify(root, time, k);
	}
	
	public boolean empty() {
		return root == null;
	}

	public boolean verify(Node tree, int time, int k){
		if (tree == null)
			return true;
		if ((Math.abs(tree.getTime() - time) < k))
			return false;
		if (tree.getTime() > time) 
			return verify(tree.getLeft(), time, k);
		if (tree.getTime() < time)
			return verify(tree.getRight(), time, k);
		return false;
	}

	/**
	 * Remove the node that contains this time. If this time is not present in the
	 * structure, this method does nothing.
	 **/
	public void delete(int time) {
		//root = delete(root);
		//root = null;
		//return root;
		 
		Node temp = find(time);
		if (temp != null)
			delete(temp);
		
	}

	public Node delete(Node root2) {
		int time = root2.getTime();
		Node the_root = root;
		
		//First we want to find the node
		//if (root2.getLeft() != null)
		//	System.out.println("root2 getLeft value is = "+root2.getLeft().getTime());
		//if (root2.getRight() != null)
		//	System.out.println("root2 getRight value is = "+root2.getRight().getTime());
		//if (root2 == null) 
		//	return root2;
			//If in the left-subtree
		if (time < root2.getTime()) {
			System.out.println("Inside LEFT-subtree.");
			//System.out.println("left-subtree root2 left node time = "+root2.getLeft().getTime());
			//System.out.println("left-subtree time = "+time);
			root2 = delete(root2.getLeft());
			//System.out.println("left-subtree root2 = "+root2);
			//If in the right-subtree
		} else if(time > root2.getTime()) {
			System.out.println("Inside RIGHT-subtree.");
			root2 = delete(root2.getRight());
			//If we found the node, then we will prepare to delete it
			//depending on which case it falls under.

			//*** Case 1: NO CHILD ***
		} else if (root2.getLeft() == null && root2.getRight() == null) {
			//root2 = null;
			//root2.setLeft(null);
			//System.out.println("*** Inside NO child. ***");
			// If node is the Right child of parent, use PRED
			Node root2_pred = pred(root2, root2.getTime());
			//System.out.println("root2 time is = "+root2.getTime());
			//System.out.println("root2 = "+root2);
			//System.out.println("root2_pred is = "+root2_pred);
			if (root2 != null && root2_pred != null && root2_pred.getRight().getTime() == root2.getTime()){
				System.out.println("Using PRED_R");
				root2_pred.setRight(null);
				root2 = root2_pred;
			} else if (root2_pred != null && root2_pred.getLeft().getTime() == root2.getTime()){
				System.out.println("Using PRED_L");
				root2_pred.setLeft(null);
				root2 = root2_pred;
			} //else
				//delete(root2);
			// If node is the Left child of parent, use succ
			Node root2_succ = succ(root2, root2.getTime());
			//System.out.println("root2_succ is = "+root2_succ);
			//System.out.println("root2_succ getLeft time is = "+root2_succ.getLeft().getTime());
			if (root2 != null && root2_succ != null && root2_succ.getLeft().getTime() == root2.getTime()){
				//System.out.println("Using SUCC_L");
				//System.out.println();
				root2_succ.setLeft(null);
				root2 = root2_succ;
			} else if (root2_succ != null && root2_succ.getRight().getTime() == root2.getTime()){
				//System.out.println("Using SUCC_R");
				//System.out.println();
				root2_succ.setRight(null);
				root2 = root2_succ;
			} //else 
				//delete(root2);
			return root2;

			//*** Case 2: One child (right child present) ***
		} else if (root2.getLeft() == null) {
			//System.out.println("Inside One right child present");
			//System.out.println("root2 time is = "+root2.getTime());
			Node temp = root2.getRight();
			//System.out.println("temp time is = "+temp.getTime());
			//root2 = root2.getRight();
			root2.setTime(temp.getTime());
			root2.setReq_index(temp.getReq_index());
			root2.setRight(null);
			return root2;

			//*** Case 2: One child (left child present) ***
		} else if (root2.getRight() == null) {
			//System.out.println("Inside One left child present");
			//System.out.println("root2 time is = "+root2.getTime());
			Node temp = root2.getLeft();
			//System.out.println("temp time is = "+temp.getTime());
			root2.setTime(temp.getTime());
			root2.setReq_index(temp.getReq_index());
			//root2 = root2.getLeft();
			root2.setLeft(null);
			return root2;

			//*** Case 3: Two children ***
		} else {
			System.out.println("Inside Two children present");
			//First find the smallest node in the right-subtree
			//That smallest node will replace the node to be deleted
			Node temp = min(root2.getLeft());//succ(root2.getTime());//min().getRight();//min(root2.getRight());
			System.out.println("temp time is = "+temp.getTime());
			System.out.println("root2 time is = "+root2.getTime());
			System.out.println("the_root time is = "+the_root.getTime());
			root2.setTime(temp.getTime());
			root2.setReq_index(temp.getReq_index());
			System.out.println("THE NEW root2 time is = "+root2.getTime());
			//temp = the_root.getRight();
			System.out.println("THE NEW temp time is = "+temp.getTime());
			System.out.println("THE NEW the_root 20 time is = "+the_root.getRight().getTime());
			root2.setLeft(null);
			//root2.setRight(null);
			//delete(root2);
			//temp = null;
			//root2 = delete(root2);
			return root2;
		}
		return root2;
	}
 
	/**
	 * Prints the contents of the tree in sorted order.
	 **/
	public void print() {
		print(root);
	}

	private void print(Node Tree) {
		if (Tree == null) {
			return;
		} else {
			print(Tree.getLeft());
			System.out.println(Tree.getTime() + " ");
			print(Tree.getRight());
		}

	}

	public void delete(Node root2, int time) {
	}

}
