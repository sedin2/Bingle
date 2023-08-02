package com.bingle.match.repository;

import com.bingle.match.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("SELECT m FROM Match m WHERE m.startDate >= :startDate AND m.startDate <= :endDate")
    List<Match> findByStartDate(Long startDate, Long endDate);

    List<Match> findAllByMatchStatusOrMatchStatus(String matchStatus1, String matchStatus2);
}
