package com.ambita.service;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ambita.config.EmailConfig;

import lombok.Setter;

@Setter
@Service
public class EmailService {

  private String text;
  private String to;
  private String subject;
  private EmailConfig config;

  @Autowired
  public void setConfig(EmailConfig config) {
    this.config = config;
  }

  void send() {
    try {
      HtmlEmail email = new HtmlEmail();
      email.setFrom(config.getEmailFrom());
      email.setHostName(config.getEmailHost());
      email.setSmtpPort(config.getEmailPort());
      email.addTo(to);
      email.setSubject(subject);
      email.setHtmlMsg(text);
      email.send();
    }
    catch (EmailException e) {
      e.printStackTrace();
    }
  }
}