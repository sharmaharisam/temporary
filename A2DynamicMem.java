// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 
    //Your BST (and AVL tree) implementations should obey the property that keys in the left subtree <= root.key < keys in the right subtree. How is this total order between blocks defined? It shouldn't be a problem when using key=address since those are unique (this is an important invariant for the entire assignment123 module). When using key=size, use address to break ties i.e. if there are multiple blocks of the same size, order them by address. Now think outside the scope of the allocation problem and think of handling tiebreaking in blocks, in case key is neither of the two. 
    public void Defragment() {
            BSTree newTree = new BSTree();
            Dictionary curr;
            for(curr = freeBlk.getFirst(); curr != null; curr = curr.getNext())
            {
                newTree.Insert(curr.address, curr.size, curr.address);
            }
            curr = newTree.getFirst();
            if(curr == null)return;
            Dictionary currNext = curr.getNext();
            Dictionary temp = null;
            while(currNext != null)
            {
                if(curr.key + curr.size == currNext.key)
                {
                    temp = new BSTree(curr.address, curr.size, curr.size);
                    freeBlk.Delete(temp);
                    temp = new BSTree(currNext.address, currNext.size, currNext.size);
                    freeBlk.Delete(temp);
                    newTree.Delete(curr);
                    newTree.Delete(currNext);
                    freeBlk.Insert(curr.address, curr.size + currNext.size, curr.size + currNext.size);
                    newTree.Insert(curr.address, curr.size + currNext.size, curr.address);
                    curr = newTree.Find(curr.address, true);
                    currNext = curr.getNext();
                    continue;
                }

                curr = curr.getNext();
                if(curr != null)currNext = currNext.getNext();
                
            }
            for(curr = freeBlk.getFirst(); curr != null; curr = curr.getNext())
            {
                temp = new BSTree(curr.address, curr.size, curr.address);
                newTree.Delete(temp);
            }
            return;
    }
}