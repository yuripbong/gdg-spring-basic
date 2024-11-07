
# 블로그 ERD
<img width="718" alt="블로그erd" src="https://github.com/user-attachments/assets/b963a1de-d33a-4eff-9cdb-561cfcd5e36e">

### 유저(User) 테이블
사용자 정보를 담고 있다.
- user_id
- username
- password
- email

Post, Comment 엔티티 -> 각 유저는 여러 개의 게시물과 댓글 작성 가능
Like 엔티티 -> 각 유저가 여러 게시물에 좋아요를 남길 수 있다.

### 게시물(Post) 테이블
게시물 정보를 담고 있다.
- title
- content
- author
- user_id (PK)
- create_date

User 엔티티와 N:1 -> 각 게시물은 한 유저의 소유
Comment, Like 엔티티 -> 하나의 게시물에 여러 댓글과 좋아요가 달릴 수 있다.

### 댓글(Comment) 테이블
게시물에 달린 댓글 정보를 담고 있다.
- cooment_id
- content
- comment_date
- user_id (PK)
- post_id (PK)

User 엔티티와 N:1 관계 -> 각 댓글은 한 유저가 작성
Post 엔티티 N:1 관계 -> 각 댓글은 하나의 게시물에 속함

### 좋아요(Like) 테이블
댓글에 달린 좋아요 정보를 담고 있다.
- like_id
- count
- user_id (PK)
- post_id (PK)

User, Post 엔티티와 N:1 -> 각 좋아요는 특정 유저가 특정 게시물에 남긴 하나의 좋아요를 의미

---
# 기능설명
### User 엔티티
회원의 정보를 가지는 테이블을 만들어주기 위해 Entity 생성
