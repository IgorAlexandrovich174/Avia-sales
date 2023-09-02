package ru.venomgopro.aviasales.controller;

import org.springframework.web.bind.annotation.*;
import ru.venomgopro.aviasales.controller.dto.TicketCreateRequest;
import ru.venomgopro.aviasales.model.Ticket;
import ru.venomgopro.aviasales.repository.TicketRepository;

@RestController
public class TicketController {

    TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // TODO: 04.08.2023 доделать
    @PostMapping("flights/{flightId}/tickets")
    public Ticket create(@PathVariable Long flightId, @RequestBody TicketCreateRequest ticketCreateRequest) {
        return ticketRepository.create(flightId, ticketCreateRequest);
    }

    @DeleteMapping("flights/{flightId}/tickets/{ticketId}")
    public void delete(@PathVariable String ticketId) {
        ticketRepository.delete(ticketId);
    }

    @PutMapping(path ="flights/{flightId}/tickets/{ticketId}")
    public Ticket change(@PathVariable String ticketId, @RequestBody TicketCreateRequest ticketCreateRequest) {
        return ticketRepository.change(ticketId,ticketCreateRequest);
    }
}
