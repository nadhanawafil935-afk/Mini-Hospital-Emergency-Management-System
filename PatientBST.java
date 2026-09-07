/**
 * Binary Search Tree that stores Patient records, keyed on Patient ID.
 * Supports: insert, search, delete, in-order traversal.
 */
public class PatientBST {

    // Internal node class for the BST
    private static class BSTNode {
        Patient patient;   // patient data held at this node
        BSTNode left;      // left child (smaller patient IDs)
        BSTNode right;     // right child (larger patient IDs)

        BSTNode(Patient patient) {
            this.patient = patient;
            this.left = null;
            this.right = null;
        }
    }

    private BSTNode root; // root node of the tree

    public PatientBST() {
        this.root = null;
    }

    // ---------- INSERT ----------

    /** Inserts a new patient into the tree, keyed by patient ID. */
    public void insert(Patient patient) {
        root = insertRecursive(root, patient);
    }

    private BSTNode insertRecursive(BSTNode current, Patient patient) {
        if (current == null) {
            return new BSTNode(patient); // found the empty spot, place the new node here
        }

        if (patient.getPatientId() < current.patient.getPatientId()) {
            current.left = insertRecursive(current.left, patient);   // go left
        } else if (patient.getPatientId() > current.patient.getPatientId()) {
            current.right = insertRecursive(current.right, patient); // go right
        } else {
            System.out.println("Patient ID " + patient.getPatientId() + " already exists. Insert skipped.");
        }

        return current;
    }

    // ---------- SEARCH ----------

    /** Searches for a patient by ID. Returns the Patient if found, otherwise null. */
    public Patient search(int patientId) {
        return searchRecursive(root, patientId);
    }

    private Patient searchRecursive(BSTNode current, int patientId) {
        if (current == null) {
            return null; // reached a leaf's child, not found
        }

        if (patientId == current.patient.getPatientId()) {
            return current.patient;
        } else if (patientId < current.patient.getPatientId()) {
            return searchRecursive(current.left, patientId);  // search left subtree
        } else {
            return searchRecursive(current.right, patientId); // search right subtree
        }
    }

    // ---------- DELETE ----------

    /** Deletes a patient by ID. Returns true if a patient was deleted, false if not found. */
    public boolean delete(int patientId) {
        if (search(patientId) == null) {
            return false;
        }
        root = deleteRecursive(root, patientId);
        return true;
    }

    private BSTNode deleteRecursive(BSTNode current, int patientId) {
        if (current == null) {
            return null;
        }

        if (patientId < current.patient.getPatientId()) {
            current.left = deleteRecursive(current.left, patientId);
        } else if (patientId > current.patient.getPatientId()) {
            current.right = deleteRecursive(current.right, patientId);
        } else {
            // Found the node to delete

            // Case 1: no children
            if (current.left == null && current.right == null) {
                return null;
            }

            // Case 2: one child
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }

            // Case 3: two children
            // Find the smallest node in the right subtree (in-order successor)
            BSTNode successor = findMin(current.right);
            current.patient = successor.patient; // copy successor's data up
            current.right = deleteRecursive(current.right, successor.patient.getPatientId()); // remove the successor
        }

        return current;
    }

    /** Helper used by delete() to find the in-order successor (smallest node in a subtree). */
    private BSTNode findMin(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // ---------- IN-ORDER TRAVERSAL ----------

    /** Displays all patients in ascending order of Patient ID. */
    public void displayInOrder() {
        if (root == null) {
            System.out.println("   No patients registered yet.");
            return;
        }
        inOrderRecursive(root);
    }

    private void inOrderRecursive(BSTNode current) {
        if (current == null) {
            return;
        }
        inOrderRecursive(current.left);           // visit left subtree
        System.out.println("   " + current.patient); // visit current node
        inOrderRecursive(current.right);          // visit right subtree
    }

    public boolean isEmpty() {
        return root == null;
    }
}
