package com.book.projectps.model;


import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "sales")
public class Sale extends Category {
    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private boolean isAvailable;
    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
