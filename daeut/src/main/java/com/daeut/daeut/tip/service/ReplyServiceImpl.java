package com.daeut.daeut.tip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daeut.daeut.tip.dto.Reply;
import com.daeut.daeut.tip.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public List<Reply> list() throws Exception {
        List<Reply> replyList = replyMapper.list();
        return replyList;
    }

    @Override
    public List<Reply> listByBoardNo(int boardNo) throws Exception {
        List<Reply> replyList = replyMapper.listByBoardNo(boardNo);
        return replyList;
    }

    @Override
    public Reply select(int replyNo) throws Exception {
        Reply reply = replyMapper.select(replyNo);
        return reply;
    }

    @Override
    public int insert(Reply reply) throws Exception {
        int result = replyMapper.insert(reply);
        int parentNo = reply.getParentNo();

        if( result > 0 && parentNo == 0 ) {
            int no = replyMapper.max();
            reply.setBoardNo(no);
            reply.setParentNo(parentNo);
            replyMapper.update(reply);
        }
        return result;
    }

    @Override
    public int update(Reply reply) throws Exception {
        int result = replyMapper.update(reply);
        return result;
    }

    @Override
    public int delete(int replyNo) throws Exception {
        int result = replyMapper.delete(replyNo);

        if( result > 0 ) {
            result += deleteByParentNo(replyNo);
        }
        return result;
    }

    @Override
    public int deleteByBoardNo(int boardNo) throws Exception {
        int result = replyMapper.deleteByBoardNo(boardNo);
        return result;
    }

    @Override
    public int max() throws Exception {
        int max = replyMapper.max();
        return max;
    }

    @Override
    public int deleteByParentNo(int parentNo) throws Exception {
        int result = replyMapper.deleteByParentNo(parentNo);
        return result;
    }
    
}
