import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;


public class Library_manager 
{   

    public static class Book
    {
        private String title,author,genre;
        private int numberOfBooks;
        Book(String title,String author,String genre,int numberOfBooks)
        {
            this.title=title;
            this.author=author;
            this.genre=genre;
            this.numberOfBooks=numberOfBooks;
        }
        public String getTitle()
        {
            return title;
        }
        public void setTitle(String title)
        {
            this.title=title;
        }
        public String getAuthor()
        {
            return author;
        }
        public void setAuthor(String author)
        {
            this.author=author;
        }
        public String getGenre()
        {
            return genre;
        }
        public void setGenre(String genre)
        {
            this.genre=genre;
        }
        public int getNumberOfBooks()
        {
            return numberOfBooks;
        }
        public void setNumberOfBooks(int numberOfBooks)
        {
            this.numberOfBooks=numberOfBooks;
        }
        public boolean isAvailable()
        {
            return numberOfBooks>0;
        }
        class BorrowInfo
        {
            String borrowerName;
            LocalDate dueDate;

            BorrowInfo(String borrowerName, LocalDate dueDate)
            {
                this.borrowerName=borrowerName;
                this.dueDate=dueDate;
            }

            void printInfo()
            {
                System.out.println(title+" is borrowed by "+borrowerName+", date : "+dueDate);
            }
        }
    }


    public static abstract class Person
    {
        private final String name,id;
        Person(String name,String id)
        {
            this.name=name;
            this.id=id;
        }
        public String getName()
        {
            return name;
        }
        public String getId()
        {
            return id;
        }
    } 


    public interface Privileged
        {
            String getPrivileges();
        }


    public static class Member extends Person implements Privileged
    {
        private final types type;
        Member(String name,String id,types type)
        {
            super(name,id);
            this.type=type;
        }
        public types getType()
        {
            return type;
        }
        @Override 
        public String getPrivileges()
        {
            return "Can borrow up to "+type.getMaxBooks()+" books as a "+type+"";
        }
    }


    public static class Librarian extends Person implements Privileged
    {
        private final String department;
        Librarian(String name,String id,String department)
        {
            super(name,id);
            this.department=department;
        }
        public String getDepartment()
        {
            return department;
        }
        @Override 
        public String getPrivileges()
        {
            return "Manages "+department+" department, full library access";
        }
    }


    /*enum constructors*/
    public enum types
    {
        STUDENT(3),
        TEACHER(5),
        STAFF(1);

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


    public static void displayBooks(ArrayList<Book> Books)
    {
        if(!Books.isEmpty())
        {
            System.out.println("Showing all books from library :");
            for (int i = 0; i < Books.size(); i++)
            {
                Book b=Books.get(i);
                System.out.println("Book name :"+b.getTitle()+"  Book author :"+b.getAuthor()+"  Book genre :"+b.getGenre()+"  Books available :"+b.getNumberOfBooks()+"");
            }
        }
        else
        {
            System.out.println("No books available");
        }
    }
    

    public static void displayBooks(ArrayList<Book> Books,String genreFilter)
    {
        if(!Books.isEmpty())
        {
            System.out.println("Showing all books from library :");
            for (int i = 0; i < Books.size(); i++)
            {
                if(Books.get(i).getGenre().equals(genreFilter))
                {
                    Book b=Books.get(i);
                    System.out.println("Book name :"+b.getTitle()+"  Book author :"+b.getAuthor()+"  Book genre :"+b.getGenre()+"  Books available :"+b.getNumberOfBooks()+"");
                }

            }
        }
        else
        {
            System.out.println("No books available");
        }
    }


      public static boolean recursiveSearchTitle(ArrayList<Book> Books,int index,String search)
    {
        if(index==Books.size())
        {
            return false;
        }
        if(Books.get(index).getTitle().equals(search))
        {
            return true;
        }
        return recursiveSearchTitle(Books, index+1, search);
    }


    public static boolean recursiveSearchAuthor(ArrayList<Book> Books,int index,String search)
    {
        if(index==Books.size())
        {
            return false;
        }
        if(Books.get(index).getAuthor().equals(search))
        {
            return true;
        }
        return recursiveSearchAuthor(Books, index+1, search);
    }


    public static void displayMembers(ArrayList<Member> Members,ArrayList<String> BorrowerName)
    {
        double percentBorrowing;
        System.out.println("Showing all members :");
        for(int i=0;i<Members.size();i++)
        {
            System.out.println("Id :"+Members.get(i).getId()+"  Names :"+Members.get(i).getName()+"  Membership type :"+Members.get(i).getType()+"");
        }
        if(!Members.isEmpty())
        {
            percentBorrowing=(double) BorrowerName.size()/Members.size()*100;
            System.out.println("Percentage of members currently borrowing books :"+percentBorrowing+"%");
        }
    }


    public static void bookBorrower(ArrayList<String> BorrowerName,ArrayList<String> BorrowerTitle,ArrayList<LocalDate> DueDates)
    {
        System.out.println("Showing all book borrowers :");
        for(int i=0;i<BorrowerName.size();i++)
        {
            System.out.println("Borrower name :"+BorrowerName.get(i)+"  Borrowed book :"+BorrowerTitle.get(i)+"  Due date :"+DueDates.get(i)+"");
        }
    }


