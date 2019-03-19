package com.easydao.earlybird.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderAircraft implements Serializable {
    private static final long serialVersionUID = 4498498484680825281L;

    private Long id;
    private Long user_id;
    private String contact_id;
    private String passenger_id;
    private String passenger_names;
    private String order_no;
    private String order_flight_no;
    private String order_flight_carrier;
    private String order_flight_model;
    private String order_flight_cabin;
    private double order_flight_fare;
    private double order_flight_construction_cost;
    private double order_flight_fuel_cost;
    private String order_departure;
    private String order_destination;
    private String order_departure_airport;
    private String order_destination_airport;
    private String order_start_time;
    private String order_end_time;
    private Long order_status;
    private Long create_by_id;
    private String create_by;
    private Date create_date;
    private Long update_by_id;
    private String update_by;
    private Date update_date;
    private List<Contact> contactList;
    private List<Passenger> passengerList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getContact_id() {
        return contact_id;
    }

    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

    public String getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(String passenger_id) {
        this.passenger_id = passenger_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getOrder_flight_no() {
        return order_flight_no;
    }

    public void setOrder_flight_no(String order_flight_no) {
        this.order_flight_no = order_flight_no;
    }

    public String getOrder_flight_carrier() {
        return order_flight_carrier;
    }

    public void setOrder_flight_carrier(String order_flight_carrier) {
        this.order_flight_carrier = order_flight_carrier;
    }

    public String getOrder_flight_model() {
        return order_flight_model;
    }

    public void setOrder_flight_model(String order_flight_model) {
        this.order_flight_model = order_flight_model;
    }

    public String getOrder_flight_cabin() {
        return order_flight_cabin;
    }

    public void setOrder_flight_cabin(String order_flight_cabin) {
        this.order_flight_cabin = order_flight_cabin;
    }

    public double getOrder_flight_fare() {
        return order_flight_fare;
    }

    public void setOrder_flight_fare(double order_flight_fare) {
        this.order_flight_fare = order_flight_fare;
    }

    public double getOrder_flight_construction_cost() {
        return order_flight_construction_cost;
    }

    public void setOrder_flight_construction_cost(double order_flight_construction_cost) {
        this.order_flight_construction_cost = order_flight_construction_cost;
    }

    public double getOrder_flight_fuel_cost() {
        return order_flight_fuel_cost;
    }

    public void setOrder_flight_fuel_cost(double order_flight_fuel_cost) {
        this.order_flight_fuel_cost = order_flight_fuel_cost;
    }

    public String getOrder_departure() {
        return order_departure;
    }

    public void setOrder_departure(String order_departure) {
        this.order_departure = order_departure;
    }

    public String getOrder_destination() {
        return order_destination;
    }

    public void setOrder_destination(String order_destination) {
        this.order_destination = order_destination;
    }

    public String getOrder_departure_airport() {
        return order_departure_airport;
    }

    public void setOrder_departure_airport(String order_departure_airport) {
        this.order_departure_airport = order_departure_airport;
    }

    public String getOrder_destination_airport() {
        return order_destination_airport;
    }

    public void setOrder_destination_airport(String order_destination_airport) {
        this.order_destination_airport = order_destination_airport;
    }

    public String getOrder_start_time() {
        return order_start_time;
    }

    public void setOrder_start_time(String order_start_time) {
        this.order_start_time = order_start_time;
    }

    public String getOrder_end_time() {
        return order_end_time;
    }

    public void setOrder_end_time(String order_end_time) {
        this.order_end_time = order_end_time;
    }

    public Long getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Long order_status) {
        this.order_status = order_status;
    }

    public Long getCreate_by_id() {
        return create_by_id;
    }

    public void setCreate_by_id(Long create_by_id) {
        this.create_by_id = create_by_id;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Long getUpdate_by_id() {
        return update_by_id;
    }

    public void setUpdate_by_id(Long update_by_id) {
        this.update_by_id = update_by_id;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public String getPassenger_names() {
        return passenger_names;
    }

    public void setPassenger_names(String passenger_names) {
        this.passenger_names = passenger_names;
    }
}
