package org.example.drivingscool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private long paymentId;

    @Column(name = "payment_date", nullable = false)
    private Date date;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private String amount;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public Payment(Date date, String method, String amount, Student student, Course course) {
        this.date = date;
        this.method = method;
        this.amount = amount;
        this.student = student;
        this.course = course;
    }
}
