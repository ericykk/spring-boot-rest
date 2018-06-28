package com.eric.base.service;

import com.eric.base.mapper.VoteUserMapper;
import com.eric.base.model.VoteUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描述:
 *
 * @author eric
 * @create 2018-06-27 下午5:59
 */
@Service
public class VoteUserService {
    @Resource
    private VoteUserMapper voteUserMapper;

    public VoteUser getVoteUserById(Long id) {
        return voteUserMapper.selectByPrimaryKey(id);
    }
}
