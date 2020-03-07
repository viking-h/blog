package com.viking.service;

import com.viking.po.Tag;
import com.viking.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by limi on 2017/10/16.
 */
public interface TagService {

    Tag saveTag(Tag type);

    Tag getTag(Long id);

    Tag getTagByName(String name);

//    /**
//     * 根据tagIds集合分页查询Tag
//     * @param tagIds
//     * @param pageable
//     * @return
//     */
//    Page<Tag> listTag(List<Long> tagIds, Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTagTop(Integer size);

    List<Tag> listTag(String ids);

    Tag updateTag(Long id, Tag type);

    void deleteTag(Long id);

    /**
     * 通过用户查询标签，并返回分页数据
     * @param userId
     * @return
     */
    Page<Tag> listTag(Long userId, Pageable pageable);

    /**
     * 根据用户和标签名联合查询
     * @param name
     * @param user
     * @return
     */
    Tag getTagByNameAndUser(String name, User user);

    /**
     * 根据id查询tag
     * @param tagId
     * @return
     */
    Tag getById(Long tagId);
}
