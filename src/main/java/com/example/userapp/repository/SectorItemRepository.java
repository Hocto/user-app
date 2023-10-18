package com.example.userapp.repository;

import com.example.userapp.model.entity.SectorItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SectorItemRepository extends JpaRepository<SectorItem, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM sector_item WHERE id in :ids")
    List<SectorItem> findAllByIds(@Param("ids") Set<Long> ids);
}
