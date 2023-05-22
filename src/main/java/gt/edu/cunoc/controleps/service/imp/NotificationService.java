/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.service.imp;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.net.URL;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

/**
 *
 * @author edvin
 */
@Service
public class NotificationService {

     private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    public NotificationService(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendNotification(String email, String subject, String link) throws MailException, InterruptedException, MessagingException {
        Context context = new Context();
        context.setVariable("link", link);

        String html = templateEngine.process("email-template.html", context);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(html, true);

        javaMailSender.send(message);
    }
    
    @Async
    public void enviarConvocatoria(String email, String subject, String message, String url) throws MailException, InterruptedException, MessagingException {
        MimeMessage messagex = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(messagex, true);

        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(message);

        try {
            // Create a URL resource from the Minio URL
            Resource resource = new UrlResource(new URL(url));

            // Extract the file name from the URL
            String fileName = resource.getFilename();

            // Attach the document from the URL
            helper.addAttachment(fileName, resource);
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        }

        javaMailSender.send(messagex);
    }
}
