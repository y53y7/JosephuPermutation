import java.util.*;
import java.io.*;

public class JosephusSim {
   private PersonNode circle;     // a PersonNode pointer that tracks first node
   private int size;              // the number of people in the circle
   private int eliminationCount;  // the number to count to for elimination       
   private PersonNode track;      // a PersonNode pointer to help with elimination

   public JosephusSim(String fileName) {
      try {
         // load names from the file in order, generating a singly linked list of PersonNodes
         Scanner file = new Scanner(new File(fileName));
         circle = new PersonNode(file.next());
         track = circle;
         size = 1;
         while(file.hasNextLine()) {
            track.next = new PersonNode(file.next());
            track = track.next;
            size++;
         }
         
         // make the ring circular by attaching last node's next to front
         track.next = circle;
         
         // generate, print, and save the random elimination count
         Random rand = new Random();
         eliminationCount = rand.nextInt(7) + 1;
         System.out.print("Elimination count is " + eliminationCount);

      } catch(FileNotFoundException e) {
         System.out.println("Something went wrong with " + fileName);
      }
   }
   
   // optional helper method for constructing the circle
   private void add(String val) {
   }
   
   public void eliminate() {
      // count to the elimination count
      track = null;
      for(int i = 0; i < eliminationCount; i++) {
         track = circle;
         circle = circle.next;
      }
      
      // print who will be eliminated
      System.out.print(circle.name + " has been eliminated.");
      
      // eliminate the person and update "front" of the circle and size
                          //remove the current node
      track.next = circle.next;
      circle = track.next;
      size--;

   }
   
   public boolean isOver() {
      // check if there's only one person left in the circle
      if(size == 1) { return true; }
      return false;
   }
   
   public String toString() {
      // if there's only one person left, print them as the last survivor
      
      // print the remaining survivors (watch out for infinite loop since list is circular)

      return "";
   }

}