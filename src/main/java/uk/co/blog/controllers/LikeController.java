package uk.co.blog.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.blog.services.LikeService;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    @PatchMapping("/{postId}/{authorId}")
    public void addOrRemoveLike(@PathVariable("postId") Long postId,
                                @PathVariable("authorId") Long authorId){
        likeService.addOrRemoveLike(postId,authorId);
    }
}
