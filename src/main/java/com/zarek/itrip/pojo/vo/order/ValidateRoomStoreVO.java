package com.zarek.itrip.pojo.vo.order;

import java.io.Serializable;
import java.util.Date;

/**
 * 验证房间库存的传入参数VO
 * Created by XX on 17-5-16.
 */
public class ValidateRoomStoreVO implements Serializable{
    private Long hotelId;
    private Long roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer count;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
