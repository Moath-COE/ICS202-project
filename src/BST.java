/************************  BST.java  **************************
 *                 generic binary search tree
 */
import java.util.*;
public class BST<T extends Comparable<T>> extends BinaryTree<T> { 
    protected BTNode<T> root;  
    public BST() {
    }

    public BST(BTNode root) {
         this.root = root;
    }

    public void purge() {
        root = null;
    }
    public boolean isEmpty() {
        return root == null;
    }

    public BTNode<T> getRoot() {
        return root;
    }

    public void insert(T el) {
        BTNode<T> p = root, prev = null;  
        while (p != null) {  // find a place for inserting new node;
            prev = p;
			int result = el.compareTo(p.data);
			
			if(result == 0)
                return;
            else if (result < 0)
                 p = p.left;
            else 
                 p = p.right;
        }
        if (root == null)    // tree is empty;
             root = new BTNode<T>(el);
        else if (el.compareTo(prev.data) < 0)
             prev.left  = new BTNode<T>(el);
        else
             prev.right = new BTNode<T>(el);
    }
   
    public boolean search(T el) {               
        BTNode<T> p = root;
        while (p != null)
            if (el.equals(p.data))                
                 return  true;                     
            else if (el.compareTo(p.data) < 0)
                 p = p.left;
            else 
                 p = p.right;
        return false;                  
    }
    
    public void deleteByCopying(T el) {
        BTNode<T> node, p = root, prev = null;
        while (p != null && !p.data.equals(el)) {  // find the node p
             prev = p;                           // with element el;
             if (el.compareTo(p.data) < 0)
                  p = p.left;
             else 
                  p = p.right;
        }
        node = p;
        if (p != null && p.data.equals(el)) {
             if (node.right == null)             // node has no right child;
                  node = node.left;
             else if (node.left == null)         // no left child for node;
                  node = node.right;
             else {
                  BTNode<T> tmp = node.left;    // node has both children;
                  BTNode<T> previous = node;    // 1.
                  while (tmp.right != null) {    // 2. find the rightmost
                      previous = tmp;            //    position in the
                      tmp = tmp.right;           //    left subtree of node;
                  }
                  node.data = tmp.data;              // 3. overwrite the reference
                                                 //    to the element being deleted;
                  if (previous == node)          // if node's left child's
                      previous.left  = tmp.left; // right subtree is null;
                 else 
                      previous.right = tmp.left; // 4.
             }
             if (p == root)
                  root = node;
             else if (prev.left == p)
                  prev.left = node;
             else prev.right = node;
        }
        else if (root != null)
             throw new java.util.NoSuchElementException("el " + el + " is not in the tree");
        else 
            throw new UnsupportedOperationException("the tree is empty");
    }
    public void levelOrderTraversal(){
       levelOrderTraversal(root);
    }
   public void printTree(){
	  if(root == null){
		  System.out.println("[ ]");
		  return;
	  }
      printTree(root, "", true);
   }
  }