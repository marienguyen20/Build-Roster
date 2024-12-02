import java.util.Scanner;
/**
 * A Roster linked list implementation
 *
 * @author Marie Nguyen 
 * @version 25/03/2022 at 1:00 am 
 */

public class Roster<T> {

    public LinkedList<T> roster;

    // Constructor 
    public Roster(){
        roster = new LinkedList<T>();
    }
    
    public Roster(T student){
		roster = new LinkedList<T>(student);
	}

    /**
     * Add student to the roster 
     * @param studentName
     */ 
    public void addStudent(String studentName){

        // If the class is empty add student 
        if (roster.isEmpty()){
            roster.add((T) studentName);
        }
        else{
            // Loop through the class the add studentName in alphabetic order
            for(int i = 0; i < (roster.size()); i++){
            
                // Get the name of the student at index i to compare to the given name 
                String nameAtIndex = (String) roster.get(i);
    
                // if the first String comes after the second, it return positive value 
                int compareValue = nameAtIndex.compareTo(studentName);
                // Add student name to that index 
                if (compareValue > 0){
                    roster.add(i,(T) studentName);
                    break;
                }
                // go through all the names
                if (i == roster.size()-1){
                    // If the new name does not come before any them, the new name should go at the end
                    
                    roster.add((T) studentName); 
                }
            }            
        }
    }

    /**
     * Remove student from the roster
     * @param studentName
     */
    public void remove(String studentName){
        
        // Loop through the class to find the index
        for(int i = 0; i < roster.size(); i++){
            if (roster.get(i).equals(studentName)){
                roster.delete(i);
            }
        }
    }

    /**
     * Get the size of the roster
     * @return
     */
    public int classSize(){
        return roster.size();
    }

    /**
     * Display the roster 
     */
    public String toString(){
        return roster.toString();
    }

    /**
     * Get first student from the roster
     */

    public String getFirstStudent(){
       return (String) roster.get(0);
    }

    /**
     * method to see if the given name is in the roster
     * @param studentName passed-in student name 
     * @return true if the name is in the roster; otherwise return false
     */
    public boolean isNameHere(String studentName){
        // Loop through the roster to find the name matched with the given name 
        for (int i = 0; i < roster.size(); i++){
            if (roster.get(i).equals(studentName)){
                return true;   // return true if the given name exists on the roster
            }
        }
        return false;  // otherwise, return false
    }

    // Main method 
    public static void main(String[] args){
        // Create a roster
        Roster<String> rosterClass = new Roster<>();
        // Getting Input Value 
        Scanner scan = new Scanner(System.in);
        System.out.println("What is the maximum size for your roster ");
        String rosterNum = scan.nextLine();
        // Convert String into int 
        int numRoster = Integer.parseInt(rosterNum);

        System.out.println("Enter [a/r/d/q]: (a)dd, (r)emove, (d)isplay, (q)uit ");
        // Assign user choice to a variable named choice
        String choice = scan.nextLine();

        // while user's choice is not quit
        while(!choice.equals("q")){
            // While there's still space in the class
            while (rosterClass.classSize() < numRoster){
                // ADD STUDENT
                if (choice.equals("a")){
                    System.out.println("Enter the name of the student to add to the roster in Lastname, Firstname format");
                    String studentName = scan.nextLine();
                    // Add student to roster
                    rosterClass.addStudent(studentName);
                    break;
                }
                // REMOVE STUDENT
                else if(choice.equals("r")){
                    System.out.println("Enter the name of the student to remove to the roster in Lastname, Firstname format");
                    String studentName = scan.nextLine();
                    // Remove student from roster
                    rosterClass.remove(studentName);
                    break;
                }
                
                // STRING DISPLAY OF ROSTER
                else if(choice.equals("d")){
                    System.out.println(rosterClass.toString());
                    break;
                }
                // EXIT 
                else if (choice.equals("q")){
                    System.exit(0);
                }
                // BAD INPUT 
                else{
                    System.out.println("Bad input; please try again");
                    break;
                }
            }

            // if the roster is full, exit 
            if (rosterClass.classSize() == numRoster){
                System.out.println("Roster is full");
                System.exit(0);
            }

            // Ask user's input again 
            System.out.println("Enter [a/r/d/q]: (a)dd, (r)emove, (d)isplay, (q)uit ");
            // Update the input 
            choice = scan.nextLine();
        }
        // EXIT 
        if (choice.equals("q")){
            System.exit(0);
        }
    }
}