    public static void overdueBooks(ArrayList<LocalDate> DueDates,ArrayList<String> BorrowerTitle,ArrayList<String> BorrowerName)
    {
        String[] lateness={"Just overdue","Moderately overdue","Severely overdue"};
        long daysLate;
        int index;
        boolean found=false;
        System.out.println("Showing overdue books :");
        for(int i=0;i<DueDates.size();i++)
        {
            if(!DueDates.get(i).isBefore(LocalDate.now()))
            {
                continue;
            }
            else
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
            System.out.println("No overdue books borrowed");
        }
    }

    
    public static void main(String[] args) 
    {   
        System.out.println("-----Library manager-----");
        int choice,status;
        String name,type,genreFilter;
        boolean found,exit=false,availabilityStatus,updateStatus;

        ArrayList<Book>Books=new ArrayList<>();
        ArrayList<Member>Members=new ArrayList<>();
        ArrayList<Privileged>AllPeople=new ArrayList<>();

        ArrayList<String>BorrowerName = new ArrayList<>();
        ArrayList<String>BorrowerTitle = new ArrayList<>();
        ArrayList<LocalDate>DueDates = new ArrayList<>();

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
                        int numberOfBooks;
                        String title,author,genre;
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

                        Book book=new Book(title, author, genre, numberOfBooks);
                        Books.add(book);
                        System.out.println("Book details added");
                    }


                    case 2->
                    {
                        System.out.print("Enter genre to filter by (or press Enter to see all):");
                        genreFilter = val.nextLine().trim();
                        if(genreFilter.isEmpty())
                        {
                            displayBooks(Books);
                        }
                        else
                        {
                            displayBooks(Books,genreFilter);
                        }
                    }
                    

                    case 3->
                    {
                        int choose;
                        String search;
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
                                found=recursiveSearchTitle(Books,0, search);
                                if(found==true)
                                {
                                    System.out.println("Book found");
                                }
                                else
                                {
                                    System.out.println("No books found");
                                }
                            }


                            case 2->
                            {
                                System.out.print("Enter the author of book to search :");
                                search=val.nextLine().trim();
                                System.out.println("Searching...");
                                found=recursiveSearchAuthor(Books,0, search);
                                if(found==true)
                                {
                                    System.out.println("Book found");
                                }
                                else
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
                        String remove;
                        found=false;
                        System.out.print("Enter the title of book to remove :");
                        remove=val.nextLine().trim();
                        for(int i=Books.size()-1;i>=0;i--)
                        {
                            if(Books.get(i).getTitle().equals(remove))
                            {
                                Books.remove(i);
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
                        String id;
                        boolean validType;
                        int idNumber;
                        validType=false;
                        System.out.println("Register new member details :");
                        System.out.print("Enter the name :");
                        name=val.nextLine().trim();  

                        idNumber=Members.size()+1; 
                        id=String.format("%05d",idNumber);


                        System.out.println("Types shown :");    
                        for(types value:types.values())
                        {
                            System.out.println(value);
                        }           
                        System.out.print("Enter the type :");     
                        type=val.nextLine().trim().toUpperCase();
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
                            types memberType=types.valueOf(type);
                            Member member=new Member(name,id,memberType);
                            Members.add(member);
                            AllPeople.add(member);
                            System.out.println("New member registered");
                        }
                        else
                        {
                            System.out.println("Type not available.Member not registered");
                        }
                    }


                    case 6->
                    {
                        displayMembers(Members,BorrowerName);
                    }


                    case 7->
                    {
                        String available,borrow;
                        int borrowCount,limit;
                        types memberType;
                        availabilityStatus=false;
                        borrowCount=0;
                        memberType=null;
                        System.out.print("Enter the title of book to check availability :");
                        available=val.nextLine().trim();
                        for(int i=0;i<Books.size();i++)
                        {
                            if(Books.get(i).getTitle().equals(available))
                            {
                                if(Books.get(i).getNumberOfBooks()>0)
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
                                        for(int j=0;j<Members.size();j++)
                                        {
                                            if(Members.get(j).getName().equals(name))
                                            {
                                                memberType=Members.get(j).getType();   
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
                                        if(memberType==null)
                                        {
                                            System.out.println("Something went wrong");
                                            break;
                                        }
                                        limit=memberType.getMaxBooks();
                                        if(borrowCount>=limit)
                                        {
                                            System.out.println(name+" has reached borrowing limit of "+limit+" books");
                                            break;
                                        }
                                        else
                                        {
                                            Books.get(i).setNumberOfBooks(Books.get(i).getNumberOfBooks()-1);
                                            BorrowerName.add(name);
                                            BorrowerTitle.add(available);
                                            DueDates.add(LocalDate.now().plusDays(7));
                                            Book.BorrowInfo value=Books.get(i).new BorrowInfo(name,LocalDate.now());
                                            value.printInfo();
                                            for(int j=0;j<AllPeople.size();j++)
                                            {
                                                System.out.println(AllPeople.get(j).getPrivileges());
                                            }
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
                        String update;
                        updateStatus=false;
                        System.out.print("Enter the title of book to update status :");
                        update=val.nextLine().trim();
                        for(int i=0;i<Books.size();i++)
                        {
                            if(Books.get(i).getTitle().equals(update))
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
                                Books.get(i).setNumberOfBooks(status);
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
                        String returnBook;
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
                            for(int i=0;i<Books.size();i++)
                            {
                                if(Books.get(i).getTitle().equals(returnBook))
                                {
                                    Books.get(i).setNumberOfBooks(Books.get(i).getNumberOfBooks()+1);
                                    System.out.println(name+" returned "+returnBook+" book");
                                    break;
                                }
                            }
                        }
                    }


                    case 10->
                    {
                        bookBorrower(BorrowerName,BorrowerTitle,DueDates);
                    }


                    case 11->
                    {
                        overdueBooks(DueDates,BorrowerTitle,BorrowerName);
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
