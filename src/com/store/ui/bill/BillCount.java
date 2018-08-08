/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.bill;

/**
 *
 * @author chenjingjun
 */
public class BillCount {
    protected Double Caculate = new Double(0);
     
    protected Double Amount = new Double(0);
    
    protected Double Received = new Double(0);
     
    protected Double Remain = new Double(0);

    public Double getCaculate() {
        return Caculate;
    }

    public void addCaculate(Double Caculate) {
        this.Caculate += Caculate;
    }

    public Double getAmount() {
        return Amount;
    }

    public void addAmount(Double Amount) {
        this.Amount += Amount;
    }

    public Double getReceived() {
        return Received;
    }

    public void addReceived(Double Received) {
        this.Received += Received;
    }

    public Double getRemain() {
        return Remain;
    }

    public void addRemain(Double Remain) {
        this.Remain += Remain;
    }
}
