package com.gyugyu.jpaprogramming.model.chap4;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.util.Date;

// chap4 Member 클래스 예제
@Entity
@Table(name = "USER", uniqueConstraints = {
        @UniqueConstraint(name = "NAME_AGE_UNIQUE", columnNames = {"NAME", "AGE"})
})
@Getter
public class User {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

}
// => create table user (
//      id varchar(255) not null,
//      age integer,
//      created_date timestamp,
//      description clob,
//      last_modified_date timestamp,
//      role_type varchar(255),
//      name varchar(255) not null,
//      primary key (id)
//    )
// alter table user add constraint NAME_AGE_UNIQUE unique (name, age)