package com.videoke.thevoicefamily.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.videoke.thevoicefamily.model.ModelMovie;

@Repository
public interface RepositoryMovie  extends JpaRepository<ModelMovie, Long> {

	//@Query()INSERT INTO MOVIES VALUES('MOVIEID', 'CHANNELID', 'CHANNELT', 1,'MOVIETILE','NEXPAGE');
	
    @Modifying
    @Query(value =  "INSERT INTO MOVIES (MOVIE_ID, CHANNEL_ID, CHANNEL_TITLE, ID, MOVIE_TITLE, NEXT_PAGE_TOKEN) VALUES(:MovieId, :ChannelId, :ChannelTitle, :id, :MovieTitle, :NextPageToken)", nativeQuery = true)
    @Transactional
    void saveCustom(@Param("MovieId") String MovieId,
    				@Param("ChannelId") String ChannelId, 
    				@Param("ChannelTitle") String ChannelTitle,
    				@Param("id") Long id,
    				@Param("MovieTitle") String MovieTitle,
    				@Param("NextPageToken") String NextPageToken);

    @Query(value =  "SELECT * FROM MOVIES where MOVIE_TITLE like %:MovieTitle%", nativeQuery = true)
    List<ModelMovie> findByMovieTitle(@Param("MovieTitle") String MovieTitle);
    
    @Query(value =  "SELECT * FROM MOVIES where MOVIE_ID = :MovieId", nativeQuery = true)
    List<ModelMovie> findByMovieId(@Param("MovieId") String MovieId);
    
    
    @Modifying
    @Query(value =  "DELETE FROM MOVIES  WHERE MOVIE_ID = :MovieId", nativeQuery = true)
    @Transactional
    void deleteMovie(@Param("MovieId") String MovieId);

    @Query(value =  "SELECT MOVIE_TITLE FROM MOVIES where MOVIE_ID = :MovieId", nativeQuery = true)
    String gettitlebyid(@Param("MovieId") String MovieId);

}