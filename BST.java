package DataS_Lab6;

import DataS_Lab6.Tree;

public class BST<E extends Baby> implements Tree<E> {

    protected TreeNode<E> root;
    protected int size = 0;
    protected java.util.Comparator<E> c;

    @Override
    public Baby heavy() { //Finds the most heavy baby. 
        double heavy = heavyHelper(root, 0.0);
        return searchWeight(heavy);
    }

    public double heavyHelper(TreeNode<E> root, Double max) { //Helper to find the heavy baby. 
        if (root == null)
            return 0;

        double currentWeight = root.element.getWeight(); //get the current weight of the baby. 

        if (max == null || currentWeight > max) { //compares the current weight to the previous max weight 
            max = currentWeight;
        }

        double maxRight = heavyHelper(root.right, max); //looks on the right side to get the max weight. 
        double maxLeft = heavyHelper(root.left, max); //looks on the left side to get the max weight. 
        if (currentWeight < maxRight || currentWeight < maxLeft) { //finds the max weight. 
            if (maxRight > maxLeft) {
                max = maxRight;
            } else if (maxLeft > maxRight) {
                max = maxLeft;
            }
        } else
            max = currentWeight;

        return max;
    }

    @Override
    public Baby least() { //finding the baby with teh least weight. 
        double weight = leastHelper(root, 0.0);
        return searchWeight(weight);
    }

    public double leastHelper(TreeNode<E> root, Double min) { //helper to find the baby with the least weight. 

        if (root == null) 
            return Double.MAX_VALUE;

        double currentWeight = root.element.getWeight(); //get the current weight of the baby.

        if (min == null || currentWeight < min) { //compares the previous min weight to the current weight. 
            min = currentWeight;
        }

        double maxRight = leastHelper(root.right, min); //look for the min on the left. 
        double maxLeft = leastHelper(root.left, min); //look for the min on the right. 
        return Math.min(currentWeight, Math.min(maxRight, maxLeft)); //return the minimum value. 

    }

    // descending order of the baby weights.
    @Override
    public void detailsDESC() {
        detailsDESCHealper(root);
    }

    public void detailsDESCHealper(TreeNode<E> root) { //helper for the descending order. 
        if (root != null) {
            detailsDESCHealper(root.right); //get the details of the elements on the right side of the tree. 
            System.out.println("Name: " + root.element.getName() + ", Weight: " + root.element.getWeight());
            detailsDESCHealper(root.left); //get the details of the elements on the left side of the tree. 
        }
    }

    // Counts the number of even numbers in the tree.
    @Override
    public int countEven() {
        return countEvenHelper(root);
    }

    public int countEvenHelper(TreeNode<E> root) {
        if (root == null) {
            return 0; //check if tree is null
        }

        int evenCount = 0; 

        if (root.element.getWeight() % 2 == 0) { //check to see if the weight is even. 
            evenCount++;
        }

        int evenCountRight = countEvenHelper(root.right); //look for even weights on the right. 

        int evenCountLeft = countEvenHelper(root.left); //look for even weights on the left. 
        
        return evenCount + evenCountRight + evenCountLeft; //add the even counts of the left and right. 
    }

    // Counts the total number of nodes in the tree.
    @Override
    public int totalNodes() {
        return totalNodesHelper(root, 1);
    }

    public int totalNodesHelper(TreeNode<E> root, int count) {
        if (root == null) {
            return 0; //check if tree is empty. 
        }
        int nodeCount = count;

        int nodeCountRight = totalNodesHelper(root.right, nodeCount); //count the nodes on the right. 

        int nodeCountLeft = totalNodesHelper(root.left, nodeCount); //count the nodes of the left. 

        
        nodeCount += nodeCountRight + nodeCountLeft; //add the left and right side together. 

        return nodeCount;
    }

    // This fucntion is to count every node in the tree that has two children.
    @Override
    public int twoChildren() {
        return twoChildrenHelper(root, 0);
    }

    public int twoChildrenHelper(TreeNode<E> root, int two) { // Helper function to find nodes with two children.
        if (root == null) {
            return 0; // returns when the tree is empty
        }

        TreeNode<E> current = root;
        int twoChild = two; // keeping track of the nodes withtwo children.

        if (current.element != null) { // checks if the current element is null.
            if (current.right != null && current.left != null) { //runs when there is a left and right child. 
                twoChild++;
            }
            twoChildrenHelper(current.right, twoChild); //call to look at the right side.
            twoChildrenHelper(current.left, twoChild); //call to look at the left side. 
        }
        return twoChild;
    }

    /**
     * Create a default BST with a natural order comparator
     */
    public BST() {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    }

    /**
     * Create a BST with a specified comparator
     */
    public BST(java.util.Comparator<E> c) {
        this.c = c;
    }

