package BookMyTrain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookingService {

    private List<Train> trainList=new ArrayList<>();
    private List<Ticket> ticketList=new ArrayList<>();

    public BookingService(){

        trainList.add(new Train(101,"Rajdhani Express","Delhi","Lucknow",100,"07-09-2025"));
        trainList.add(new Train(102,"Shatabdi Express","Delhi","Mumbai",60,"07-09-2025"));
        trainList.add(new Train(103,"Durunto Express","Agra","Delhi",70,"08-09-2025"));
        trainList.add(new Train(104,"vande Bharat Express","Delhi","Goa",100,"09-09-2025"));
        trainList.add(new Train(105,"Intercity","Kolkata","Manali",90,"08-09-2025"));
        trainList.add(new Train(106,"Tejas Express","Delhi","Bengaluru",80,"10-09-2025"));

    }
//HomeWork add date to for filteration
    public List<Train> searchTrain(String source ,String destination,String date){

        List<Train> res=new ArrayList<>();

        for(Train train:trainList){
            if(train.getSource().equalsIgnoreCase(source) && train.getDestination().equalsIgnoreCase(destination )
            && train.getDate().equalsIgnoreCase(date)){
                res.add(train);
            }

        }
        return res;

    }


    public Ticket bookTicket(User user,int seatCount,int trainId){

        for(Train train:trainList){
            if(train.getTrainId()==trainId){
                if(train.bookSeats(seatCount)){

                    Ticket ticket=new Ticket(user,train,seatCount);
                    ticketList.add(ticket);
                    return ticket;
                }
                System.out.println("Seat Not Available");
                return null;
            }
        }
        System.out.println("TrainID not found ");
        return null;
    }

    public List<Ticket> getTicketByUser(User user){

        List<Ticket> res= new ArrayList<>();
        for(Ticket ticket:ticketList){
            if(ticket.getUser().getUserName().equalsIgnoreCase(user.getUserName())){
                res.add(ticket);
            }
        }
        return res;
    }
    public boolean cancelTicket(int ticketId,User user){

        Iterator<Ticket> iterator=ticketList.iterator();
        while(iterator.hasNext()){

            Ticket ticket=iterator.next();
            if(ticket.getTicketId()==ticketId && ticket.getUser().getUserName().equalsIgnoreCase(user.getUserName())){
                Train train=ticket.getTrain();
                train.cancelSeats(ticket.getSeatBooked());

                iterator.remove();
                System.out.println("Ticket cancel Successfully ");
                return true;
            }
        }
        System.out.println("TicketID not found or userName does Not exist");
        return false;
    }
    public void listAllTrains(){
        System.out.println("List of Trains");
        for(Train train:trainList){

            System.out.println(train);
        }
    }
}
