// package com.daeut.daeut.reservation.dao;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.stereotype.Repository;

// import com.daeut.daeut.reservation.dto.Reservation;

// import java.util.List;
// import java.util.Map;

// @Repository
// public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

//     @Query(value = "SELECT reservation_no AS id, reg_date AS start FROM reservation", nativeQuery = true)
//     List<Map<String, Object>> getAllReservationsForCalendar();
// }
