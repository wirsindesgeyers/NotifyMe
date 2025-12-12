package com.notifyme.notifyme.repository.notifications;


import com.notifyme.notifyme.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
