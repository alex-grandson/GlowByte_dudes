package com.example.glow.entity.mySql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLIENT_INN", schema = "prod_ins")
public class ClientInnResource implements Serializable {

    private static final long serialVersionUID = 5L;

    @Id
    @Column(name = "CLIENT_ID")
    private Long clientID;

    @Column(name = "CLIENT_INN")
    private String clientInn;

    @Column(name = "EFFECTIVE_FROM_DTTM")
    private Timestamp effectiveFromDttm;

}
