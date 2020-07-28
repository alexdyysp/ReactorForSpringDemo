# Reactor For Spring 

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
1.根据UUID id查询blog：/api/blog/{id}
> Url examole: http://localhost:8080/api/blog/a7eb7a00-d080-11ea-a4e0-ef9074370d3e

2.根据String id查询blog：/api/blog/string/?id=
> Url example: http://localhost:8080/api/blog/string/?id=bd3ae648-41ab-42c8-bc9e-4dcd9737f698
    
3.传入Mono类型的body：/api/blog
> Mono型只能代码传入
```java
PostContent postContent = new PostContent("Title", "Author", "Body");
final UUID id = controller.addPost(Mono.just(postContent)).block();
```

4.传入json类型body：/api/blog/json
> {
      "title":"reactortestPost",
      "author":"dyy",
      "body":"second test for api"
  }

5.更新对应id的blog：/api/blog/{id}
```java
PostContent updateContent = new PostContent("Title", "Author2","Other body for update");
controller.updatePost(id, Mono.just(updateContent)).block();
final Mono<PostContent> updatedMono = controller.getPost(id);
```


[reactor]: https://projectreactor.io/
