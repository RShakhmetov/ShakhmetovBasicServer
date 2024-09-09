package net.dunice.BasicServer.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ToDo {
    @CurrentTimestamp
    private String createdAt;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean status;
    private String text;
    private String updatedAt;
}
