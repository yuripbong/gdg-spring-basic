
# 블로그 ERD
<img width="1369" alt="image" src="https://github.com/user-attachments/assets/af2927a9-2182-4d96-bc8f-169220030858">

### 유저(User) 테이블
사용자 정보를 담고 있다.

Post, Comment 엔티티 -> 각 유저는 여러 개의 게시물과 댓글 작성 가능
Like 엔티티 -> 각 유저가 여러 게시물에 좋아요를 남길 수 있다.

### 게시물(Post) 테이블
게시물 정보를 담고 있다.

User 엔티티와 N:1 -> 각 게시물은 한 유저의 소유
Comment, Like 엔티티 -> 하나의 게시물에 여러 댓글과 좋아요가 달릴 수 있다.

### 댓글(Comment) 테이블
게시물에 달린 댓글 정보를 담고 있다.

User 엔티티와 N:1 관계 -> 각 댓글은 한 유저가 작성
Post 엔티티 N:1 관계 -> 각 댓글은 하나의 게시물에 속함

### 좋아요(Like) 테이블
댓글에 달린 좋아요 정보를 담고 있다.

User, Post 엔티티와 N:1 -> 각 좋아요는 특정 유저가 특정 게시물에 남긴 하나의 좋아요를 의미

---
# 기능설명
- 게시물
  - 이미지 업로드
  - 해시태그
  - 댓글
  - 좋아요
 
- 댓글
  - 댓글 좋아요

- 사용자
  - 좋아요
