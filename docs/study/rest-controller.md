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
