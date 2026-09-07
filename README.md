# Mini Hospital Emergency Management System

**Course:** CIT300 - Data Structures and Algorithms
**Assignment:** Individual Mid Assignment — Mini Hospital Emergency Management System Using Data Structures

## Project Overview

This is a console-based Java application that simulates the core operations of a
hospital emergency unit: registering patients, queuing them for treatment,
recording completed treatments, and tracking each patient's visit history.

The project was built specifically to demonstrate four data structures
implemented from scratch (no `java.util.LinkedList`, `Queue`, `Stack`, etc.):

| Requirement | Data Structure | File(s) |
|---|---|---|
| Patient Records | Binary Search Tree (BST), keyed on Patient ID | `Patient.java`, `PatientBST.java` |
| Emergency Patient Queue | Queue (FIFO) | `EmergencyQueue.java` |
| Treatment History | Stack (LIFO) | `TreatmentRecord.java`, `TreatmentStack.java` |
| Patient Visit History | Singly Linked List (one per patient) | `Visit.java`, `VisitLinkedList.java` |

## Project Structure

```
hospital-ems/
├── src/
│   ├── Patient.java           # Patient record (also owns a VisitLinkedList)
│   ├── PatientBST.java        # BST: insert, search, delete, in-order traversal
│   ├── EmergencyQueue.java    # Queue: enqueue, dequeue, display, empty handling
│   ├── TreatmentRecord.java   # A single completed treatment record
│   ├── TreatmentStack.java    # Stack: push, pop, display, empty handling
│   ├── Visit.java             # A single past visit record
│   ├── VisitLinkedList.java   # Singly linked list: add, remove, search, display
│   ├── Main.java              # Interactive console menu (use this for normal use)
│   └── Demo.java              # Scripted, non-interactive walkthrough (use this for the video)
└── README.md
```

## How to Compile and Run

Requires a JDK (Java 8+).

```bash
cd src
javac *.java

# Interactive menu-driven system:
java Main

# Scripted demo (recommended for the demonstration video):
java Demo
```

## How Each Data Structure Is Used

- **Binary Search Tree (`PatientBST`)** — Patients are inserted keyed by Patient
  ID. Search and delete both use the BST ordering property (smaller IDs left,
  larger IDs right) to run in O(h) time. Delete handles all three cases: leaf
  node, one child, and two children (using the in-order successor). In-order
  traversal naturally prints patients sorted by ID.
- **Queue (`EmergencyQueue`)** — A singly linked queue with separate `front`
  and `rear` pointers so enqueue and dequeue are both O(1). Follows strict
  FIFO — the first patient enqueued is the first one dequeued for treatment.
- **Stack (`TreatmentStack`)** — A singly linked stack where `push` and `pop`
  both operate on the `top` pointer, giving O(1) operations. Follows LIFO —
  the most recently completed treatment is the first one popped/undone.
- **Singly Linked List (`VisitLinkedList`)** — Each `Patient` object owns its
  own `VisitLinkedList` instance, so every patient has an independent visit
  history. Supports adding a visit at the tail, searching by visit ID,
  removing a visit by ID (handling the head-removal special case), and
  displaying the full history in order.

## Notes on Design Decisions

- All four structures are implemented manually with their own internal node
  classes, rather than using Java's built-in collections, to directly satisfy
  the assignment's data-structure requirements.
- `Patient` composes a `VisitLinkedList` rather than the system managing a
  separate global list, since each patient's visit history is inherently
  tied to that patient.
- Empty-structure cases (empty queue dequeue, empty stack pop, empty BST
  traversal) are handled with friendly messages instead of exceptions or
  crashes.
- `Demo.java` is kept separate from `Main.java` so the interactive menu stays
  clean for normal use, while the demo gives a repeatable, scripted run for
  the required demonstration video.

## Author

Individual assignment — implementation, testing, and explanation are the
author's own work, per the assignment's academic integrity requirement.
