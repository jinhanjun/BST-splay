package SPLT_A4;

//import BST_A2.BST_Node;

public class SPLT implements SPLT_Interface{
  private BST_Node root;
  private int size;
  
  public SPLT() {
    this.size = 0;
  } 
  
  public BST_Node getRoot() { //please keep this in here! I need your root node to test your tree!
    return root;
  }

public void insert(String s) {

	if (root == null) {
		root = new BST_Node(s, null, null, null);
		size += 1;
		return;
	} if (root.containsNode(s).data.equals(s)) {
		splay(root.containsNode(s));
		return;
	} else {
		root.insertNode(s);
		splay(root.containsNode(s));
		size += 1;
		return;
	}
	
}

public void remove(String s) {

	if(empty()) {
		return;
	}

		if (root.data.equals(s) && size == 1) {
			splay(root.containsNode(s));
			root = null;
			size -= 1;
		} else if (!root.containsNode(s).data.equals(s)) {
			splay(root.containsNode(s));
			return;
		} else if (root.containsNode(s).data.equals(s)) {
			splay(root.containsNode(s));
			root.removeNode(s);
			size -= 1;
			return;
		}
	
}

public String findMin() {

	if (empty()) {
		return null;
	} else {
		BST_Node temp = root.findMin();
		splay(temp);
		return temp.data;
	}
}

public String findMax() {

	if (empty()) {
		return null;
	} else {
		BST_Node temp = root.findMax();
		splay(temp);
		return temp.data;
	}
}

public boolean empty() {
	if (size == 0) {
		return true;
	} else {
		return false;
	}
}

public boolean contains(String s) {
	if (empty()) {
		return false;
	}
	if(root.containsNode(s).data.equals(s)) {
		splay(root.containsNode(s));
		return true;
	} else {
		splay(root.containsNode(s));
		return false;
	}

	
}

public int size() {
	return size;
}

public int height() {
	return root.getHeight();
}  



private void splay(BST_Node splayN) {

	if (splayN == null) {
		//return null;
		return;
	}
	while(splayN.par != null) {
		if (splayN.par.par == null) {
			if (splayN.par.left == splayN) {
				rotateR(splayN.par);
			} else {
				rotateL(splayN.par);
			}
		}else if (splayN.par.left == splayN && splayN.par.par.left == splayN.par) {
			rotateR(splayN.par.par);
			rotateR(splayN.par);
		}else if (splayN.par.right == splayN && splayN.par.par.right == splayN.par) {
			rotateL(splayN.par.par);
			rotateL(splayN.par);
		}else if(splayN.par.left == splayN && splayN.par.par.right == splayN.par) {
			rotateR(splayN.par);
			rotateL(splayN.par);
		} else {
			rotateL(splayN.par);
			rotateR(splayN.par);
			
		}
	}
//return null;
	return;
		
}

private void rotateL(BST_Node node) {

	BST_Node temp = node.right;
	if (temp != null) {
		node.right = temp.left;
		if (temp.left != null) {
			temp.left.par = node;
		}
		temp.par = node.par;
	}
	if(node.par == null) {
		root = temp;
	} else if( node == node.par.left) {
		node.par.left = temp;
	} else {
		node.par.right = temp;
	}
	if(temp != null) {
		temp.left = node;
	}
	node.par = temp;
}
private void rotateR(BST_Node node) {

	BST_Node temp = node.left;
	if (temp != null) {
		node.left = temp.right;
		if(temp.right != null) {
			temp.right.par = node;
		}
		temp.par = node.par;
	}
	if(node.par == null) {
		root = temp;
	} else if(node == node.par.left) {
		node.par.left = temp;
	} else {
		node.par.right = temp;
	}
	if (temp != null) {
		temp.right = node;
	}
	node.par = temp;
}


}