package DataS_Lab6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class DataS_Lab6 {
    public static void main(String[] args) {
        BST<Baby> baby = new BST<>();
        String fileName = "Babies.txt";

        String projectDirectory = System.getProperty("user.dir");

        String absoluteFilePath = projectDirectory + File.separator + fileName;

        File file = new File(absoluteFilePath);

        if (!(file.exists())) {
            System.out.println("File does not exist.");
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            int count = 0;
            String[] temp;
            String[] Id = new String[13];
            String[] name = new String[13];
            String[] weight = new String[13];
            while ((line = reader.readLine()) != null) {
                temp = line.split(" ");

                if (temp.length >= 4) {
                    Id[count] = temp[0];
                    
                    StringBuilder nameBuilder = new StringBuilder();
                    for (int i = 1; i < temp.length - 1; i++) {
                        nameBuilder.append(temp[i]).append(" ");
                    }
                    name[count] = nameBuilder.toString().trim();
                    weight[count] = temp[temp.length - 1];
                    count++;
                }
            }
            Baby baby1 = new Baby(Integer.parseInt(Id[0]), name[0], Double.parseDouble(weight[0]));
            Baby baby2 = new Baby(Integer.parseInt(Id[1]), name[1], Double.parseDouble(weight[1]));
            Baby baby3 = new Baby(Integer.parseInt(Id[2]), name[2], Double.parseDouble(weight[2]));

            baby.insert(baby1);
            baby.insert(baby2);
            baby.insert(baby3);

            System.out.println("The value of the heaviest baby is = " + baby.heavy().toString());
            System.out.println("The value of the least heaviest baby is = " + baby.least().toString());
            System.out.println("The baby name you are searching for is = " + baby.searchName(baby1.getName()).toString());
            System.out.println("The baby weight you are searching for is = " + baby.searchWeight(baby2.getWeight()).toString());

            System.out.println("Number of babies with even weights = "+baby.countEven());
            System.out.println("Number of total nodes in the tree = "+baby.totalNodes());
            System.out.println("Number of roots with 2 children = "+baby.twoChildren());
            baby.detailsDESC();

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
