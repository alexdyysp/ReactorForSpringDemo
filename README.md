# Reactor For Spring 

## Basic Data

```sqlite
cqlsh:blogs>describe blogpost;
CREATE TABLE blogs.blogpost (
    id uuid PRIMARY KEY,
    author text,
    body text,
    title text
)

cqlsh:blogs> select * from blogpost;

 id                                   | author  | body                        | title
--------------------------------------+---------+-----------------------------+-----------------
 ebc86ab0-c9cf-417e-8d69-c4157abe9422 |     dyy |         second test for api | reactortestPost
 a7eb7a00-d080-11ea-a4e0-ef9074370d3e |     dyy | first test blog can be read | first test blog
 9350f7f2-0df5-4415-882c-2bfbb87c9b7c | Author2 |       Other body for update |           Title
 bd3ae648-41ab-42c8-bc9e-4dcd9737f698 |  Author |                  Other body |           Title
```

## 项目依赖
```xml
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.0.0.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			<version>2.0.0.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-cassandra</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
		</dependency>
		<dependency>
			<groupId>io.projectreactor.ipc</groupId>
			<artifactId>reactor-netty</artifactId>
		</dependency>
		<dependency>
			<groupId>org.apache.cassandra</groupId>
			<artifactId>cassandra-all</artifactId>
			<version>3.9</version>
		</dependency>
	</dependencies>
```

## 技术覆盖
- [x] Reactor
- [x] SpringBoot
- [x] Cassandra

主要使用了project-reactor项目中的Mono.defer()方法实现多线程查询

## API列表
1.路径传入id查询blog："/api/blog/{id}"
> Url examole: http://localhost:8080/api/blog/a7eb7a00-d080-11ea-a4e0-ef9074370d3e

2.传入Mono类型的body："/api/blog"
> Mono型只能代码传入
```java
PostContent postContent = new PostContent("Title", "Author", "Body");
final UUID id = controller.addPost(Mono.just(postContent)).block();
```

3.传入json类型body："/api/blog/json"
> {
      "title":"reactortestPost",
      "author":"dyy",
      "body":"second test for api"
  }

4.更新对应id的blog："/api/blog/{id}"
```java
PostContent updateContent = new PostContent("Title", "Author2","Other body for update");
controller.updatePost(id, Mono.just(updateContent)).block();
final Mono<PostContent> updatedMono = controller.getPost(id);
```


[reactor]: https://projectreactor.io/
