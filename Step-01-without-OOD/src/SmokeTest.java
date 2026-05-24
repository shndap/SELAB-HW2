import constants.Notifier;
import constants.PaymentMethods;
import models.Customer;
import models.LuxuryRoom;
import models.Room;
import services.Reservation;
import services.ReservationService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SmokeTest {
    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        runScenario("card + email + paris", PaymentMethods.CARD, Notifier.EMAIL, "Paris", 100, 2, true, "Paid by card");
        runScenario("card + sms + london", PaymentMethods.CARD, Notifier.SMS, "London", 80, 3, false, "Paid by card");
        runScenario("paypal + email + paris", PaymentMethods.PAYPAL, Notifier.EMAIL, "Paris", 100, 2, true, "Paid by PayPal");
        runScenario("paypal + sms + berlin", PaymentMethods.PAYPAL, Notifier.SMS, "Berlin", 120, 1, false, "Paid by PayPal");
        runScenario("cash + email + paris", PaymentMethods.CASH, Notifier.EMAIL, "Paris", 100, 2, true, "Paid by cash");
        runScenario("cash + sms + tehran", PaymentMethods.CASH, Notifier.SMS, "Tehran", 90, 4, false, "Paid by cash");
        runScenario("onsite + email + paris", PaymentMethods.ONSITE, Notifier.EMAIL, "Paris", 100, 2, true, "Paid on site");
        runScenario("onsite + sms + madrid", PaymentMethods.ONSITE, Notifier.SMS, "Madrid", 75, 3, false, "Paid on site");

        System.out.println("================================");
        System.out.println("Smoke tests passed: " + passed);
        System.out.println("Smoke tests failed: " + failed);
        System.out.println("================================");

        if (failed > 0) {
            System.exit(1);
        }
    }

    private static void runScenario(String name, PaymentMethods payment, Notifier notifier, String city,
                                    double roomPrice, int nights, boolean expectDiscount, String paymentLabel) {
        System.out.println("[ACTION] makeReservation(payment=" + payment + ", notifier=" + notifier
                + ", city=" + city + ", nights=" + nights + ")");
        String output = capture(() -> {
            Customer customer = new Customer("Ali", "ali@example.com", "09124483765", city);
            Room room = new LuxuryRoom("2" + passed, roomPrice);
            Reservation reservation = new Reservation(room, customer, nights);
            ReservationService service = new ReservationService();
            service.makeReservation(reservation, payment, notifier);
        });

        double expectedTotal = expectDiscount ? roomPrice * nights * 0.9 : roomPrice * nights;
        boolean ok = true;
        ok &= assertContains(name, output, "Processing reservation for Ali");
        ok &= assertContains(name, output, paymentLabel + ": " + expectedTotal);
        ok &= assertContains(name, output, "Total: " + expectedTotal);

        if (expectDiscount) {
            ok &= assertContains(name, output, "Apply city discount for Paris!");
        } else {
            ok &= assertNotContains(name, output, "Apply city discount for Paris!");
        }

        if (notifier == Notifier.EMAIL) {
            ok &= assertContains(name, output, "Sending email to ali@example.com: Your reservation confirmed!");
        } else {
            ok &= assertContains(name, output, "Sending sms to 09124483765: Your reservation confirmed!");
        }

        if (ok) {
            System.out.print(output);
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name);
            System.out.println(output);
        }
    }

    private static String capture(Runnable action) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream capture = new PrintStream(buffer);
        System.setOut(capture);
        try {
            action.run();
        } finally {
            System.out.flush();
            System.setOut(originalOut);
        }
        return buffer.toString();
    }

    private static boolean assertContains(String name, String output, String expected) {
        if (!output.contains(expected)) {
            System.out.println("[ASSERTION FAILED] " + name + " missing: " + expected);
            return false;
        }
        return true;
    }

    private static boolean assertNotContains(String name, String output, String unexpected) {
        if (output.contains(unexpected)) {
            System.out.println("[ASSERTION FAILED] " + name + " unexpectedly contained: " + unexpected);
            return false;
        }
        return true;
    }
}
