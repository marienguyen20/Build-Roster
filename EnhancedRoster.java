import java.util.Scanner;

/**
 * A EnhancedRoster linked list implementation inherits Roster
 *
 * @author Marie Nguyen 
 * @version 25/03/2022 at 1:00 am 
 */
public class EnhancedRoster<T> extends Roster<T> {

    public LinkedList<T> waitList;

    // Constructor 
    public EnhancedRoster(){
        super();
        waitList = new LinkedList<T>();
    }
    
    public EnhancedRoster(T student){
        super();
        waitList = new LinkedList<T>(student);
	}

    /**
     * Add student to the waitlist
     * @param studentName passed-in student name 
     * no return 
     */
    public void addWaitList(String studentName){
        // If the waitlist is empty add student 
        if (waitList.isEmpty()){
            waitList.add(0, (T) studentName);
        }
        else {
            for (int i = 1; i < waitList.size(); i++){
                waitList.add(i,(T) studentName);
            }
        }
    }

    /**
     * Remove student to the waitlist 
     * @param studentName passed-in student name 
     * no return 
     */
    public void removeWaitList(String studentName){

        // Loop through the class to find the index
        for(int i = 0; i < waitList.size(); i++){
            if (waitList.get(i).equals(studentName)){
                waitList.delete(i);
            }
        }
        // super.remove(studentName);
    }

    /**
     * String representation of the roster
     */
    public String toString(){
        return waitList.toString();
    }

    // Main method 
    public static void main(String[] args){
        // Create linked list for roster and waitlist
        Roster<String> rosterClass = new Roster<>();
        EnhancedRoster<String> waitList = new EnhancedRoster<>();

        Scanner scan = new Scanner(System.in);
        System.out.println("Whar is the maximum size for your roster ");
        // Assigned the maximum size as rosterNum
        String rosterNum = scan.nextLine();
        // Convert String into int 
        int numRoster = Integer.parseInt(rosterNum);

        System.out.println("Enter [a/r/d/q]: (a)dd, (r)emove, (d)isplay, (q)uit ");
        // Assign user choice to a variable named choice
        String choice = scan.nextLine();

        // while user's choice is not exit 
        while(!choice.equals("q")){
            // If the roster is not full or there is student on waitlist 
            while (rosterClass.classSize() <= numRoster || waitList.classSize() != 0){
                // ADD STUDENT 
                if (choice.equals("a")){
                    // Getting input value, student name 
                    System.out.println("Enter the name of the student to add to the roster in Lastname, Firstname format");
                    String studentName = scan.nextLine();
                    // If the name is already existed in the roster and waitlist 
                    if (rosterClass.isNameHere(studentName) == true || waitList.isNameHere(studentName) == true){
                        System.out.println("Error! Existed Name");
                            break;
                    }
                    // If the roster is not full and there is nothing in the waitlist
                    if (rosterClass.classSize() < numRoster && waitList.classSize() == 0){
                        rosterClass.addStudent(studentName);  // add in roster
                        break;
                    }
                    // If the roster is full
                    else if (rosterClass.classSize() == numRoster){
                        waitList.addWaitList(studentName);  // add in waitlist
                        break;
                    }
                    // If the roster is full and waitlist is not 
                    else if (rosterClass.classSize() == numRoster && waitList.classSize() != 0){
                        waitList.addWaitList(studentName); // add in waitlist
                        break;
                    }
                
                    else{  // Otherwise, add student to waitlist 
                        waitList.addWaitList(studentName);
                        break;
                    } 
                }
                // REMOVE STUDENT 
                else if(choice.equals("r")){
                    // If there is nothing in roster and waitlist break
                    if (rosterClass.classSize() == 0 && waitList.classSize() == 0){break;}
                    // Get input value 
                    System.out.println("Enter the name of the student to remove to the roster in Lastname, Firstname format");
                    String studentName = scan.nextLine();

                    // Invalid remove when the name of the student does not exist in roster and waitlist
                    if ( rosterClass.isNameHere(studentName) == false && waitList.isNameHere(studentName) == false){
                        System.out.println("Error! No Name Founded");
                            break;
                    }
                    // Remove people from roster 
                    // If there are students on waitlist and someone is removed from roster
                    else if ( rosterClass.isNameHere(studentName) == true && waitList.classSize() != 0){
                        // Remove input's student from roster
                        rosterClass.remove(studentName);

                        // Add first student on waitlist to roster
                        rosterClass.addStudent((waitList.getFirstStudent()));
                        // Remove that student from waitlist 
                        waitList.removeWaitList((waitList.getFirstStudent()));
                        
                        break;
                    }

                    // Remove student from roster if the name is matched 
                    else if (rosterClass.isNameHere(studentName) == true && waitList.classSize() == 0){
                        rosterClass.remove(studentName);
                        break;
                    }
                    // Otherwise remove student from waitlist
                    else{
                        waitList.removeWaitList(studentName);
                        break;
                    }
                }
                // DISPLAY THE WAITLIST AND ROSTER 
                else if(choice.equals("d")){
                    System.out.println(rosterClass.toString());
                    System.out.println(waitList.toString());
                    break;
                }
                // EXIT
                else if(choice.equals("q")){
                    System.exit(0);
                }
                // BAD INPUT 
                else{
                    System.out.println("Bad input; please try again");
                    break;
                }
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
