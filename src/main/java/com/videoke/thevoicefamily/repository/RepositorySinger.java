package com.videoke.thevoicefamily.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.videoke.thevoicefamily.model.Singer;

@Repository
public interface RepositorySinger  extends JpaRepository<Singer, Long>{
    @Modifying
    @Query(value =  "INSERT INTO SINGER (REQUEST_NUMBER, NAME, SONGID, SONG_TITLE) VALUES(:requestNumber, :name, :songID, :songTitle)", nativeQuery = true)
    @Transactional
    void saveCustom(@Param("requestNumber") Long requestNumber,
    				@Param("name") String name, 
    				@Param("songTitle") String songTitle,
    				@Param("songID") String songID);
    
    @Query(value =  "SELECT * FROM SINGER where SONGID = :songID", nativeQuery = true)
    List<Singer> findBySongID(@Param("songID") String songID);
    
    @Modifying
    @Query(value =  "DELETE FROM SINGER  WHERE REQUEST_NUMBER = :requestNumber", nativeQuery = true)
    @Transactional
    void deleteSong(@Param("requestNumber") Long requestNumber);

}
