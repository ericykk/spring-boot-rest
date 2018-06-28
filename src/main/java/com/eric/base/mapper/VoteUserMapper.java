package com.eric.base.mapper;

import com.eric.base.model.VoteUser;

public interface VoteUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VoteUser record);

    int insertSelective(VoteUser record);

    VoteUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VoteUser record);

    int updateByPrimaryKey(VoteUser record);
}