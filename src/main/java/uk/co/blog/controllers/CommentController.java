package uk.co.blog.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uk.co.blog.dtos.comments.CommentDTO;
import uk.co.blog.dtos.comments.CreateCommentDTO;
import uk.co.blog.dtos.comments.UpdateCommentDTO;
import uk.co.blog.services.CommentService;
import java.util.List;
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @GetMapping("/{postId}")
    public List<CommentDTO> getComments(@PathVariable("postId") Long postId){
        return commentService.findCommentsByPostId(postId);
    }
    @PostMapping("/{authorId}/{postId}")
    public CommentDTO makeComment(@PathVariable("authorId") Long authorId,
                                  @PathVariable("postId") Long postId,
                                  @RequestBody CreateCommentDTO createCommentDTO){
        return commentService.addComment(postId, authorId, createCommentDTO);
    }
    @PutMapping("/{commentId}")
    public CommentDTO editComment(@PathVariable("commentId") Long commentId,
                                  @RequestBody UpdateCommentDTO updateCommentDTO){
        return commentService.updateComment(commentId, updateCommentDTO);
    }
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable("commentId")Long commentId){
        commentService.deleteComment(commentId);
    }
}
