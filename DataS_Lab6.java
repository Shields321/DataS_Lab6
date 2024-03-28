package datas_lab6;

/**
 *
 * @author dylan
 */
public class DataS_Lab6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BST<Baby> baby = new BST<>();
        Baby baby1 = new Baby(1,"john", 5.5);
        Baby baby2 = new Baby(1,"john", 2.5);
        Baby baby3 = new Baby(1,"john", 7.5);
        Baby baby4 = new Baby(1,"john", 1.5);
        Baby baby5 = new Baby(1,"john", 0.5);
        baby.insert(baby1);
        baby.insert(baby2);
        baby.insert(baby3);
        baby.insert(baby4);
        baby.insert(baby5);
        
        baby.heavy();
    }
    
}
