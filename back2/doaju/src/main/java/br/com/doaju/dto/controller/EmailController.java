package br.com.doaju.dto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.doaju.dto.MensagemDTO;
import br.com.doaju.request.MensagemRequest;
import br.com.doaju.service.EmailService;
import br.com.doaju.service.MensagemService;

@CrossOrigin
@RestController
@RequestMapping("/emailSend")
public class EmailController {

    	@Autowired
        private EmailService emailService;
    	
    	@Autowired
        private MensagemService service;

        @PostMapping
        public void sendmail(@RequestBody MensagemRequest mensagemRequest) {
        	String destinatario = mensagemRequest.getDestinatario();
        	String assunto = mensagemRequest.getAssunto();
        	String mensagem = mensagemRequest.getMensagem();
        	
            emailService.sendMail(destinatario, assunto, mensagem);
            salvar(mensagemRequest);
        }
        
    	public ResponseEntity<?> salvar(MensagemRequest mensagemRequest) {	
    		try {
    			
    			MensagemDTO mensagemDTO = service.salvar(mensagemRequest);			
    			return ResponseEntity.status(HttpStatus.CREATED).body(mensagemDTO);
    		
    		}catch(Exception ex) {
    			return ResponseEntity.badRequest().body(ex.getMessage());
    		}		
    	}

}