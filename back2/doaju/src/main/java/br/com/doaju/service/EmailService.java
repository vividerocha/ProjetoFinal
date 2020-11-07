package br.com.doaju.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.doaju.dto.DoadorDTO;
import br.com.doaju.dto.MensagemDTO;
import br.com.doaju.mapper.DoadorMapper;
import br.com.doaju.mapper.MensagemMapper;
import br.com.doaju.model.Doador;
import br.com.doaju.model.Mensagem;
import br.com.doaju.repository.DoadorRepository;
import br.com.doaju.repository.MensagemRepository;
import lombok.var;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;
    
	@Autowired
	private MensagemMapper mapper;
	
    @Autowired
	private MensagemRepository repository;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String toEmail, String subject, String message) {

        var mailMessage = new SimpleMailMessage();

        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailMessage.setFrom("johndoe@example.com");

        javaMailSender.send(mailMessage);
    }
    
    
}