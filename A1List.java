// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        A1List newNode = new A1List(address, size, key); // create new node with the given values of address, size, and key
        newNode.next = this.next; // elementary pointer fix
        this.next.prev = newNode; // elementary pointer fix
        this.next = newNode; // elementary pointer fix
        newNode.prev = this; // elementary pointer fix
        return newNode; // return the inserted node
    }

    public boolean Delete(Dictionary d) 
    {
        A1List curr = this.getFirst();
        if(curr == null) return false;
        while(curr.next!=null)
        {
            if((curr.key == d.key) && (curr.address == d.address) && (curr.size == d.size))
            {
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public A1List Find(int k, boolean exact)
    {
        A1List curr = this.getFirst();
        if(curr == null) return null;
        if(exact == true)
        {
            while(curr.key != -1)
            {
                if(curr.key == k)return curr;
                curr = curr.next;
            }
            return null;
        }
        else
        {
            while(curr.key != -1)
            {
                if(curr.key >= k)return curr;
                curr = curr.next;
            }
            return null;
        }
    }

    public A1List getFirst()
    {
        A1List curr = this; // create a new node and initialize it with the current node
        while(curr.prev!= null) // search for the head node, ie. the next node to the head sentinel
        {
            curr = curr.prev;
        }
        if(curr.next.next == null)return null;
        return curr.next; // return the first/head node
    }
    
    public A1List getNext() 
    {
        if (this.next.next == null)
        {
            return this.getFirst();
        }
        return this.next;
    }

    public boolean sanity()
    {
        return true;
    }

}


