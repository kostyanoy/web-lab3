package com.itmo.weblab3.hibernate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


// class represents database table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class CheckEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String sessionId;
    private double x;
    private double y;
    private double r;
    private long executionTime;
    private Date checkDate;
    private boolean result;
    private boolean isDeleted;
}
