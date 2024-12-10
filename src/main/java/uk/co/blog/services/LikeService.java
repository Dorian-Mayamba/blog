package uk.co.blog.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.co.blog.models.Author;
import uk.co.blog.models.Like;
import uk.co.blog.models.Post;
import uk.co.blog.repositories.LikeRepository;

@Service
@RequiredArgsConstructor
public class LikeService {
    private LikeRepository likeRepository;

    private PostService postService;

    private AuthorService authorService;

    public void addOrRemoveLike(Long postId, Long authorId){
        likeRepository.findLikeByAuthorIdAndPostId(postId, authorId).ifPresentOrElse
                (likeRepository::delete, ()->{
                    Post post = postService.findPostById(postId);
                    Author author = (Author) authorService.findAuthorById(authorId);
                    Like like = Like.builder()
                            .post(post)
                            .author(author)
                            .build();
                    likeRepository.save(like);
                });
    }
}
