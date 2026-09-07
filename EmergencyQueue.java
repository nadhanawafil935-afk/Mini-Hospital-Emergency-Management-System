/**
 * Queue that manages patients waiting in the emergency unit.
 * Follows FIFO (First-In, First-Out).
 * Implemented using a singly linked structure (not java.util.Queue),
 * so the underlying data structure logic is visible and self-contained.
 */
public class EmergencyQueue {

    // Internal node class for the queue
    private static class QueueNode {
        Patient patient;   // patient waiting in this node
        QueueNode next;    // next node in the queue

        QueueNode(Patient patient) {
            this.patient = patient;
            this.next = null;
        }
    }

    private QueueNode front; // patient at the front (next to be treated)
    private QueueNode rear;  // last patient in the line
    private int size;        // number of patients currently waiting

    public EmergencyQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /** Adds a patient to the back of the waiting queue. */
    public void enqueue(Patient patient) {
        QueueNode newNode = new QueueNode(patient);
        if (rear == null) {
            // queue was empty
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Enqueued: " + patient.getName() + " (ID: " + patient.getPatientId() + ")");
    }

    /** Removes and returns the next patient for treatment. Returns null if queue is empty. */
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. No patient to dequeue.");
            return null;
        }

        Patient nextPatient = front.patient;
        front = front.next;

        if (front == null) {
            rear = null; // queue is now empty
        }

        size--;
        return nextPatient;
    }

    /** Displays all patients currently waiting, front to back. */
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("   No patients currently waiting.");
            return;
        }
        QueueNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println("   " + position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int getSize() {
        return size;
    }
}
