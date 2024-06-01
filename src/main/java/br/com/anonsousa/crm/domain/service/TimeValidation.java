package br.com.anonsousa.crm.domain.service;

import br.com.anonsousa.crm.infra.exceptions.InvalidHourException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class TimeValidation {

    public void validateHourInteraction(LocalDateTime interactionHour){
        LocalTime startTime = LocalTime.of(7, 30);
        LocalTime endTime = LocalTime.of(18, 0);

        LocalTime interactionTime = interactionHour.toLocalTime();

        if (interactionTime.isBefore(startTime) || interactionTime.isAfter(endTime)){
            throw new InvalidHourException("As interações com cliente podem ser feitas apenas em horario comercial da companhia, 7:30 até 18:00");
        }
    }

}
