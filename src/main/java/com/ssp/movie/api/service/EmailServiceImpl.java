package com.ssp.movie.api.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.ssp.movie.api.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    ConfigService configService;


    private boolean isValidEmail(String emailAddress) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex)
                .matcher(emailAddress)
                .matches();
    }

    public void sendEmail(String emailAddress, String searchType, List<Movie> movies) throws IllegalArgumentException, IOException {

        String apiKey = configService.getSendGridAPIKey();
        if (apiKey == null) {
            throw new IllegalStateException("Email not supported");
        }

        if (!isValidEmail(emailAddress)) {
            throw new IllegalArgumentException("Invalid email address");
        }

        Email from = new Email(configService.getMailFrom());
        String subject = "Your movie recommendations";
        Email to = new Email(emailAddress);

        StringBuilder emailBody = new StringBuilder();
        emailBody.append(MessageFormat.format("Your recommendations for: {0}<br><br>", searchType));

        for (Movie movie : movies) {
            emailBody.append(movie.toEmailContent()).append("<br><br>");
        }

        Content content = new Content("text/html", emailBody.toString());
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sendGrid = new SendGrid(apiKey);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        if (!apiKey.equals(configService.getTestKey())) {
            Response response = sendGrid.api(request);

            if (!Arrays.asList(HttpStatus.OK.value(), HttpStatus.CREATED.value(), HttpStatus.ACCEPTED.value()).contains(response.getStatusCode())) {
                throw new IOException("Failed to send email. Response code: " + response.getStatusCode());
            }
        }
    }
}