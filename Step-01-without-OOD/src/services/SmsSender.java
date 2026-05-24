package services;

class SmsSender implements MessageSender {
    public void sendEmail(String to, String message) {
        System.out.println("SmsSender cannot send email to " + to);
    }

    public void sendSmsMessage(String to, String message) {
        System.out.println("Sending sms to " + to + ": " + message);
    }
}
