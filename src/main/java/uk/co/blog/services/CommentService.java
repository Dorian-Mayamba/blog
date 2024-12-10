package uk.co.blog.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uk.co.blog.dtos.comments.CommentDTO;
import uk.co.blog.models.Author;
import uk.co.blog.models.Comment;
import uk.co.blog.models.Post;
import uk.co.blog.repositories.CommentRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final AuthorService authorService;
    private final PostService postService;
    private final ModelMapper modelMapper;

    public List<Comment> findCommentsByPostId(Long postId){
        return commentRepository.findCommentsByPostId(postId);
    }

    public List<Comment> findCommentsByAuthorId(Long authorId){
        return commentRepository.findCommentsByAuthorId(authorId);
    }

    public CommentDTO addComment(Long postId, Long authorId, String content){
        Author author = (Author) authorService.findAuthorById(authorId);
        Post post = postService.findPostById(postId);
        Comment newComment = Comment.builder()
                .content(content)
                .author(author)
                .post(post)
                .build();
        newComment = commentRepository.save(newComment);
        return modelMapper.map(newComment, CommentDTO.class);
    }

    public CommentDTO updateComment(Long commentId, String content){
        Comment updatedComment = commentRepository.findById(commentId).orElseThrow(()-> new NoSuchElementException("Could not find comment by id "+commentId));
        updatedComment.setContent(content);
        Comment savedComment = commentRepository.save(updatedComment);
        return modelMapper.map(savedComment, CommentDTO.class);
    }

    public void deleteComment(Long commentId){
        Comment comment = this.commentRepository.findById(commentId).orElse(null);
        if(comment == null){
            throw new IllegalStateException("Could not find comment");
        }
        this.commentRepository.delete(comment);
    }
}
