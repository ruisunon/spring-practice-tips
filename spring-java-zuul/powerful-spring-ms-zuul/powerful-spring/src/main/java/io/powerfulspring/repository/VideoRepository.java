package io.powerfulspring.repository;

import io.powerfulspring.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends PagingAndSortingRepository<Video, Long> {
	@Query("FROM Video c ORDER BY c.date DESC")
	Page<Video> findAll(Pageable pageable);
}
