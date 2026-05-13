package com.jgtech.pqrs.domain.model;
import com.jgtech.pqrs.domain.enums.RequestStatus;
import java.time.LocalDateTime;

public class Request {
    private Long id;
    private String title;
    private String description;
    private RequestStatus status;
    private LocalDateTime createdAt;

    //constructor
    public Request(Long id,String title,String description, RequestStatus status, LocalDateTime createdAt){
    this.id = id;
    this.title=title;
    this.description=description;
    this.status=status;
    this.createdAt=createdAt;
    }

    //Setters
    public void resolve() {

        if (status == RequestStatus.RESOLVED) {
            throw new IllegalStateException(
                    "Request already resolved"
            );
        }

        this.status = RequestStatus.RESOLVED;
    }

    public void reject() {

        if (status == RequestStatus.REJECTED) {
            throw new IllegalStateException(
                    "Request already rejected"
            );
        }

        this.status = RequestStatus.REJECTED;
    }

    public void startReview() {

        if (status != RequestStatus.PENDING) {
            throw new IllegalStateException(
                    "Only pending requests can start review"
            );
        }

        this.status = RequestStatus.IN_REVIEW;
    }


    //Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
