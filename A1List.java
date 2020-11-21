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
        A1List curr = this.getFirst(); // start from the first node
        if(curr == null) return false; // if list is empty, there is nothing to return
        while(curr.next!=null) // // loop through the list
        {
            if((curr.key == d.key) && (curr.address == d.address) && (curr.size == d.size)) // check for a match
            {
                curr.prev.next = curr.next; // elementary pointer fix
                curr.next.prev = curr.prev; // elementary pointer fix
                return true;
            }
            curr = curr.next;
        }
        return false; // return false if search is unsuccessfull
    }

    public A1List Find(int k, boolean exact)
    {
        A1List curr = this.getFirst(); // start from the first node 
        if(curr == null) return null; // if list is empty, there is nothing to return
        if(exact == true) // for the case of exact matching
        {
            while(curr.next != null) // loop through the list
            {
                if(curr.key == k)return curr; // check for match 
                curr = curr.next;
            }
            return null; // return null in case of unsuccessfull search
        }
        else // the case of first fit search
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
        if(curr.next.next == null)return null; // if list is empty, return null
        return curr.next; // return the first/head node
    }
    
    public A1List getNext() 
    {
        if (this.next.next == null)// check if the current node id the last node
        {
            return this.getFirst(); // in case of the last node, return the head node
        }
        return this.next; // return the next node
    }

    public boolean sanity()
    {
        if( this.checkLoops() == true ) return false;

        A1List curr = this;
        while(true)
        {
            if(curr.next.prev != curr)return false; // this is a necessary condition for each node
            if(curr.prev.next != curr)return false; // this is a necessary condition for each node
            if(curr.key == -1)break; // break when you reach the head sentinel
            curr = curr.prev;
        }
        A1List headSentinel = curr;
        if(headSentinel.prev != null) return false; // the prev of the head sentinel should be null
        
        curr = this;
        while(true)
        {
            if(curr.next.prev != curr)return false; // this is a necessary condition for each node
            if(curr.prev.next != curr)return false; // this is a necessary condition for each node
            if(curr.key == -1)break; // break when you reach the tail sentinel
            curr= curr.next;
        }
        if(curr.next != null)return false; // the next of the tail sentinel should be null

        return true;
    }

    private boolean checkLoops()
    {
        A1List p1 = this;
        A1List p2 = this;
        while(p1.next != null && p2.next != null && p2.next.next != null)
        {
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2)return true;
        }
        p1 = this;
        p2 = this;
        while(p1.prev != null && p2.prev != null && p2.prev.prev != null)
        {
            p1 = p1.prev;
            p2 = p2.prev;
            if(p1 == p2)return true;
        }
        return false;
    }

}


