import javax.lang.model.util.ElementScanner6;

// import jdk.nashorn.internal.ir.ReturnNode;

// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
         
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    {  
        BSTree newNode = new BSTree(address, size, key);
        BSTree curr = this;
        while(curr.parent != null)
        {
            curr = curr.parent;
        }
        BSTree sentinel = curr;
        curr = curr.right;
        if(curr == null)
        {
            sentinel.right = newNode;
            newNode.parent = sentinel;
            return newNode;
        }
        while(curr.left != null || curr.right != null)
        {
            if(curr.key < newNode.key)
            {
                if(curr.right == null)
                {
                    curr.right = newNode;
                    newNode.parent = curr;
                    return newNode;
                }
                else curr = curr.right;
            }
            else
            {
                if(curr.left == null)
                {
                    curr.left = newNode;
                    newNode.parent = curr;
                    return newNode;

                }
                else curr = curr.left;
            }
            
        }
        if(curr.key < newNode.key) curr.right = newNode;
        else curr.left = newNode;
        newNode.parent = curr;
        return newNode;
    }

    public boolean Delete(Dictionary e)
    { 
        
        BSTree curr = this;
        while(curr.parent != null)
        {
            curr = curr.parent;
        }
        curr = curr.right;
        if(curr == null) return  false;
        curr = curr.getFirst();
        while(curr != null)
        {
            if(curr.key == e.key && curr.size == e.size && curr.address == e.address)
            {
                if(curr.left == null && curr.right == null) // case when node to be deleted is a leaf
                {
                    curr.DeleteLeaf();
                    return true;
                }
                if(curr.left == null || curr.right == null)
                {
                    curr.DeleteOneChild();
                    return true;
                }
                else
                {
                    BSTree temp = curr.getNext();
                    if(temp == null)return false;
                    curr.key = temp.key;
                    curr.size = temp.size;
                    curr.address = temp.address;
                    if(temp.left == null && temp.right == null)
                    {
                        temp.DeleteLeaf();
                        return true;
                    }
                    else
                    {
                        temp.DeleteOneChild();
                        return true;
                    }
                }
            }
            curr = curr.getNext();
        }
        return false;
    }

    private void DeleteLeaf()
    {
        if(this.parent.right == this) this.parent.right = null;
        else this.parent.left = null;
    }

    private void DeleteOneChild()
    {
        if(this.left == null) // case when left child is null
        {
            if(this.parent.right == this)
            {
                this.parent.right = this.right;
                this.right.parent = this.parent;
            }
            else
            {
                this.parent.left = this.right;
                this.right.parent = this.parent;
            }
        }
        if(this.right == null) // case when left child is null
        {
            if(this.parent.right == this)
            {
                this.parent.right = this.left;
                this.left.parent = this.parent;
            }
            else
            {
                this.parent.left = this.left;
                this.left.parent = this.parent;
            }
        }
    }

    public BSTree Find(int key, boolean exact)
    { 
        BSTree curr = this;
        while(curr.parent != null)
        {
            curr = curr.parent;
        }
        curr = curr.right;
        if(curr == null) return  null;
        curr = curr.getFirst();
        if(exact == true)
        {
            while(curr != null)
            {
                if(curr.key == key) return curr;
                curr = curr.getNext();
            }
            return null;
        }
        else
        {
            while(curr != null)
            {
                if(curr.key >= key) return curr;
                curr = curr.getNext();
            }
            return  null;
        }
    }

    public BSTree getFirst()
    { 
        BSTree curr = this;
        while(curr.parent != null)
        {
            curr = curr.parent;
        }
        curr = curr.right;
        if(curr == null) return null;
        while(curr.left != null)
        {
            curr = curr.left;
        }
        return curr;
    }

    public BSTree getNext()
    { 
        BSTree curr = this;

        if(this.parent == null) return null;

        if(curr.right != null)
        {
            curr = curr.right;
            while(curr.left != null)
            {
                curr = curr.left;
            }
            return curr;
        } 
        else
        {
            BSTree curr2 = curr.parent;
            while(curr2.parent != null)
            {
                if(curr2.left == curr)return curr2;
                curr = curr.parent;
                curr2 = curr2.parent;
            }
            return null;
        }
    }

    public boolean sanity()
    { 
        return false;
    }

}


 


