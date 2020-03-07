package com.viking.dao;

import com.viking.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by viking on 2018/10/16.
 */
public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag findByName(String name);

    /**
     * 根据用户id和名字筛选是否存在
     * @param name
     * @param userId
     * @return
     */
    @Query(value = "select * from t_tag t where name = ?1 and user_id = ?2", nativeQuery=true)
    Tag findByNameAndUserId(String name, Long userId);

    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);

//    //按tagIds集合查询Tag
////    Page<Tag> findByIdIn(List<Long> tagIds, Pageable pageable);

}
