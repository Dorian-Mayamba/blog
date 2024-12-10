package uk.co.blog.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import uk.co.blog.dtos.posts.PostDTO;
import uk.co.blog.models.Author;
import uk.co.blog.models.Post;
import uk.co.blog.repositories.PostRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final AuthorService authorService;
    private final ModelMapper mapper;
    public Post findPostById(Long postId){
        return postRepository.findById(postId).orElseThrow(()-> new NoSuchElementException("Could not find post by id "+ postId));
    }

    public List<PostDTO> findPostsByAuthorId(Long authorId){
        List<Post> posts = postRepository.findPostsByAuthorId(authorId);
        return mapper.map(posts, new TypeToken<PostDTO>(){}.getType());
    }

    public PostDTO createPost(Long authorId, String content, String title){

        Author author = (Author) authorService.findAuthorById(authorId);

        Post newPost = Post.builder()
                .content(content)
                .title(title)
                .author(author)
                .build();
        return mapper.map(postRepository.save(newPost), PostDTO.class);
    }

    public PostDTO updatePost(Long postId, String content){
        Post updatedPost = findPostById(postId);
        updatedPost.setContent(content);
        postRepository.save(updatedPost);
        return mapper.map(updatedPost, PostDTO.class);
    }

    public void deletePost(Long postId){
        Post post = findPostById(postId);
        postRepository.delete(post);
    }
}
