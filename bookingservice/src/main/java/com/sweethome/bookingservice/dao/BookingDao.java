package com.sweethome.bookingservice.dao;

import com.sweethome.bookingservice.entities.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDao extends JpaRepository<BookingInfoEntity, Integer>{
}
