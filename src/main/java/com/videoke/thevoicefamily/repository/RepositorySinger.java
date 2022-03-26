package com.videoke.thevoicefamily.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videoke.thevoicefamily.model.Singer;

@Repository
public interface RepositorySinger  extends JpaRepository<Singer, Long>{

}
