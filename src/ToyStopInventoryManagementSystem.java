//package toystopinventorymanagementsystem;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Fahad Satti
 *
 * Hashir salam awan on 11/17/2016
 */
//GIT link : https://github.com/HashirSalam/Lab7.git
public class ToyStopInventoryManagementSystem {
    ToyStopService tsService = new ToyStopService();

    public void init(){
        
        tsService.initEmployees();
        tsService.initStores();
        tsService.initToys();
        System.out.println("\nInitialization complete");
    }

    public void saveState(){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("tmp/tservice.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(tsService);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in tmp/tservice.ser");
        }catch(IOException i) {
            i.printStackTrace();
        }

    }

    public void loadState(){
        //ToyStopService tsServiceLoaded = new ToyStopService();
        //tsServiceLoaded = null;
        try {
            FileInputStream fileIn = new FileInputStream("tmp/tservice.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tsService = (ToyStopService) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c) {
            System.out.println("ToyStop class not found");
            c.printStackTrace();
            return;
        }
        System.out.println("\nLoad State complete");
    }

    public int addNewEmployee(){
        System.out.println("\nNew Employee Added. Employee ID:");
        return tsService.addEmployee();
    }

    public int addNewStore(){  //will assign any new employees or the ones remaining after previous store insertions.
        System.out.println("\nNew Store Added. Store ID:");
        return tsService.addStore();
    }

    public void addNewToy(){  //will assign any new employees or the ones remaining after previous store insertions.
        tsService.addToy();
        System.out.println("\nNew Toy Added");

    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        ToyStopInventoryManagementSystem tsims = new ToyStopInventoryManagementSystem();
        tsims.init();

        while(true){
            tsims.showMenu();
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            int choice = reader.nextInt();
            if(choice == 1)
            {tsims.printAll();}                       //Print Everything
            else if (choice == 2) {                   //Add new Store
                System.out.println(tsims.addNewStore());
            }
            else if (choice ==3) {                     //Add new Employee
                System.out.println(tsims.addNewEmployee());
            }
            else if (choice == 4) {                    //Add new Toy
                tsims.addNewToy();
            }
            else if (choice == 5) {                    //Save State
                tsims.saveState();
            }
            else if (choice == 6) {                    //Load State
                tsims.loadState();
            }
            else if(choice == 7){                      //EXIT
                break;
            }

            //load previous data
            //tsims.loadData();

        }
        
    }

    private void loadData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void showMenu() {
        System.out.println("Welcome to Toy Stop Inventory Management System");
        System.out.println("Enter 1 to show all data");
        System.out.println("Enter 2 to add a new Store");
        System.out.println("Enter 3 to add a new Employee");
        System.out.println("Enter 4 to add a new Toy");
        System.out.println("Enter 5 to save state");
        System.out.println("Enter 6 to load state");


        System.out.println("Enter 7 to EXIT");
    }

    private void printAll() {
        System.out.println(this.tsService.stores);
    }
    
}
