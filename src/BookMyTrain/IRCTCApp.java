package BookMyTrain;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class IRCTCApp {

    private  final Scanner scanner =new Scanner(System.in);

    private final UserService userService=new UserService();

    private final BookingService bookingService=new BookingService();

    public static void main(String[] args) {

        new IRCTCApp().start();
    }


    public void start(){

        while(true){

            System.out.println("-----Welcome To IRCTC APP-----");
            if(!userService.isLoggedIn()){
                System.out.println("1. Registration");
                System.out.println("2. LoggedIn");
                System.out.println("3. Exit");
                System.out.println("Enter Your Choice");
                int choice=scanner.nextInt();

                switch (choice){

                    case 1 -> {
                        registration();

                    }
                    case 2 -> loggedIn();
                    case 3 -> exitAPP();
                    default -> System.out.println("Invalid Choice");
                }
            }
            else{
                showUserMenu();
            }
        }
    }

    public void registration(){

        System.out.println("Enter username");
        String username=scanner.next();
        System.out.println("Enter password");
        String password=scanner.next();
        System.out.println("Enter Full name");
        scanner.nextLine();
        String fullName=scanner.nextLine();
        System.out.println("Enter contact");
        String contact=scanner.nextLine();

        boolean success= userService.registerUser(username,password,contact,fullName);

        if (success){
            userService.loginUser(username,password);
        }


    }

    public void loggedIn(){

        System.out.println("Enter username");
        String username=scanner.next();
        System.out.println("Enter password");
        String password=scanner.next();
        userService.loginUser(username,password);
    }

    private void showUserMenu(){

        while(userService.isLoggedIn()){
            System.out.println("\n-----User Menu-----");
            System.out.println("1. Search Train");
            System.out.println("2. Book Ticket");
            System.out.println("3. View my Ticket");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. View All Trains");
            System.out.println("6. LogOut");
            System.out.println("Enter Your Choice.");

            int choice=scanner.nextInt();

            switch (choice){

                case 1 -> searchTrain();
                case 2 -> bookTicket();
                case 3 -> viewMyTicket();
                case 4 -> cancelTicket();
                case 5 -> bookingService.listAllTrains();
                case 6 -> userService.logOutUser();
                default -> System.out.println("Invalid Choice.");

            }
        }

    }

    private void searchTrain(){
        System.out.println("Enter Source Station: ");
        String source=scanner.next();
        System.out.println("Enter Destination Station: ");
        String destination=scanner.next();
        System.out.println("Enter The date");
        String date=scanner.next();

        List<Train> trains=bookingService.searchTrain(source,destination,date);

        if(trains.isEmpty()){
            System.out.println("No Train found between "+source+"-->"+destination);
            return ;
        }
        System.out.println("Train Found:");

        for(Train train:trains){
            System.out.println(train);
        }

        System.out.println("Do you want to book train? (yes/no)");
        String choice=scanner.next();

        if(choice.equalsIgnoreCase("yes")){

            System.out.println("Enter TrainID to book");
            int trainId=scanner.nextInt();
            System.out.println("Enter number of seat to enter.");
            int seats=scanner.nextInt();

            Ticket ticket=bookingService.bookTicket(userService.getCurerntUser(),seats,trainId);
            if(ticket!=null){
                System.out.println("Booking Successfull");
                System.out.println(ticket);
            }
        }
        else {
            System.out.println("Returning to user menu");
        }

    }

    private void bookTicket(){
        System.out.println("Enter Source Station: ");
        String source=scanner.next();
        System.out.println("Enter Destination Station: ");
        String destination=scanner.next();
        System.out.println("Enter the date");
        String date=scanner.next();
        List<Train> trains=bookingService.searchTrain(source,destination,date);

        if(trains.isEmpty()){
            System.out.println("No Train found between "+source+"-->"+destination);
            return ;
        }
        System.out.println("Train Found:");

        for(Train train:trains){
            System.out.println(train);
        }
        System.out.println("Enter Train ID to book:");
        int trainID=scanner.nextInt();
        System.out.println("Enter number of seats to book:");
        int seats = scanner.nextInt();

        Ticket ticket=bookingService.bookTicket(userService.getCurerntUser(),seats,trainID);
        if(ticket!=null){
            System.out.println("Booking Successfull");
            System.out.println(ticket);
        }
    }
    private void viewMyTicket(){

        List<Ticket> ticketList=bookingService.getTicketByUser(userService.getCurerntUser());
        if(ticketList.isEmpty()){
            System.out.println("No Ticket booked yet.");

        }
        else{
            System.out.println("Ticket found");
            for(Ticket ticket:ticketList){
                System.out.println(ticket);
            }
        }
    }

    private void cancelTicket(){
        System.out.println("Enter TicketId to cancel Ticket.");
        int ticketId=scanner.nextInt();
        bookingService.cancelTicket(ticketId,userService.getCurerntUser());
    }


    private void exitAPP(){

        System.out.println("Thank you to visit IRCTC APP");

        System.exit(0);
    }



}
