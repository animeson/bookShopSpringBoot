package com.svistun.bookshoop.service.email;

import com.svistun.bookshoop.entity.User;
import com.svistun.bookshoop.entity.UserVerifiedCode;
import com.svistun.bookshoop.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final CodeRepository codeRepository;
    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }


    public void sendRegisterCode(User user) {
        String generateCode = generateCode();
        sendEmail(user.getEmail(), "Activate account",
                "copy this code \n" + generateCode );
        var code = UserVerifiedCode.builder()
                .code(generateCode)
                .deadline(LocalDateTime.now().plusHours(24))
                .user(user)
                .build();
        codeRepository.save(code);

    }

    public void sendMessage(User user, String subject, String body ){
        sendEmail(user.getEmail(),subject,body);

    }


    private String generateCode(){
        return UUID.randomUUID().toString();
    }


}
