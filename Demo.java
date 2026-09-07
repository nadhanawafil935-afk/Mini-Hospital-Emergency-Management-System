/**
 * Non-interactive scripted demo driver.
 *
 * Run this file (not Main.java) while screen-recording the
 * demonstration video. It walks through every required operation
 * for every data structure automatically, with clear printed
 * section headers, so you can narrate over it live.
 *
 * Order matches the video script (see video_script.md):
 *   1. BST:    insert, search, delete, in-order traversal
 *   2. Queue:  enqueue, dequeue, display, empty-queue handling
 *   3. Stack:  push, pop, display, empty-stack handling
 *   4. Linked List: add, search, remove, display visit history
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException {

        PatientBST patientBST = new PatientBST();
        EmergencyQueue emergencyQueue = new EmergencyQueue();
        TreatmentStack treatmentStack = new TreatmentStack();

        section("1. PATIENT RECORDS - BINARY SEARCH TREE (BST)");

        System.out.println("-- Inserting patients --");
        patientBST.insert(new Patient(105, "Nimal Perera", 34, "0771234567", "Chest Pain"));
        patientBST.insert(new Patient(102, "Kamal Silva", 45, "0772345678", "Fracture - Left Arm"));
        patientBST.insert(new Patient(110, "Anusha Fernando", 28, "0773456789", "High Fever"));
        patientBST.insert(new Patient(101, "Ruwan Jayasuriya", 60, "0774567890", "Breathing Difficulty"));
        patientBST.insert(new Patient(108, "Dilani Wickramasinghe", 22, "0775678901", "Minor Burns"));
        pause();

        System.out.println("\n-- In-order traversal (ascending by Patient ID) --");
        patientBST.displayInOrder();
        pause();

        System.out.println("\n-- Searching for Patient ID 102 --");
        Patient found = patientBST.search(102);
        System.out.println(found != null ? "Found: " + found : "Not found.");
        pause();

        System.out.println("\n-- Searching for a non-existent Patient ID 999 --");
        Patient notFound = patientBST.search(999);
        System.out.println(notFound != null ? "Found: " + notFound : "Not found (as expected).");
        pause();

        System.out.println("\n-- Deleting Patient ID 105 --");
        patientBST.delete(105);
        System.out.println("In-order traversal after deletion:");
        patientBST.displayInOrder();
        pause();

        section("2. EMERGENCY PATIENT QUEUE - QUEUE");

        EmergencyQueue emptyQueueDemo = new EmergencyQueue();
        System.out.println("-- Displaying an empty queue --");
        emptyQueueDemo.displayQueue();
        System.out.println("-- Dequeuing from an empty queue --");
        emptyQueueDemo.dequeue();
        pause();

        System.out.println("\n-- Enqueuing patients into the waiting queue --");
        emergencyQueue.enqueue(patientBST.search(102));
        emergencyQueue.enqueue(patientBST.search(110));
        emergencyQueue.enqueue(patientBST.search(101));
        pause();

        System.out.println("\n-- Displaying the waiting queue --");
        emergencyQueue.displayQueue();
        pause();

        System.out.println("\n-- Dequeuing the next patient for treatment --");
        Patient treating = emergencyQueue.dequeue();
        System.out.println("Now treating: " + treating);
        System.out.println("Queue after dequeue:");
        emergencyQueue.displayQueue();
        pause();

        section("3. TREATMENT HISTORY - STACK");

        TreatmentStack emptyStackDemo = new TreatmentStack();
        System.out.println("-- Displaying an empty stack --");
        emptyStackDemo.displayStack();
        System.out.println("-- Popping from an empty stack --");
        emptyStackDemo.pop();
        pause();

        System.out.println("\n-- Pushing completed treatment records --");
        treatmentStack.push(new TreatmentRecord(102, "Kamal Silva", "Arm cast applied", "2026-09-07 09:15"));
        treatmentStack.push(new TreatmentRecord(110, "Anusha Fernando", "Fever medication given", "2026-09-07 09:40"));
        pause();

        System.out.println("\n-- Displaying treatment history (most recent first) --");
        treatmentStack.displayStack();
        pause();

        System.out.println("\n-- Popping the most recent treatment record --");
        TreatmentRecord popped = treatmentStack.pop();
        System.out.println("Removed: " + popped);
        System.out.println("Stack after pop:");
        treatmentStack.displayStack();
        pause();

        section("4. PATIENT VISIT HISTORY - SINGLY LINKED LIST");

        Patient anusha = patientBST.search(110);
        System.out.println("-- Adding visits to " + anusha.getName() + "'s history --");
        anusha.getVisitHistory().addVisit(new Visit(1, "2025-11-02", "Dr. Silva", "Flu", "Rest and fluids"));
        anusha.getVisitHistory().addVisit(new Visit(2, "2026-02-18", "Dr. Perera", "Allergic reaction", "Antihistamine"));
        anusha.getVisitHistory().addVisit(new Visit(3, "2026-09-07", "Dr. Fernando", "High fever", "Fever reducer"));
        pause();

        System.out.println("\n-- Displaying visit history --");
        anusha.getVisitHistory().displayVisits();
        pause();

        System.out.println("\n-- Searching for Visit ID 2 --");
        Visit foundVisit = anusha.getVisitHistory().searchVisit(2);
        System.out.println(foundVisit != null ? "Found: " + foundVisit : "Not found.");
        pause();

        System.out.println("\n-- Removing Visit ID 1 --");
        anusha.getVisitHistory().removeVisit(1);
        System.out.println("Visit history after removal:");
        anusha.getVisitHistory().displayVisits();
        pause();

        section("DEMO COMPLETE");
        System.out.println("All BST, Queue, Stack, and Linked List operations demonstrated successfully.");
    }

    private static void section(String title) {
        System.out.println("\n====================================================");
        System.out.println(" " + title);
        System.out.println("====================================================");
    }

    // Small pause so output doesn't fly past too fast while recording.
    // Comment out or reduce if you want the demo to run faster.
    private static void pause() throws InterruptedException {
        Thread.sleep(600);
    }
}
