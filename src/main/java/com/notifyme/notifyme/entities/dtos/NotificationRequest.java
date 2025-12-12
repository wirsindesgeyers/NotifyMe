package com.notifyme.notifyme.entities.dtos;

import com.notifyme.notifyme.entities.Notification;
import com.notifyme.notifyme.entities.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record NotificationRequest(

        @NotBlank(message = "O destinatário é obrigatório")
        @Email(message = "O e-mail deve ser válido")
        String recipient,

        @NotBlank(message = "O assunto é obrigatório")
        String subject,

        @NotBlank(message = "A mensagem não pode estar vazia")
        String message) {

        public Notification toEntity() {
            Notification notification = new Notification();
            notification.setRecipient(this.recipient);
            notification.setSubject(this.subject);
            notification.setMessage(this.message);
            notification.setStatus(Status.PENDING);
            return notification;
        }
    }

