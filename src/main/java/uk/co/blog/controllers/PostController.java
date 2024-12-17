package uk.co.blog.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uk.co.blog.dtos.posts.CreatePostDTO;
import uk.co.blog.dtos.posts.PostDTO;
import uk.co.blog.dtos.posts.UpdatePostDTO;
import uk.co.blog.services.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping
    public List<PostDTO> findAll(){
        return postService.findAll();
    }
    @GetMapping("/{authorId}")
    public List<PostDTO> findByAuthorId(@PathVariable("authorId") Long authorId){
        return postService.findPostsByAuthorId(authorId);
    }
    @GetMapping("/{postId}")
    public PostDTO findPost(@PathVariable("postId") Long postId){
        return postService.findPost(postId);
    }
    @PostMapping("/{authorId}")
    public PostDTO makePost(@PathVariable("authorId") Long authorId,
                            @RequestBody CreatePostDTO createPostDTO){
        return postService.createPost(authorId, createPostDTO);
    }
    @PutMapping("/{postId}")
    public PostDTO editPost(@PathVariable("postId") Long postId,
                            @RequestBody UpdatePostDTO updatePostDTO){
        return postService.updatePost(postId, updatePostDTO);
    }
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable("postId") Long postId){
        postService.deletePost(postId);
    }

}
