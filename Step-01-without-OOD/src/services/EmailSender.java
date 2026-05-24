package services;

class EmailSender implements MessageSender{
    public void sendEmail(String to, String message){
        System.out.println("[LOG] sending email notification");
        System.out.println("Sending email to " + to + ": " + message);
    }

    public void sendSmsMessage(String to, String message){
        System.out.println("[LOG] email sender cannot send sms");
        System.out.println("EmailSender cannot send SMS to " + to);
    }
}
