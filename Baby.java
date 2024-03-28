package DataS_Lab6;

public class Baby implements Comparable<Baby> {
    private int admissionNumber;
    private String Name;    
    private Double Weight;

    public Baby(int admissionNumber, String Name, Double weight) {

        this.admissionNumber = admissionNumber;
        this.Name = Name;
        this.Weight = weight;
    }

    public int getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(int admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Double getWeight() {
        return Weight;
    }

    public void setWeight(Double Weight) {
        this.Weight = Weight;
    }
    @Override
    public String toString() {
        return "Admission Number: " + admissionNumber + ", Name: " + Name + ", Weight: " + Weight;
    }

    @Override
    public int compareTo(Baby o) {
        return Double.compare(this.Weight, o.Weight);
    }
}
