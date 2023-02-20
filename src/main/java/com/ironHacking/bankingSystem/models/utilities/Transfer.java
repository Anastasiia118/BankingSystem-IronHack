package com.ironHacking.bankingSystem.models.utilities;

import java.math.BigDecimal;

public class Transfer {
    private Long idSender;
    private Long idReceiver;

    private Long idSenderAccount;

    private Long idReceiverAccount;
    private BigDecimal amount;

    public Long getIdSenderAccount() {
        return idSenderAccount;
    }

    public Transfer(Long idSender, Long idReceiver, Long idSenderAccount, Long idReceiverAccount, BigDecimal amount) {
        this.idSender = idSender;
        this.idReceiver = idReceiver;
        this.idSenderAccount = idSenderAccount;
        this.idReceiverAccount = idReceiverAccount;
        this.amount = amount;
    }

    public void setIdSenderAccount(Long idSenderAccount) {
        this.idSenderAccount = idSenderAccount;
    }

    public Long getIdReceiverAccount() {
        return idReceiverAccount;
    }

    public void setIdReceiverAccount(Long idReceiverAccount) {
        this.idReceiverAccount = idReceiverAccount;
    }

    public Long getIdSender() {
        return idSender;
    }

    public void setIdSender(Long idSender) {
        this.idSender = idSender;
    }

    public Long getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(Long idReceiver) {
        this.idReceiver = idReceiver;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
