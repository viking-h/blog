package com.viking.service;

import com.viking.NotFoundException;
import com.viking.dao.TagRepository;
import com.viking.dao.UserRepository;
import com.viking.po.Tag;
import com.viking.po.User;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viking on 2018/10/16.
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.findOne(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

//    @Transactional
//    @Override
//    public Page<Tag> listTag(List<Long> tagIds, Pageable pageable) {
//        return tagRepository.findByIdIn(tagIds ,pageable);
//    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = new PageRequest(0, size, sort);
        return tagRepository.findTop(pageable);
    }


    @Override
    public List<Tag> listTag(String ids) { //1,2,3
        return tagRepository.findAll(convertToList(ids));
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }


    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.findOne(id);
        if (t == null) {
            throw new NotFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }


    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.delete(id);
    }

    @Override
    public Page<Tag> listTag(Long userId, Pageable pageable) {
        User user = userRepository.findOne(userId);
        List<Tag> tags = user.getTags();
        Page<Tag> tags1 = new PageImpl<>(tags, pageable, tags.size());
        return tags1;
    }

    @Override
    public Tag getTagByNameAndUser(String name, User user) {
        return tagRepository.findByNameAndUserId(name, user.getId());
    }

    @Override
    public Tag getById(Long tagId) {
        return tagRepository.findOne(tagId);
    }
}
