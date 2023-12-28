package com.swing.zcy.BQS;

public class Bus {

    private String routeID;
    private Double price;
    private String serviceTime1;
    private String serviceTime2;
    private  String availableCards;
    private String[] stations;
    public Bus() {
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }

    public String getRouteID() {
        return routeID;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setServiceTime1(String serviceTime1) {
        this.serviceTime1 = serviceTime1;
    }

    public String getServiceTime1() {
        return serviceTime1;
    }

    public void setServiceTime2(String serviceTime2) {
        this.serviceTime2 = serviceTime2;
    }

    public String getServiceTime2() {
        return serviceTime2;
    }

    public void setAvailableCards(String availableCards) {
        this.availableCards = availableCards;
    }

    public String getAvailableCards() {
        return availableCards;
    }

    public void setStations(String[] stations) {
        this.stations = stations;
    }

    public String[] getStations() {
        return stations;
    }

}
