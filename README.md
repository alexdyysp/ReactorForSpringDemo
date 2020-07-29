# Reactor For Spring 

## Basic Data

```sqlite
mysql> describe BLOGPOST;

CREATE TABLE BLOGPOST(
    ID varchar(64) NOT NULL COMMENT '唯一指定ID，使用UUID',
    TITLE TEXT COMMENT '博客标题',
    AUTHOR varchar(32) COMMENT '作者',
    BODY TEXT COMMENT '博客内容',
    PRIMARY KEY (ID))
);

mysql> select * from BLOGPOST;

 id                                   | author  | body                        | title
--------------------------------------+---------+-----------------------------+-----------------
 ebc86ab0-c9cf-417e-8d69-c4157abe9422 |     dyy |         second test for api | reactortestPost
 a7eb7a00-d080-11ea-a4e0-ef9074370d3e |     dyy | first test blog can be read | first test blog
 9350f7f2-0df5-4415-882c-2bfbb87c9b7c | Author2 |       Other body for update |           Title
 bd3ae648-41ab-42c8-bc9e-4dcd9737f698 |  Author |                  Other body |           Title
```

## 技术覆盖
- [x] Reactor
- [x] SpringBoot
- [x] MySql
- [x] JPA

主要使用了project-reactor项目中的Mono.defer()方法实现多线程查询

## API列表
1.路径传入id查询blog："/api/blog/{id}"
> Url examole: http://localhost:8080/api/blog/a7eb7a00-d080-11ea-a4e0-ef9074370d3e

2.根据参数id查询blog："/api/blog/string/?id="
> Url example: http://localhost:8080/api/blog/string/?id=bd3ae648-41ab-42c8-bc9e-4dcd9737f698
    
3.传入Mono类型的body："/api/blog"
> Mono型只能代码传入
```java
PostContent postContent = new PostContent("Title", "Author", "Body");
final UUID id = controller.addPost(Mono.just(postContent)).block();
```

4.传入json类型body："/api/blog/json"
> {
      "title":"reactortestPost",
      "author":"dyy",
      "body":"second test for api"
  }

5.更新对应id的blog："/api/blog/{id}"
```java
PostContent updateContent = new PostContent("Title", "Author2","Other body for update");
controller.updatePost(id, Mono.just(updateContent)).block();
final Mono<PostContent> updatedMono = controller.getPost(id);
```

6.查询全部blogs数据列表："/api/blog/allblogs"
> Url examole: http://localhost:8080/api/blog/allblogs


7.查询全部blogs的title数据列表："/api/blog/alltitles"
> Url examole: http://localhost:8080/api/blog/alltitles


[reactor]: https://projectreactor.io/
