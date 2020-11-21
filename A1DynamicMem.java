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
        if(blockSize<1)return -1;
        Dictionary found = freeBlk.Find(blockSize, false);
        if(found != null)
        {
            int foundAddress = found.address;
            if(found.size > blockSize)
            {
                allocBlk.Insert(found.address, blockSize, found.address);
                freeBlk.Insert(found.address+blockSize, found.size-blockSize, found.size-blockSize);
                freeBlk.Delete(found);
                return foundAddress;
            }
            else
            {
                allocBlk.Insert(found.address, blockSize, found.address);
                freeBlk.Delete(found);
                return foundAddress;
            }
        }
        return -1;
    } 
    // return 0 if successful, -1 otherwise
    public int Free(int startAddr) {
        Dictionary found = allocBlk.Find(startAddr, true);
        if(found != null)
        {
        freeBlk.Insert(found.address, found.size, found.size);
        allocBlk.Delete(found);
        return 0;
        }
        return -1;
    }
}