    /**
     * Create a binary tree from an array of objects
     */
    public BST(E[] objects) {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
        for (int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }

    @Override
    /**
     * Returns true if the element is in the tree
     */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            if (c.compare(e, current.element) < 0) {
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                return true; // Element is found
            }
        }

        return false;
    }

    @Override
    public Baby searchName(String name) { // Finds the baby based on the name.
        return searchNameHelper(root, name);
    }

    public Baby searchNameHelper(TreeNode<E> root, String name) { // This is a helper funciton to find the baby with
                                                                  // that name.
        if (root == null || root.element == null) { // runs when the root or element is null.
            return null;
        }

        String currentName = root.element.getName(); // get the name of the current element.

        if (currentName.equals(name)) { // When the name is equal to the current name.
            return root.element;
        }

        Baby resultFromRight = searchNameHelper(root.right, name); // runs to find the name on the right side.

        if (resultFromRight != null) { // runs when name is on the right side of the tree
            return resultFromRight;
        }

        Baby resultFromLeft = searchNameHelper(root.left, name); // runs to find the name on the left side.

        if (resultFromLeft != null) { // runs when the name is on the left side of the tree.
            return resultFromLeft;
        }

        return null;
    }

    @Override
    public Baby searchWeight(double weight) { // Finds the baby based on the weight of the baby.
        return searchWeightHelper(root, weight);
    }

    public Baby searchWeightHelper(TreeNode<E> root, double weight) { // This is a helper funciton to find the baby
                                                                      // based on the weight of the baby.
        if (root == null || root.element == null) { // runs when the root or element is null.
            return null;
        }

        double currentWeight = root.element.getWeight(); // get the weight of the current element.

        if (currentWeight == weight) { // When the weight is equal to the current weight.
            return root.element;
        }

        Baby resultFromRight = searchWeightHelper(root.right, weight); // Searches on the right for that baby weight.

        if (resultFromRight != null) { // return if the baby is on the right.
            return resultFromRight;
        }

        Baby resultFromLeft = searchWeightHelper(root.left, weight); // Seraches the left for the baby based on the
                                                                     // weight.

        if (resultFromLeft != null) { // return if the baby with that weight is on that left side.
            return resultFromLeft;
        }

        return null;
    }

    @Override
    /**
     * Insert element e into the binary tree Return true if the element is
     * inserted successfully
     */
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e); // Create a new root
        } else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (c.compare(e, current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (c.compare(e, current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (c.compare(e, parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }

        size++;
        return true; // Element inserted successfully
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override
    /**
     * Inorder traversal from the root
     */
    public void inorder() {
        inorder(root);
    }

    /**
     * Inorder traversal from a subtree
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    @Override
    /**
     * Postorder traversal from the root
     */
    public void postorder() {
        postorder(root);
    }

    /**
     * Postorder traversal from a subtree
     */
    protected void postorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    @Override
    /**
     * Preorder traversal from the root
     */
    public void preorder() {
        preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     */
    protected void preorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /**
     * This inner class is static, because it does not access any instance
     * members defined in its outer class
     */
    public static class TreeNode<E> {

        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    @Override
    /**
     * Get the number of nodes in the tree
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     */
    public TreeNode<E> getRoot() {
        return root;
    }

    /**
     * Returns a path from the root leading to the specified element
     */
    public java.util.ArrayList<TreeNode<E>> path(E e) {
        java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            list.add(current); // Add the node to the list
            if (c.compare(e, current.element) < 0) {
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return list; // Return an array list of nodes
    }

    @Override
    /**
     * Delete an element from the binary tree. Return true if the element is
     * deleted successfully Return false if the element is not in the tree
     */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (c.compare(e, current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break; // Element is in the tree pointed at by current
            }
        }

        if (current == null) {
            return false; // Element is not in the tree
        }
        // Case 1: current has no left child
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else {
                if (c.compare(e, parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else // Special case: parentOfRightMost == current
            {
                parentOfRightMost.left = rightMost.left;
            }
        }

        size--; // Reduce the size of the tree
        return true; // Element deleted successfully
    }

    @Override
    /**
     * Obtain an iterator. Use inorder.
     */
    public java.util.Iterator<E> iterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    private class InorderIterator implements java.util.Iterator<E> {
        // Store the elements in a list

        private java.util.ArrayList<E> list = new java.util.ArrayList<>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /**
         * Inorder traversal from the root
         */
        private void inorder() {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         */
        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        /**
         * More elements for traversing?
         */
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }

            return false;
        }

        @Override
        /**
         * Get the current element and move to the next
         */
        public E next() {
            return list.get(current++);
        }

        @Override // Remove the element returned by the last next()
        public void remove() {
            if (current == 0) // next() has not been called yet
            {
                throw new IllegalStateException();
            }

            delete(list.get(--current));
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    @Override
    /**
     * Remove all elements from the tree
     */
    public void clear() {
        root = null;
        size = 0;
    }
}
