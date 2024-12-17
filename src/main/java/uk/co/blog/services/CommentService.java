package uk.co.blog.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.co.blog.dtos.comments.CommentDTO;
import uk.co.blog.dtos.comments.CreateCommentDTO;
import uk.co.blog.dtos.comments.UpdateCommentDTO;
import uk.co.blog.mappers.CommentMapper;
import uk.co.blog.models.Author;
import uk.co.blog.models.Comment;
import uk.co.blog.models.Post;
import uk.co.blog.repositories.CommentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final AuthorService authorService;
    private final PostService postService;
    private final CommentMapper commentMapper;
    public List<CommentDTO> findCommentsByPostId(Long postId){
        return commentRepository.findCommentsByPostId(postId)
                .stream().map(commentMapper::commentToDTO).collect(Collectors.toList());
    }

    public List<CommentDTO> findCommentsByAuthorId(Long authorId){
        return commentRepository.findCommentsByAuthorId(authorId)
                .stream().map(commentMapper::commentToDTO).collect(Collectors.toList());
    }

    public CommentDTO addComment(Long postId, Long authorId, CreateCommentDTO createCommentDTO){
        Author author = (Author) authorService.findAuthorById(authorId);
        Post post = postService.findPostById(postId);
        Comment newComment = Comment.builder()
                .content(createCommentDTO.content())
                .author(author)
                .post(post)
                .authorName(author.getName())
                .build();
        newComment = commentRepository.save(newComment);
        return commentMapper.commentToDTO(newComment);
    }
    public CommentDTO updateComment(Long commentId, UpdateCommentDTO updateCommentDTO){
        Comment updatedComment = commentRepository.findById(commentId).orElseThrow(()-> new NoSuchElementException("Could not find comment by id "+commentId));
        updatedComment.setContent(updatedComment.getContent());
        Comment savedComment = commentRepository.save(updatedComment);
        return commentMapper.commentToDTO(savedComment);
    }
    public void deleteComment(Long commentId){
        Comment comment = this.commentRepository.findById(commentId).orElse(null);
        if(comment == null){
            throw new IllegalStateException("Could not find comment");
        }
        this.commentRepository.delete(comment);
    }
}
