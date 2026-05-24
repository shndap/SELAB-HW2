import models.Customer;
import models.LuxuryRoom;
import services.Reservation;
import models.Room;
import services.OnSitePaymentProcessor;
import services.ReservationService;
import services.SmsSender;

public class Main {
    public static void main(String[] args){
        Customer customer = new Customer("Ali", "ali@example.com","09124483765", "Paris");
        Room room = new LuxuryRoom("203", 150);
        Reservation res = new Reservation(room, customer, 2);

        ReservationService service = new ReservationService();
        service.makeReservation(res, new OnSitePaymentProcessor(), new SmsSender());
    }
}
