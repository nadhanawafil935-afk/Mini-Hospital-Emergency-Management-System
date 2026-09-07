/**
 * Stack that stores completed treatment records.
 * Follows LIFO (Last-In, First-Out).
 * Implemented using a singly linked structure (not java.util.Stack).
 */
public class TreatmentStack {

    // Internal node class for the stack
    private static class StackNode {
        TreatmentRecord data; // treatment record stored in this node
        StackNode next;       // node below this one in the stack

        StackNode(TreatmentRecord data) {
            this.data = data;
            this.next = null;
        }
    }

    private StackNode top; // node currently at the top of the stack
    private int size;      // number of records currently in the stack

    public TreatmentStack() {
        this.top = null;
        this.size = 0;
    }

    /** Pushes a new completed treatment record onto the top of the stack. */
    public void push(TreatmentRecord record) {
        StackNode newNode = new StackNode(record);
        newNode.next = top; // new node points down to the old top
        top = newNode;       // new node becomes the top
        size++;
        System.out.println("Pushed treatment record for Patient ID " + record.getPatientId());
    }

    /** Removes and returns the most recently completed treatment record. Returns null if empty. */
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment stack is empty. No record to pop.");
            return null;
        }
        TreatmentRecord record = top.data;
        top = top.next;
        size--;
        return record;
    }

    /** Displays all treatment records, most recent first. */
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("   No treatment records available.");
            return;
        }
        StackNode current = top;
        while (current != null) {
            System.out.println("   " + current.data);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return size;
    }
}
