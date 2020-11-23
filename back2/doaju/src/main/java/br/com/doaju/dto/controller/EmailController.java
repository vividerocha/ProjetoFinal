package br.com.doaju.dto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.doaju.dto.MensagemDTO;
import br.com.doaju.dto.controller.swagger.EmailControllerSwagger;
import br.com.doaju.request.MensagemRequest;
import br.com.doaju.service.EmailService;
import br.com.doaju.service.MensagemService;

@CrossOrigin
@RestController
@RequestMapping("/emailSend")

public class EmailController implements EmailControllerSwagger{

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
        
        @PostMapping("/senha")
        public void enviarEmailSenha(@RequestBody MensagemRequest mensagemRequest) {
        	String destinatario = mensagemRequest.getDestinatario();
        	String assunto = mensagemRequest.getAssunto();
        	String mensagem = mensagemRequest.getMensagem();
        	
            emailService.sendMail(destinatario, assunto, mensagem);            
        }
        
    	public ResponseEntity<?> salvar(MensagemRequest mensagemRequest) {	
    		try {
    			
    			MensagemDTO mensagemDTO = service.salvar(mensagemRequest);			
    			return ResponseEntity.status(HttpStatus.CREATED).body(mensagemDTO);
    		
    		}catch(Exception ex) {
    			return ResponseEntity.badRequest().body(ex.getMessage());
    		}		
    	}
    	
    	@GetMapping("/{id}")
    	public ResponseEntity<?> buscar(@PathVariable Long id) {
    		
    		MensagemDTO mensagemDTO = service.buscar(id);
    		
    		if (mensagemDTO != null) {
    			return ResponseEntity.ok(mensagemDTO);
    		}
    		
    		return ResponseEntity.badRequest().body("Não retornou Mensagem!");
    	
    	}
    	
    	@GetMapping
    	public List<MensagemDTO> listar(){
    		return service.listar();
    	}

}