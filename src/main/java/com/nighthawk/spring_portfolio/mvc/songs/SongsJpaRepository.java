package com.nighthawk.spring_portfolio.mvc.songs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// JPA is an object-relational mapping (ORM) to persistent data, originally relational databases (SQL). Today JPA implementations has been extended for NoSQL.
public interface SongsJpaRepository extends JpaRepository<Songs, Long> {
    // JPA has many built in methods, these few have been prototyped for this application
    void save(String Song);
    List<Songs> findAllByOrderBySongAsc();
    List<Songs> findBySongIgnoreCase(String name);
}
