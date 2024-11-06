
# 블로그 ERD
<img width="718" alt="블로그erd" src="https://github.com/user-attachments/assets/b963a1de-d33a-4eff-9cdb-561cfcd5e36e">

### 유저(User) 테이블
사용자 정보를 담고 있다.
- username
- password
- email

### 게시물(Post) 테이블
게시물 정보를 담고 있다.
- title
- content
- author
- user_id
- create_date

### 댓글(Comment) 테이블
게시물에 달린 댓글 정보를 담고 있다.
- cooment_id
- content
- comment_date
- user_id
- post_id

### 좋아요(Like) 테이블
댓글에 달린 좋아요 정보를 담고 있다.
- like_id
- count
- user_id
- post_id
