package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CommentsMapper;
import entity.Comments;

@Service
public class CommentsService {
	@Autowired
	CommentsMapper commentsMapper;
	public Comments getComments(Long commentsId){
		return commentsMapper.selectByPrimaryKey(commentsId);
	}
	public void insertComments(Comments comments){
		commentsMapper.insert(comments);
	}
	public void updateComments(Comments comments){
		commentsMapper.updateByPrimaryKey(comments);
	}
}
