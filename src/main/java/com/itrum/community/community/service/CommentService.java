package com.itrum.community.community.service;

import com.itrum.community.community.domain.Comment;
import com.itrum.community.community.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    void addComment(Comment comment);

    List<CommentDTO> findCommentByQuestionId(Long id,Integer type);
}
