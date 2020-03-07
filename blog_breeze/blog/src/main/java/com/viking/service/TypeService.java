package com.viking.service;

import com.viking.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by limi on 2017/10/16.
 */
public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    Type updateType(Long id,Type type);

    void deleteType(Long id);

    /**
     * 根据用户id分页查询
     * @param userid
     * @param pageable
     * @return
     */
    Page<Type> listType(Long userid, Pageable pageable);

    /**
     * 根据分类名和用户id筛选
     * @param name
     * @param userId
     * @return
     */
    Type getTypeByNameAndUserId(String name, Long userId);
}
