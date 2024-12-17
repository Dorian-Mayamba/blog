package uk.co.blog.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uk.co.blog.dtos.posts.CreatePostDTO;
import uk.co.blog.dtos.posts.PostDTO;
import uk.co.blog.dtos.posts.UpdatePostDTO;
import uk.co.blog.mappers.PostMapper;
import uk.co.blog.models.Author;
import uk.co.blog.models.Post;
import uk.co.blog.repositories.PostRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final AuthorService authorService;
    private final ModelMapper mapper;

    private final PostMapper postMapper;
    public Post findPostById(Long postId){
        return postRepository.findById(postId).orElseThrow(()-> new NoSuchElementException("Could not find post by id "+ postId));
    }
    public PostDTO findPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(()-> new NoSuchElementException("Could not find post by id "+ postId));
        return postMapper.postToDTO(post);
    }
    public List<PostDTO> findAll(){
        return postRepository.findAll().stream().map(postMapper::postToDTO).collect(Collectors.toList());
    }
    public List<PostDTO> findPostsByAuthorId(Long authorId){
        List<Post> posts = postRepository.findPostsByAuthorId(authorId);
        return posts.stream().map(postMapper::postToDTO).collect(Collectors.toList());
    }
    public PostDTO createPost(Long authorId, CreatePostDTO createPostDTO){

        Author author = (Author) authorService.findAuthorById(authorId);

        Post newPost = Post.builder()
                .content(createPostDTO.content())
                .title(createPostDTO.title())
                .author(author)
                .authorEmail(author.getEmail())
                .authorName(author.getName())
                .build();
        Post savedPost = postRepository.save(newPost);
        return postMapper.postToDTO(savedPost);
    }
    public PostDTO updatePost(Long postId, UpdatePostDTO updatePostDTO){
        Post updatedPost = findPostById(postId);
        updatedPost.setContent(updatePostDTO.content());
        Post savedUpdatedPost =  postRepository.save(updatedPost);
        return postMapper.postToDTO(savedUpdatedPost);
    }

    public void deletePost(Long postId){
        Post post = findPostById(postId);
        postRepository.delete(post);
    }
}
