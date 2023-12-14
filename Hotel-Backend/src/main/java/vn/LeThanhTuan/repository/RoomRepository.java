package vn.LeThanhTuan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.LeThanhTuan.entity.Hotel;
import vn.LeThanhTuan.entity.Room;
import vn.LeThanhTuan.entity.id.RoomId;

import java.util.Date;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, RoomId> {

    @Query("SELECT r FROM Room r WHERE r.hotel.name LIKE %?1% OR r.roomType.name LIKE %?1%")
    Page<Room> findAllByKeyword(String keyword, Pageable pageable);

    Page<Room> findByHotel(Hotel hotel, Pageable pageable);

    @Query("SELECT r FROM Room r LEFT JOIN r.reservations res " +
            "WHERE (res.id IS NULL OR res.dayEnd < :startDate OR res.dayStart > :endDate) " +
            "AND r.state = 'PHÒNG TRỐNG'")
    List<Room> findAvailableRooms(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
