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
         while(file.hasNextLine()) {            //cycles through names by setting the current element by the next inside the loop
            track.next = new PersonNode(file.next());
            track = track.next;
            size++;
         }
         
         // make the ring circular by attaching last node's next to front
         track.next = circle;
         
         // generate, print, and save the random elimination count
         Random rand = new Random();
         eliminationCount = rand.nextInt(7) + 1;
         System.out.println("Elimination count is " + eliminationCount);

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
      for(int i = 0; i < eliminationCount - 1; i++) {    //removes one from the count because we would need to remove the next node
         track = circle;
         circle = circle.next;
      }
      
      // print who will be eliminated
      System.out.println(circle.name + " has been eliminated.");
      
      // eliminate the person and update "front" of the circle and size
      track.next = circle.next;        //eliminates the element by cycling through every element like in the constructor
      circle = track.next;             // puts the next element of the element we just eliminated to the front 
      size--;

   }
   
   public boolean isOver() {
      // check if there's only one person left in the circle
      if(size == 1) { return true; }
      return false;
   }
   
   public String toString() {
      // if there's only one person left, print them as the last survivor
      String s = "";
      if(size == 1) {
         s += "The last person is " + circle.name;
      } else {
      
      // print the remaining survivors (watch out for infinite loop since list is circular)
         for(int i = 0; i < size; i++) {
            s += (i + 1) + " " + circle.name + ", ";     //Since we removed one from eliminate, we need to add back in one to properly show the count
            circle = circle.next;
         }
      }

      return s;
   }

}

/*
 Elimination count is 6
 1 Muhammad, 2 Beza, 3 Ibrar, 4 Nur, 5 Krystal, 6 River, 7 Soham, 8 Leon, 9 Will, 10 Qiao, 
 
 Continue elimination? <press enter>
 
 River has been eliminated.
 1 Soham, 2 Leon, 3 Will, 4 Qiao, 5 Muhammad, 6 Beza, 7 Ibrar, 8 Nur, 9 Krystal, 
 
 Continue elimination? <press enter>
 
 Beza has been eliminated.
 1 Ibrar, 2 Nur, 3 Krystal, 4 Soham, 5 Leon, 6 Will, 7 Qiao, 8 Muhammad, 
 
 Continue elimination? <press enter>
 
 Will has been eliminated.
 1 Qiao, 2 Muhammad, 3 Ibrar, 4 Nur, 5 Krystal, 6 Soham, 7 Leon, 
 
 Continue elimination? <press enter>
 
 Soham has been eliminated.
 1 Leon, 2 Qiao, 3 Muhammad, 4 Ibrar, 5 Nur, 6 Krystal, 
 
 Continue elimination? <press enter>
 
 Krystal has been eliminated.
 1 Leon, 2 Qiao, 3 Muhammad, 4 Ibrar, 5 Nur, 
 
 Continue elimination? <press enter>
 
 Leon has been eliminated.
 1 Qiao, 2 Muhammad, 3 Ibrar, 4 Nur, 
 
 Continue elimination? <press enter>
 
 Muhammad has been eliminated.
 1 Ibrar, 2 Nur, 3 Qiao, 
 
 Continue elimination? <press enter>
 
 Qiao has been eliminated.
 1 Ibrar, 2 Nur, 
 
 Continue elimination? <press enter>
 
 Nur has been eliminated.
 The last person is Ibrar
*/