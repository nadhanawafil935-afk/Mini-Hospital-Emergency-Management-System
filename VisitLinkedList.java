/**
 * Singly Linked List that stores a patient's previous hospital visits.
 * Supports: add, remove, search, display.
 */
public class VisitLinkedList {

    // Internal node class for the singly linked list
    private static class VisitNode {
        Visit data;      // the visit stored in this node
        VisitNode next;  // reference to the next node in the list

        VisitNode(Visit data) {
            this.data = data;
            this.next = null;
        }
    }

    private VisitNode head; // first node of the list
    private int size;       // number of visits currently stored

    public VisitLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /** Adds a new visit to the end of the list. */
    public void addVisit(Visit visit) {
        VisitNode newNode = new VisitNode(visit);
        if (head == null) {
            head = newNode;
        } else {
            VisitNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /** Removes a visit by its visit ID. Returns true if removed, false if not found. */
    public boolean removeVisit(int visitId) {
        if (head == null) {
            return false; // empty list, nothing to remove
        }

        // Special case: removing the head node
        if (head.data.getVisitId() == visitId) {
            head = head.next;
            size--;
            return true;
        }

        VisitNode previous = head;
        VisitNode current = head.next;

        while (current != null) {
            if (current.data.getVisitId() == visitId) {
                previous.next = current.next; // unlink current node
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }

        return false; // not found
    }

    /** Searches for a visit by visit ID. Returns the Visit if found, otherwise null. */
    public Visit searchVisit(int visitId) {
        VisitNode current = head;
        while (current != null) {
            if (current.data.getVisitId() == visitId) {
                return current.data;
            }
            current = current.next;
        }
        return null; // not found
    }

    /** Displays all visits in this patient's history, in order added. */
    public void displayVisits() {
        if (head == null) {
            System.out.println("   No visit history available.");
            return;
        }
        VisitNode current = head;
        while (current != null) {
            System.out.println("   " + current.data);
            current = current.next;
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
