package com.freetalk.freetalk_backend.controller.freetalk_backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.freetalk.freetalk_backend.FreeTalkBackendApplication;
import com.freetalk.freetalk_backend.controller.*;
import com.freetalk.freetalk_backend.dto.TopicView;
import com.freetalk.freetalk_backend.entity.UserInfo;
import com.freetalk.freetalk_backend.service.*;
import com.freetalk.freetalk_backend.serviceimpl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FreeTalkBackendApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class CommentControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    private MockMvc mockMvc;

    private HttpEntity<String> getHttpEntity()
    {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("userId", "1");
        requestHeaders.add("token", Base64.getEncoder().encodeToString("1和酮不让我下班".getBytes(StandardCharsets.UTF_8)));
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        return requestEntity;
    }

    @Mock
    private CommentService commentService=new CommentServiceImpl();

    @InjectMocks
    private CommentController commentController;
    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }
    @Test
    public void testLikeAComment() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.post("/Security/likeAComment?userId=2&commentId=8")
                .headers(getHttpEntity().getHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL);
        ResultActions perform = mockMvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testStarAComment() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.post("/Security/starAComment?userId=2&commentId=8")
                .headers(getHttpEntity().getHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL);
        ResultActions perform = mockMvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testUnlikeAComment() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.post("/Security/unlikeAComment?userId=2&commentId=8")
                .headers(getHttpEntity().getHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL);
        ResultActions perform = mockMvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testUnStarAComment() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.post("/Security/unstarAComment?userId=2&commentId=8")
                .headers(getHttpEntity().getHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL);
        ResultActions perform = mockMvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testCheckLikeAComment() throws Exception {
        ResponseEntity<String> responseEntity=testRestTemplate.exchange("/Security/checkLikeAComment?userId=5&commentId=10",HttpMethod.GET,getHttpEntity(),String.class);

        String result=responseEntity.getBody();
        assertThat(result,equalTo("\"yes\""));
    }
    @Test
    public void testCheckStarAComment() throws Exception {
        ResponseEntity<String> responseEntity=testRestTemplate.exchange("/Security/checkStarAComment?userId=2&commentId=10",HttpMethod.GET,getHttpEntity(),String.class);
        String result=responseEntity.getBody();
        assertThat(result,equalTo("\"yes\""));
    }
    @Test
    public void testAddAComment() throws Exception{
        Map<String,Object> map=new HashMap<>();
        map.put("content","刘尧");
        map.put("replyId",10);
        map.put("topicId",3);
        map.put("userId",6);
        String requestBody= JSONObject.toJSONString(map);
        RequestBuilder request = MockMvcRequestBuilders.post("/Security/addAComment")
                .headers(getHttpEntity().getHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.ALL);
        ResultActions perform = mockMvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testAllCommentsOfATopic() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.post("/allCommentsOfATopic?topicId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL);
        ResultActions perform = mockMvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFindCommentsByReplyId() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.post("/findCommentsByReplyId?replyId=9")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL);
        ResultActions perform = mockMvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testChangeAComment() throws Exception{
        Map<String,Object> map=new HashMap<>();
        map.put("content","test");
        map.put("commentId",9);
        String requestBody= JSONObject.toJSONString(map);
        RequestBuilder request = MockMvcRequestBuilders.post("/Security/changeAComment")
                .headers(getHttpEntity().getHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.ALL);
        ResultActions perform = mockMvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void deleteAComment() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.post("/Security/deleteAComment?commentId=9&type=1")
                .headers(getHttpEntity().getHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL);
        ResultActions perform = mockMvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testGetAComment() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/getAComment?commentId=9")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL);
        ResultActions perform = mockMvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @ExtendWith(SpringExtension.class)
    @SpringBootTest(classes = FreeTalkBackendApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @Transactional
    static
    class CommentControllerTest {
        @Autowired
        private TestRestTemplate testRestTemplate;

        private MockMvc mockMvc;

        private HttpEntity<String> getHttpEntity()
        {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("userId", "1");
            requestHeaders.add("token", Base64.getEncoder().encodeToString("1和酮不让我下班".getBytes(StandardCharsets.UTF_8)));
            HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
            return requestEntity;
        }

        @Mock
        private CommentService commentService=new CommentServiceImpl();

        @InjectMocks
        private CommentController commentController;
        @BeforeEach
        public void beforeEach() {
            MockitoAnnotations.initMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
        }
        @Test
        public void testLikeAComment() throws Exception{
            RequestBuilder request = MockMvcRequestBuilders.post("/Security/likeAComment?userId=2&commentId=8")
                    .headers(getHttpEntity().getHeaders())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        public void testStarAComment() throws Exception{
            RequestBuilder request = MockMvcRequestBuilders.post("/Security/starAComment?userId=2&commentId=8")
                    .headers(getHttpEntity().getHeaders())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        public void testUnlikeAComment() throws Exception{
            RequestBuilder request = MockMvcRequestBuilders.post("/Security/unlikeAComment?userId=2&commentId=8")
                    .headers(getHttpEntity().getHeaders())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        public void testUnStarAComment() throws Exception{
            RequestBuilder request = MockMvcRequestBuilders.post("/Security/unstarAComment?userId=2&commentId=8")
                    .headers(getHttpEntity().getHeaders())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        public void testCheckLikeAComment() throws Exception {
            ResponseEntity<String> responseEntity=testRestTemplate.exchange("/Security/checkLikeAComment?userId=5&commentId=10",HttpMethod.GET,getHttpEntity(),String.class);

            String result=responseEntity.getBody();
            assertThat(result,equalTo("\"yes\""));
        }
        @Test
        public void testCheckStarAComment() throws Exception {
            ResponseEntity<String> responseEntity=testRestTemplate.exchange("/Security/checkStarAComment?userId=2&commentId=10",HttpMethod.GET,getHttpEntity(),String.class);
            String result=responseEntity.getBody();
            assertThat(result,equalTo("\"yes\""));
        }
        @Test
        public void testAddAComment() throws Exception{
            Map<String,Object> map=new HashMap<>();
            map.put("content","刘尧");
            map.put("replyId",10);
            map.put("topicId",3);
            map.put("userId",6);
            String requestBody= JSONObject.toJSONString(map);
            RequestBuilder request = MockMvcRequestBuilders.post("/Security/addAComment")
                    .headers(getHttpEntity().getHeaders())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
                    .accept(MediaType.ALL);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        public void testAllCommentsOfATopic() throws Exception{
            RequestBuilder request = MockMvcRequestBuilders.post("/allCommentsOfATopic?topicId=1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void testFindCommentsByReplyId() throws Exception{
            RequestBuilder request = MockMvcRequestBuilders.post("/findCommentsByReplyId?replyId=9")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        public void testChangeAComment() throws Exception{
            Map<String,Object> map=new HashMap<>();
            map.put("content","test");
            map.put("commentId",9);
            String requestBody= JSONObject.toJSONString(map);
            RequestBuilder request = MockMvcRequestBuilders.post("/Security/changeAComment")
                    .headers(getHttpEntity().getHeaders())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
                    .accept(MediaType.ALL);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        public void deleteAComment() throws Exception{
            RequestBuilder request = MockMvcRequestBuilders.post("/Security/deleteAComment?commentId=9&type=1")
                    .headers(getHttpEntity().getHeaders())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        public void testGetAComment() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.post("/getAComment?commentId=9")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }
    }

    @ExtendWith(SpringExtension.class)
    @SpringBootTest(classes = FreeTalkBackendApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @org.springframework.transaction.annotation.Transactional
    public static class FollowControllerTest {

        @Autowired
        private TestRestTemplate testRestTemplate;

        private MockMvc mockMvc;

        @Mock
        private FollowService topicService=new FollowServiceImpl();

        @Mock
        private UserService userService=new UserServiceImpl();

        @InjectMocks
        private FollowController followController;

        @BeforeEach
        public void beforeEach() {
            MockitoAnnotations.initMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(followController).build();
        }

        private HttpEntity<String> getHttpEntity()
        {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("userId", "1");
            requestHeaders.add("token", Base64.getEncoder().encodeToString("1和酮不让我下班".getBytes(StandardCharsets.UTF_8)));
            HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
            return requestEntity;
        }


        @Test
        public void followAUser() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.get("/Security/followAUser?userId=3&userId_toFollow=5").headers(getHttpEntity().getHeaders()).accept(MediaType.TEXT_PLAIN);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        void unfollowAUser() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.get("/Security/unfollowAUser?userId=2&userId_toFollow=4").headers(getHttpEntity().getHeaders()).accept(MediaType.TEXT_PLAIN);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        void getAllUserFollowingMe() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.get("/getAllUserFollowingMe?userId=4").accept(MediaType.APPLICATION_JSON);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        void getAllUserIFollowing() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.get("/getAllUserIFollowing?userId=4").accept(MediaType.APPLICATION_JSON);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        void getTopicOfFollowingUsers() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.get("/getTopicOfFollowingUsers?userId=1").accept(MediaType.APPLICATION_JSON);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        void getCommentsOfFollowingUsers() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.get("/getCommentsOfFollowingUsers?userId=1").accept(MediaType.APPLICATION_JSON);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        void getUserAnswerMyCommentAndTopic() throws Exception{
            RequestBuilder request = MockMvcRequestBuilders.get("/getUserAnswerMyCommentAndTopic?userId=1").accept(MediaType.APPLICATION_JSON);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        void getUserLikeAComment() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.get("/getUserLikeAComment?commentId=1").accept(MediaType.APPLICATION_JSON);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        void getUserStarAComment() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.get("/getUserStarAComment?commentId=1").accept(MediaType.APPLICATION_JSON);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        void getUserLikeATopic() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.get("/getUserLikeATopic?topicId=1").accept(MediaType.APPLICATION_JSON);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        void getTopicsUserLike() {
            ResponseEntity<List> responseEntity=testRestTemplate.getForEntity("/getTopicsUserLike?userId=3",List.class);
            assertThat(responseEntity.getStatusCode(),equalTo(HttpStatus.OK));
        }

        @Test
        void getTopicsUserStar() {
            ResponseEntity<List> responseEntity=testRestTemplate.getForEntity("/getTopicsUserStar?userId=1",List.class);
            assertThat(responseEntity.getStatusCode(),equalTo(HttpStatus.OK));
        }

        @Test
        void getCommentsUserLike() {
            ResponseEntity<List> responseEntity=testRestTemplate.getForEntity("/getCommentsUserLike?userId=1",List.class);
            assertThat(responseEntity.getStatusCode(),equalTo(HttpStatus.OK));

        }

        @Test
        void getCommentsUserStar() {
            ResponseEntity<List> responseEntity=testRestTemplate.getForEntity("/getCommentsUserStar?userId=1",List.class);
            assertThat(responseEntity.getStatusCode(),equalTo(HttpStatus.OK));
        }

        @Test
        void getCommentsUserCreat() {
            ResponseEntity<List> responseEntity=testRestTemplate.getForEntity("/getCommentsUserCreat?userId=1",List.class);
            assertThat(responseEntity.getStatusCode(),equalTo(HttpStatus.OK));
        }

        @Test
        void getTopicsUserCreat() {
            ResponseEntity<List> responseEntity=testRestTemplate.getForEntity("/getTopicsUserCreat?userId=1",List.class);
            assertThat(responseEntity.getStatusCode(),equalTo(HttpStatus.OK));
        }

        @Test
        void checkUserFollow() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.get("/checkUserFollow?userId=1&userId_toFollow=2").accept(MediaType.APPLICATION_JSON);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }
    }

    @ExtendWith(SpringExtension.class)
    @SpringBootTest(classes = FreeTalkBackendApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @org.springframework.transaction.annotation.Transactional
    static
    class MessageControllerTest {
        @Autowired
        private TestRestTemplate testRestTemplate;

        private MockMvc mockMvc;

        @Mock
        private MessageService messageService=new MessageServiceImpl();

        @InjectMocks
        private MessageController messageController;

        private HttpEntity<String> getHttpEntity()
        {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("userId", "1");
            requestHeaders.add("token", Base64.getEncoder().encodeToString("1和酮不让我下班".getBytes(StandardCharsets.UTF_8)));
            HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
            return requestEntity;
        }

        @BeforeEach
        public void beforeEach() {
            MockitoAnnotations.initMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
        }

        @Test
        void getMessagesBetweenTwoUsers() {

            HttpEntity<String> requestEntity = getHttpEntity();
            ResponseEntity<List> response = testRestTemplate.exchange("/Security/getMessagesBetweenTwoUsers?me=2&you=1", HttpMethod.GET, requestEntity, List.class);
            List result=response.getBody();
            assert result != null;
            assertThat(result.size(),equalTo(16));
        }

        @Test
        void getNewMessagesBetweenTwoUsers() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.post("/Security/getNewMessagesBetweenTwoUsers?me=3&you=2")
                    .headers(getHttpEntity().getHeaders())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        void getAllUsersHasChattedWithAUser() {
            ResponseEntity<List> responseEntity=testRestTemplate.exchange("/Security/getAllUsersHasChattedWithAUser?userId=2", HttpMethod.GET,getHttpEntity(),List.class);
            List result=responseEntity.getBody();
            assert result != null;
            assertThat(result.size(),equalTo(2));
        }

        @Test
        @Rollback
        void postAMessage() throws Exception {
            Map<String,Object> map=new HashMap<>();
            map.put("sendUserId", 3);
            map.put("receiveUserId",2);
            map.put("messageContent","Hello world");
            String requestBody= JSONObject.toJSONString(map);
            RequestBuilder request = MockMvcRequestBuilders.post("/Security/postAMessage")
                    .headers(getHttpEntity().getHeaders())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
                    .accept(MediaType.TEXT_PLAIN);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @Rollback
        void setAllMessageWithAUserHaveRead() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.post("/Security/setAllMessageWithAUserHaveRead?acceptUserId=3&sentUserId=2")
                    .headers(getHttpEntity().getHeaders())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.TEXT_PLAIN);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @Rollback
        void numberOfMessagesUnreadWithAUser() {
            ResponseEntity<List> responseEntity=testRestTemplate.exchange("/Security/numberOfMessagesUnreadWithAUser?acceptUserId=3",HttpMethod.GET,getHttpEntity(),List.class);
            List result=responseEntity.getBody();
            assert result != null;
            assertThat(result.get(0),equalTo(1));
        }

        @Test
        @Rollback
        void numberOfMessagesUnread() {
            ResponseEntity<Integer> responseEntity=testRestTemplate.exchange("/Security/numberOfMessagesUnread?acceptUserId=3",HttpMethod.GET,getHttpEntity(),Integer.class);
            Integer result=responseEntity.getBody();
            assertThat(result,equalTo(11));
        }
    }

    @ExtendWith(SpringExtension.class)
    @SpringBootTest(classes = FreeTalkBackendApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @org.springframework.transaction.annotation.Transactional
    public static class TopicControllerTest {

        @Autowired
        private TestRestTemplate testRestTemplate;

        private MockMvc mockMvc;

        @Mock
        private TopicService topicService=new TopicServiceImpl();

        @InjectMocks
        private TopicController topicController;

        @BeforeEach
        public void beforeEach() {
            MockitoAnnotations.initMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(topicController).build();
        }


        private HttpEntity<String> getHttpEntity()
        {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("userId", "1");
            requestHeaders.add("token", Base64.getEncoder().encodeToString("1和酮不让我下班".getBytes(StandardCharsets.UTF_8)));
            HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
            return requestEntity;
        }

        @Test
        public void testGetTopics() throws Exception {
            ResponseEntity<List> responseEntity=testRestTemplate.getForEntity("/getTopics/2/2",List.class);
            List result=responseEntity.getBody();
            assertThat(result.size(),equalTo(2));
        }

        @Test
        public void testGetSearchedTopicsByPage() throws Exception{
            ParameterizedTypeReference<List<TopicView>> type=new ParameterizedTypeReference<List<TopicView>>() {};
            ResponseEntity<List<TopicView>> responseEntity=testRestTemplate.exchange("/getSearchedTopics/河童/1/3", HttpMethod.GET,null,type);
            List<TopicView> result=responseEntity.getBody();
            Collection<String> stringCollection=new HashSet<>();
            for(TopicView o:result)
            {
                TopicView topicView=(TopicView) o;
                stringCollection.add(topicView.getTitle());
            }
            assertThat(stringCollection.size(),equalTo(3));
            assertThat(stringCollection.contains("如何看待河童？"),equalTo(true));
            assertThat(stringCollection.contains("河童是嘉心糖吗？"),equalTo(true));
            assertThat(stringCollection.contains("“童河”牌药膏的效果如何？"),equalTo(true));
        }


        @Test
        @org.springframework.transaction.annotation.Transactional
        @Rollback
        public void testAddATopic() throws Exception{
            Map<String,Object> map=new HashMap<>();
            map.put("title", "如何看待河童是嘉心糖？");
            map.put("topic_description","我们都知道，河童是嘉心糖，那么，如何看待河童是嘉心糖呢？");
            map.put("userId",1);
            String requestBody= JSONObject.toJSONString(map);
            RequestBuilder request = MockMvcRequestBuilders.post("/Security/addATopic")
                    .headers(getHttpEntity().getHeaders())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
                    .accept(MediaType.TEXT_PLAIN);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void testGetATopicView()
        {
            ResponseEntity<TopicView> responseEntity=testRestTemplate.getForEntity("/getATopicView/1",TopicView.class);
            TopicView topicView=responseEntity.getBody();
            assert topicView != null;
            assertThat(topicView.getTopicId(),equalTo(1));
            assertThat(topicView.getTitle(),equalTo("如何看待河童？"));
            assertThat(topicView.getTopicDescription(),equalTo("<body<p>如何看待河童？</p></body>"));
            assertThat(topicView.getUserId(),equalTo(1));
        }

        @Test
        public void testBrowseATopic() throws Exception {
            RequestBuilder request=MockMvcRequestBuilders.get("/browseATopic/1").accept(MediaType.TEXT_PLAIN);
            ResultActions perform=mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void testStarATopic() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.get("/Security/starATopic/3/5").headers(getHttpEntity().getHeaders()).accept(MediaType.TEXT_PLAIN);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }


        @Test
        public void testUndoStarATopic() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.get("/Security/undoStarATopic/1/1").headers(getHttpEntity().getHeaders()).accept(MediaType.TEXT_PLAIN);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }


        @Test
        public void testLikeATopic() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.get("/Security/likeATopic/3/5").headers(getHttpEntity().getHeaders()).accept(MediaType.TEXT_PLAIN);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void testUndoLikeATopic() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.get("/Security/undoLikeATopic/3/5").headers(getHttpEntity().getHeaders()).accept(MediaType.TEXT_PLAIN);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void testCheckUserStar() throws Exception{
            ResponseEntity<String> responseEntity=testRestTemplate.getForEntity("/checkUserStar/1/1",String.class);
            String response=responseEntity.getBody();
            assertThat(response,equalTo("true"));
            responseEntity=testRestTemplate.getForEntity("/checkUserStar/1/5",String.class);
            response=responseEntity.getBody();
            assertThat(response,equalTo("false"));
        }

        @Test
        public void testCheckUserLike() throws Exception{
            ResponseEntity<String> responseEntity=testRestTemplate.getForEntity("/checkUserLike/1/1",String.class);
            String response=responseEntity.getBody();
            assertThat(response,equalTo("true"));
            responseEntity=testRestTemplate.getForEntity("/checkUserLike/1/5",String.class);
            response=responseEntity.getBody();
            assertThat(response,equalTo("false"));
        }

        @Test
        public void testGetTopHotTopics() throws Exception{
            ResponseEntity<List> responseEntity=testRestTemplate.getForEntity("/getHotTopics/20",List.class);
            assertThat(responseEntity.getStatusCode(),equalTo(HttpStatus.OK));
            List response=responseEntity.getBody();
            assertThat(response.size(),equalTo(6));

            responseEntity=testRestTemplate.getForEntity("/getHotTopics/2",List.class);
            response=responseEntity.getBody();
            assertThat(response.size(),equalTo(2));
        }

        @Test
        public void testGetRecommendHotTopics() throws Exception{
            ResponseEntity<List> responseEntity=testRestTemplate.getForEntity("/getRecommendTopics?userId=1&size=20",List.class);
            assertThat(responseEntity.getStatusCode(),equalTo(HttpStatus.OK));
            List response=responseEntity.getBody();
            assertThat(response.size(),equalTo(6));

            responseEntity=testRestTemplate.getForEntity("/getRecommendTopics?userId=1&size=2",List.class);
            response=responseEntity.getBody();
            assertThat(response.size(),equalTo(2));
        }







    }

    @ExtendWith(SpringExtension.class)
    @SpringBootTest(classes = FreeTalkBackendApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @org.springframework.transaction.annotation.Transactional
    public static class UserControllerTest {
        @Autowired
        private TestRestTemplate testRestTemplate;

        private MockMvc mockMvc;

        @Mock
        private UserService userService=new UserServiceImpl();

        @InjectMocks
        private UserController userController;
        @BeforeEach
        public void beforeEach()
        {
            MockitoAnnotations.initMocks(this);
            mockMvc=MockMvcBuilders.standaloneSetup(userController).build();
        }

        private HttpEntity<String> getHttpEntity()
        {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("userId", "1");
            requestHeaders.add("token", Base64.getEncoder().encodeToString("1和酮不让我下班".getBytes(StandardCharsets.UTF_8)));
            HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
            return requestEntity;
        }

        @Test
        public void testLogin() throws Exception {
            Map<String,String> map=new HashMap<>();
            map.put("username","和酮");
            map.put("password","123");
            String requestBody= JSONObject.toJSONString(map);
            RequestBuilder request = MockMvcRequestBuilders.post("/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
                    .accept(MediaType.TEXT_PLAIN);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        @org.springframework.transaction.annotation.Transactional
        @Rollback
        public void testRegister() throws Exception{
            Map<String,String> map=new HashMap<>();
            map.put("username","刘尧");
            map.put("password","123");
            map.put("email","liuyao@sjtu.edu.cn");
            String requestBody= JSONObject.toJSONString(map);
            RequestBuilder request = MockMvcRequestBuilders.post("/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
                    .accept(MediaType.ALL);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        @org.springframework.transaction.annotation.Transactional
        @Rollback
        public void testUpdateImage() throws Exception{
            Map<String,Object> map=new HashMap<>();
            map.put("userId",1);
            map.put("imageBase64", "data:image/jpeg;base64,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");
            String requestBody= JSONObject.toJSONString(map);
            RequestBuilder request = MockMvcRequestBuilders.post("/updateImage")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
                    .accept(MediaType.ALL);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        @org.springframework.transaction.annotation.Transactional
        @Rollback
        public void testUpdateUserInfo() throws Exception{
            Map<String,Object> map=new HashMap<>();
            map.put("userId",1);
            map.put("email","1@sjtu.edu.cn");
            map.put("description","???");
            map.put("image","wecewfwe");
            map.put("username","abc");
            String requestBody= JSONObject.toJSONString(map);
            RequestBuilder request = MockMvcRequestBuilders.post("/Security/updateUserInfo")
                    .headers(getHttpEntity().getHeaders())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
                    .accept(MediaType.ALL);
            ResultActions perform = mockMvc.perform(request);
            perform.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        public void testGetUserInfo() throws Exception {
            ResponseEntity<UserInfo> responseEntity=testRestTemplate.getForEntity("/getUserInfo?userId=1",UserInfo.class);
            UserInfo result=responseEntity.getBody();
            assertThat(result.getUsername(),equalTo("河童"));
        }
    }
}
