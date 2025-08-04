package com.bash.Event.ticketing.event.controller;

import com.bash.Event.ticketing.event.dto.request.PurchaseRequest;
import com.bash.Event.ticketing.event.dto.request.TicketRequest;
import com.bash.Event.ticketing.event.dto.response.DashboardInsights;
import com.bash.Event.ticketing.event.dto.response.TicketResponse;
import com.bash.Event.ticketing.event.service.EventService;
import com.bash.Event.ticketing.event.dto.request.EventRequest;
import com.bash.Event.ticketing.event.dto.response.EventResponse;
import com.bash.Event.ticketing.event.dto.response.MessageResponse;
import com.bash.Event.ticketing.event.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventsController {

    private final EventService eventsService;
    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<MessageResponse<EventResponse>> createEvent(@RequestBody EventRequest eventRequest) {
        MessageResponse<EventResponse> response = eventsService.createEvent(eventRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<MessageResponse<EventResponse>> updateEvent(@PathVariable UUID eventId, @RequestBody EventRequest eventRequest) {
        MessageResponse<EventResponse> response = eventsService.updateEvent(eventId, eventRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<MessageResponse<Void>> deleteEvent(@PathVariable UUID eventId) {
        MessageResponse<Void> response = eventsService.deleteEvent(eventId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<MessageResponse<EventResponse>> getEventById(@PathVariable UUID eventId) {
        MessageResponse<EventResponse> response = eventsService.getEventById(eventId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<MessageResponse<Page<EventResponse>>> getAllEvents(@RequestParam(defaultValue = "0") int page,
                                                                              @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        MessageResponse<Page<EventResponse>> response = eventsService.getAllEvents(pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{eventId}/tickets")
    public ResponseEntity<MessageResponse<TicketResponse>> createTicket(
            @PathVariable UUID eventId, 
            @RequestBody TicketRequest ticketRequest) {
        MessageResponse<TicketResponse> response = ticketService.createTicket(eventId, ticketRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{eventId}/tickets")
    public ResponseEntity<MessageResponse<List<TicketResponse>>> getEventTickets(@PathVariable UUID eventId) {
        MessageResponse<List<TicketResponse>> response = ticketService.getEventTickets(eventId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{eventId}/tickets/{ticketId}/purchase")
    public ResponseEntity<MessageResponse<TicketResponse>> purchaseTicket(
            @PathVariable UUID eventId,
            @PathVariable UUID ticketId,
            @RequestBody PurchaseRequest purchaseRequest) {
        MessageResponse<TicketResponse> response = ticketService.purchaseTicket(eventId, ticketId, purchaseRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/tickets/{ticketId}/scan")
    public ResponseEntity<MessageResponse<TicketResponse>> scanTicket(@PathVariable UUID ticketId) {
        MessageResponse<TicketResponse> response = ticketService.scanTicket(ticketId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/dashboard/insights")
    public ResponseEntity<MessageResponse<DashboardInsights>> getDashboardInsights() {
        MessageResponse<DashboardInsights> response = ticketService.getDashboardInsights();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/my-events")
    public ResponseEntity<MessageResponse<Page<EventResponse>>> getUserEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        MessageResponse<Page<EventResponse>> response = eventsService.getUserEvents(pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/dashboard/my-insights")
    public ResponseEntity<MessageResponse<DashboardInsights>> getUserDashboardInsights() {
        MessageResponse<DashboardInsights> response = ticketService.getUserDashboardInsights();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
