package com.jumpie.tombaza.back.models;

public class Fine extends Model<Integer> {
    private String fineDescription;
    private int fineCost;
    private int agreementId;

    public Fine() {
    }

    public Fine(int fineId, String fineDescription, int fineCost, int agreementId) {
        setId(fineId);
        setFineDescription(fineDescription);
        setFineCost(fineCost);
        setAgreementId(agreementId);
    }

    public String getFineDescription() {
        return fineDescription;
    }

    public void setFineDescription(String fineDescription) {
        if (fineDescription.length() <= 255) {
            this.fineDescription = fineDescription;
        } else error();
    }

    public int getFineCost() {
        return fineCost;
    }

    public void setFineCost(int fineCost) {
        this.fineCost = fineCost;
    }

    public int getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(int agreementId) {
        this.agreementId = agreementId;
    }

    public void print() {
        System.out.println(super.getId() + " " + this.fineDescription + " " + this.fineCost + " " + this.agreementId);
    }

    public String allInString() {
        String str = "ID: " + super.getId() + " DESCRIPTION: " + this.fineDescription + " COST:  " + this.fineCost + " AGREEMENTID: " + this.agreementId;
        return str;
    }
}
