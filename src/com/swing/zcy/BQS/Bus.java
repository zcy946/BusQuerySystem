package com.swing.zcy.BQS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bus {

    private String routeID;
    private Double price;
    private String serviceTime1;
    private String serviceTime2;
    private  String availableCards;
    private String[] stations;
    public Bus() {
    }
    // 设置线路名
    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }
    // 得到线路名
    public String getRouteID() {
        return routeID;
    }
    // 设置票价
    public void setPrice(Double price) {
        this.price = price;
    }
    // 得到票价
    public Double getPrice() {
        return price;
    }
    // 设置运营时间1
    public void setServiceTime1(String serviceTime1) {
        this.serviceTime1 = serviceTime1;
    }
    // 得到运营时间1
    public String getServiceTime1() {
        return serviceTime1;
    }
    // 设置运营时间2
    public void setServiceTime2(String serviceTime2) {
        this.serviceTime2 = serviceTime2;
    }
    // 得到运营时间2
    public String getServiceTime2() {
        return serviceTime2;
    }
    // 设置有效卡
    public void setAvailableCards(String availableCards) {
        this.availableCards = availableCards;
    }
    // 得到有效卡
    public String getAvailableCards() {
        return availableCards;
    }
    // 设置站点信息
    public void setStations(String[] stations) {
        this.stations = stations;
    }

    // 得到站点信息
    public String[] getStations() {
        return stations;
    }
    // 得到所有信息
    public Object[] getAllInformation() {
        Object[] allInformation = new Object[BusQuerySystem.maxCapacity];
        allInformation[0] = this.getRouteID();
        allInformation[1] = this.getPrice();
        allInformation[2] = this.getServiceTime1();
        allInformation[3] = this.getServiceTime2();
        allInformation[4] = this.getAvailableCards();
        for (int i = 5; i < allInformation.length; i++) {
            allInformation[i] = this.getStations()[i - 5];
        }
        return allInformation;
    }
    // 是否包含
    public boolean isContain(String searchedStation) {
        for (var station : this.stations) {
            if (station.equals(searchedStation)) {
                return true;
            }
        }
        return false;
    }
    //是否有相同的站
    public boolean isHaveSameStation(Bus bus) {
        for (var station : bus.getStations()) {
            if (Arrays.stream(this.stations).toList().contains(station)) {
                return true;
            }
        }
        return false;
    }
    // 得到所有相同的站点
    public List<String> getAllSameStations(Bus route) {
        List<String> result = new ArrayList<>();
        for (String station : this.stations) {
            for (String station2 : route.stations) {
                if (station.equals(station2)) {
                    result.add(station2);
                }
            }
        }
        return result;
    }
    // 是否直达
    public boolean isDirect(String searchedStation1, String searchedStation2) {
        int containStationCount = 0;
        for (var station : this.stations) {
            if (station.equals(searchedStation1)) {
                containStationCount = 1;
            }
            if (station.equals(searchedStation2) && containStationCount == 1) {
                return true;
            }
        }
        return false;
    }
    // 得到站点在所有站点中国的下标
    public int getIndexOfStation(String searchedStation) {
        int index;
        for (index = 0; index < this.stations.length; index++) {
            if (this.stations[index].equals(searchedStation)) {
                break;
            }
        }
        return index;
    }
}
