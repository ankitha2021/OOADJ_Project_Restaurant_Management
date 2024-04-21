package com.example.demo.repository;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
//import java.util.Map;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.TableBookingDto;
import com.example.demo.model.TableBooking;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TableBookingRepository extends JpaRepository<TableBooking, Integer> {
    @Query("SELECT new com.example.demo.dto.TableBookingDto(tb.table_no, tb.no_of_seats) FROM TableBooking tb WHERE tb.customerId = 0")
    List<TableBookingDto> findTableInfoByCustomerId(int customerId);

    @Transactional
    @Modifying
    @Query("UPDATE TableBooking tb SET tb.customerId = :customerId WHERE tb.table_no = :table_no")
    void updateCustomerIdForTable(@Param("table_no") int tableNo, @Param("customerId") int customerId);
}
