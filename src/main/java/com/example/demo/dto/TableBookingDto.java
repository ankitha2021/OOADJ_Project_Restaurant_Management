package com.example.demo.dto;

public class TableBookingDto {
    private int table_no;
    private int no_of_seats;

    public TableBookingDto() {
    }

    // Constructor with matching signature
    public TableBookingDto(Integer table_no, Integer no_of_seats) {
        this.table_no = table_no;
        this.no_of_seats= no_of_seats;
    }

    public int getTableNo()
    {
        return table_no;
    }

    public void setTableNo(int table_no)
    {
        this.table_no=table_no;
    }


    public void setNoOfSeats(int no_of_seats)
    {
        this.no_of_seats=no_of_seats;
    }
    public int getNoOfSeats()
    {
        return no_of_seats;
    }


}
