// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).
    // While inserting into the list, only call insert at the head of the list
    // Please note that ALL insertions in the DLL (used either in A1DynamicMem or used independently as the “dictionary” class implementation) are to be made at the HEAD (from the front).
    // Also, the find-first should start searching from the head (irrespective of the use for A1DynamicMem). Similar arguments will follow with regards to the ROOT in the case of trees (specifying this in case it was not so trivial to anyone of you earlier)
    public int Allocate(int blockSize) {
        if(blockSize<1)return -1; // since memory is in units of bytes, blockSize cannot be less than 1
        Dictionary found = freeBlk.Find(blockSize, false); // search for the first fitting block
        if(found != null)
        {
            int foundAddress = found.address; // store the starting address of the found block
            if(found.size > blockSize) // check if splitting is required
            {
                allocBlk.Insert(found.address, blockSize, found.address); // allocate the required mamount of memory from the found dictionary
                freeBlk.Insert(found.address+blockSize, found.size-blockSize, found.size-blockSize); // insert the remaining memory back into the free memory 
                freeBlk.Delete(found); // delete the block from the free memory
                return foundAddress; // return the address of the found block
            }
            else
            {
                allocBlk.Insert(found.address, blockSize, found.address); // allocate the founf block
                freeBlk.Delete(found); // delete the block from free memory
                return foundAddress; // return the address of the found block
            }
        }
        return -1;
    } 
    // return 0 if successful, -1 otherwise
    public int Free(int startAddr) {
        Dictionary found = allocBlk.Find(startAddr, true); // search for the block with the given starting address in the allocated memory
        if(found != null)
        {
        freeBlk.Insert(found.address, found.size, found.size); // insert the block to the free memory
        allocBlk.Delete(found); // delete the block from the allocated memory
        return 0;
        }
        return -1; // return -1 in case of unsuccessfull search
    }
}
