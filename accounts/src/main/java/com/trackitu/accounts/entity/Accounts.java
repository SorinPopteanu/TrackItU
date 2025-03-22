package com.trackitu.accounts.entity;

import com.trackitu.accounts.enums.AccountStatus;
import com.trackitu.accounts.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity {

  @Id
  @Column(name = "account_number", unique = true)
  private long accountNumber;

  @Column(name = "customer_id")
  private Long customerId;

  @Column(name = "account_type")
  @Enumerated(EnumType.STRING)
  private AccountType accountType;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private AccountStatus accountStatus;

}
