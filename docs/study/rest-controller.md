# @RestController와 @RequestMapping

REST API 컨트롤러를 만들 때 자주 사용하는 `@RestController`와 `@RequestMapping` 정리입니다.

## @RestController

`@RestController`는 `@Controller`와 `@ResponseBody`를 합친 애노테이션입니다.

```java
@RestController
public class ApiV1PostController {
}
```

위 코드는 아래처럼 작성한 것과 비슷한 의미입니다.

```java
@Controller
@ResponseBody
public class ApiV1PostController {
}
```

## @Controller와의 차이

`@Controller`는 기본적으로 MVC에서 화면(View)을 반환할 때 사용합니다.
따라서 REST API처럼 JSON 데이터를 응답하려면 `@ResponseBody`가 필요합니다.

```java
@Controller
public class ApiV1PostController {

    @ResponseBody
    @GetMapping("/api/v1/posts")
    public List<Post> getPosts() {
        return postService.findAll();
    }
}
```

`@RestController`를 사용하면 메서드마다 `@ResponseBody`를 붙이지 않아도 응답 객체가 HTTP response body에 담깁니다.

```java
@RestController
public class ApiV1PostController {

    @GetMapping("/api/v1/posts")
    public List<Post> getPosts() {
        return postService.findAll();
    }
}
```

## 클래스 레벨 @RequestMapping

컨트롤러 클래스에 `@RequestMapping`을 붙이면 해당 컨트롤러의 모든 API에 공통 경로 접두어를 적용할 수 있습니다.

```java
@RestController
@RequestMapping("/api/v1/posts")
public class ApiV1PostController {

    @GetMapping
    public List<Post> getPosts() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.findById(id);
    }
}
```

최종 API 경로는 다음과 같습니다.

```text
GET /api/v1/posts
GET /api/v1/posts/{id}
```

## 왜 클래스 레벨에 공통 경로를 두는가

- 중복되는 URL 접두어를 줄일 수 있습니다.
- 컨트롤러가 담당하는 API 범위를 한눈에 알 수 있습니다.
- API 버전(`/api/v1`)과 리소스 이름(`/posts`)을 일관되게 관리할 수 있습니다.

## 정리

- `@RestController`는 REST API 응답을 만들 때 사용합니다.
- `@RestController`는 `@Controller + @ResponseBody` 역할을 합니다.
- 클래스 레벨 `@RequestMapping`은 컨트롤러 안의 API에 공통 URL 접두어를 붙입니다.
- 메서드 레벨 `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`은 공통 접두어 뒤에 붙는 세부 경로를 담당합니다.

## 관련 커밋 예시

```text
feat(post): 게시글 API 기능 구현
docs(study): RestController와 RequestMapping 정리
```

## 단건 조회 API에서 사용한 애노테이션

게시글 단건 조회 API 예시입니다.

```java
@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class ApiV1PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public Post getItem(@PathVariable Long id) {
        Post item = postService.getPost(id);

        return item;
    }
}
```

### @RestController

REST API 컨트롤러임을 나타냅니다.

`@RestController`가 붙은 클래스의 메서드가 객체를 반환하면, Spring은 그 객체를 JSON 형태의 HTTP 응답 본문으로 변환합니다.

```java
@RestController
public class ApiV1PostController {
}
```

즉, `Post` 객체를 반환하면 화면 이름을 찾는 것이 아니라 JSON 응답을 만듭니다.

```json
{
  "id": 1,
  "title": "제목 1",
  "content": "내용 1"
}
```

### @RequestMapping

컨트롤러 전체에 공통 URL 접두어를 붙입니다.

```java
@RequestMapping("/api/v1/posts")
```

이 설정이 있으면 이 컨트롤러 안의 API는 모두 `/api/v1/posts`로 시작합니다.

예를 들어 메서드에 `@GetMapping("/{id}")`가 있으면 최종 요청 경로는 다음과 같습니다.

```text
GET /api/v1/posts/{id}
```

### @RequiredArgsConstructor

Lombok이 제공하는 애노테이션입니다.

`final` 필드를 매개변수로 받는 생성자를 자동으로 만들어줍니다.

```java
private final PostService postService;
```

위 필드가 있으면 Lombok이 아래와 같은 생성자를 만들어주는 것과 비슷합니다.

```java
public ApiV1PostController(PostService postService) {
    this.postService = postService;
}
```

Spring은 이 생성자를 통해 `PostService` 객체를 주입합니다.
그래서 컨트롤러에서 `postService.getPost(id)`처럼 서비스 기능을 사용할 수 있습니다.

### @GetMapping

HTTP GET 요청을 처리하는 메서드에 붙입니다.

```java
@GetMapping("/{id}")
```

`@RequestMapping("/api/v1/posts")`와 합쳐져서 아래 요청을 처리합니다.

```text
GET /api/v1/posts/1
```

GET은 보통 데이터를 조회할 때 사용합니다.
따라서 게시글 단건 조회 API에는 `@GetMapping`이 어울립니다.

### @PathVariable

URL 경로에 들어온 값을 메서드 매개변수로 받을 때 사용합니다.

```java
@GetMapping("/{id}")
public Post getItem(@PathVariable Long id) {
}
```

요청이 이렇게 들어오면:

```text
GET /api/v1/posts/1
```

Spring은 URL의 `1`을 꺼내서 `Long id`에 넣어줍니다.

```java
postService.getPost(id);
```

결과적으로 `id`가 `1`인 게시글을 조회할 수 있습니다.

### @ResponseBody

객체를 HTTP 응답 본문에 직접 담도록 하는 애노테이션입니다.

다만 `@RestController` 안에서는 이미 `@ResponseBody`가 적용된 것과 같기 때문에, 보통 메서드마다 다시 붙이지 않아도 됩니다.

```java
@RestController
public class ApiV1PostController {

    @GetMapping
    public List<Post> getItems() {
        return postService.getList();
    }
}
```

`@Controller`를 사용할 때 JSON 응답을 만들고 싶다면 `@ResponseBody`를 직접 붙여야 합니다.

```java
@Controller
public class ApiV1PostController {

    @ResponseBody
    @GetMapping("/api/v1/posts")
    public List<Post> getItems() {
        return postService.getList();
    }
}
```

## 단건 조회 API 요청 흐름

```text
GET /api/v1/posts/1
```

1. `/api/v1/posts`는 클래스의 `@RequestMapping`이 담당합니다.
2. `/{id}`는 메서드의 `@GetMapping("/{id}")`가 담당합니다.
3. URL의 `1`은 `@PathVariable Long id`에 들어갑니다.
4. `postService.getPost(id)`로 게시글을 조회합니다.
5. 반환된 `Post` 객체는 `@RestController`에 의해 JSON 응답으로 변환됩니다.
