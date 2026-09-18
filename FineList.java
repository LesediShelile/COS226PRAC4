public class FineList 
{
    private final Node head;
    private final Node tail;

    public FineList() 
    {
        head = new Node(Integer.MIN_VALUE);
        tail = new Node(Integer.MAX_VALUE);

        head.next = tail;
    }

    public boolean add(int value) 
    {
        Node pred = head;
        pred.lock.lock();

        try 
        {
            Node curr = pred.next;
            curr.lock.lock();

            try 
            {
                while (curr.value < value) 
                {
                    pred.lock.unlock();

                    pred = curr;
                    curr = curr.next;

                    curr.lock.lock();
                }

                if (curr.value == value) 
                {
                    return false;
                }

                Node newNode = new Node(value);

                newNode.next = curr;
                pred.next = newNode;

                return true;
            } 
            finally 
            {
                curr.lock.unlock();
            }
        } 
        finally 
        {
            pred.lock.unlock();
        }
    }

    public boolean remove(int value) 
    {
        Node pred = head;
        pred.lock.lock();

        try 
        {
            Node curr = pred.next;
            curr.lock.lock();

            try 
            {
                while (curr.value < value) 
                {
                    pred.lock.unlock();

                    pred = curr;
                    curr = curr.next;

                    curr.lock.lock();
                }

                if (curr.value != value) 
                {
                    return false;
                }

                pred.next = curr.next;

                return true;
            } 
            finally 
            {
                curr.lock.unlock();
            }
        } 
        finally 
        {
            pred.lock.unlock();
        }
    }

    public boolean contains(int value) 
    {
        Node pred = head;
        pred.lock.lock();

        try 
        {
            Node curr = pred.next;
            curr.lock.lock();

            try 
            {
                while (curr.value < value) 
                {
                    pred.lock.unlock();

                    pred = curr;
                    curr = curr.next;

                    curr.lock.lock();
                }

                return curr.value == value;
            } 
            finally 
            {
                curr.lock.unlock();
            }
        } 
        finally 
        {
            pred.lock.unlock();
        }
    }
}