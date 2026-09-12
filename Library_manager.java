import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Library_manager 
{   
    /*enum constructors*/
    enum types
        {
            Student(3),
            Teacher(5),
            Staff(1);

            final int maxBooks;

            types(int maxBooks)
            {
                this.maxBooks=maxBooks;
            }
            public int getMaxBooks()
            {
                return maxBooks;
            }
        }
    public static void main(String[] args) 
    {   
        System.out.println("-----Library manager-----");
        int choice,choose,numberOfBooks,status,borrowCount,limit,idNumber,index;
        String title,author,genre,search,remove,name,type,available,update,borrow,returnBook,id;
        boolean found,validType,exit=false,availabilityStatus,updateStatus;
        double percentBorrowing;
        long daysLate;

        ArrayList<String>Titles = new ArrayList<>();
        ArrayList<String>Authors = new ArrayList<>();
        ArrayList<String>Genres = new ArrayList<>();
        ArrayList<Integer>UpdateStatus = new ArrayList<>();
        ArrayList<String>Ids = new ArrayList<>();
        ArrayList<String>Names = new ArrayList<>();
        ArrayList<String>Types = new ArrayList<>();
        ArrayList<String>BorrowerName = new ArrayList<>();
        ArrayList<String>BorrowerTitle = new ArrayList<>();
        ArrayList<LocalDate>DueDates = new ArrayList<>();

        String[] lateness={"Just overdue","Moderately overdue","Severely overdue"};
        String[] options={"1-Add book","2-Display books","3-Search book","4-Remove book","5-Register member","6-Display members",
        "7-Borrow book","8-Update status of books","9-Return book","10-Book borrowers","11-Over due books","12-Exit"};
        String []option={"1-Search by title","2-search by author"};

        try(Scanner val=new Scanner(System.in))
        {
            while (true) 
            {
                System.out.println("Menu :");
                for (String value : options)
                {
                    System.out.println(value);
                }
                choice=-1;
                while(choice==-1)
                {
                    try
                    {
                        System.out.print("Enter the choice number :");
                        choice = val.nextInt();
                        val.nextLine();
                    }
                    catch(java.util.InputMismatchException e)
                    {
                        System.out.println("Invalid choice, Enter the number");
                        val.nextLine();
                        choice=-1;
                    }
                }
                switch(choice)
                {
                    case 1->
                    {
                        System.out.println("Enter the details of book to add :");
                        System.out.print("Enter the title of book :");
                        title = val.nextLine().trim();
                        System.out.print("Enter the author of book :");
                        author = val.nextLine().trim();
                        System.out.print("Enter the genre of book :");
                        genre = val.nextLine().trim();

                        numberOfBooks=-9865;
                        while(numberOfBooks==-9865)
                        {
                            try
                            {
                                System.out.print("Enter the number of books :");
                                numberOfBooks = val.nextInt();
                                val.nextLine();
                                if(numberOfBooks<=0)
                                {
                                    throw new IllegalArgumentException("Number of books cannot be negative");
                                }
                            }
                            catch(java.util.InputMismatchException e)
                            {
                                System.out.println("Invalid input, Enter number only ");
                                val.nextLine();
                                numberOfBooks=-9865;
                            }
                            catch(IllegalArgumentException e)
                            {
                                System.out.println("Invalid input, Enter positive number only");
                                numberOfBooks=-9865;
                            }
                        }

                        Titles.add(title);
                        Authors.add(author);
                        Genres.add(genre);
                        UpdateStatus.add(numberOfBooks);
                        System.out.println("Book details added");
                    }

                    case 2->
                    {
                        if(!Titles.isEmpty())
                        {
                            System.out.println("Showing all books from library :");
                            for (int i = 0; i < Titles.size(); i++)
                            {
                                System.out.println("Book name :"+Titles.get(i)+"  Book author :"+Authors.get(i)+"  Book genre :"+Genres.get(i)+"  Books available :"+UpdateStatus.get(i)+"");
                            }
                        }
                        else
                        {
                            System.out.println("No books available");
                        }
                    }
                    
                    case 3->
                    {
                        found=false;
                        System.out.println("Menu :");
                        for (String value : option)
                        {
                            System.out.println(value);
                        }
                        choose=-1;
                        while(choose==-1)
                        {
                            try
                            {
                                System.out.print("Enter the choice number :");
                                choose=val.nextInt();
                                val.nextLine();
                            }
                            catch(java.util.InputMismatchException e)
                            {
                                System.out.println("Invalid input, Enter the number");
                                val.nextLine();
                                choose=-1;
                            }
                        }
                        switch(choose)
                        {
                            case 1->
                            {
                                System.out.print("Enter the title of book to search :");
                                search=val.nextLine().trim();
                                System.out.println("Searching...");
                                for(int i=0;i<Titles.size();i++)
                                {
                                    if(Titles.get(i).equals(search))
                                    {
                                        System.out.println("Book Found at index :"+i);
                                        found=true;
                                    }
                                }
                                if(found==false)
                                {
                                    System.out.println("No books found");
                                }
                            }

                            case 2->
                            {
                                System.out.print("Enter the author of book to search :");
                                search=val.nextLine().trim();
                                System.out.println("Searching...");
                                for(int i=0;i<Authors.size();i++)
                                {
                                    if(Authors.get(i).equals(search))
                                    {
                                        System.out.println("Book Found at index :"+i);
                                        found=true;
                                    }
                                }
                                if(found==false)
                                {
                                    System.out.println("No books found");
                                }
                            }
                            
                            default->
                            {
                                System.out.println("Invalid option chosen");
                            }
                        }
                    }

                    case 4->
                    {
                        found=false;
                        System.out.print("Enter the title of book to remove :");
                        remove=val.nextLine().trim();
                        for(int i=Titles.size()-1;i>=0;i--)
                        {
                            if(Titles.get(i).equals(remove))
                            {
                                Titles.remove(i);
                                Authors.remove(i);
                                Genres.remove(i);
                                UpdateStatus.remove(i);
                                System.out.println("Book details removed");
                                found=true;
                            }
                        }
                        if(found==false)
                        {
                            System.out.println("No book found to remove");
                        }
                    }
                    
                    case 5->
                    {
                        validType=false;
                        System.out.println("Register new member details :");
                        System.out.print("Enter the name :");
                        name=val.nextLine().trim();  

                        idNumber=Ids.size()+1; 
                        id=String.format("%05d",idNumber);


                        System.out.println("Types shown :");    
                        for(types value:types.values())
                        {
                            System.out.println(value);
                        }           
                        System.out.print("Enter the type :");     
                        type=val.nextLine().trim();
                        for(types value:types.values())
                        {
                            if(value.toString().equalsIgnoreCase(type))
                            {
                                validType=true;
                                break;
                            }
                        }  
                        if(validType==true)
                        {
                            Ids.add(id);
                            Names.add(name);
                            Types.add(type);
                            System.out.println("New member registered");
                        }
                        else
                        {
                            System.out.println("Type not available.Member not registered");
                        }
                    }

                    case 6->
                    {
                        System.out.println("Showing all members :");
                        for(int i=0;i<Names.size();i++)
                        {
                            System.out.println("Id :"+Ids.get(i)+"  Names :"+Names.get(i)+"  Membership type :"+Types.get(i)+"");
                        }
                        if(!Names.isEmpty())
                        {
                            percentBorrowing=(double) BorrowerName.size()/Names.size()*100;
                            System.out.println("Percentage of members currently borrowing books :"+percentBorrowing+"%");
                        }
                    }

                    case 7->
                    {
                        availabilityStatus=false;
                        borrowCount=0;
                        type="";
                        System.out.print("Enter the title of book to check availability :");
                        available=val.nextLine().trim();
                        for(int i=0;i<Titles.size();i++)
                        {
                            if(Titles.get(i).equals(available))
                            {
                                if(UpdateStatus.get(i)>0)
                                {
                                    System.out.println("Book available");
                                    availabilityStatus=true;
                                    System.out.print("Enter (y/n) to borrow book :");
                                    borrow=val.nextLine();

                                    if(borrow.equalsIgnoreCase("y"))
                                    {
                                        System.out.print("Enter the name :");
                                        name=val.nextLine().trim();
                                        found=false;
                                        for(int j=0;j<Names.size();j++)
                                        {
                                            if(Names.get(j).equals(name))
                                            {
                                                type=Types.get(j);   
                                                found=true;
                                                break;
                                            }
                                        }
                                        if(found==false)
                                        {
                                            System.out.println(name+" is not a registered member.cannot borrow book");
                                            break;
                                        }
                                        for(int j=0;j<BorrowerName.size();j++)
                                        {
                                            if(BorrowerName.get(j).equals(name))
                                            {
                                                borrowCount++;
                                            }
                                        }
                                        types memberType=types.valueOf(type);
                                        limit=memberType.getMaxBooks();
                                        if(borrowCount>=limit)
                                        {
                                            System.out.println(name+" has reached borrowing limit of "+limit+" books");
                                            break;
                                        }
                                        else
                                        {
                                            status=UpdateStatus.get(i);
                                            System.out.println(""+name+" borrowed "+available+" book");
                                            UpdateStatus.set(i,status-1);
                                            BorrowerName.add(name);
                                            BorrowerTitle.add(available);
                                            DueDates.add(LocalDate.now().plusDays(7));
                                            break;
                                        }
                                    }
                                    else
                                    {
                                        break;
                                    }  
                                }
                            }
                        }
                        if(availabilityStatus==false)
                        {
                            System.out.println("Book not available");
                        }
                    }

                    case 8->
                    {
                        updateStatus=false;
                        System.out.print("Enter the title of book to update status :");
                        update=val.nextLine().trim();
                        for(int i=0;i<Titles.size();i++)
                        {
                            if(Titles.get(i).equals(update))
                            {
                                status=-1;
                                while(status==-1)
                                {
                                    try
                                    {
                                        System.out.print("Enter new number of book available :");
                                        status=val.nextInt();
                                        val.nextLine();
                                        status=Math.max(status,0);
                                    }
                                    catch(java.util.InputMismatchException e)
                                    {
                                        System.out.println("Invalid input, Enter the number");
                                        val.nextLine();
                                        status=-1;
                                    }
                                }
                                UpdateStatus.set(i,status);
                                System.out.println("Book availablity updated");
                                updateStatus=true;
                                break;
                            }
                        }
                        if(updateStatus==false)
                        {
                            System.out.println("Book not found");
                        }
                    }

                    case 9->
                    {
                        availabilityStatus=false;
                        System.out.print("Enter the title  of book to return :");
                        returnBook=val.nextLine().trim();
                        System.out.print("Enter the name :");
                        name=val.nextLine().trim();
                        for(int i=0;i<BorrowerTitle.size();i++)
                        {
                            if(BorrowerTitle.get(i).equals(returnBook) && BorrowerName.get(i).equals(name))
                            {
                                BorrowerName.remove(i);   
                                BorrowerTitle.remove(i);
                                DueDates.remove(i);
                                availabilityStatus=true;
                                break;                         
                            }
                        }
                        if(availabilityStatus==false)
                        {
                            System.out.println("Book "+returnBook+" wasn't borrowed from here");
                        }
                        else
                        {
                            for(int i=0;i<Titles.size();i++)
                            {
                                if(Titles.get(i).equals(returnBook))
                                {
                                    status=UpdateStatus.get(i);
                                    UpdateStatus.set(i,status+1);
                                    System.out.println(name+" returned "+returnBook+" book");
                                    break;
                                }
                            }
                        }
                    }

                    case 10->
                    {
                        System.out.println("Showing all book borrowers :");
                        for(int i=0;i<BorrowerName.size();i++)
                        {
                            System.out.println("Borrower name :"+BorrowerName.get(i)+"  Borrowed book :"+BorrowerTitle.get(i)+"  Due date :"+DueDates.get(i)+"");
                        }
                    }

                    case 11->
                    {
                        found=false;
                        System.out.println("Showing overdue books :");
                        for(int i=0;i<DueDates.size();i++)
                        {
                            if(DueDates.get(i).isBefore(LocalDate.now()))
                            {
                                daysLate=ChronoUnit.DAYS.between(DueDates.get(i),LocalDate.now());
                                if(daysLate>=10)
                                {
                                    index=2;
                                }
                                else if(daysLate>=5)
                                {
                                    index=1;
                                }
                                else
                                {
                                    index=0;
                                }
                                System.out.println("Borrower name :"+BorrowerName.get(i)+"  Borrowed book :"+BorrowerTitle.get(i)+"  Due date :"+DueDates.get(i)+"  OVERDUE :"+lateness[index]+"");
                                found=true;
                            }
                        }
                        if(found==false)
                        {
                            System.out.println("No overdue book borrowers");
                        }
                    }

                    case 12->
                    {
                        System.out.println("Visit again");
                        exit=true;
                    }

                    default->
                    {
                        System.out.println("Invalid option chosen");
                    }
                }
                if(exit==true)
                {
                    break;
                }
            }
        }
    }
}
