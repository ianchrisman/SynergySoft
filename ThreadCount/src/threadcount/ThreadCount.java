package threadcount;
//ThreadCount Class
//Contains Main Method
//This class creates the GUI and all tabbed panes
//Final Programming Project
////////////////////////////////////////////////////////////////////////////////
//
//
//15 Nov 2016
//GUI 1.0 is complete.  We may refine it a little bit.  Not sure how to 
//handle deletes yet.  I may need to add a JTextBox to enter the Customer
//Index or Catalog index, then hit delete.  Not sure if we can actually select 
//the item from a JScrollPane.
//
//
////////////////////////////////////////////////////////////////////////////////


class ThreadCount {// Begin class ThreadCount

    public static void main(String[] args) {//Main Method
        ThreadGUI frame = new ThreadGUI();
        System.out.println("got Here main");
        ThreadGUI.createGUI();
    }//End Main Method
}// End Class ThreadCount
